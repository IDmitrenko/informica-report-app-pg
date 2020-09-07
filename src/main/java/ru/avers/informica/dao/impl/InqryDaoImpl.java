package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dao.mapper.*;
import ru.avers.informica.dto.dictcode.InqryStatusCode;
import ru.avers.informica.dto.dictcode.InqrySysInteraction;
import ru.avers.informica.dto.dictcode.TypeClassCode;
import ru.avers.informica.dto.informica.*;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InqryDaoImpl implements InqryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final InqryMapper inqryMapper;
    private final InqryEnrolmentMapper inqryEnrolmentMapper;
    private final IdMapper idMapper;
    private final ReportSetting reportSetting;
    private final InqryInd8Mapper inqryInd8Mapper;
    private final InqryInd19_3Mapper inqryInd19_3Mapper;
    private final InqryInd20_1Mapper inqryInd20_1Mapper;

    @Override
    public List<InqryInf> getAllInqry(Date currDate, Date currEducDate, Date beginCurrYear) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            List<Integer> idPortalList = new ArrayList<>();
            String sql = "select v87.id as id " +
                         "from app.v_dict_87_sys_interaction v87 " +
                         "where v87.nm = :portal_name";
            for (String portalName : InqrySysInteraction.s_portal_names) {
                parameterSource.addValue("portal_name", portalName);
                Integer idPortal = jdbcTemplate.queryForObject(sql, parameterSource, idMapper);
                if (idPortal > 0) {
                    idPortalList.add(idPortal);
                }
            }
            String code_refusing = InqryStatusCode.REFUSING_08;
            String code_didnt_arrive = InqryStatusCode.DIDNT_ARRIVE_13;
            String code_archive_status = InqryStatusCode.ARCHIVED_09;

            parameterSource.addValue("code_cname_refusing", code_refusing);
            parameterSource.addValue("code_cname_didnt_arrive", code_didnt_arrive);
            parameterSource.addValue("code_cname_archive_status", code_archive_status);

            Integer id_refusing = jdbcTemplate.queryForObject("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.code = :code_cname_refusing",
                    parameterSource,
                    idMapper);

            Integer id_didnt_arrive = jdbcTemplate.queryForObject("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.code = :code_cname_didnt_arrive",
                    parameterSource,
                    idMapper);

            Integer id_archive_status = jdbcTemplate.queryForObject("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.code = :code_cname_archive_status",
                    parameterSource,
                    idMapper);

            List<Integer> refused_ids = Arrays.asList(id_refusing, id_didnt_arrive);
            parameterSource.addValue("refused_ids", refused_ids);
            parameterSource.addValue("dt_curr", currDate);
            parameterSource.addValue("id_archive_status", id_archive_status);
            parameterSource.addValue("rf_from", beginCurrYear);
            parameterSource.addValue("portal_name_ids", idPortalList);

            List<InqryInf> allInqry = jdbcTemplate.query("select a.id_app as id, " +
                            "ub.uch as uch_id, " +
                            "a.num as num, " +
                            "a.d_plan as dtPlan, " +
                            "a.d_birth as dtBirth, " +
                            "qi.d_enter as enterQueueDt, " +
                            "qi.d_reg as regDt, " +
// старый вариант             "sbf.spare_01 as grpTypeCode, " +
                            "a.health_csp as healthCsp, " +
                            "sbfh.spare_01 as healthNeedsCode, " +
//                            "sbfa.spare_01 as healthNeedsRootCode, " +
                            "sts.code as statusCode, " +
                            "st.d_status as statusSetDate, " +
                            "a.statement_type as typeInqry, " +
                            "b.prty as uch_prty, " +
                            "(select min(bui.prty) " +
                            " from app.buildings bui " +
                            " where bui.app_id = a.id_app) as uch_minprty, " +
                            "case when exists" +
                            " (select stat.id_status" +
                            "  from app.status stat" +
                            "  where stat.app_id = a.id_app and stat.statuses_id in (:refused_ids) " +
                            "        and stat.d_status >= :rf_from " +
                            "        and stat.d_status < :dt_curr) " +
                            "  then true" +
                            "  else false" +
                            "  end" +
                            "  as haveRefusedStatus, " +
                            "case when es.sys_sp in (:portal_name_ids) " +
                            "then true " +
                            "else false " +
                            "end as from_portal " +
                    "from app.applications a " +
                    "inner join app.queue_info qi on qi.app_id = a.id_app " +
                    "inner join app.status st on st.app_id = a.id_app " +
                    "inner join app.statuses sts on sts.id = st.statuses_id " +
                    "inner join app.buildings b on b.app_id = a.id_app " +
                    "inner join public.uch_buildings ub on ub.id_uch_buildings = b.uch_buildings_id " +
//                    "inner join app.grp_time gt on gt.app_id = a.id_app " +
//                    "left  join public.spr_b_fspeo sbf on sbf.id = gt.grp_time_csp " +
                    "left  join public.spr_b_fspeo sbfh on sbfh.id = a.health_csp " +
                    "left  join app.es es on es.app_id = a.id_app " +
//отказались от использования родительских кодов по состоянию здоровья из-за их отсутствия в справочнике
//                    "left  join public.spr_b_fspeo sbfa on sbfa.id = sbfh.id " + // parent_id
                    "where st.d_status <= :dt_curr and st.d_validity > :dt_curr and " +
                          "(sts.id <> :id_archive_status or " +
                          " qi.d_reg >= :rf_from and qi.d_reg < :dt_curr)" +
                    "order by haveRefusedStatus desc",
//                          "and a.num = '20BE7D20A8561C71'",
                    parameterSource,
                    inqryMapper);

            return allInqry;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса AllInqry.", ex);
            throw ex;
        }
    }

    @Override
    public List<InqryEnrolmentInf> getIngryEnrolment() {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            String code_arrived = InqryStatusCode.HAS_ARRIVED_04;
            String code_sent_to = InqryStatusCode.SENT_TO_DOO_12;

            List<String> enrolment_codes = Arrays.asList(code_arrived, code_sent_to);
            parameterSource.addValue("enrolment_codes", enrolment_codes);

            List<InqryEnrolmentInf> allInqryEnrolments = jdbcTemplate
                    .query("select a.id_app as id, " +
                            "u.domen_uch as uch_id, " +
                            "u.comp_code as uch_code, " +
                            "a.d_birth as dtBirth, " +
                            "a.health_csp as healthCsp, " +
                            "sts.code as statusCode " +
                            "from app.applications a " +
                            "inner join app.status st on st.app_id = a.id_app " +
                            "inner join app.statuses sts on sts.id = st.statuses_id " +
                            "inner join app.buildings b on b.app_id = a.id_app " +
                            "inner join public.uch_buildings ub on ub.id_uch_buildings = b.uch_buildings_id " +
                            "inner join public.uch u on u.domen_uch = ub.uch " +
                            "where sts.code in (:enrolment_codes) and " +
                            "      st.d_validity = '9999-12-31 00:00:00' " +
                            "order by uch_id",
                    parameterSource,
                    inqryEnrolmentMapper);

            return allInqryEnrolments;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса IngryEnrolment.", ex);
            throw ex;
        }
    }

    @Override
    public List<InqryInd8Inf> getInqryInd8() {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            Date currDate = reportSetting.getCurrDate();
            String code_sent_to = InqryStatusCode.SENT_TO_DOO_12;
            int shift_year = reportSetting.getShiftYear();
            Date dateFromInterval = (shift_year == 0 ? currDate :
                    DateUtil.adjustDate(currDate, shift_year));
// для сравнения с ручным запросом так как current_date работает с временем 00:00:00
//            dateFromInterval = DateUtil.clearDateTimePart(dateFromInterval);
            List<String> codesOvz = TypeClassCode.getCodesOvz();

            parameterSource.addValue("sent_to_doo_code", code_sent_to);
            parameterSource.addValue("currDate", currDate);
            parameterSource.addValue("dateFromInterval", dateFromInterval);
            parameterSource.addValue("listCodesOvz", codesOvz);

            List<InqryInd8Inf> allInqrysInd8 = jdbcTemplate
                    .query("select a.id_app as id, " +
                                    "a.d_plan as wishDt, " +
                                    "a.d_birth as birthDt, " +
                                    "st.uch as idUch, " +
                                    "st.d_status as stsDt, " +
                                    "case when exists(select bl.id_benefits " +
                                    "                 from app.benefits bl " +
                                    "                 where bl.app_id = a.id_app) " +
                                    "then true " +
                                    "else false " +
                                    "end as lgot, " +
                                    "case when v08.code in (:listCodesOvz)" +
                                    "then true " +
                                    "else false " +
                                    "end as ovz " +
                                    "from app.applications a " +
                                    "inner join app.status st on st.app_id = a.id_app " +
                                    "inner join app.statuses sts on sts.id = st.statuses_id " +
                                    "left join app.v_dict_08_type_class as v08 on v08.id = st.health_csp " +
                                    "where st.d_status <= :currDate and " +
                                    "      st.d_status >= :dateFromInterval and " +
                                    "      sts.code = :sent_to_doo_code " +
                                    "order by id",
                            parameterSource,
                            inqryInd8Mapper);

            return allInqrysInd8;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса IngryInd8.", ex);
            throw ex;
        }
    }

    @Override
    public List<InqryInd19_3Inf> getInqryInd19_3() {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            Date currDate = reportSetting.getCurrDate();
            List<String> codes19_3 = Arrays.asList(InqryStatusCode.HAS_ARRIVED_04,
                    InqryStatusCode.SENT_TO_DOO_12);

            parameterSource.addValue("currDate", currDate);
            parameterSource.addValue("listCodes19_3", codes19_3);

            List<InqryInd19_3Inf> allInqrysInd19_3 = jdbcTemplate
                    .query("select count(a.id_app) as cntInqry, " +
                                    "a.d_birth as birthDt, " +
                                    "st.uch as idUch " +
                                    "from app.applications a " +
                                    "inner join app.status st on st.app_id = a.id_app " +
                                    "inner join app.statuses sts on sts.id = st.statuses_id " +
                                    "where st.d_status <= :currDate and " +
                                    "      st.d_validity > :currDate and " +
                                    "      sts.code in (:listCodes19_3) and " +
                                    "      st.uch is not null " +
                                    "group by st.uch, a.d_birth",
                            parameterSource,
                            inqryInd19_3Mapper);

            return allInqrysInd19_3;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса IngryInd19_3.", ex);
            throw ex;
        }
    }

    @Override
    public List<InqryInd20_1Inf> getInqryInd20_1() {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            Date currDate = reportSetting.getCurrDate();
            List<String> codes20_1 = Arrays.asList(InqryStatusCode.HAS_ARRIVED_04,
                    InqryStatusCode.SENT_TO_DOO_12);
            Short typeCode = InqryStatusCode.TYPE_APPLICATION_TRANSFER;
            parameterSource.addValue("currDate", currDate);
            parameterSource.addValue("listCodes20_1", codes20_1);
            parameterSource.addValue("typeCode", typeCode);

            List<InqryInd20_1Inf> allInqrysInd20_1 = jdbcTemplate
                    .query("select count(a.id_app) as cntInqry, " +
                                    "a.d_birth as birthDt, " +
                                    "st.uch as idUch " +
                                    "from app.applications a " +
                                    "inner join app.status st on st.app_id = a.id_app " +
                                    "inner join app.statuses sts on sts.id = st.statuses_id " +
                                    "where st.d_status <= :currDate and " +
                                    "      st.d_validity > :currDate and " +
                                    "      a.statement_type = :typeCode and " +
                                    "      sts.code in (:listCodes20_1) and " +
                                    "      st.uch is not null " +
                                    "group by st.uch, a.d_birth",
                            parameterSource,
                            inqryInd20_1Mapper);

            return allInqrysInd20_1;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса IngryInd20_1.", ex);
            throw ex;
        }
    }


}
