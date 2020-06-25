package ru.avers.informica.common.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Dias
 */
public class Esia {
    
    private boolean enabled = false;
    private String url;

    @XmlAttribute
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean pEnabled) {
        this.enabled = pEnabled;
    }

    @XmlValue
    public String getUrl() {
        return url;
    }

    public void setUrl(String pUrl) {
        this.url = pUrl;
    }
    
}
