package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 				Описание тега building
 * 				Он содержит в себе вложенные теги group
 * 				а также набор атрибутов id, name, fias_house_guid, plain_address, building_type_area, type_building, filial, deprecation
 * 				Все атрибуты обязательны, наличие хотя бы одной группы - тоже
 * 			
 * 
 * <p>Java class for tag_single_building complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_single_building", propOrder = {
    "group"
})
public class Tag_Single_Building {

    @XmlElement(required = true)
    protected List<Tag_Single_Group> group;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "fias_house_guid", required = true)
    protected String fias_House_Guid;
    @XmlAttribute(name = "plain_address", required = true)
    protected String plain_Address;
    @XmlAttribute(name = "building_type_area", required = true)
    protected String building_Type_Area;
    @XmlAttribute(name = "type_building", required = true)
    protected String type_Building;
    @XmlAttribute(name = "filial", required = true)
    protected String filial;
    @XmlAttribute(name = "depreciation", required = true)
    protected int depreciation;
    @XmlAttribute(name = "cabinet_psychologist")
    protected String cabinet_Psychologist;
    @XmlAttribute(name = "cabinet_defectologist")
    protected String cabinet_Defectologist;
    @XmlAttribute(name = "cabinet_logopedist")
    protected String cabinet_Logopedist;
    @XmlAttribute(name = "cabinet_med")
    protected String cabinet_Med;
    @XmlAttribute(name = "sport_gym")
    protected String sport_Gym;
    @XmlAttribute(name = "meeting_room")
    protected String meeting_Room;
    @XmlAttribute(name = "pool")
    protected String pool;
    @XmlAttribute(name = "oda_territory")
    protected String oda_Territory;
    @XmlAttribute(name = "oda_entrance")
    protected String oda_Entrance;
    @XmlAttribute(name = "oda_way")
    protected String oda_Way;
    @XmlAttribute(name = "oda_room")
    protected String oda_Room;
    @XmlAttribute(name = "oda_washroom")
    protected String oda_Washroom;
    @XmlAttribute(name = "oda_communication")
    protected String oda_Communication;
    @XmlAttribute(name = "oda_path")
    protected String oda_Path;
    @XmlAttribute(name = "oda_equipment")
    protected String oda_Equipment;
    @XmlAttribute(name = "vision_territory")
    protected String vision_Territory;
    @XmlAttribute(name = "vision_entrance")
    protected String vision_Entrance;
    @XmlAttribute(name = "vision_way")
    protected String vision_Way;
    @XmlAttribute(name = "vision_room")
    protected String vision_Room;
    @XmlAttribute(name = "vision_washroom")
    protected String vision_Washroom;
    @XmlAttribute(name = "vision_communication")
    protected String vision_Communication;
    @XmlAttribute(name = "vision_path")
    protected String vision_Path;
    @XmlAttribute(name = "vision_equipment")
    protected String vision_Equipment;
    @XmlAttribute(name = "ear_territory")
    protected String ear_Territory;
    @XmlAttribute(name = "ear_entrance")
    protected String ear_Entrance;
    @XmlAttribute(name = "ear_way")
    protected String ear_Way;
    @XmlAttribute(name = "ear_room")
    protected String ear_Room;
    @XmlAttribute(name = "ear_washroom")
    protected String ear_Washroom;
    @XmlAttribute(name = "ear_communication")
    protected String ear_Communication;
    @XmlAttribute(name = "ear_path")
    protected String ear_Path;
    @XmlAttribute(name = "ear_equipment")
    protected String ear_Equipment;

    public List<Tag_Single_Group> getGroup() {
        if (group == null) {
            group = new ArrayList<Tag_Single_Group>();
        }
        return this.group;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getFias_House_Guid() {
        return fias_House_Guid;
    }

    public void setFias_House_Guid(String value) {
        this.fias_House_Guid = value;
    }

    public String getPlain_Address() {
        return plain_Address;
    }

    public void setPlain_Address(String value) {
        this.plain_Address = value;
    }

    public String getBuilding_Type_Area() {
        return building_Type_Area;
    }

    public void setBuilding_Type_Area(String value) {
        this.building_Type_Area = value;
    }

    public String getType_Building() {
        return type_Building;
    }

    public void setType_Building(String value) {
        this.type_Building = value;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String value) {
        this.filial = value;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int value) {
        this.depreciation = value;
    }

    public String getCabinet_Psychologist() {
        if (cabinet_Psychologist == null) {
            return "0";
        } else {
            return cabinet_Psychologist;
        }
    }

    public void setCabinet_Psychologist(String value) {
        this.cabinet_Psychologist = value;
    }

    public String getCabinet_Defectologist() {
        if (cabinet_Defectologist == null) {
            return "0";
        } else {
            return cabinet_Defectologist;
        }
    }

    public void setCabinet_Defectologist(String value) {
        this.cabinet_Defectologist = value;
    }

    public String getCabinet_Logopedist() {
        if (cabinet_Logopedist == null) {
            return "0";
        } else {
            return cabinet_Logopedist;
        }
    }

    public void setCabinet_Logopedist(String value) {
        this.cabinet_Logopedist = value;
    }

    public String getCabinet_Med() {
        if (cabinet_Med == null) {
            return "0";
        } else {
            return cabinet_Med;
        }
    }

    public void setCabinet_Med(String value) {
        this.cabinet_Med = value;
    }

    public String getSport_Gym() {
        if (sport_Gym == null) {
            return "0";
        } else {
            return sport_Gym;
        }
    }

    public void setSport_Gym(String value) {
        this.sport_Gym = value;
    }

    public String getMeeting_Room() {
        if (meeting_Room == null) {
            return "0";
        } else {
            return meeting_Room;
        }
    }

    public void setMeeting_Room(String value) {
        this.meeting_Room = value;
    }

    public String getPool() {
        if (pool == null) {
            return "0";
        } else {
            return pool;
        }
    }

    public void setPool(String value) {
        this.pool = value;
    }

    public String getOda_Territory() {
        if (oda_Territory == null) {
            return "0";
        } else {
            return oda_Territory;
        }
    }

    public void setOda_Territory(String value) {
        this.oda_Territory = value;
    }

    public String getOda_Entrance() {
        if (oda_Entrance == null) {
            return "0";
        } else {
            return oda_Entrance;
        }
    }

    public void setOda_Entrance(String value) {
        this.oda_Entrance = value;
    }

    public String getOda_Way() {
        if (oda_Way == null) {
            return "0";
        } else {
            return oda_Way;
        }
    }

    public void setOda_Way(String value) {
        this.oda_Way = value;
    }

    public String getOda_Room() {
        if (oda_Room == null) {
            return "0";
        } else {
            return oda_Room;
        }
    }

    public void setOda_Room(String value) {
        this.oda_Room = value;
    }

    public String getOda_Washroom() {
        if (oda_Washroom == null) {
            return "0";
        } else {
            return oda_Washroom;
        }
    }

    public void setOda_Washroom(String value) {
        this.oda_Washroom = value;
    }

    public String getOda_Communication() {
        if (oda_Communication == null) {
            return "0";
        } else {
            return oda_Communication;
        }
    }

    public void setOda_Communication(String value) {
        this.oda_Communication = value;
    }

    public String getOda_Path() {
        if (oda_Path == null) {
            return "0";
        } else {
            return oda_Path;
        }
    }

    public void setOda_Path(String value) {
        this.oda_Path = value;
    }

    public String getOda_Equipment() {
        if (oda_Equipment == null) {
            return "0";
        } else {
            return oda_Equipment;
        }
    }

    public void setOda_Equipment(String value) {
        this.oda_Equipment = value;
    }

    public String getVision_Territory() {
        if (vision_Territory == null) {
            return "0";
        } else {
            return vision_Territory;
        }
    }

    public void setVision_Territory(String value) {
        this.vision_Territory = value;
    }

    public String getVision_Entrance() {
        if (vision_Entrance == null) {
            return "0";
        } else {
            return vision_Entrance;
        }
    }

    public void setVision_Entrance(String value) {
        this.vision_Entrance = value;
    }

    public String getVision_Way() {
        if (vision_Way == null) {
            return "0";
        } else {
            return vision_Way;
        }
    }

    public void setVision_Way(String value) {
        this.vision_Way = value;
    }

    public String getVision_Room() {
        if (vision_Room == null) {
            return "0";
        } else {
            return vision_Room;
        }
    }

    public void setVision_Room(String value) {
        this.vision_Room = value;
    }

    public String getVision_Washroom() {
        if (vision_Washroom == null) {
            return "0";
        } else {
            return vision_Washroom;
        }
    }

    public void setVision_Washroom(String value) {
        this.vision_Washroom = value;
    }

    public String getVision_Communication() {
        if (vision_Communication == null) {
            return "0";
        } else {
            return vision_Communication;
        }
    }

    public void setVision_Communication(String value) {
        this.vision_Communication = value;
    }

    public String getVision_Path() {
        if (vision_Path == null) {
            return "0";
        } else {
            return vision_Path;
        }
    }

    public void setVision_Path(String value) {
        this.vision_Path = value;
    }

    public String getVision_Equipment() {
        if (vision_Equipment == null) {
            return "0";
        } else {
            return vision_Equipment;
        }
    }

    public void setVision_Equipment(String value) {
        this.vision_Equipment = value;
    }

    public String getEar_Territory() {
        if (ear_Territory == null) {
            return "0";
        } else {
            return ear_Territory;
        }
    }

    public void setEar_Territory(String value) {
        this.ear_Territory = value;
    }

    public String getEar_Entrance() {
        if (ear_Entrance == null) {
            return "0";
        } else {
            return ear_Entrance;
        }
    }

    public void setEar_Entrance(String value) {
        this.ear_Entrance = value;
    }

    public String getEar_Way() {
        if (ear_Way == null) {
            return "0";
        } else {
            return ear_Way;
        }
    }

    public void setEar_Way(String value) {
        this.ear_Way = value;
    }

    public String getEar_Room() {
        if (ear_Room == null) {
            return "0";
        } else {
            return ear_Room;
        }
    }

    public void setEar_Room(String value) {
        this.ear_Room = value;
    }

    public String getEar_Washroom() {
        if (ear_Washroom == null) {
            return "0";
        } else {
            return ear_Washroom;
        }
    }

    public void setEar_Washroom(String value) {
        this.ear_Washroom = value;
    }

    public String getEar_Communication() {
        if (ear_Communication == null) {
            return "0";
        } else {
            return ear_Communication;
        }
    }

    public void setEar_Communication(String value) {
        this.ear_Communication = value;
    }

    public String getEar_Path() {
        if (ear_Path == null) {
            return "0";
        } else {
            return ear_Path;
        }
    }

    public void setEar_Path(String value) {
        this.ear_Path = value;
    }

    public String getEar_Equipment() {
        if (ear_Equipment == null) {
            return "0";
        } else {
            return ear_Equipment;
        }
    }

    public void setEar_Equipment(String value) {
        this.ear_Equipment = value;
    }

}
