package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SchemaConfig {
    private String id,
                   descr;
    private SourceUch source;

    @XmlAttribute(required = true)
    @XmlID
    public String getId() {
        return id;
    }

    public void setId(String pId) {
        this.id = pId;
    }

    @XmlAttribute
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String pDescr) {
        descr = pDescr;
    }

    @XmlElement(name = "source-uch")
    public SourceUch getSource() {
        return source;
    }
    
    public void setSource(SourceUch pSource) {
        source = pSource;
    }
    
}
