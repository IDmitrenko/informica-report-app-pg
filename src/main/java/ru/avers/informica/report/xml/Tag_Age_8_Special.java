package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Десятичные значения с разбивкой по годам (или прочерк, если значение отсутствует)
 * 
 * <p>Java class for tag_age_8_special complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_age_8_special")
public class Tag_Age_8_Special {

    @XmlAttribute(name = "y_0_1", required = true)
    protected String y_0_1;
    @XmlAttribute(name = "y_1_2", required = true)
    protected String y_1_2;
    @XmlAttribute(name = "y_2_3", required = true)
    protected String y_2_3;
    @XmlAttribute(name = "y_3_4", required = true)
    protected String y_3_4;
    @XmlAttribute(name = "y_4_5", required = true)
    protected String y_4_5;
    @XmlAttribute(name = "y_5_6", required = true)
    protected String y_5_6;
    @XmlAttribute(name = "y_6_7", required = true)
    protected String y_6_7;
    @XmlAttribute(name = "y_7_e", required = true)
    protected String y_7_E;

    public String getY_0_1() {
        return y_0_1;
    }

    public void setY_0_1(String value) {
        this.y_0_1 = value;
    }

    public String getY_1_2() {
        return y_1_2;
    }

    public void setY_1_2(String value) {
        this.y_1_2 = value;
    }

    public String getY_2_3() {
        return y_2_3;
    }

    public void setY_2_3(String value) {
        this.y_2_3 = value;
    }

    public String getY_3_4() {
        return y_3_4;
    }

    public void setY_3_4(String value) {
        this.y_3_4 = value;
    }

    public String getY_4_5() {
        return y_4_5;
    }

    public void setY_4_5(String value) {
        this.y_4_5 = value;
    }

    public String getY_5_6() {
        return y_5_6;
    }

    public void setY_5_6(String value) {
        this.y_5_6 = value;
    }

    public String getY_6_7() {
        return y_6_7;
    }

    public void setY_6_7(String value) {
        this.y_6_7 = value;
    }

    public String getY_7_E() {
        return y_7_E;
    }

    public void setY_7_E(String value) {
        this.y_7_E = value;
    }

}
