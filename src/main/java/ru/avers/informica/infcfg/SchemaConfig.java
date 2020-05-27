package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SchemaConfig {
    private String m_id,
                   m_descr; 
    private SourceUch m_source;

    @XmlAttribute(required = true)
    @XmlID
    public String getId() {
        return m_id;
    }

    public void setId(String p_id) {
        this.m_id = p_id;
    }

    @XmlAttribute
    public String getDescr() {
        return m_descr;
    }
    
    public void setDescr(String p_descr) {
        m_descr = p_descr;
    }

    @XmlElement(name = "source-uch")
    public SourceUch getSource() {
        return m_source;
    }
    
    public void setSource(SourceUch p_source) {
        m_source = p_source;
    }
    
}
