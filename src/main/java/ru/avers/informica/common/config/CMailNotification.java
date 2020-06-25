package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Dias
 */
class CMailNotification implements IDTO {

    public static final String S_ROOT_NAME = "mail-notification";
    private static final String S_ATTR_ENABLED = "enabled",
                                S_ATTR_SSL_ENABLED = "ssl-enabled",
                                S_ATTR_START_TLS_ENABLED = "start-tls-enabled",
                                S_ATTR_DEBUG = "debug",
                                S_ATTR_PORT = "port",
                                S_NODE_HOST = "host",
                                S_ATTR_USER = "user",
                                S_ATTR_PASSWORD = "password",
                                S_NODE_FROM = "from";
    
    private Boolean isEnabled = false,
            isSslEnabled,
            isStartTlsEnabled,
                    isDebug;
    private Integer port;
    private String host,
            user,
            password,
                   from;
    
    public CMailNotification() {
    }
    
    @XmlAttribute(name = S_ATTR_ENABLED)
    public Boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(Boolean pVal) {
        isEnabled = pVal;
    }        
    
    @XmlAttribute(name = S_ATTR_SSL_ENABLED)
    public Boolean isSslEnabled() {
        return isSslEnabled;
    }
    public void setSslEnabled(Boolean pVal) {
        isSslEnabled = pVal;
    }
    
    @XmlAttribute(name = S_ATTR_START_TLS_ENABLED)
    public Boolean isStartTlsEnabled() {
        return isStartTlsEnabled;
    }
    public void setStartTlsEnabled(Boolean pVal) {
        isStartTlsEnabled = pVal;
    }

    @XmlAttribute(name = S_ATTR_DEBUG)
    public Boolean isDebug() {
        return isDebug;
    }
    public void setDebug(Boolean pVal) {
        isDebug = pVal;
    }

    @XmlAttribute(name = S_ATTR_PORT)
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer pVal) {
        port = pVal;
    }

    @XmlElement(name = S_NODE_HOST)
    public String getHost() {
        return host;
    }
    public void setHost(String pVal) {
        host = pVal;
    }

    @XmlAttribute(name = S_ATTR_USER)
    public String getUser() {
        return user;
    }
    public void setUser(String pVal) {
        user = pVal;
    }

    @XmlAttribute(name = S_ATTR_PASSWORD)
    public String getPassword() {
        return password;
    }
    public void setPassword(String pVal) {
        password = pVal;
    }

    @XmlElement(name = S_NODE_FROM)
    public String getFrom() {
        return from;
    }
    public void setFrom(String pVal) {
        from = pVal;
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
    
    public void set(CMailNotification pVal) {
        if (pVal == null) throw new IllegalArgumentException();
        
        setDebug(pVal.isDebug());
        setEnabled(pVal.isEnabled());
        setSslEnabled(pVal.isSslEnabled());
        setStartTlsEnabled(pVal.isStartTlsEnabled());
        setFrom(pVal.getFrom());
        setHost(pVal.getHost());
        setPassword(pVal.getPassword());
        setPort(pVal.getPort());
        setUser(pVal.getUser());
    }
    
}
