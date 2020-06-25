package ru.avers.informica.common.config;


import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class Defaults implements IDTO {
    public final static String S_ROOT_NAME = "defaults",
                               S_ATTR_TIMEZONE = "timezone",
                               S_ATTR_THEME_ID = "theme-id";
    
    private String timezone, themeId;
    
    public Defaults() {
    }

    @XmlAttribute(name = S_ATTR_TIMEZONE)
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String pVal) {
        timezone = pVal;
    }

    @XmlAttribute(name = S_ATTR_THEME_ID)
    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String pVal) {
        themeId = pVal;
    }    
    
    public final void set(Defaults pVal) {
        if (pVal == null) throw new IllegalArgumentException();
        
        setTimezone(pVal.getTimezone());
        setThemeId(pVal.getThemeId());
    }        
    
}
