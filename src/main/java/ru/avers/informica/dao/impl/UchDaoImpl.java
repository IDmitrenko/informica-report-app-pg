package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dao.mapper.IdMapper;
import ru.avers.informica.dao.mapper.UchMapper;
import ru.avers.informica.dto.informica.UchInf;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UchDaoImpl implements UchDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final IdMapper idMapper;
    private final UchMapper uchMapper;

    @Override
    public List<UchInf> getUchInformica(List<IFieldFilterParams> repForUchFilter) {

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

            List<UchInf> allUch = jdbcTemplate.query("select u.domen_uch as id, " +
                            "u.comp_code as code, " +
                            "u.name as name, " +
                            "u.name_long as shortName, " +
                            "sbt.sp as idTer, " +
                            "sbt.cname as terName, " +
                            "u.cf_name + ' ' + u.ci_name + ' ' + u.co_name as chief, " +
                            "u.code_oktmo as municipObrOktmo, " +

                            "u.epgu_link as epguLink, " +
                            "u.epgu_link as rpguLink, " +
                            "u.work_days as workDays, " +
                            "u.work_from as timeFrom, " +
                            "u.work_to as timeTo, " +


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

                    "from public.uch u " +
                    "inner  join public.spr_b sbt on sbt.sp = u.uch_ter_csp " +
                    "inner join public.municip mc on mc.m_id = u.uch_ter_csp " +

                            "inner join app.status st on st.app_id = a.id_app " +
                            "inner join app.statuses sts on sts.id = st.statuses_id " +
                            "inner join app.buildings b on b.app_id = a.id_app " +
                            "inner join app.grp_time gt on gt.app_id = a.id_app " +
                    "left  join public.spr_b sbn on sbn.sp = a.health_csp " +
                    "left  join public.spr_b sba on sba.sp = sbn.spra_id " +
                    "where st.d_status <= :dt_curr and st.d_validity > :dt_curr and " +
                          "(sts.id <> :id_archive_status or " +
                          " qi.d_reg >= :rf_from and qi.d_reg < :dt_curr)",
                    parameterSource,
                    uchMapper);

            return allUch;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса UchIformica.", ex);
            throw ex;
        }
    }

}
