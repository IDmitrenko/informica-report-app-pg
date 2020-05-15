/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

import java.util.Collection;

/**
 *
 * @author Dias
 */
public interface IMisc {   

    enum TTypeQueue{TOTAL, DISTRICTS, UCHS}
    
    enum AccessLevel{SUPERUSER, ADMIN, COMMISSION, OPERATOR, PRIVILEGED, NONE}
    
    // Настройки по умолчанию (временная зона, тема)
    IDefaults getDefaults();
    void setDefaults(IDefaults p_val);
    
    // ***************************************************************************
    //  День и месяц начала учебного года. Используется для расчета диапазона возрастов
    //  на начало учебного года отображаемых при проверке статуса заявления. (для месяца: январь=0)
    // ***************************************************************************
    IInqryEducYearBegin getInqryEducYearBegin();
    void setInqryEducYearBegin(IInqryEducYearBegin p_val);
    
    /**
     * Конфигурация проверки статуса заявления.
     * @return 
     */
    IInqryCheckStatus getInqryCheckStatus();
    void setInqryCheckStatus(IInqryCheckStatus p_val);
    
    //        ***************************************************************************
    //        Коды льгот которые не отображать при подаче заявления
    //        ***************************************************************************
    Collection<String> getExcludeLgotCodes();
    void setExcludeLgotCodes(Collection<String> p_val);
    
    // Коды документов ребенка которые не отображать при подаче заявления
    Collection<String> getExcludeDocKindChldCodes();
    void setExcludeDocKindChldCodes(Collection<String> p_val);
    
    // Коды статусов заявлений которые не отображать при выборе нового статуса заявления
    Collection<String> getExcludeStatusCodes();
    void setExcludeStatusCodes(Collection<String> p_val);

    // Коды причин установки статуса заявления которые не отображать при выборе новой причины
    Collection<String> getExcludeStatusReasonCodes();
    void setExcludeStatusReasonCodes(Collection<String> p_val);
    
    // Коды отчетов которые не отображать
    Collection<String> getExcludeReportCodes();
    void setExcludeReportCodes(Collection<String> p_val);
    
    // Минимальный уровень доступа, для которого доступна форма "Регистрация"
    // (позволяет зарегестрировать нового пользователя)
    AccessLevel getUserRegistrationAccessLevel();
    void setUserRegistrationAccessLevel(AccessLevel p_value);
    
    // Минимальный уровень доступа, для которого доступна форма "Пользователи"
    // (позволяет зарегестрировать нового пользователя, просматривать списки пользователей,
    // распределять пользователей по группам)
    AccessLevel getUsersManagementAccessLevel();
    void setUsersManagementAccessLevel(AccessLevel p_value);
    
    // Минимальный уровень доступа, для которого доступна кнопка "Изменить дату регистрации" на форме
    // "Заявления" и "Список заявлений". Если данная функция отключена возвращается null.
    // (кнопка позволяет установить произвольную дату постановки на очередь для заявления, например после
    // заливки заявлений из шаблона)    
    AccessLevel getManualRegChangeAccessLevel();
    void setManualRegChangeAccessLevel(AccessLevel p_value);

    // Минимальный уровень доступа, для которого доступна вкладка "Журнал изменений" на форме
    // "Заявления" и "Список заявлений".
    AccessLevel getMinEventViewerAccessLevel();
    void setMinEventViewerAccessLevel(AccessLevel p_value);
    
    // Автоматически раскрывать список учреждений первого района на форме Учреждения
    Boolean isFormUchsAutoExpandList();
    void setFormUchsAutoExpandList(Boolean p_val);

    /**
     * Конфигурация механизма комплектования
     * @return 
     */
    IQueueAllocation getQueueAllocation();
    void setQueueAllocation(IQueueAllocation p_queue_allocation);
    
    /**
     * Видимость вкладок Мать, Отец на форме заявления
     * @return 
     */
    Boolean isFormInqriesMFTabsVisible();
    void setFormInqriesMFTabsVisible(Boolean p_val);

    /**
     * Настройки ЕСИА для приложения Комиссия
     * @return 
     */
    IEsia getEsiaCommission();
    void setEsiaCommission(IEsia p_esia);
    
    /**
     * адрес веб-сервиса получения информации из контингента
     * 2016-06-20:  для построения отчета информики версии 4
     * @return 
     */
    String getContingentEndpoint();
    void setContingentEndpoint(String p_val);
    
    void set(IMisc p_val);
    
}
