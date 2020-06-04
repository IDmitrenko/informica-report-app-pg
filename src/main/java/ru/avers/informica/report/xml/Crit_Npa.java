package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * tag crit pay
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crit_npa")
public class Crit_Npa {

    @XmlAttribute(name = "crit_npa")
    protected String crit_Npa;

    public String getCrit_Npa() {
        if (crit_Npa == null) {
            return "http://site.ru/npa.pdf";
        } else {
            return crit_Npa;
        }
    }

    public void setCrit_Npa(String value) {
        this.crit_Npa = value;
    }
}
