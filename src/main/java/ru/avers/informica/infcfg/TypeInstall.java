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
    
    private final String value;

    private TypeInstall(String pValue) {
        value = pValue;
    }

    public String value() {
        return value;
    }

    public static TypeInstall fromValue(String pVal) {
        for (TypeInstall item : TypeInstall.values()) {
            if (item.value.equals(pVal)) {
                return item;
            }
        }
        throw new IllegalArgumentException(pVal);
    }
}
