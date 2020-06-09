package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * Целочисленные значения с разбивкой по годам
 * 
 * <p>Java class for tag_age_8 complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_age_8")
public class TagAge8 {

    @XmlAttribute(name = "y_0_1", required = true)
    protected BigInteger y_0_1;
    @XmlAttribute(name = "y_1_2", required = true)
    protected BigInteger y_1_2;
    @XmlAttribute(name = "y_2_3", required = true)
    protected BigInteger y_2_3;
    @XmlAttribute(name = "y_3_4", required = true)
    protected BigInteger y_3_4;
    @XmlAttribute(name = "y_4_5", required = true)
    protected BigInteger y_4_5;
    @XmlAttribute(name = "y_5_6", required = true)
    protected BigInteger y_5_6;
    @XmlAttribute(name = "y_6_7", required = true)
    protected BigInteger y_6_7;
    @XmlAttribute(name = "y_7_e", required = true)
    protected BigInteger y_7_E;

    public BigInteger getY_0_1() {
        return y_0_1;
    }

    public void setY_0_1(BigInteger value) {
        this.y_0_1 = value;
    }

    public BigInteger getY_1_2() {
        return y_1_2;
    }

    public void setY_1_2(BigInteger value) {
        this.y_1_2 = value;
    }

    public BigInteger getY_2_3() {
        return y_2_3;
    }

    public void setY_2_3(BigInteger value) {
        this.y_2_3 = value;
    }

    public BigInteger getY_3_4() {
        return y_3_4;
    }

    public void setY_3_4(BigInteger value) {
        this.y_3_4 = value;
    }

    public BigInteger getY_4_5() {
        return y_4_5;
    }

    public void setY_4_5(BigInteger value) {
        this.y_4_5 = value;
    }

    public BigInteger getY_5_6() {
        return y_5_6;
    }

    public void setY_5_6(BigInteger value) {
        this.y_5_6 = value;
    }

    public BigInteger getY_6_7() {
        return y_6_7;
    }

    public void setY_6_7(BigInteger value) {
        this.y_6_7 = value;
    }

    public BigInteger getY_7_E() {
        return y_7_E;
    }

    public void setY_7_E(BigInteger value) {
        this.y_7_E = value;
    }

}
