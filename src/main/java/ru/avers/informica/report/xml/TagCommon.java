package ru.avers.informica.report.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tag_common complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_common", propOrder = {
    "no_Doo_Act",
    "no_Doo_Def",
    "medic",
    "family"
})
@Getter
@Setter
public class TagCommon {

    @XmlElement(name = "no_doo_act", required = true)
    protected TagCommonAged no_Doo_Act;
    @XmlElement(name = "no_doo_def", required = true)
    protected TagCommonAged no_Doo_Def;
    @XmlElement(required = true)
    protected TagCommonAged medic;
    @XmlElement(required = true)
    protected TagCommonAged family;

}
