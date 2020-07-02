package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.CommonDao;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dao.mapper.IdMapper;
import ru.avers.informica.dao.mapper.UchMapper;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.dto.inqry.AgeDto;
import ru.avers.informica.utils.DateUtil;

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
    private final CommonDao commonDao;

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
                            "coalesce(u.cf_name, '') || ' ' || coalesce(u.ci_name, '') || " +
                                     "' ' || coalesce(u.co_name, '') as chief, " +
                            "u.code_oktmo as municipObrOktmo, " +
                            "mc.epgu_link as epguLink, " +
                            "mc.epgu_link as rpguLink, " +
// сделать обработку: Пятидневка, с 07:30 по 18:00
                            "coalesce(fw.nm || ', ', '') || 'с ' || " +
                                     "coalesce(to_char(u.work_from, 'HH24:MI'), '') || ' по ' || " +
                                     "coalesce(to_char(u.work_to, 'HH24:MI'), '') as worktime, " +
                            "fms.nm as mealServingType, " +
                            "u.site as website, " +
                            "u.mail as email, " +
                            "coalesce (u.phone, '') || ', ' || coalesce (u.phone2, '') || " +
                                      "', ' || coalesce (u.fax, '') as phone, " +
                            "sbs.cname as orgLegalFormName, " +
                            "sbs.sr as orgLegalFormCode, " +
                            "sts.cname as statusName, " +
                            "sts.sr as statusCode, " +
                            "(select string_agg(c.name || '  (' || sb.cname || ')', '; ' order by c.name) as educNapr " +
                            " from public.circ c " +
                            " left outer join public.spr_b sb on sb.sp = c.circle_sp " +
                            " where c.uch = u.domen_uch and " +
                            "       c.year_school = :currEducYear and " +
                            "       (c.logopunkt = '-' or c.logopunkt is null) and " +
                            "       c.edu_activity is null) as addEducation, " +
