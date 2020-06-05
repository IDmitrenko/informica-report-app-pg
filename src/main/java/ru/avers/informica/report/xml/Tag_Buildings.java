package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for tag_buildings complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_buildings", propOrder = {
    "building"
})
public class Tag_Buildings {

    @XmlElement(required = true)
    protected List<Tag_Single_Building> building;

    public List<Tag_Single_Building> getBuilding() {
        if (building == null) {
            building = new ArrayList<Tag_Single_Building>();
        }
        return this.building;
    }

}
