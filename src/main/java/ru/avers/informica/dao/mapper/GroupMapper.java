package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.GroupInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class GroupMapper implements RowMapper<GroupInf> {

    @Override
    public GroupInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupInf groupInf = new GroupInf();
        groupInf.setId(rs.getInt("id"));
/*
        groupInf.setUchCode(rs.getString("code"));
        groupInf.setIdCode("0" + groupInf.getUchCode() + "-000-" + groupInf.getId());
        groupInf.setName(rs.getString("name"));
        groupInf.setFiasHouseGuid(rs.getString("fias_house_guid"));
        groupInf.setPlainAddress(rs.getString("plain_address"));
        groupInf.setBuildingTypeArea(rs.getString("building_type_area"));
        groupInf.setTypeBuilding(rs.getString("type_building"));
        groupInf.setFilial(rs.getString("filial"));
        groupInf.setDepreciation(rs.getString("depreciation"));
        groupInf.setPool(rs.getString("pool"));
        groupInf.setEarEquipment(rs.getString("ear_equipment"));
        groupInf.setEarPath(rs.getString("ear_path"));
        groupInf.setEarCommunication(rs.getString("ear_communication"));
        groupInf.setEarWashroom(rs.getString("ear_washroom"));
        groupInf.setEarRoom(rs.getString("ear_room"));
        groupInf.setEarWay(rs.getString("ear_way"));
        groupInf.setEarEntrance(rs.getString("ear_entrance"));
        groupInf.setEarTerritory(rs.getString("ear_territory"));
        groupInf.setVisionEquipment(rs.getString("vision_equipment"));
        groupInf.setVisionPath(rs.getString("vision_path"));
        groupInf.setVisionCommunication(rs.getString("vision_communication"));
        groupInf.setVisionWashroom(rs.getString("vision_washroom"));
        groupInf.setVisionRoom(rs.getString("vision_room"));
        groupInf.setVisionWay(rs.getString("vision_way"));
        groupInf.setVisionEntrance(rs.getString("vision_entrance"));
        groupInf.setVisionTerritory(rs.getString("vision_territory"));
        groupInf.setOdaEquipment(rs.getString("oda_equipment"));
        groupInf.setOdaPath(rs.getString("oda_path"));
        groupInf.setOdaCommunication(rs.getString("oda_communication"));
        groupInf.setOdaWashroom(rs.getString("oda_washroom"));
        groupInf.setOdaRoom(rs.getString("oda_room"));
        groupInf.setOdaWay(rs.getString("oda_way"));
        groupInf.setOdaEntrance(rs.getString("oda_entrance"));
        groupInf.setOdaTerritory(rs.getString("oda_territory"));
        groupInf.setMeetingRoom(rs.getString("meeting_room"));
        groupInf.setSportGym(rs.getString("sport_gym"));
        groupInf.setCabinetMed(rs.getString("cabinet_med"));
        groupInf.setCabinetLogopedist(rs.getString("cabinet_logopedist"));
        groupInf.setCabinetDefectologist(rs.getString("cabinet_defectologist"));
        groupInf.setCabinetPsychologist(rs.getString("cabinet_psychologist"));
        groupInf.setStatusBuilding(rs.getString("status_building"));
*/

        return groupInf;
    }
}
