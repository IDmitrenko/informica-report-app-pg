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
    private String key;
    private int orientation;
    private Integer ovzType;
    private Integer wellness;
    
    @XmlAttribute
    public String getKey() {
        return key;
    }

    public void setKey(String pKey) {
        this.key = pKey;
    }

    @XmlAttribute
    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int pOrientation) {
        this.orientation = pOrientation;
    }    
    
    @XmlAttribute
    public Integer getOvzType() {
        return ovzType;
    }

    public void setOvzType(Integer pOvzType) {
        this.ovzType = pOvzType;
    }

    @XmlAttribute
    public Integer getWellness() {
        return wellness;
    }

    public void setWellness(Integer pWellness) {
        this.wellness = pWellness;
    }
            
}
