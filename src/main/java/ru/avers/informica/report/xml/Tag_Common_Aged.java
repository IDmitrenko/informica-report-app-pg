package ru.avers.informica.report.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * ������ � ���������� �����, ������� �� ����� � ����� � ����������� ���,
 * �� ����������� ��� �� ����������� ����������,
 * � ����� ���������� ���������� ����������� � ����� ��������� �����������
 * � ������� ��������� �� 0 �� 3 ��� (������� age_0_3)
 * � �� 3 �� 7 ��� (������� age_3_7)
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
