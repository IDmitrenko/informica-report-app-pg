package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Dias
 */
public class ReportInformica implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(ReportInformica.class);
    
    public final static String s_root_name = "informica-report",
                               s_attr_report_access_level = "level",
                               s_attr_email_log = "email-log",
                               s_attr_state_name = "state-name",
                               s_attr_auto_upload_enabled = "auto-upload-enabled",
                               s_attr_auto_upload_hours = "auto-upload-hours",
                               s_attr_auto_upload_minutes = "auto-upload-minutes",
                               s_attr_debug_enabled = "debug",
                               s_attr_get_enrolled_from = "get-enrolled-from",
                               s_attr_con_timeout = "con-timeout",
                               s_attr_req_timeout = "req-timeout",
                               s_attr_push_data_delay = "delay",
                               s_attr_async_upload = "async",                               
                               s_attr_mt = "mt",
                               s_attr_ver = "ver";

    public ReportInformica() { 
    }
    
    private CMisc.AccessLevel m_access_level = CMisc.AccessLevel.SUPERUSER;
    private EnrolledSource m_enrolled_source = EnrolledSource.CMSN;
    private Boolean m_is_email_log = true,
                    m_is_auto_upload_enabled = true,
                    m_is_debug_enabled = false,
                    m_is_async_upload_enabled = true,            
                    m_is_mt = true;
    private Integer m_auto_upload_hours = 2,
                    m_auto_upload_minutes = 0,
                    m_connection_timeout,
                    m_request_timeout,
                    m_delay = 60;
    private String m_state_name;
    private Version m_version = Version.Five;
    private enum EnrolledSource{CMSN, CNTGN, PLUS}
    public enum Version {
        @XmlEnumValue("5")
        Five
    }

    @XmlAttribute(name = s_attr_email_log)
    public Boolean isEmailLog() {
        return m_is_email_log;
    }

    public void setEmailLog(Boolean p_val) {
        m_is_email_log = p_val;
    }

    // Минимальный уровень доступа для которого доступна функция "Отчет информики"
    // (Кнопка на главной форме приложения, пункт "Отчет. Информика" в выпадающем списке формы "Отчеты")
    // Используется только в приложении "Комиссия". Если данная функция отключена возвращается null (для
    // отключения в конфиге в качестве параметра указать пустую строку)
    @XmlAttribute(name = s_attr_report_access_level, required = true)
    public CMisc.AccessLevel getReportAccessLevel() {
        return m_access_level;
    }

    public void setReportAccessLevel(CMisc.AccessLevel p_value) {
        m_access_level = p_value;
    }

    // Наименование региона (используется в логах на email)
    @XmlAttribute(name = s_attr_state_name)
    public String getStateName() {
        return m_state_name;
    }

    public void setStateName(String p_val) {
        m_state_name = p_val;
    }

    // Откуда брать заявления в показатели со статусом "зачислены"
    @XmlAttribute(name = s_attr_get_enrolled_from)
    public EnrolledSource getEnrolledFrom() {
        return m_enrolled_source;
    }

    public void setEnrolledFrom(EnrolledSource p_value) {
        m_enrolled_source = p_value;
    }

    // Включена ли автоматическая загрузка отчета информики
    @XmlAttribute(name = s_attr_auto_upload_enabled)
    public Boolean isAutoUploadEnabled() {
        return m_is_auto_upload_enabled;
    }

    public void setAutoUploadEnabled(Boolean p_val) {
        m_is_auto_upload_enabled = p_val;
    }

    // Часы когда производить авто загрузку отчета
    @XmlAttribute(name = s_attr_auto_upload_hours)
    public Integer getAutoUploadHours() {
        return m_auto_upload_hours; 
    }

    public void setAutoUploadHours(Integer p_val) {
        m_auto_upload_hours = p_val;
    }

    // Минуты когда производить авто загрузку отчета
    @XmlAttribute(name = s_attr_auto_upload_minutes)
    public Integer getAutoUploadMinutes() {
        return m_auto_upload_minutes;
    }

    public void setAutoUploadMinutes(Integer p_val) {
        m_auto_upload_minutes = p_val;
    }

    // Включить отладку отчета
    @XmlAttribute(name = s_attr_debug_enabled)
    public Boolean isDebugEnabled() {
        return m_is_debug_enabled;
    }

    public void setDebugEnabled(Boolean p_val) {
        m_is_debug_enabled = p_val;
    }

    // Таймаут на соединение с веб сервисом информики, в минутах
    @XmlAttribute(name = s_attr_con_timeout)
    public Integer getConnectionTimeout() {
        return m_connection_timeout;
    }

    public void setConnectionTimeout(Integer p_connection_timeout) {
        m_connection_timeout = p_connection_timeout;
    }

    // Таймаут на ожидание ответа от веб сервиса информики, в минутах
    @XmlAttribute(name = s_attr_req_timeout)
    public Integer getRequestTimeout() {
        return m_request_timeout;
    }

    public void setRequestTimeout(Integer p_request_timeout) {
        m_request_timeout = p_request_timeout;
    }    
    
    @XmlAttribute(name = s_attr_async_upload)
    public Boolean isAsyncUploadEnabled() {
        return m_is_async_upload_enabled;
    }

    public void setAsyncUploadEnabled(Boolean p_val) {
        m_is_async_upload_enabled = p_val;
    }

    // Задержка между повторными попытками выгрузки отчета, в минутах
    @XmlAttribute(name = s_attr_push_data_delay)
    public Integer getPushDataDelay() {
        return m_delay;
    }

    public void setPushDataDelay(Integer p_val) {
        m_delay = p_val;
    }

    // Использовать многопоточность при построении отчета
    @XmlAttribute(name = s_attr_mt)
    public Boolean isMt() {
        return m_is_mt;
    }

    public void setMt(Boolean p_val) {
        m_is_mt = p_val;
    }

    @XmlAttribute(name = s_attr_ver)
    public Version getVersion() {
        return m_version;
    }

    public void setVersion(Version p_version) {
        m_version = p_version;
    }
          
    public final void set(ReportInformica p_val) {
        if (p_val == null) throw new IllegalArgumentException();

        setEmailLog(p_val.isEmailLog());
        setReportAccessLevel(p_val.getReportAccessLevel());
        setAutoUploadEnabled(p_val.isAutoUploadEnabled());
        setAutoUploadHours(p_val.getAutoUploadHours());
        setAutoUploadMinutes(p_val.getAutoUploadMinutes());
        setDebugEnabled(p_val.isDebugEnabled());
        setEnrolledFrom(p_val.getEnrolledFrom());
        setConnectionTimeout(p_val.getConnectionTimeout());
        setRequestTimeout(p_val.getRequestTimeout());
        setPushDataDelay(p_val.getPushDataDelay());
        setMt(p_val.isMt());
    }
    
}
