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
import ru.avers.informica.dto.inqry.AgeDto;
import ru.avers.informica.utils.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class UchDaoImpl implements UchDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final IdMapper idMapper;
    private final UchMapper uchMapper;

    @Override
    public List<UchInf> getUchInformica(List<IFieldFilterParams> repForUchFilter,
                                        Date currDate, Date currEducDate) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            Integer currEducYear = DateUtil.getYearPart(currEducDate);


            parameterSource.addValue("currEducYear", currEducYear);

            List<UchInf> allUch = jdbcTemplate.query("select u.domen_uch as id, " +
                            "u.comp_code as code, " +
                            "u.name as name, " +
                            "u.name_long as shortName, " +
                            "sbt.sp as idTer, " +
                            "sbt.cname as terName, " +
                            "u.cf_name + ' ' + u.ci_name + ' ' + u.co_name as chief, " +
                            "u.code_oktmo as municipObrOktmo, " +
                            "mc.epgu_link as epguLink, " +
                            "mc.epgu_link as rpguLink, " +
                            "u.work_days as workDays, " +
                            "u.work_from as timeFrom, " +
                            "u.work_to as timeTo, " +
// сделать обработку: Пятидневка, с 07:30 по 18:00
                            "u.meal_serving as mealServingType, " +
                            "sbs.cname as orgLegalFormName, " +
                            "sbs.sr as orgLegalFormCode, " +
                            "sts.cname as statusName, " +
                            "sts.sr as statusCode, " +
                            "(select c.name, sb.name as napr " +
                            " from public.circ c " +
                            " left outer join public.spr_b sb on sb.sp = c.circle_sp " +
                            " where c.uch = u.domen_uch and " +
                            "       c.year_school = :currEducYear and " +
                            "       (c.logopunkt = '-' or c.logopunkt is null) and " +
                            "       c.edu_activity is null) as addEducation, " +
// сделать обработку: 1-ое поле + пробел + ( + второе поле + ) + ;(кроме последнего)
                            "u.edu_activity as features, " +
                            "u.addr_fias as fiasHouseGuid, " +
                            "u.address_fakt as addrKladr, " +
                            "sbu.cname as structureName, " +
                            "sbu.sr as structureCode " +
                    "from public.uch u " +
                    "left join public.spr_b sbt on sbt.sp = u.uch_ter_csp " +
                    "left join public.municip mc on mc.m_id = u.uch_ter_csp " +
                    "left join public.spr_b sbs on sbs.sp = u.org_form_csp " +
                    "left join public.spr_b sts on sts.sp = u.org_status_csp " +
                    "left join public.spr_b sbu on sbu.sp = u.uch_struct_csp",
                    parameterSource,
                    uchMapper);

            // Муниципальные показатели
            AgeDto age0 = new AgeDto((short)0, (short)0, (short)0);
            AgeDto age3 = new AgeDto((short)3, (short)0, (short)0);
            AgeDto age7 = new AgeDto((short)7, (short)0, (short)0);
            // Данные о детях, стоящих на учете в связи с отсутствием ДОО, передаются
            // в тэге noDooAct для детей, желающих получить место в текущем учебном году
//            Map<Integer, Integer> noDooAct_0_3 =

            return allUch;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса UchIformica.", ex);
            throw ex;
        }
    }

}
