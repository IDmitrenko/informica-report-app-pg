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
    
    private final String value;
    
    private TypeSchemaVersion(String pVal) {
        value = pVal;
    }
    public String value() {
        return value;
    }

    public static TypeSchemaVersion fromValue(String pVal) {
        for (TypeSchemaVersion item: TypeSchemaVersion.values()) {
            if (item.value.equals(pVal)) {
                return item;
            }
        }
        throw new IllegalArgumentException(pVal);
    }
    
}
