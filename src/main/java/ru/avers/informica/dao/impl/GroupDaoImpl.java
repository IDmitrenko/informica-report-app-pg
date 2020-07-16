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

import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final GroupMapper groupMapper;

    @Override
    public List<GroupInf> getGroupsBuildingUch(Integer idBuilding,
                                               Integer currEducYear,
                                               String idCodeBuilding) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("currEducYear", currEducYear);
            parameterSource.addValue("idBuilding", idBuilding);
            parameterSource.addValue("idCodeBuilding", idCodeBuilding);

            List<GroupInf> allGroupsBuildingUch = jdbcTemplate.query("select cl.id_classes as id, " +
                            ":idCodeBuilding as idCodeBuilding, " +
                            "(get_group_name(cl.class_num) || ' ' || cl.stream_let) as  name, " +
                            "gy.from_y as age_from_years, " +
                            "gy.from_m as age_from_months, " +
                            "gy.to_y as age_to_years, " +
                            "gy.to_m as age_to_months, " +
                            "v08.sr as orientation, " +
                            "v85.sr as worktime_group, " +
                            "v93.sr as activity, " +
                            "(select sum(ct1.plan_quantity) " +
                            " from public.class_types ct1 " +
                            " where ct1.classes_id = cl.id_classes) as capacity " +
                            "from public.classes cl " +
                            "left join public.uch_buildings ub on ub.id_uch_buildings = cl.building_id " +
                            "left join public.group_years gy on gy.uch = cl.uch " +
                            "left join public.class_types ct on ct.classes_id = cl.id_classes " +
                            "left join app.v_dict_08_type_class v08 on v08.id = ct.class_type_csp " +
                            "left join app.v_dict_85_dou_grp_time v85 on v85.id = cl.work_time_csp " +
                            "left join app.v_dict_93_grp_activity v93 on v93.id = cl.activity_csp " +
                            "where ub.id_uch_buildings = :idBuilding and " +
                            "      cl.year_class = :currEducYear and " +
                            "      cl.year_class = gy.year_class and " +
                            "      cl.grp_age_csp = gy.grp_age_csp and " +
                            "      cl.work_time_csp = gy.work_time_csp and " +
                            "      gy.class_type_csp in (select ct1.class_type_csp " +
                            "                            from public.class_types ct1 " +
                            "                            where ct1.classes_id = cl.id_classes) and " +
                            "      (coalesce (gy.from_y, 0) + coalesce (gy.from_m, 0) + " +
                            "       coalesce (gy.from_d, 0) + coalesce (gy.to_y, 0) + " +
                            "       coalesce (gy.to_m, 0) + coalesce (gy.to_d, 0) > 0)",
                    parameterSource,
                    groupMapper);

/*
                            "u.comp_code as code, " +
                            "ub.b_name as name, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "        and c.ftype_sp = 2250004 limit 1) = 2250004 " +
                            "then 1 " +
                            "else 0 " +
                            "end as pool, " +
                            "case when ubo.p1 is null " +
                            "then 0 " +
                            "else ubo.p1 " +
                            "end as oda_territory, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "        and c.ftype_sp in (2250047, 2250001) limit 1) in (2250047, 2250001) " +
                            "then 1 " +
                            "else 0 " +
                            "end as meeting_room, " +
                            "case when (ub.status_csp = 0 or ub.status_csp is null) " +
                            "then (case when (u.org_status_csp = 4950000) " +
                            "      then 1 else 0 end) " +
                            "when ub.status_csp = 4950000 " +
                            "then 1 " +
                            "else 0 " +
                            "end as status_building " +
*/

            return allGroupsBuildingUch;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса GroupsBuildingUch с idBuilding = {}", idBuilding, ex);
            throw ex;
        }
    }

}