// обработка: 1-ое поле + пробел + ( + второе поле + ) + ;(кроме последнего)
                            "u.edu_activity as features, " +
                            "(select count (ub.id_uch_buildings) " +
                            " from uch_buildings ub " +
                            " where ub.uch = u.domen_uch and ub.filial = '+') as num_filial, " +
                            "(select count (ub.id_uch_buildings) " +
                            " from uch_buildings ub " +
                            " where ub.uch = u.domen_uch) as num_building, " +
                            "(select count (cl.id_classes) " +
                            " from classes cl " +
                            " where cl.uch = u.domen_uch and cl.year_class = :currEducYear) as num_group, " +
                            "u.addr_fias as fiasOrgGuid, " +
                            "u.address as addrKladr, " +
                            "sbu.cname as structureName, " +
                            "sbu.sr as structureCode, " +
                            "case when " +
                            " (select count(und.id_norm_docs)" +
                            "  from uch_norm_docs und " +
                            "  where und.norm_docs_type = 1 and und.uch = u.domen_uch) > 0 " +
                            "then 1 " +
                            "else 2 " +
                            "end as license, " +
                            "case when " +
                            " (select count(und.id_norm_docs)" +
                            "  from uch_norm_docs und " +
                            "  where und.norm_docs_type = 1 and und.uch = u.domen_uch) > 0 " +
                            "then 2 " +
                            "else 1 " +
                            "end as partner_doo, " +
                            "case when u.addr_locality = 'г' " +
                            "then 1 " +
                            "else 2 " +
                            "end as type_area, " +
                            "case when " +
                            " u.lekoteka = '+' " +
                            "then 1 " +
                            "else 0 " +
                            "end as lekoteka, " +
                            "case when " +
                            " u.centre_game = '+' " +
                            "then 1 " +
                            "else 0 " +
                            "end as centre_game, " +
                            "u.passport_link as passport, " +
                            "case " +
                            "when sts.sr in ('2','3') " +
                            "then 'Планируемая дата окончания работ: ' || " +
                                  "coalesce(to_char(u.d_overhaul_end, 'DD.MM.YYYY'), '') " +
                            "when sts.sr in ('4','5') " +
                            "then coalesce(u.close_reason, '') || " +
                                  "', планируемая дата открытия: ' || " +
                                  "coalesce(to_char(u.d_opening, 'DD.MM.YYYY'), '') " +
                            "when sts.sr = '6' " +
                            "then 'Планируемая дата открытия: ' || " +
                                  "coalesce(to_char(u.d_opening , 'DD.MM.YYYY'), '') " +
                            "else 'Нет' " +
                            "end as commet_status " +
                    "from public.uch u " +
                    "left join public.spr_b sbt on sbt.sp = u.uch_ter_csp " +
                    "left join public.municip mc on mc.m_id = u.uch_ter_csp " +
                    "left join public.spr_b sbs on sbs.sp = u.org_form_csp " +
                    "left join public.spr_b sts on sts.sp = u.org_status_csp " +
                    "left join public.spr_b sbu on sbu.sp = u.uch_struct_csp " +
                    "left join app.fspeo_worktime fw on fw.sr = u.work_days " +
                    "left join app.fspeo_meal_serving_type fms on fms.sr = u.meal_serving " +
                    "where u.hidden = '-'",
                    parameterSource,
                    uchMapper);

            // Муниципальные показатели
            AgeDto age0 = new AgeDto((short)0, (short)0, (short)0);
            AgeDto age3 = new AgeDto((short)3, (short)0, (short)0);
            AgeDto age7 = new AgeDto((short)7, (short)0, (short)0);

            // Данные о детях, стоящих на учете в связи с отсутствием ДОО, передаются
            // в тэге noDooAct для детей, желающих получить место в текущем учебном году
            Map<Integer, Integer> noDooAct_0_3 = commonDao.getNoDooCounter(currDate,
                    DateUtil.adjustDate(currEducDate, 1),
                    true,
                    age0, age3);
            Map<Integer, Integer> noDooAct_3_7 = commonDao.getNoDooCounter(currDate,
                    DateUtil.adjustDate(currEducDate, 1),
                    true,
                    age3, age7);

            // или в тэге nooDooDef для детей, желающих получить место в последующие годы.
            Map<Integer, Integer> noDooDef_0_3 = commonDao.getNoDooCounter(currDate,
                    DateUtil.adjustDate(currEducDate, 1),
                    false,
                    age0, age3);
            Map<Integer, Integer> noDooDef_3_7 = commonDao.getNoDooCounter(currDate,
                    DateUtil.adjustDate(currEducDate, 1),
                    false,
                    age3, age7);

            // Данные о детях, не посещающих ДОО по медицинским показаниям
            Map<Integer, Integer> medic_0_3 = commonDao.getMedicCounter(currDate,
                    age0, age3);
            Map<Integer, Integer> medic_3_7 = commonDao.getMedicCounter(currDate,
                    age3, age7);

            // Данные о детях, получающих дошкольное образование в семейной форме


            for (UchInf uchInf : allUch) {
                String oktmo = uchInf.getMunicipObrOktmo();
                Integer idTerUch = uchInf.getIdTer();
                if (noDooAct_0_3.containsKey(idTerUch)) {
                    uchInf.setNoDooAct_0_3(noDooAct_0_3.get(idTerUch));
                }
                if (noDooAct_3_7.containsKey(idTerUch)) {
                    uchInf.setNoDooAct_3_7(noDooAct_3_7.get(idTerUch));
                }
                if (noDooDef_0_3.containsKey(idTerUch)) {
                    uchInf.setNoDooDef_0_3(noDooDef_0_3.get(idTerUch));
                }
                if (noDooDef_3_7.containsKey(idTerUch)) {
                    uchInf.setNoDooDef_3_7(noDooDef_3_7.get(idTerUch));
                }
                if (medic_0_3.containsKey(idTerUch)) {
                    uchInf.setMedic_0_3(medic_0_3.get(idTerUch));
                }
                if (medic_3_7.containsKey(idTerUch)) {
                    uchInf.setMedic_3_7(medic_3_7.get(idTerUch));
                }
                // TODO семейная форма
            }
            return allUch;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса UchIformica.", ex);
            throw ex;
        }
    }

}
