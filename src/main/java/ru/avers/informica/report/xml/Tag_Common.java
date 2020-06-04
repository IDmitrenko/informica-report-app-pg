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
public class Tag_Common {

    @XmlElement(name = "no_doo_act", required = true)
    protected Tag_Common_Aged no_Doo_Act;
    @XmlElement(name = "no_doo_def", required = true)
    protected Tag_Common_Aged no_Doo_Def;
    @XmlElement(required = true)
    protected Tag_Common_Aged medic;
    @XmlElement(required = true)
    protected Tag_Common_Aged family;

}
