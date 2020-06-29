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
    
    private String login,
            password,
                   serviceEndpoint;
    private TypeSchemaVersion ver;
    
    @XmlAttribute
    public String getLogin() {
        return login;
    }
    public void setLogin(String pLogin) {
        login = pLogin;
    }
    
    @XmlAttribute
    public String getPassword() {
        return password;
    }
    public void setPassword(String pPassword) {
        password = pPassword;
    }
    
    @XmlAttribute
    public TypeSchemaVersion getVer() {
        return ver;
    }
    public void setVer(TypeSchemaVersion pVal) {
        ver = pVal;
    }
    
    @XmlValue
    public String getServiceEndpoint() {
        return serviceEndpoint;
    }
    public void setServiceEndpoint(String pVal) {
        serviceEndpoint = pVal;
    }

    @Override
    public int hashCode() {
        int x_hash = 5;
        x_hash = 53 * x_hash + (login != null ? login.hashCode() : 0);
        x_hash = 53 * x_hash + (password != null ? password.hashCode() : 0);
        x_hash = 53 * x_hash + (serviceEndpoint != null ?
                serviceEndpoint.hashCode() : 0);
        x_hash = 53 * x_hash + (ver != null ? ver.hashCode() : 0);
        return x_hash;
    }

    @Override
    public boolean equals(Object pObj) {
        if (pObj == null) {
            return false;
        }
        if (getClass() != pObj.getClass()) {
            return false;
        }
        final ServiceEndpoint other = (ServiceEndpoint) pObj;
        if((login == null) ? (other.login != null) : !login.equals(other.login)) {
            return false;
        }
        if((password == null) ? (other.password != null) :
                !password.equals(other.password)) {
            return false;
        }
        if((serviceEndpoint == null) ?
                (other.serviceEndpoint != null) :
                !serviceEndpoint.equals(other.serviceEndpoint)) {
            return false;
        }
        if((ver == null) ? (other.getVer() != null) : !ver.equals(other.ver)) {
            return false;
        }
        return true;
    }
           
}