package ru.avers.informica.report.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * Данные о количестве детей, стоящих на учете в связи с отсутствием ДОО,
 * не посещающими ДОО по медицинским показаниям,
 * а также получающим дошкольное образование в форме семейного образования
 * в разрезе возрастов от 0 до 3 лет (атрибут age_0_3)
 * и от 3 до 7 лет (атрибут age_3_7)
 *
 * <p>Java class for tag_common_aged complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_common_aged")
@Getter
@Setter
public class Tag_Common_Aged {

    @XmlAttribute(name = "age_0_3", required = true)
    protected BigInteger age_0_3;
    @XmlAttribute(name = "age_3_7", required = true)
    protected BigInteger age_3_7;

}
