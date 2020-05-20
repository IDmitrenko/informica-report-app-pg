package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger s_logger = LoggerFactory.getLogger(CProfile.class);

    public static final String s_root_name = "profile";
    public static final String s_attr_id = "id";

    public CProfile() {
    }
    
    private String m_id;
    private CMailNotification m_mail_notification;
    private CMisc m_misc;
    private Reports m_reports;

    @XmlAttribute(name=s_attr_id)
    public String getId() {
        return m_id;
    }

    public void setId(String p_val) {
        m_id = p_val;
    }   

    @XmlElement(name = CMailNotification.s_root_name, type = CMailNotification.class)
    public CMailNotification getMailNotification() {
        if (m_mail_notification == null) m_mail_notification = new CMailNotification();
        return m_mail_notification;
    }
    public void setMailNotification(CMailNotification p_val) {
        m_mail_notification = p_val;
    }    
    
    @XmlElement(name = CMisc.s_root_name, type = CMisc.class)
    public CMisc getMisc() {
        if (m_misc == null) m_misc = new CMisc();
        return m_misc;
    }

    public void setMisc(CMisc p_val) {
        m_misc = p_val;
    }    
    
    @XmlElement(name = Reports.s_root_name, type = Reports.class)
    public Reports getReports() {
        if (m_reports == null) {
            m_reports = new Reports();
        }
        return m_reports;
    }

    public void setReports(Reports p_reports) {
        m_reports = p_reports;
    }    
    
    public void set(CProfile p_val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
