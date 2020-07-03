package ru.avers.informica.dao.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.CommonDao;
import ru.avers.informica.dao.mapper.CommonMapper;
import ru.avers.informica.dao.mapper.IdMapper;
import ru.avers.informica.dto.dictcode.InqryStatusCode;
import ru.avers.informica.dto.informica.CommonInf;
import ru.avers.informica.dto.inqry.AgeDto;

import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommonDaoImpl implements CommonDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final IdMapper idMapper;
    private final CommonMapper commonMapper;

    private final String strIdTer = "id",
            strCnt = "cnt";

    @Override
    public Map<Integer, Integer> getNoDooCounter(Date currDate,
                                                 Date nextEducDate,
                                                 boolean pActiveQueue,
                                                 AgeDto ageFrom,
                                                 AgeDto ageTo) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            String codeAccepted = InqryStatusCode.ACCEPTED_02;
            String codeDidntArrive = InqryStatusCode.DIDNT_ARRIVE_13;
            List<String> codesStatus = Arrays.asList(codeAccepted, codeDidntArrive);

            parameterSource.addValue("codes_status", codesStatus);

            List<Integer> idsStatus = jdbcTemplate.query("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.cname in (:codes_status)",
                    parameterSource,
                    idMapper);

            parameterSource.addValue("dt_curr", currDate);
            parameterSource.addValue("ids_statuses", idsStatus);
            if (ageFrom == null) {
                parameterSource.addValue("isAgeFrom", false);
            } else {
                parameterSource.addValue("isAgeFrom", true);
                parameterSource.addValue("bdt_from", ageFrom.getBirthDate(currDate));
            }
            if (ageTo == null) {
                parameterSource.addValue("isAgeTo", false);
            } else {
                parameterSource.addValue("isAgeTo", true);
                parameterSource.addValue("bdt_to", ageTo.getBirthDate(currDate));
            }
            parameterSource.addValue("dt_next_educ", nextEducDate);
            if (pActiveQueue) {
                parameterSource.addValue("isActiveQueue", true);
            } else {
                parameterSource.addValue("isActiveQueue", false);
            }

            List<CommonInf> commonInfs = jdbcTemplate.query("select inq.ter_sp as " + strIdTer + ", " +
                            "count(inq.id_app) as " + strCnt + " " +
                    "from app.status st " +
                    "inner join app.applications inq on inq.id_app = st.app_id " +
                    "inner join app.statuses sts on sts.id = st.statuses_id " +
                    "where (((st.d_status <= :dt_curr and st.d_validity > :dt_curr and " +
                          "st.statuses_id in (:ids_statuses) and " +
                          "0 = (select count(bu.app_id)" +
                            "from app.buildings bu " +
                            "where bu.app_id = inq.id_app)) and " +
                          "(case when (:isAgeFrom)" +
                          " then inq.d_birth <= :bdt_from" +
                          " else true" +
                          " end)) and " +
                          "(case when (:isAgeTo)" +
                          " then inq.d_birth > :bdt_to" +
                          " else true" +
                          " end)) and " +
                          "(case when (:isActiveQueue)" +
                          " then inq.d_plan < :dt_next_educ" +
                          " else inq.d_plan >= :dt_next_educ" +
                          " end) " +
                    "group by inq.ter_sp",
                    parameterSource,
                    commonMapper);

            Map<Integer, Integer> noDooMap = new HashMap<Integer, Integer>();
            if (commonInfs != null) {
                for (CommonInf commonInf : commonInfs) {
                    noDooMap.put((Integer) commonInf.getId(), ((Long) commonInf.getCnt()).intValue());
                }
            }
            return noDooMap;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса CommonInf - NoDooCounter.", ex);
            throw ex;
        }
    }

    @Override
    public Map<Integer, Integer> getMedicCounter(Date currDate,
                                                 AgeDto ageFrom,
                                                 AgeDto ageTo) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            String codeMedic = InqryStatusCode.CANT_GO_TO_DOO_MEDIC_14;
            List<String> codesStatus = Arrays.asList(codeMedic);

            parameterSource.addValue("codes_status", codesStatus);

            List<Integer> idsStatus = jdbcTemplate.query("select sts.id as id " +
                            "from app.statuses sts " +
                            "where sts.cname in (:codes_status)",
                    parameterSource,
                    idMapper);

            parameterSource.addValue("dt_curr", currDate);
            parameterSource.addValue("ids_statuses", idsStatus);
            if (ageFrom == null) {
                parameterSource.addValue("isAgeFrom", false);
            } else {
                parameterSource.addValue("isAgeFrom", true);
                parameterSource.addValue("bdt_from", ageFrom.getBirthDate(currDate));
            }
            if (ageTo == null) {
                parameterSource.addValue("isAgeTo", false);
            } else {
                parameterSource.addValue("isAgeTo", true);
                parameterSource.addValue("bdt_to", ageTo.getBirthDate(currDate));
            }

            List<CommonInf> commonInfs = jdbcTemplate.query("select inq.ter_sp as " + strIdTer + ", " +
                            "count(inq.id_app) as " + strCnt + " " +
                            "from app.status st " +
                            "inner join app.applications inq on inq.id_app = st.app_id " +
                            "inner join app.statuses sts on sts.id = st.statuses_id " +
                            "where ((st.d_status <= :dt_curr and st.d_validity > :dt_curr and " +
                            "st.statuses_id in (:ids_statuses)) and " +
                            "(case when (:isAgeFrom)" +
                            " then inq.d_birth <= :bdt_from" +
                            " else true" +
                            " end)) and " +
                            "(case when (:isAgeTo)" +
                            " then inq.d_birth > :bdt_to" +
                            " else true" +
                            " end) " +
                            "group by inq.ter_sp",
                    parameterSource,
                    commonMapper);

            Map<Integer, Integer> medicMap = new HashMap<Integer, Integer>();
            if (commonInfs != null) {
                for (CommonInf commonInf : commonInfs) {
                    medicMap.put((Integer) commonInf.getId(), ((Long) commonInf.getCnt()).intValue());
                }
            }
            return medicMap;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса CommonInf - MedicCounter.", ex);
            throw ex;
        }
    }

    @Override
    public Map<Integer, Integer> getFamilyCounter(@NonNull Date currDate,
                                                 @NonNull Integer currEducYear,
                                                 @NonNull AgeDto ageFrom,
                                                 @NonNull AgeDto ageTo) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            parameterSource.addValue("dt_curr", currDate);
            parameterSource.addValue("year_educ", currEducYear);
