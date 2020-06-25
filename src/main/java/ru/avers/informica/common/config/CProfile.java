package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CProfile implements IDTO {

    public static final String S_ROOT_NAME = "profile";
    public static final String S_ATTR_ID = "id";

    public CProfile() {
    }
    
    private String id;
    private CMailNotification cMailNotification;
    private CMisc cMisc;
    private Reports reports;

    @XmlAttribute(name= S_ATTR_ID)
    public String getId() {
        return id;
    }

    public void setId(String pVal) {
        id = pVal;
    }   

    @XmlElement(name = CMailNotification.S_ROOT_NAME, type = CMailNotification.class)
    public CMailNotification getMailNotification() {
        if (cMailNotification == null) cMailNotification = new CMailNotification();
        return cMailNotification;
    }
    public void setMailNotification(CMailNotification pVal) {
        cMailNotification = pVal;
    }    
    
    @XmlElement(name = CMisc.S_ROOT_NAME, type = CMisc.class)
    public CMisc getMisc() {
        if (cMisc == null) cMisc = new CMisc();
        return cMisc;
    }

    public void setMisc(CMisc pVal) {
        cMisc = pVal;
    }    
    
    @XmlElement(name = Reports.S_ROOT_NAME, type = Reports.class)
    public Reports getReports() {
        if (reports == null) {
            reports = new Reports();
        }
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
    }    
    
    public void set(CProfile pVal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
