package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "")
public class OrientationDef {
    private String m_key;
    private int m_orientation;
    private Integer m_ovz_type;
    private Integer m_wellness;
    
    @XmlAttribute
    public String getKey() {
        return m_key;
    }

    public void setKey(String p_key) {
        this.m_key = p_key;
    }

    @XmlAttribute
    public int getOrientation() {
        return m_orientation;
    }

    public void setOrientation(int orientation) {
        this.m_orientation = orientation;
    }    
    
    @XmlAttribute
    public Integer getOvzType() {
        return m_ovz_type;
    }

    public void setOvzType(Integer ovzType) {
        this.m_ovz_type = ovzType;
    }

    @XmlAttribute
    public Integer getWellness() {
        return m_wellness;
    }

    public void setWellness(Integer wellness) {
        this.m_wellness = wellness;
    }
            
}
