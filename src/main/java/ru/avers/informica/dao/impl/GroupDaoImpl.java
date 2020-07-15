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
                                               Integer currEducYear) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("currEducYear", currEducYear);
            parameterSource.addValue("idBuilding", idBuilding);

            List<GroupInf> allGroupsBuildingUch = jdbcTemplate.query("select cl.id_classes as id " +
                            "from public.classes cl " +
                            "left join public.uch_buildings ub on ub.id_uch_buildings = cl.building_id " +
                            "where ub.id_uch_buildings = :idBuilding and " +
                            "      cl.year_class = :currEducYear",
                    parameterSource,
                    groupMapper);

/*
                            "u.comp_code as code, " +
                            "ub.b_name as name, " +
                            "case when ub.houseguid = '' " +
                            "then '00000000-0000-0000-0000-000000000000' " +
                            "else ub.houseguid " +
                            "end as fias_house_guid, " +
                            "ub.address as plain_address, " +
                            "case when ub.addr_locality = 'г' " +
                            "then 1 " +
                            "else 2 " +
                            "end as building_type_area, " +
                            "sb.sr as type_building, " +
                            "case when ub.filial = '+' " +
                            "then 1 " +
                            "else 0 " +
                            "end as filial, " +
                            "ub.depreciation as depreciation, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "        and c.ftype_sp = 2250004 limit 1) = 2250004 " +
                            "then 1 " +
                            "else 0 " +
                            "end as pool, " +
                            "case when ubo.p24 is null " +
                            "then 0 " +
                            "else ubo.p24 " +
                            "end as ear_equipment, " +
                            "case when ubo.p23 is null " +
                            "then 0 " +
                            "else ubo.p23 " +
                            "end as ear_path, " +
                            "case when ubo.p22 is null " +
                            "then 0 " +
                            "else ubo.p22 " +
                            "end as ear_communication, " +
                            "case when ubo.p21 is null " +
                            "then 0 " +
                            "else ubo.p21 " +
                            "end as ear_washroom, " +
                            "case when ubo.p20 is null " +
                            "then 0 " +
                            "else ubo.p20 " +
                            "end as ear_room, " +
                            "case when ubo.p19 is null " +
                            "then 0 " +
                            "else ubo.p19 " +
                            "end as ear_way, " +
                            "case when ubo.p18 is null " +
                            "then 0 " +
                            "else ubo.p18 " +
                            "end as ear_entrance, " +
                            "case when ubo.p17 is null " +
                            "then 0 " +
                            "else ubo.p17 " +
                            "end as ear_territory, " +
                            "case when ubo.p16 is null " +
                            "then 0 " +
                            "else ubo.p16 " +
                            "end as vision_equipment, " +
                            "case when ubo.p15 is null " +
                            "then 0 " +
                            "else ubo.p15 " +
                            "end as vision_path, " +
                            "case when ubo.p14 is null " +
                            "then 0 " +
                            "else ubo.p14 " +
                            "end as vision_communication, " +
                            "case when ubo.p13 is null " +
                            "then 0 " +
                            "else ubo.p13 " +
                            "end as vision_washroom, " +
                            "case when ubo.p12 is null " +
                            "then 0 " +
                            "else ubo.p12 " +
                            "end as vision_room, " +
                            "case when ubo.p11 is null " +
                            "then 0 " +
                            "else ubo.p11 " +
                            "end as vision_way, " +
                            "case when ubo.p10 is null " +
                            "then 0 " +
                            "else ubo.p10 " +
                            "end as vision_entrance, " +
                            "case when ubo.p9 is null " +
                            "then 0 " +
                            "else ubo.p9 " +
                            "end as vision_territory, " +
                            "case when ubo.p8 is null " +
                            "then 0 " +
                            "else ubo.p8 " +
                            "end as oda_equipment, " +
                            "case when ubo.p7 is null " +
                            "then 0 " +
                            "else ubo.p7 " +
                            "end as oda_path, " +
                            "case when ubo.p6 is null " +
                            "then 0 " +
                            "else ubo.p6 " +
                            "end as oda_communication, " +
                            "case when ubo.p5 is null " +
                            "then 0 " +
                            "else ubo.p5 " +
                            "end as oda_washroom, " +
                            "case when ubo.p4 is null " +
                            "then 0 " +
                            "else ubo.p4 " +
                            "end as oda_room, " +
                            "case when ubo.p3 is null " +
                            "then 0 " +
                            "else ubo.p3 " +
                            "end as oda_way, " +
                            "case when ubo.p2 is null " +
                            "then 0 " +
                            "else ubo.p2 " +
                            "end as oda_entrance, " +
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
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "        and c.ftype_sp in (2250062, 2250002) limit 1) in (2250062, 2250002) " +
                            "then 1 " +
                            "else 0 " +
                            "end as sport_gym, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "    and c.ftype_sp in (2250012, 2250015, 2250063) limit 1) in (2250012, 2250015, 2250063) " +
                            "then 1 " +
                            "else 0 " +
                            "end as cabinet_med, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "    and c.ftype_sp = 2250058 limit 1) = 2250058 " +
                            "then 1 " +
                            "else 0 " +
                            "end as cabinet_logopedist, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "    and c.ftype_sp = 2250023 limit 1) = 2250023 " +
                            "then 1 " +
                            "else 0 " +
                            "end as cabinet_defectologist, " +
                            "case when " +
                            " (select c.ftype_sp " +
                            "  from cabinets c " +
                            "  where c.building_id = ub.id_uch_buildings " +
                            "    and c.ftype_sp = 2250010 limit 1) = 2250010 " +
                            "then 1 " +
                            "else 0 " +
                            "end as cabinet_psychologist, " +
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
