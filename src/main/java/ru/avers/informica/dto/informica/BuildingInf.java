package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация для тэга building
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class BuildingInf {
    private int id;            // id здания

    private String idCode,     // код здания (id)
            name,              // наименование (name)
            fiasHouseGuid,     // юридический адрес через код здания ФИАС (fias_house_guid)
            plainAddress,      // юридический адрес по правилам КЛАДР (plain_address)
            buildingTypeArea,  // тип местности (1 - город, 2 - сельская) (building_type_area)
            typeBuilding,      // код типа собственности (type_building)
            filial,            // признак филиала (filial)
            depreciation,      // ... (depreciation)
            pool,              // ... (pool)
            earEquipment,      // ... (ear_equipment)
            earPath,           // ... (ear_path)
            earCommunication,  // ... (ear_communication)
            earWashroom,       // ... (ear_washroom)
            earRoom,           // ... (ear_room)
            earWay,            // ... (ear_way)
            earEntrance,       // ... (ear_entrance)
            earTerritory,      // ... (ear_territory)
            visionEquipment,      // ... (vision_equipment)
            visionPath,           // ... (vision_path)
            visionCommunication,  // ... (vision_communication)
            visionWashroom,       // ... (vision_washroom)
            visionRoom,           // ... (vision_room)
            visionWay,            // ... (vision_way)
            visionEntrance,       // ... (vision_entrance)
            visionTerritory,      // ... (vision_territory)
            odaEquipment,      // ... (oda_equipment)
            odaPath,           // ... (oda_path)
            odaCommunication,  // ... (oda_communication)
            odaWashroom,       // ... (oda_washroom)
            odaRoom,           // ... (oda_room)
            odaWay,            // ... (oda_way)
            odaEntrance,       // ... (oda_entrance)
            odaTerritory,      // ... (oda_territory)
            meetingRoom,       // ... (meeting_room)
            sportGym,          // ... (sport_gym)
            cabinetMed,             // ... (cabinet_med)
            cabinetLogopedist,      // ... (cabinet_logopedist)
            cabinetDefectologist,   // ... (cabinet_defectologist)
            cabinetPsychologist,    // ... (cabinet_psychologist)
            statusBuilding;         // статус здания (status_building)

}
