package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.GroupDao;
import ru.avers.informica.dao.mapper.GroupMapper;
import ru.avers.informica.dto.informica.GroupInf;
import ru.avers.informica.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final GroupMapper groupMapper;

/* TODO
   Общее:
   1) Поля, которые имеют значение null в отчет не включаются

   Для поля add_cont :
   1) Запрос по заявлениям может возвращать дубли,
      так как в заявлении могут быть выбраны несколько режимов посещения. (grp_time)
      (app.applications.id_app -> app.grp_time.app_id
       app.grp_time.grp_time_csp -> app.v_dict_85_dou_grp_time.code  [sr])
   == не будет - так как я буду для заявления формировать HashSet ссылок на режим посещения == InqryEnrolmentInf
   2) Запрос по группам может возвращать дубли,
      так как в группах могут быть указаны несколько направленностей.
      (public.class_types.class_type_csp -> app.v_dict_08_type_class.code [sr] )
 */
    private String sql = "select cl.uch as idUch, cl.building_id as idBuilding, " +
            "cl.id_classes as id, " +
            ":idCodeBuilding as idCodeBuilding, " +
            "(get_group_name(cl.class_num) || ' ' || cl.stream_let) as  name, " +
/* Вариант когда записи из-за group_years нужно обьединять в разновозрастных группах
            "gy.from_y as age_from_years, " +
            "gy.from_m as age_from_months, " +
            "gy.to_y as age_to_years, " +
            "gy.to_m as age_to_months, " +
*/
            "(select coalesce(gyf.from_y) || ' ' || coalesce(gyf.from_m) as fym " +
            " from group_years gyf " +
            " where gyf.uch = cl.uch and gyf.year_class = cl.year_class and " +
            "       gyf.grp_age_csp = cl.grp_age_csp and " +
            "       gyf.class_type_csp in (select ct1.class_type_csp " +
            "                             from class_types ct1 " +
            "                             where ct1.classes_id = cl.id_classes) and " +
            "       gyf.work_time_csp = cl.work_time_csp and " +
            "       (coalesce(gyf.from_y, 0) + coalesce(gyf.from_m, 0) + " +
            "        coalesce(gyf.from_d, 0) + coalesce(gyf.to_y, 0) + " +
            "        coalesce(gyf.to_m, 0) + coalesce(gyf.to_d, 0) > 0) " +
            " order by gyf.from_y, gyf.from_m asc limit 1), " +
            "(select coalesce(gyt.to_y) || ' ' || coalesce(gyt.to_m) as tym " +
            " from group_years gyt " +
            " where gyt.uch = cl.uch and gyt.year_class = cl.year_class and " +
            "       gyt.grp_age_csp = cl.grp_age_csp and " +
            "       gyt.class_type_csp in (select ct1.class_type_csp " +
            "                             from class_types ct1 " +
            "                             where ct1.classes_id = cl.id_classes) and " +
            "       gyt.work_time_csp = cl.work_time_csp and " +
            "       (coalesce(gyt.from_y, 0) + coalesce(gyt.from_m, 0) + " +
            "        coalesce(gyt.from_d, 0) + coalesce(gyt.to_y, 0) + " +
            "        coalesce(gyt.to_m, 0) + coalesce(gyt.to_d, 0) > 0) " +
            " order by gyt.to_y, gyt.to_m desc limit 1), " +
            "case when " +
            " (select count(ct.classes_id)" +
            "  from class_types ct " +
            "  where ct.classes_id = cl.id_classes) > 1 " +
            "then '3' " +
            "else v08.sr " +
            "end as orientation, " +
//            "v08.id as idHealthCsp, " +  // заменил одиночную на коллекцию
            "v85.sr as worktime_group, " +
            "cl.work_time_csp as idWorkTimeCsp, " +
            "v93.sr as activity, " +
            "(select sum(ct1.plan_quantity) " +
            " from public.class_types ct1 " +
            " where ct1.classes_id = cl.id_classes) as capacity, " +
