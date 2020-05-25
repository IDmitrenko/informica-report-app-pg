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
            s_code_dailymotion = "DAILYMOTION",
            s_code_google = "GOOGLE",
            s_code_yandex = "YANDEX",
            s_code_esia = "ESIA";
    
    
    private String m_code_auth_provider, m_user_id;
    
    @XmlAttribute(name="auth-provider-code")
    public String getAuthProviderCode() { return m_code_auth_provider; }
    public void setAuthProviderCode(String p_val) { m_code_auth_provider = p_val; }
    
    @XmlAttribute(name="user-id")
    public String getUserId() { return m_user_id; }
    public void setUserId(String p_val) { m_user_id = p_val; }
    
    public CExtUserId() { this(null, null); }
    public CExtUserId(String p_auth_provider_code) { this(p_auth_provider_code, null); }
    public CExtUserId(String p_auth_provider_code, String p_user_id) {
        m_code_auth_provider = p_auth_provider_code;
        m_user_id = p_user_id;
    }
    
    @Override
    public String toString() {
        return CExtUserId.class.getName()
                + " {" 
                + "auth-provider-code = " + String.valueOf(m_code_auth_provider)
                + "; user-id = " + String.valueOf(m_user_id)
                + "}";
    }
    
}
