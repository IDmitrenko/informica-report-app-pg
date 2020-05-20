package ru.avers.informica.common.config;

import javax.xml.bind.annotation.XmlAttribute;

public class CalcAgeElementTer extends CalcAgeElement {

    private String terCode;

    @XmlAttribute(name = "ter-code")
    public String getTerCode() {
        return terCode;
    }

    public void setTerCode(String terCode) {
        this.terCode = terCode;
    }

    @Override
    public String toString() {
        return "CalcAgeElementTer{" +
                "terCode='" + terCode + '\'' +
                '}';
    }
}
