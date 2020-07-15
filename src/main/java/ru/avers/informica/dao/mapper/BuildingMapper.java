package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.impl.GroupDaoImpl;
import ru.avers.informica.dto.informica.BuildingInf;
import ru.avers.informica.report.ReportSetting;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class BuildingMapper implements RowMapper<BuildingInf> {

    private final GroupDaoImpl groupDao;
    private final ReportSetting reportSetting;

    @Override
    public BuildingInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        BuildingInf buildingInf = new BuildingInf();
        buildingInf.setId(rs.getInt("id"));
        buildingInf.setUchCode(rs.getString("code"));
        buildingInf.setIdCode("0" + buildingInf.getUchCode() + "-000-" + buildingInf.getId());
        buildingInf.setName(rs.getString("name"));
        buildingInf.setFiasHouseGuid(rs.getString("fias_house_guid"));
        buildingInf.setPlainAddress(rs.getString("plain_address"));
        buildingInf.setBuildingTypeArea(rs.getString("building_type_area"));
        buildingInf.setTypeBuilding(rs.getString("type_building"));
        buildingInf.setFilial(rs.getString("filial"));
        buildingInf.setDepreciation(rs.getString("depreciation"));
        buildingInf.setPool(rs.getString("pool"));
        buildingInf.setEarEquipment(rs.getString("ear_equipment"));
        buildingInf.setEarPath(rs.getString("ear_path"));
        buildingInf.setEarCommunication(rs.getString("ear_communication"));
        buildingInf.setEarWashroom(rs.getString("ear_washroom"));
        buildingInf.setEarRoom(rs.getString("ear_room"));
        buildingInf.setEarWay(rs.getString("ear_way"));
        buildingInf.setEarEntrance(rs.getString("ear_entrance"));
        buildingInf.setEarTerritory(rs.getString("ear_territory"));
        buildingInf.setVisionEquipment(rs.getString("vision_equipment"));
        buildingInf.setVisionPath(rs.getString("vision_path"));
        buildingInf.setVisionCommunication(rs.getString("vision_communication"));
        buildingInf.setVisionWashroom(rs.getString("vision_washroom"));
        buildingInf.setVisionRoom(rs.getString("vision_room"));
        buildingInf.setVisionWay(rs.getString("vision_way"));
        buildingInf.setVisionEntrance(rs.getString("vision_entrance"));
        buildingInf.setVisionTerritory(rs.getString("vision_territory"));
        buildingInf.setOdaEquipment(rs.getString("oda_equipment"));
        buildingInf.setOdaPath(rs.getString("oda_path"));
        buildingInf.setOdaCommunication(rs.getString("oda_communication"));
        buildingInf.setOdaWashroom(rs.getString("oda_washroom"));
        buildingInf.setOdaRoom(rs.getString("oda_room"));
        buildingInf.setOdaWay(rs.getString("oda_way"));
        buildingInf.setOdaEntrance(rs.getString("oda_entrance"));
        buildingInf.setOdaTerritory(rs.getString("oda_territory"));
        buildingInf.setMeetingRoom(rs.getString("meeting_room"));
        buildingInf.setSportGym(rs.getString("sport_gym"));
        buildingInf.setCabinetMed(rs.getString("cabinet_med"));
        buildingInf.setCabinetLogopedist(rs.getString("cabinet_logopedist"));
        buildingInf.setCabinetDefectologist(rs.getString("cabinet_defectologist"));
        buildingInf.setCabinetPsychologist(rs.getString("cabinet_psychologist"));
        buildingInf.setStatusBuilding(rs.getString("status_building"));
        buildingInf.setGroupInfs(groupDao.getGroupsBuildingUch(buildingInf.getId(), reportSetting.getCurrEducYear()));
        return buildingInf;
    }
}