/* Алгоритм сбора зачисленных - теперь будет браться из UchInfo_01
            "(select count(p.id_pupil) " +
            " from pupils p " +
            " where p.uch = cl.uch and " +
            "      (p.d_leave is null or p.d_leave > :currDate) and " +
            "      (p.id_pupil in(" +
            "        select id_pupil from pupils ps " +
            "        left outer join pupil_classes(:currDate, id_pupil, 1, 2, 3) pc on 1 = 1 " +
            "        inner join classes cl2 on (cl2.class_num = pc.out_class_num and " +
            "                                   cl2.stream_let = pc.out_stream_let and " +
            "                                   cl2.year_class = :currEducYear and cl2.uch = cl.uch) " +
            "        where ps.uch = p.uch and cl2.id_classes = cl.id_classes)) and " +
            "              p.id_pupil in(" +
            "                         select distinct icl.pupil_id " +
            "                         from inclass icl " +
            "                         where icl.uch = cl.uch and " +
            "                               icl.move_st in (1, 2) and " +
            "                              (icl.d_up between :dateFromInterval and :dateToInterval)" +
            "                           )" +
            ") as enrolled " +
*/
            "(select count(sp.out_id_pupil) " +
            " from school_pupil(cl.uch, current_date, 1, 2, 3) sp " +
            " inner join inclass ic on ic.id_inclass = sp.out_id_inclass " +
            " where sp.out_class_num = cl.class_num and sp.out_stream_let = cl.stream_let and " +
            "       (sp.out_d_leave is null or sp.out_d_leave > :currDate) and " +
            "       (ic.d_up between :dateFromInterval and :dateToInterval)" +
            ") as enrolled, " +
            "case when cl.subgroups_count is null " +
            "then 0 " +
            "else cl.subgroups_count " +
            "end as subgroup, " +
            "cl.from_other_uch as transfer_space, " +
            "case when cl.third_party_education = '-' " +
            "then 2 " +
            "else 1 " +
            "end as partner_group, " +
            "case when cl.third_party_education = '-' " +
            "then 'нет' " +
            "else (select u.comp_code " +
            "      from uch u " +
            "      where u.sided_edu_uch_id is not null and u.domen_uch = cl.uch) " +
            "end as partner, " +
            "f5a.days as days, " +
            "case when v93.sr = '1' " +
            "then (select count(cp.id_clsper) " +
            "      from clsper cp " +
            "      where cp.classes_id = cl.id_classes) " +
            "else 0 " +
            "end as educator, " +
            "f5c.invalid as invalid, " +
            "round(cast(cl.floor_area as numeric), 0) as size, " +
            "case when v93.sr = '2' and " +
            "          (select v08.sr " +
            "           from app.v_dict_08_type_class v08 " +
            "           inner join class_types ct on v08.id = ct.class_type_csp " +
            "           where ct.classes_id = cl.id_classes) in ('2', '5', '6', '7') " +
            "then 0 " +
            "else 1 " +
            "end as program, " +
            "f5c.reduction_other as reduction_other, " +
            "f5c.reduction_school as reduction_school, " +
            "f5c.add_cont_ovz as add_cont_ovz, " +
            "f5c.add_cont_gkp as add_cont_gkp, " +
            "f5c.enrolled_gkp as enrolled_gkp, " +
            "case when v85.sr = '1' " +
            "then (select sum(ct1.plan_quantity) " +
            "      from class_types ct1 " +
            "      where ct1.classes_id = cl.id_classes) " +
            "else 0 " +
            "end as capacity_gkp, " +
            "case when v93.sr = '2' " +
            "then 0 " +
            "     when (select v08.sr " +
            "           from app.v_dict_08_type_class v08 " +
            "           inner join class_types ct on v08.id = ct.class_type_csp " +
            "           where ct.classes_id = cl.id_classes limit 1) = '2' " +
            "then 1 " +
            "else 0 " +
            "end as program_ovz, " +
            "(select string_agg(v08.code, ' ' order by v08.code) as class_type " +
            " from app.v_dict_08_type_class v08 " +
            " inner join class_types ct on v08.id = ct.class_type_csp " +
            " where ct.classes_id = cl.id_classes) as ovz_type, " +
            "(select string_agg(v08.sr, ' ' order by v08.sr) as class_type_ovz " +
            " from app.v_dict_08_type_class v08 " +
            " inner join class_types ct on v08.id = ct.class_type_csp " +
            " where ct.classes_id = cl.id_classes) as ovz_type_dop " +
            "from public.classes cl " +
            "inner join public.uch_buildings ub on ub.id_uch_buildings = cl.building_id " +
