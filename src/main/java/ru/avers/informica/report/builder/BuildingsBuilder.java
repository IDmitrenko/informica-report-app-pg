package ru.avers.informica.report.builder;

import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.BuildingInf;
import ru.avers.informica.dto.informica.GroupInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.report.xml.TagBuildings;
import ru.avers.informica.report.xml.TagSingleBuilding;
import ru.avers.informica.report.xml.TagSingleGroup;

@Component
public class BuildingsBuilder {
    public TagBuildings build(UchInf uchInf) {

        TagBuildings buildings = new TagBuildings();

        for (BuildingInf building : uchInf.getBuildingInfs()) {
            TagSingleBuilding sb = new TagSingleBuilding();

            sb.setId(building.getIdCode());
            sb.setName(building.getName());
            sb.setFias_House_Guid(building.getFiasHouseGuid());
            sb.setPlain_Address(building.getPlainAddress());
            sb.setBuilding_Type_Area(building.getBuildingTypeArea());
            sb.setType_Building(building.getTypeBuilding());
            sb.setFilial(building.getFilial());
            sb.setDepreciation(building.getDepreciation());
            sb.setPool(building.getPool());
            sb.setEar_Equipment(building.getEarEquipment());
            sb.setEar_Path(building.getEarPath());
            sb.setEar_Communication(building.getEarCommunication());
            sb.setEar_Washroom(building.getEarWashroom());
            sb.setEar_Room(building.getEarRoom());
            sb.setEar_Way(building.getEarWay());
            sb.setEar_Entrance(building.getEarEntrance());
            sb.setEar_Territory(building.getEarTerritory());
            sb.setVision_Equipment(building.getVisionEquipment());
            sb.setVision_Path(building.getVisionPath());
            sb.setVision_Communication(building.getVisionCommunication());
            sb.setVision_Washroom(building.getVisionWashroom());
            sb.setVision_Room(building.getVisionRoom());
            sb.setVision_Way(building.getVisionWay());
            sb.setVision_Entrance(building.getVisionEntrance());
            sb.setVision_Territory(building.getVisionTerritory());
            sb.setOda_Equipment(building.getOdaEquipment());
            sb.setOda_Path(building.getOdaPath());
            sb.setOda_Communication(building.getOdaCommunication());
            sb.setOda_Washroom(building.getOdaWashroom());
            sb.setOda_Room(building.getOdaRoom());
            sb.setOda_Way(building.getOdaWay());
            sb.setOda_Entrance(building.getOdaEntrance());
            sb.setOda_Territory(building.getOdaTerritory());
            sb.setMeeting_Room(building.getMeetingRoom());
            sb.setSport_Gym(building.getSportGym());
            sb.setCabinet_Med(building.getCabinetMed());
            sb.setCabinet_Logopedist(building.getCabinetLogopedist());
            sb.setCabinet_Defectologist(building.getCabinetDefectologist());
            sb.setCabinet_Psychologist(building.getCabinetPsychologist());
            sb.setStatus_building(building.getStatusBuilding());

            for (GroupInf group : building.getGroupInfs()) {

                TagSingleGroup sg = groupBuilder(group);

                sb.getGroup().add(sg);
            }

            buildings.getBuilding().add(sb);
        }

        return buildings;
    }

    private TagSingleGroup groupBuilder(GroupInf group) {
        TagSingleGroup sg = new TagSingleGroup();

        sg.setId(group.getIdCode());
        sg.setName(group.getName());
        sg.setAge_From(group.getAgeFrom().toString());
        sg.setAge_To(group.getAgeTO().toString());
        sg.setOrientation(group.getOrientation());
        sg.setWorktime_Group(group.getWorktimeGroup());
        sg.setActivity(group.getActivity());
        sg.setCapacity(group.getCapacity());
        sg.setEnrolled(group.getEnrolled());
        sg.setSubgroup(group.getSubgroup());
        sg.setOvz_Deti(group.getOvzDeti());
        sg.setFree_Space(group.getFreeSpace());
        sg.setAdd_Cont(group.getAddCont());
        sg.setTransfer_Space(group.getTransferSpace());
        sg.setPartner_Group(group.getPartnerGroup());
        sg.setPartner(group.getPartner());
        sg.setDays(group.getDays());
        sg.setEducator(group.getEducator());
        sg.setInvalid(group.getInvalid());
        sg.setSize(group.getSize());
        sg.setProgram(group.getProgram());
        sg.setReduction_Other(group.getReductionOther());
        sg.setReduction_School(group.getReductionSchool());
        sg.setAdd_Cont_Ovz(group.getAddContOvz());
        sg.setAdd_Cont_Gkp(group.getAddContGkp());
        sg.setEnrolled_Gkp(group.getEnrolledGkp());
        sg.setCapacity_Gkp(group.getCapacityGkp());
        sg.setProgram_Ovz(group.getProgramOvz());
        if (group.getOvzType() != null) {
            sg.setOvz_Type(group.getOvzType());
        }
        if (group.getOvzTypeDop() != null) {
            sg.setOvz_Type_Dop(group.getOvzTypeDop());
        }
        if (group.getOvzTypeNew() != null) {
            sg.setOvz_Type_New(group.getOvzTypeNew());
        }
        if (group.getWellness() != null) {
            sg.setWellness(group.getWellness());
        }

        return sg;
    }
}
