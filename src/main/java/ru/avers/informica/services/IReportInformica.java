/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.avers.informica.services;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * 
 * @author lavrov
 */
public interface IReportInformica {    
    // Минимальный уровень доступа для которого доступна функция "Отчет информики"
    // (Кнопка на главной форме приложения, пункт "Отчет. Информика" в выпадающем списке формы "Отчеты")
    // Используется только в приложении "Комиссия". Если данная функция отключена возвращается null (для
    // отключения в конфиге в качестве параметра указать пустую строку)
    IMisc.AccessLevel getReportAccessLevel();
    void setReportAccessLevel(IMisc.AccessLevel p_value);    

    Boolean isEmailLog();
    void setEmailLog(Boolean p_val);
    
    // Наименование региона (используется в логах на email)
    String getStateName();
    void setStateName(String p_val);
    
    // Включена ли автоматическая загрузка отчета информики
    Boolean isAutoUploadEnabled();
    void setAutoUploadEnabled(Boolean p_val);

    // Часы когда производить авто загрузку отчета
    Integer getAutoUploadHours();
    void setAutoUploadHours(Integer p_val);

    // Минуты когда производить авто загрузку отчета
    Integer getAutoUploadMinutes();
    void setAutoUploadMinutes(Integer p_val);
                
    // Включить отладку отчета
    Boolean isDebugEnabled();
    void setDebugEnabled(Boolean p_val);
        
    enum EnrolledSource{CMSN, CNTGN, PLUS}
    // Откуда брать заявления в показатели со статусом "зачислены"
    EnrolledSource getEnrolledFrom();
    void setEnrolledFrom(EnrolledSource p_source);
    
    // Таймаут на соединение с веб сервисом информики, в минутах
    Integer getConnectionTimeout();
    void setConnectionTimeout(Integer p_connection_timeout);

    // Таймаут на ожидание ответа от веб сервиса информики, в минутах
    Integer getRequestTimeout();
    void setRequestTimeout(Integer p_request_timeout);
    
    // Задержка между повторными попытками выгрузки отчета, в минутах
    Integer getPushDataDelay();
    void setPushDataDelay(Integer p_val);
    
    Boolean isAsyncUploadEnabled();
    void setAsyncUploadEnabled(Boolean p_val);
    
    // Использовать многопоточность при построении отчета
    Boolean isMt();
    void setMt(Boolean p_val);
    
    enum Version {
        @XmlEnumValue("2")
        Two, 
        @XmlEnumValue("3")
        Three,
        @XmlEnumValue("4")
        Four
    }
    Version getVersion();
    void setVersion(Version p_version);
    
    void set(IReportInformica p_val);
}
