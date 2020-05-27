package ru.avers.informica.common.config;


import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class Defaults implements IDTO {
    public final static String s_root_name = "defaults",
                               s_attr_timezone = "timezone",
                               s_attr_theme_id = "theme-id";
    
    private String m_timezone, m_theme_id;
    
    public Defaults() {
    }

    @XmlAttribute(name = s_attr_timezone)
    public String getTimezone() {
        return m_timezone;
    }

    public void setTimezone(String p_val) {
        m_timezone = p_val;
    }

    @XmlAttribute(name = s_attr_theme_id)
    public String getThemeId() {
        return m_theme_id;
    }

    public void setThemeId(String p_val) {
        m_theme_id = p_val;
    }    
    
    public final void set(Defaults p_val) {
        if (p_val == null) throw new IllegalArgumentException();
        
        setTimezone(p_val.getTimezone());
        setThemeId(p_val.getThemeId());
    }        
    
}
