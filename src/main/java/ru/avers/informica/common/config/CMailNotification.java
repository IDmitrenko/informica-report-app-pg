package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Dias
 */
class CMailNotification implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(CMailNotification.class);

    public static final String s_root_name = "mail-notification";
    private static final String s_attr_enabled = "enabled",
                                s_attr_ssl_enabled = "ssl-enabled",
                                s_attr_start_tls_enabled = "start-tls-enabled",
                                s_attr_debug = "debug",
                                s_attr_port = "port",
                                s_node_host = "host",
                                s_attr_user = "user",
                                s_attr_password = "password",
                                s_node_from = "from";
    
    private Boolean m_is_enabled = false,
                    m_is_ssl_enabled,
                    m_is_start_tls_enabled,
                    m_is_debug;
    private Integer m_port;
    private String m_host,
                   m_user,
                   m_password,
                   m_from;
    
    public CMailNotification() {
    }
    
    @XmlAttribute(name = s_attr_enabled)
    public Boolean isEnabled() {
        return m_is_enabled; 
    }
    public void setEnabled(Boolean p_val) {
        m_is_enabled = p_val; 
    }        
    
    @XmlAttribute(name = s_attr_ssl_enabled)
    public Boolean isSslEnabled() {
        return m_is_ssl_enabled;
    }
    public void setSslEnabled(Boolean p_val) {
        m_is_ssl_enabled = p_val;
    }
    
    @XmlAttribute(name = s_attr_start_tls_enabled)
    public Boolean isStartTlsEnabled() {
        return m_is_start_tls_enabled;
    }
    public void setStartTlsEnabled(Boolean p_val) {
        m_is_start_tls_enabled = p_val;
    }

    @XmlAttribute(name = s_attr_debug)
    public Boolean isDebug() {
        return m_is_debug;
    }
    public void setDebug(Boolean p_val) {
        m_is_debug = p_val;
    }

    @XmlAttribute(name = s_attr_port)
    public Integer getPort() {
        return m_port;
    }
    public void setPort(Integer p_val) {
        m_port = p_val;
    }

    @XmlElement(name = s_node_host)
    public String getHost() {
        return m_host;
    }
    public void setHost(String p_val) {
        m_host = p_val;
    }

    @XmlAttribute(name = s_attr_user)
    public String getUser() {
        return m_user;
    }
    public void setUser(String p_val) {
        m_user = p_val;
    }

    @XmlAttribute(name = s_attr_password)
    public String getPassword() {
        return m_password;
    }
    public void setPassword(String p_val) {
        m_password = p_val;
    }

    @XmlElement(name = s_node_from)
    public String getFrom() {
        return m_from;
    }
    public void setFrom(String p_val) {
        m_from = p_val;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CMailNotification [");
        builder.append("getFrom=").append(getFrom());
        builder.append(", getHost=").append(getHost());
        builder.append(", getPassword=").append(getPassword());
        builder.append(", getPort=").append(getPort());
        builder.append(", getUser=").append(getUser());
        builder.append(", isDebug=").append(isDebug());
        builder.append(", isEnabled=").append(isEnabled());
        builder.append(", isSslEnabled=").append(isSslEnabled());
        builder.append(", isStartTlsEnabled=").append(isStartTlsEnabled());
        builder.append("]");
        return builder.toString();
    }           
    
    public void set(CMailNotification p_val) {
        if (p_val == null) throw new IllegalArgumentException();
        
        setDebug(p_val.isDebug());
        setEnabled(p_val.isEnabled());
        setSslEnabled(p_val.isSslEnabled());
        setStartTlsEnabled(p_val.isStartTlsEnabled());
        setFrom(p_val.getFrom());
        setHost(p_val.getHost());
        setPassword(p_val.getPassword());
        setPort(p_val.getPort());
        setUser(p_val.getUser());
    }
    
}
