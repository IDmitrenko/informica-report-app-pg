package ru.avers.informica.dto.user;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeExtUserId", propOrder={"authProviderCode", "userId"})
public class CExtUserId implements IDTO {
    final static public String
            S_CODE_DAILYMOTION = "DAILYMOTION",
            S_CODE_GOOGLE = "GOOGLE",
            S_CODE_YANDEX = "YANDEX",
            S_CODE_ESIA = "ESIA";
    
    
    private String authProviderCode, userId;
    
    @XmlAttribute(name="auth-provider-code")
    public String getAuthProviderCode() {
        return authProviderCode;
    }
    public void setAuthProviderCode(String pVal) {
        authProviderCode = pVal;
    }
    
    @XmlAttribute(name="user-id")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String pVal) {
        userId = pVal;
    }
    
    public CExtUserId() {
        this(null, null);
    }
    public CExtUserId(String pAuthProviderCode) {
        this(pAuthProviderCode, null);
    }
    public CExtUserId(String pAuthProviderCode, String pUserId) {
        authProviderCode = pAuthProviderCode;
        userId = pUserId;
    }
    
    @Override
    public String toString() {
        return CExtUserId.class.getName()
                + " {" 
                + "auth-provider-code = " + String.valueOf(authProviderCode)
                + "; user-id = " + String.valueOf(userId)
                + "}";
    }
    
}
