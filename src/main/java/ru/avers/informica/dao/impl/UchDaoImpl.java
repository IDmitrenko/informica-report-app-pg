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
import ru.avers.informica.utils.DateUtil;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UchDaoImpl implements UchDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UchMapper uchMapper;

    @Override
    public List<UchInf> getUchInformica(List<IFieldFilterParams> repForUchFilter,
                                        Date currEducDate) {

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
                            "mc.rpgu_link as rpguLink, " +
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
                            "sbfs.spare_01 as orgLegalFormCode, " +
                            "sts.cname as statusName, " +
                            "stsf.spare_01 as statusCode, " +
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
                            "sbfu.spare_01 as structureCode, " +
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
                            "left join public.spr_b_fspeo sbfs on sbfs.id = u.org_form_csp " +
                            "left join public.spr_b sts on sts.sp = u.org_status_csp " +
                            "left join public.spr_b_fspeo stsf on stsf.id = u.org_status_csp " +
                            "left join public.spr_b sbu on sbu.sp = u.uch_struct_csp " +
                            "left join public.spr_b_fspeo sbfu on sbfu.id = u.uch_struct_csp " +
                            "left join app.fspeo_worktime fw on fw.sr = u.work_days " +
                            "left join app.fspeo_meal_serving_type fms on fms.sr = u.meal_serving " +
                            "where u.hidden = '-'",
// TODO repForUchFilter
//                         " and u.domen_uch = 1151",
                    parameterSource,
                    uchMapper);

            return allUch;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса UchIformica.", ex);
            throw ex;
        }
    }

}
