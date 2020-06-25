package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * @author Dias
 */
public class ReportInformica implements IDTO {

    public final static String S_ROOT_NAME = "informica-report",
                               S_ATTR_REPORT_ACCESS_LEVEL = "level",
                               S_ATTR_EMAIL_LOG = "email-log",
                               S_ATTR_STATE_NAME = "state-name",
                               S_ATTR_AUTO_UPLOAD_ENABLED = "auto-upload-enabled",
                               S_ATTR_AUTO_UPLOAD_HOURS = "auto-upload-hours",
                               S_ATTR_AUTO_UPLOAD_MINUTES = "auto-upload-minutes",
                               S_ATTR_DEBUG_ENABLED = "debug",
                               S_ATTR_GET_ENROLLED_FROM = "get-enrolled-from",
                               S_ATTR_CON_TIMEOUT = "con-timeout",
                               S_ATTR_REQ_TIMEOUT = "req-timeout",
                               S_ATTR_PUSH_DATA_DELAY = "delay",
                               S_ATTR_ASYNC_UPLOAD = "async",
                               S_ATTR_MT = "mt",
                               S_ATTR_VER = "ver";

    public ReportInformica() { 
    }
    
    private CMisc.AccessLevel accessLevel = CMisc.AccessLevel.SUPERUSER;
    private EnrolledSource enrolledSource = EnrolledSource.CMSN;
    private Boolean emailLog = true,
                    autoUploadEnabled = true,
                    debugEnabled = false,
                    asyncUploadEnabled = true,
                    mt = true;
    private Integer autoUploadHours = 2,
                    autoUploadMinutes = 0,
            connectionTimeout,
            requestTimeout,
                    delay = 60;
    private String stateName;
    private Version version = Version.Five;
    private enum EnrolledSource{CMSN, CNTGN, PLUS}
    public enum Version {
        @XmlEnumValue("5")
        Five
    }

    @XmlAttribute(name = S_ATTR_EMAIL_LOG)
    public Boolean isEmailLog() {
        return emailLog;
    }

    public void setEmailLog(Boolean pVal) {
        emailLog = pVal;
    }

    // Минимальный уровень доступа для которого доступна функция "Отчет информики"
    // (Кнопка на главной форме приложения, пункт "Отчет. Информика" в выпадающем списке формы "Отчеты")
    // Используется только в приложении "Комиссия". Если данная функция отключена возвращается null (для
    // отключения в конфиге в качестве параметра указать пустую строку)
    @XmlAttribute(name = S_ATTR_REPORT_ACCESS_LEVEL, required = true)
    public CMisc.AccessLevel getReportAccessLevel() {
        return accessLevel;
    }

    public void setReportAccessLevel(CMisc.AccessLevel pVal) {
        accessLevel = pVal;
    }

    // Наименование региона (используется в логах на email)
    @XmlAttribute(name = S_ATTR_STATE_NAME)
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String pVal) {
        stateName = pVal;
    }

    // Откуда брать заявления в показатели со статусом "зачислены"
    @XmlAttribute(name = S_ATTR_GET_ENROLLED_FROM)
    public EnrolledSource getEnrolledFrom() {
        return enrolledSource;
    }

    public void setEnrolledFrom(EnrolledSource pVal) {
        enrolledSource = pVal;
    }

    // Включена ли автоматическая загрузка отчета информики
    @XmlAttribute(name = S_ATTR_AUTO_UPLOAD_ENABLED)
    public Boolean isAutoUploadEnabled() {
        return autoUploadEnabled;
    }

    public void setAutoUploadEnabled(Boolean pVal) {
        autoUploadEnabled = pVal;
    }

    // Часы когда производить авто загрузку отчета
    @XmlAttribute(name = S_ATTR_AUTO_UPLOAD_HOURS)
    public Integer getAutoUploadHours() {
        return autoUploadHours;
    }

    public void setAutoUploadHours(Integer pVal) {
        autoUploadHours = pVal;
    }

    // Минуты когда производить авто загрузку отчета
    @XmlAttribute(name = S_ATTR_AUTO_UPLOAD_MINUTES)
    public Integer getAutoUploadMinutes() {
        return autoUploadMinutes;
    }

    public void setAutoUploadMinutes(Integer pVal) {
        autoUploadMinutes = pVal;
    }

    // Включить отладку отчета
    @XmlAttribute(name = S_ATTR_DEBUG_ENABLED)
    public Boolean isDebugEnabled() {
        return debugEnabled;
    }

    public void setDebugEnabled(Boolean pVal) {
        debugEnabled = pVal;
    }

    // Таймаут на соединение с веб сервисом информики, в минутах
    @XmlAttribute(name = S_ATTR_CON_TIMEOUT)
    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer pConnectionTimeout) {
        connectionTimeout = pConnectionTimeout;
    }

    // Таймаут на ожидание ответа от веб сервиса информики, в минутах
    @XmlAttribute(name = S_ATTR_REQ_TIMEOUT)
    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer pRequestTimeout) {
        requestTimeout = pRequestTimeout;
    }    
    
    @XmlAttribute(name = S_ATTR_ASYNC_UPLOAD)
    public Boolean isAsyncUploadEnabled() {
        return asyncUploadEnabled;
    }

    public void setAsyncUploadEnabled(Boolean pVal) {
        asyncUploadEnabled = pVal;
    }

    // Задержка между повторными попытками выгрузки отчета, в минутах
    @XmlAttribute(name = S_ATTR_PUSH_DATA_DELAY)
    public Integer getPushDataDelay() {
        return delay;
    }

    public void setPushDataDelay(Integer pVal) {
        delay = pVal;
    }

    // Использовать многопоточность при построении отчета
    @XmlAttribute(name = S_ATTR_MT)
    public Boolean isMt() {
        return mt;
    }

    public void setMt(Boolean pVal) {
        mt = pVal;
    }

    @XmlAttribute(name = S_ATTR_VER)
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version pVersion) {
        version = pVersion;
    }
          
    public final void set(ReportInformica pVal) {
        if (pVal == null) throw new IllegalArgumentException();

        setEmailLog(pVal.isEmailLog());
        setReportAccessLevel(pVal.getReportAccessLevel());
        setAutoUploadEnabled(pVal.isAutoUploadEnabled());
        setAutoUploadHours(pVal.getAutoUploadHours());
        setAutoUploadMinutes(pVal.getAutoUploadMinutes());
        setDebugEnabled(pVal.isDebugEnabled());
        setEnrolledFrom(pVal.getEnrolledFrom());
        setConnectionTimeout(pVal.getConnectionTimeout());
        setRequestTimeout(pVal.getRequestTimeout());
        setPushDataDelay(pVal.getPushDataDelay());
        setMt(pVal.isMt());
    }
    
}
