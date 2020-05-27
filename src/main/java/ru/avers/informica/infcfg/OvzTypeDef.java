package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OvzTypeDef {
    private String m_key;
    private TypeOvz m_type_ovz;
    
    @XmlAttribute
    public String getKey() {
        return m_key;
    }

    public void setKey(String p_key) {
        this.m_key = p_key;
    }

    @XmlValue
    public TypeOvz getTypeOvz() {
        return m_type_ovz;
    }

    public void setTypeOvz(TypeOvz p_type_ovz) {
        this.m_type_ovz = p_type_ovz;
    }
    
}