/* Вариант когда записи из-за group_years нужно обьединять в разновозрастных группах
            "inner join public.group_years gy on gy.uch = cl.uch " +
*/
            "inner join public.class_types ct on ct.classes_id = cl.id_classes " +
            "inner join app.v_dict_08_type_class v08 on v08.id = ct.class_type_csp " +
            "inner join app.v_dict_85_dou_grp_time v85 on v85.id = cl.work_time_csp " +
            "inner join app.v_dict_93_grp_activity v93 on v93.id = cl.activity_csp " +
            "left join f5_att f5a on f5a.classes_id = cl.id_classes " +
            "left join f5_classes f5c on f5c.classes_id = cl.id_classes " +
            "where ub.id_uch_buildings = :idBuilding and " +
            "      cl.year_class = :currEducYear";
/* Вариант когда записи из-за group_years нужно обьединять в разновозрастных группах
          + "  and cl.year_class = gy.year_class and " +
            "      cl.grp_age_csp = gy.grp_age_csp and " +
            "      cl.work_time_csp = gy.work_time_csp and " +
            "      gy.class_type_csp in (select ct1.class_type_csp " +
            "                            from public.class_types ct1 " +
            "                            where ct1.classes_id = cl.id_classes) and " +
            "      (coalesce (gy.from_y, 0) + coalesce (gy.from_m, 0) + " +
            "       coalesce (gy.from_d, 0) + coalesce (gy.to_y, 0) + " +
            "       coalesce (gy.to_m, 0) + coalesce (gy.to_d, 0) > 0)";
*/

    @Override
    public List<GroupInf> getGroupsBuildingUch(Integer idBuilding,
                                               Date currEducDate,
                                               Date currDate,
                                               String idCodeBuilding) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            Integer currEducYear = DateUtil.getYearPart(currEducDate);
            Date dateFromInterval = DateUtil.adjustDate(currEducDate, 0, 0, -1);
            Date dateToInterval = DateUtil.adjustDate(dateFromInterval, 1, 0, 0);
            parameterSource.addValue("currEducYear", currEducYear);
            parameterSource.addValue("idBuilding", idBuilding);
            parameterSource.addValue("idCodeBuilding", idCodeBuilding);
            parameterSource.addValue("dateFromInterval", dateFromInterval);
            parameterSource.addValue("dateToInterval", dateToInterval);
            parameterSource.addValue("currDate", currDate);

            List<GroupInf> allGroupsBuildingUch = jdbcTemplate.query(sql,
                    parameterSource,
                    groupMapper);

// обьединить записи с одинаковыми id (id_uch_buildings = 126  (22 группы схлопываются в 13))
/* Вариант когда записи из-за group_years нужно обьединять в разновозрастных группах */
            if (allGroupsBuildingUch.size() > 0) {
                List<GroupInf> groupInfs = new ArrayList<>();
                groupInfs.add(allGroupsBuildingUch.get(0));
                for (GroupInf groupInf : allGroupsBuildingUch) {
                    GroupInf groupInfPrev = groupInfs.get(groupInfs.size() - 1);
                    if (groupInf.getId() == groupInfPrev.getId()) {
                        if (groupInf.getAgeFrom().compareTo(groupInfPrev.getAgeFrom()) == -1) {
                            groupInfPrev.setAgeFrom(groupInf.getAgeFrom());
                        }
                        if (groupInf.getAgeTO().compareTo(groupInfPrev.getAgeTO()) == 1) {
                            groupInfPrev.setAgeTO(groupInf.getAgeTO());
                        }

                    } else {
                        groupInfs.add(groupInf);
                    }
                }
                allGroupsBuildingUch = groupInfs;
            }

            return allGroupsBuildingUch;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса GroupsBuildingUch с idBuilding = {}", idBuilding, ex);
            throw ex;
        }
    }

}
