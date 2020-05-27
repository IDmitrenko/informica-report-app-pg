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
public enum TypeOvz {
    
    @XmlEnumValue(IConst.s_ovz_no_ovz_groups)
    no_ovz_groups(IConst.s_ovz_no_ovz_groups),
    @XmlEnumValue(IConst.s_ovz_sluha)
    sluha(IConst.s_ovz_sluha),
    @XmlEnumValue(IConst.s_ovz_zrenia)
    zrenia(IConst.s_ovz_zrenia),
    @XmlEnumValue(IConst.s_ovz_rechi)
    rechi(IConst.s_ovz_rechi),
    @XmlEnumValue(IConst.s_ovz_oda)
    oda(IConst.s_ovz_oda),
    @XmlEnumValue(IConst.s_ovz_psihich_razv)
    psi_razv(IConst.s_ovz_psihich_razv),
    @XmlEnumValue(IConst.s_ovz_intellect)
    um_otstalost(IConst.s_ovz_intellect),
    @XmlEnumValue(IConst.s_ovz_sl_defect)
    sl_defect(IConst.s_ovz_sl_defect),
    @XmlEnumValue(IConst.s_ovz_other)
    other(IConst.s_ovz_other),
    @XmlEnumValue(IConst.s_ovz_combined)
    combined(IConst.s_ovz_combined);
            
    private final String m_value;

    private TypeOvz(String p_value) {
        m_value = p_value;
    }

    public String value() { return m_value; }

    public static TypeOvz fromValue(String p_val) {
        for(TypeOvz x_item: TypeOvz.values())
            if(x_item.m_value.equals(p_val)) return x_item;
        throw new IllegalArgumentException(p_val);
    }
    
}
