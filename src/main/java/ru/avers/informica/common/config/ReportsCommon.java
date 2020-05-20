package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class ReportsCommon implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(ReportsCommon.class);
    
    public final static String s_root_name = "common",
                               s_attr_authority = "authority",
                               s_attr_app_descr = "app-descr";

    private String m_authority,
                   m_app_descr;
    
    public ReportsCommon() { }
    
    @XmlAttribute(name = s_attr_authority)
    public String getAuthority() {
        return m_authority;
    }

    public void setAuthority(String p_val) {
        m_authority = p_val;
    }

    @XmlAttribute(name = s_attr_app_descr)
    public String getAppDescr() {
        return m_app_descr;
    }
    
    public void setAppDescr(String p_val) {
        m_app_descr = p_val;
    }

    public final void set(ReportsCommon p_val) {
        if (p_val == null) throw new IllegalArgumentException();
        
        setAuthority(p_val.getAuthority());
        setAppDescr(p_val.getAppDescr());
    }

}
