package ru.avers.informica.common.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Dias
 */
public class Esia {
    
    private boolean m_enabled = false;
    private String m_url;

    @XmlAttribute
    public boolean isEnabled() {
        return m_enabled;
    }

    public void setEnabled(boolean p_enabled) {
        this.m_enabled = p_enabled;
    }

    @XmlValue
    public String getUrl() {
        return m_url;
    }

    public void setUrl(String p_url) {
        this.m_url = p_url;
    }
    
}
