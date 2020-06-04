package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for founders complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "founders", propOrder = {
    "founder"
})
public class Founders {

    @XmlElement(required = true)
    protected List<Founder> founder;

    public List<Founder> getFounder() {
        if (founder == null) {
            founder = new ArrayList<Founder>();
        }
        return this.founder;
    }
}
