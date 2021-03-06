package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * Целочисленное значение - сумма по всем возрастам
 * 
 * <p>Java class for tag_age_1 complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_age_1")
public class TagAge1 {

    @XmlAttribute(name = "all", required = true)
    protected int all;

    public int getAll() {
        return all;
    }

    public void setAll(int value) {
        this.all = value;
    }

}
