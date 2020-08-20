package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.GroupActionsDao;
import ru.avers.informica.dao.mapper.IdMapper;
import ru.avers.informica.dto.dictcode.TypeClassCode;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.utils.DateUtil;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GroupActionsDaoImpl implements GroupActionsDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final IdMapper idMapper;
    private final ReportSetting reportSetting;

    @Override
    public Collection<Integer> getGroupHealths(Integer idGroup) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            parameterSource.addValue("idGroup", idGroup);

            String sql = "select ct.class_type_csp as id " +
                    "from public.class_types ct " +
                    "where ct.classes_id = :idGroup";

            List<Integer> idGroupHealthsList = jdbcTemplate
                    .query(sql, parameterSource, idMapper);

            Collection<Integer> idGroupHealths = new HashSet<>();
            for (Integer idh : idGroupHealthsList) {
                idGroupHealths.add(idh.intValue());
            }
            return idGroupHealths;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса GroupHealths с idGroup = {}", idGroup, ex);
            throw ex;
        }
    }

    @Override
    public Integer getCombinedGroupSubtract(Integer idGroup, Collection<Integer> idHealthCsp) {

        try {
            Integer idDirection = getIdDirection();
            if (idHealthCsp.size() == 1) {
                if (idHealthCsp.contains(idDirection)) {
                    return 0;
                } else {
                    return 1;
                }
            }

            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("idGroup", idGroup);
            parameterSource.addValue("idDirection", idDirection);
            Date dateFromInterval = DateUtil.adjustDate(reportSetting.getCurrEducDate(), 0, 0, -1);
            Date dateToInterval = DateUtil.adjustDate(dateFromInterval, 1, 0, 0);
            Date currDate = reportSetting.getCurrDate();
            parameterSource.addValue("dateFromInterval", dateFromInterval);
            parameterSource.addValue("dateToInterval", dateToInterval);
            parameterSource.addValue("currDate", currDate);

            String sql = "select (select count(sp.out_id_pupil) " +
                    "from school_pupil(cl.uch, :currDate, 1, 2, 3) sp " +
                    "inner join inclass ic on ic.id_inclass = sp.out_id_inclass " +
                    "where sp.out_class_num = cl.class_num and " +
                    "sp.out_stream_let = cl.stream_let and " +
                    "(ic.d_up between :dateFromInterval and :dateToInterval) and " +
                    "ic.class_type_csp = :idDirection and " + //Общеразвивающая-комбинированная
                    "(sp.out_d_leave is null or sp.out_d_leave > :currDate) " +
                    ") as id " +
                    "from public.classes cl " +
                    "where cl.id_classes = :idGroup and " +
                    "(select count(ct.classes_id) from class_types ct where ct.classes_id = cl.id_classes) > 1";

            Integer subtract = jdbcTemplate.queryForObject(sql, parameterSource, idMapper);
            return subtract;

        } catch (EmptyResultDataAccessException ex) {
            log.info("Для комбинированной группы с id = {} определена только одна направленность!", idGroup, ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса CombinedGroupSubtract с idGroup = {}", idGroup, ex);
            throw ex;
        }
    }

    private Integer getIdDirection() {
        String codeGDC = TypeClassCode.GENERAL_DEVELOPMENT_COMBINED;
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("codeGDC", codeGDC);
            Integer idDirection = jdbcTemplate.queryForObject("select v08.id as id " +
                            "from app.v_dict_08_type_class v08 " +
                            "where v08.code = :codeGDC",
                    parameterSource,
                    idMapper);
            return idDirection;
        } catch (EmptyResultDataAccessException ex) {
            log.info("В справочнике V_DICT_08_TYPE_CLASS отсутствует направленность {} !", codeGDC, ex);
            return 0;
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса IdDirection с codeGDC = {}", codeGDC, ex);
            throw ex;
        }
    }
}
