package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Dias
 */
@XmlType(name = "")
@XmlEnum
public enum TypeInstall {
    @XmlEnumValue(IConst.s_install_type_region)
    install_type_region(IConst.s_install_type_region),
    
    @XmlEnumValue(IConst.s_install_type_municipal)
    install_type_municipal(IConst.s_install_type_municipal);
    
    private final String m_value;

    private TypeInstall(String p_value) {
        m_value = p_value;
    }

    public String value() { return m_value; }

    public static TypeInstall fromValue(String p_val) {
        for (TypeInstall x_item : TypeInstall.values()) {
            if (x_item.m_value.equals(p_val)) {
                return x_item;
            }
        }
        throw new IllegalArgumentException(p_val);
    }
}
