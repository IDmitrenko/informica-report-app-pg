package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Dias
 */
@XmlType(name = "TypeSchemaVersion")
@XmlEnum
public enum TypeSchemaVersion {

    @XmlEnumValue(IConst.s_schema_ver_5_0)
    tFiveDotO(IConst.s_schema_ver_5_0);
    
    private final String m_value;
    
    private TypeSchemaVersion(String p_val) {
        m_value = p_val; 
    }
    public String value() {
        return m_value;
    }

    public static TypeSchemaVersion fromValue(String p_val) {
        for (TypeSchemaVersion x_item: TypeSchemaVersion.values()) {
            if (x_item.m_value.equals(p_val)) {
                return x_item;
            }
        }
        throw new IllegalArgumentException(p_val);
    }
    
}
