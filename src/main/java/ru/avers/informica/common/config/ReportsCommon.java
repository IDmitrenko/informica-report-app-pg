package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class ReportsCommon implements IDTO {

    public final static String S_ROOT_NAME = "common",
                               S_ATTR_AUTHORITY = "authority",
                               S_ATTR_APP_DESCR = "app-descr";

    private String authority,
                   appDescr;
    
    public ReportsCommon() { }
    
    @XmlAttribute(name = S_ATTR_AUTHORITY)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String pVal) {
        authority = pVal;
    }

    @XmlAttribute(name = S_ATTR_APP_DESCR)
    public String getAppDescr() {
        return appDescr;
    }
    
    public void setAppDescr(String pVal) {
        appDescr = pVal;
    }

    public final void set(ReportsCommon pVal) {
        if (pVal == null) throw new IllegalArgumentException();
        
        setAuthority(pVal.getAuthority());
        setAppDescr(pVal.getAppDescr());
    }

}
