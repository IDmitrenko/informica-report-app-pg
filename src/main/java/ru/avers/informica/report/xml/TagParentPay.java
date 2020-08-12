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
public class TagParentPay {

    @XmlElement(name = "crit_pay")
    protected CritPay crit_Pay;
    @XmlElement(required = true)
    protected Founders founders;
    @XmlElement(name = "max_pay", required = true)
    protected TagPay max_Pay;
    @XmlElement(name = "mid_pay", required = true)
    protected TagPay mid_Pay;
    @XmlAttribute(name = "reg")
    protected String reg;

}
