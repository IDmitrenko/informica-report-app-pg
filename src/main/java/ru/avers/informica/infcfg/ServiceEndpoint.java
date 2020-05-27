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
public class ServiceEndpoint {
    
    private String m_login,
                   m_password,
                   m_informica_service_endpoint;
    private TypeSchemaVersion m_ver;
    
    @XmlAttribute
    public String getLogin() { return m_login; }
    public void setLogin(String p_login) { m_login = p_login; }
    
    @XmlAttribute
    public String getPassword() { return m_password; }
    public void setPassword(String p_password) { m_password = p_password; }
    
    @XmlAttribute
    public TypeSchemaVersion getVer() { return m_ver; }
    public void setVer(TypeSchemaVersion p_val) { m_ver = p_val; }    
    
    @XmlValue
    public String getServiceEndpoint() { return m_informica_service_endpoint; }
    public void setServiceEndpoint(String p_val) { m_informica_service_endpoint = p_val; }

    @Override
    public int hashCode() {
        int x_hash = 5;
        x_hash = 53 * x_hash + (m_login != null ? m_login.hashCode() : 0);
        x_hash = 53 * x_hash + (m_password != null ? m_password.hashCode() : 0);
        x_hash = 53 * x_hash + (m_informica_service_endpoint != null ?
                m_informica_service_endpoint.hashCode() : 0);
        x_hash = 53 * x_hash + (m_ver != null ? m_ver.hashCode() : 0);
        return x_hash;
    }

    @Override
    public boolean equals(Object p_obj) {
        if (p_obj == null) {
            return false;
        }
        if (getClass() != p_obj.getClass()) {
            return false;
        }
        final ServiceEndpoint x_other = (ServiceEndpoint) p_obj;
        if((m_login == null) ? (x_other.m_login != null) : !m_login.equals(x_other.m_login)) {
            return false;
        }
        if((m_password == null) ? (x_other.m_password != null) :
                !m_password.equals(x_other.m_password)) {
            return false;
        }
        if((m_informica_service_endpoint == null) ?
                (x_other.m_informica_service_endpoint != null) :
                !m_informica_service_endpoint.equals(x_other.m_informica_service_endpoint)) {
            return false;
        }
        if((m_ver == null) ? (x_other.getVer() != null) : !m_ver.equals(x_other.m_ver)) {
            return false;
        }
        return true;
    }
           
}