package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.InqryGrpTimeDao;
import ru.avers.informica.dao.mapper.InqryGrpTimesMapper;
import ru.avers.informica.dto.informica.InqryGrpTimeInf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InqryGrpTimeDaoImpl implements InqryGrpTimeDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final InqryGrpTimesMapper inqryGrpTimesMapper;

    @Override
    public Collection<String> getGrpTimesInqry(Integer idApplications) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            parameterSource.addValue("idApplications", idApplications);


            List<InqryGrpTimeInf> inqryGrpTimeInfs = jdbcTemplate
                    .query("select :idApplications as id, " +
                            "gt.grp_time_csp as idGrpTimeCsp, " +
                            "v85.code as grpTimeCode " +
                    "from app.grp_time gt " +
                    "inner join app.v_dict_85_dou_grp_time v85 on v85.id = gt.grp_time_csp " +
                    "where gt.app_id = :idApplications",
                    parameterSource,
                    inqryGrpTimesMapper);

            Collection<String> inqryGrpTimes = new HashSet<>();
            for (InqryGrpTimeInf igt : inqryGrpTimeInfs) {
                inqryGrpTimes.add(igt.getGrpTimeCode());
            }
            return inqryGrpTimes;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса GrpTimesInqry с idApplications = {}", idApplications, ex);
            throw ex;
        }
    }

    @Override
    public Collection<Integer> getGrpTimeIdsInqry(Integer idApplications) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            parameterSource.addValue("idApplications", idApplications);


            List<InqryGrpTimeInf> inqryGrpTimeInfs = jdbcTemplate
                    .query("select :idApplications as id, " +
                                    "gt.grp_time_csp as idGrpTimeCsp, " +
                                    "v85.code as grpTimeCode " +
                                    "from app.grp_time gt " +
                                    "inner join app.v_dict_85_dou_grp_time v85 on v85.id = gt.grp_time_csp " +
                                    "where gt.app_id = :idApplications",
                            parameterSource,
                            inqryGrpTimesMapper);

            Collection<Integer> inqryGrpTimeIds = new HashSet<>();
            for (InqryGrpTimeInf igt : inqryGrpTimeInfs) {
                inqryGrpTimeIds.add(igt.getIdGrpTime());
            }
            return inqryGrpTimeIds;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса GrpTimesInqry с idApplications = {}", idApplications, ex);
            throw ex;
        }
    }

}