//            if (ageFrom == null) {
//                parameterSource.addValue("isAgeFrom", false);
//            } else {
//                parameterSource.addValue("isAgeFrom", true);
                parameterSource.addValue("bdt_from", ageFrom.getBirthDate(currDate));
//            }
//            if (ageTo == null) {
//                parameterSource.addValue("isAgeTo", false);
//            } else {
//                parameterSource.addValue("isAgeTo", true);
                parameterSource.addValue("bdt_to", ageTo.getBirthDate(currDate));
//            }

            List<CommonInf> commonInfs = jdbcTemplate.query("select u.uch_ter_sp as " + strIdTer + ", " +
                            "(select count(sp.out_id_pupil) " +
                            " from school_pupil(u.domen_uch, :dt_curr, 1, 2, 3) sp " +
                            " inner join public.pupil p on p.pupil_id = sp.out_id_pupil " +
                            " inner join public.classes cl on (cl.uch = sp.out_uch and " +
                            "       cl.year_class = :year_educ and " +
                            "       cl.class_num = sp.out_class_num and " +
                            "       cl.stream_let = sp.out_stream_let) " +
                            " where cl.family_group = '+' and " +
                            "       p.d_birth <= :bdt_from and " +
                            "       p.d_birth > :bdt_to and " +
                            "       (sp.out_d_leave is null or sp.out_d_leave > :dt_curr)" +
                            ") as " + strCnt + " " +
                            "from public.uch u " +
                            "left join public.municip m on m.m_id = u.uch_ter_sp " +
                            "where u.hidden = '-'",
                    parameterSource,
                    commonMapper);

            Map<Integer, Integer> familyMap = new HashMap<Integer, Integer>();
            if (commonInfs != null) {
                for (CommonInf commonInf : commonInfs) {
                    familyMap.put((Integer) commonInf.getId(), ((Long) commonInf.getCnt()).intValue());
                }
            }
            return familyMap;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса CommonInf - FamilyCounter.", ex);
            throw ex;
        }
    }

}
