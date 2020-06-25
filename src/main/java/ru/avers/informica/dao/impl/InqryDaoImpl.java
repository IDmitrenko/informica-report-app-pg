package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dao.mapper.IdMapper;
import ru.avers.informica.dao.mapper.InqryMapper;
import ru.avers.informica.dto.dictcode.InqryStatusCode;
import ru.avers.informica.dto.informica.InqryInf;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InqryDaoImpl implements InqryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final InqryMapper inqryMapper;
    private final IdMapper idMapper;

    @Override
    public List<InqryInf> getAllInqry(Date currDate, Date currEducDate, Date beginCurrYear) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            String code_refusing = InqryStatusCode.REFUSING_08;
            String code_didnt_arrive = InqryStatusCode.DIDNT_ARRIVE_13;
            String code_archive_status = InqryStatusCode.ARCHIVED_09;

            parameterSource.addValue("code_cname_refusing", code_refusing);
            parameterSource.addValue("code_cname_didnt_arrive", code_didnt_arrive);
            parameterSource.addValue("code_cname_archive_status", code_archive_status);

            Integer id_refusing = jdbcTemplate.queryForObject("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.cname = :code_cname_refusing",
                    parameterSource,
                    idMapper);

            Integer id_didnt_arrive = jdbcTemplate.queryForObject("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.cname = :code_cname_didnt_arrive",
                    parameterSource,
                    idMapper);

            Integer id_archive_status = jdbcTemplate.queryForObject("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.cname = :code_cname_archive_status",
                    parameterSource,
                    idMapper);

            List<Integer> refused_ids = Arrays.asList(id_refusing, id_didnt_arrive);
            parameterSource.addValue("refused_ids", refused_ids);
            parameterSource.addValue("dt_curr", currDate);
            parameterSource.addValue("id_archive_status", id_archive_status);
            parameterSource.addValue("rf_from", beginCurrYear);

            List<InqryInf> allInqry = jdbcTemplate.query("select a.id_app as id, " +
                            "b.uch_buildings_id as uch_id, " +
                            "a.num as num, " +
                            "a.d_plan as dtPlan, " +
                            "a.d_birth as dtBirth, " +
                            "qi.d_enter as enterQueueDt, " +
                            "qi.d_reg as regDt, " +
                            "sb.cname as grpTypeCode, " +
                            "a.health_csp as healthCsp, " +
                            "sbn.cname as healthNeedsCode, " +
                            "sba.cname as healthNeedsRootCode, " +
                            "sts.cname as statusCode, " +
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
                            "  as haveRefusedStatus " +
                    "from app.applications a " +
                    "inner join app.queue_info qi on qi.app_id = a.id_app " +
                    "inner join app.status st on st.app_id = a.id_app " +
                    "inner join app.statuses sts on sts.id = st.statuses_id " +
                    "inner join app.buildings b on b.app_id = a.id_app " +
                    "inner join app.grp_time gt on gt.app_id = a.id_app " +
                    "left  join public.spr_b sb on sb.sp = gt.grp_time_csp " +
                    "left  join public.spr_b sbn on sbn.sp = a.health_csp " +
                    "left  join public.spr_b sba on sba.sp = sbn.spra_id " +
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

}
