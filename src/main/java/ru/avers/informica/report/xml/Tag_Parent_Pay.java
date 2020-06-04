package ru.avers.informica.report.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;


/**
 *  tag parent_pay
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_parent_pay", propOrder = {
    "crit_Pay",
    "founders",
    "max_Pay",
    "mid_Pay"
})
@Getter
@Setter
public class Tag_Parent_Pay {

    @XmlElement(name = "crit_pay")
    protected Crit_Npa crit_Pay;
    @XmlElement(required = true)
    protected Founders founders;
    @XmlElement(name = "max_pay", required = true)
    protected Tag_Pay max_Pay;
    @XmlElement(name = "mid_pay", required = true)
    protected Tag_Pay mid_Pay;
    @XmlAttribute(name = "reg")
    protected String reg;

}
