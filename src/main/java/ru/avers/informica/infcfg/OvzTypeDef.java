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
    private String key;
    private TypeOvz typeOvz;
    
    @XmlAttribute
    public String getKey() {
        return key;
    }

    public void setKey(String pKey) {
        this.key = pKey;
    }

    @XmlValue
    public TypeOvz getTypeOvz() {
        return typeOvz;
    }

    public void setTypeOvz(TypeOvz pTypeOvz) {
        this.typeOvz = pTypeOvz;
    }
    
}
