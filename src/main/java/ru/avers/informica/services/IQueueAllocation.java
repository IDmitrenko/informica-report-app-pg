/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.avers.informica.services;

import dal.QueueDatesRule;
import org.avers.common.config.impl.CalcAgeElementTer;
import org.avers.dto.inqry.QueueRule;
import org.avers.inqry.config.IQueueDtRule;

import java.util.Collection;
import java.util.List;

/**
 * Настройки комплектования
 * @author lavrov
 */
public interface IQueueAllocation {
    
    /**
     * Как загружать номер заявления в очереди на форме "Комплектование"
     */
    enum LoadInqryPosType {
        OFF, // Не отображать вообще
        EAGER, // Рассчитывать сразу при загрузке очереди
        LAZY} // Рассчитывать после того, как очередь показана на форме
    
    /**
     * День и месяц на который вычислять возраст
     * @return 
     */
    ICalcAgeElement getCalculateAgeOnDate();
    void setCalculateAgeOnDate(ICalcAgeElement p_date);

    List<CalcAgeElementTer> getCalculateAgeOnDateTer();
    void setCalculateAgeOnDateTer(List<CalcAgeElementTer> p_date_ter);

    ICalcAgeElement getCalculateAgeOnDateAdd();
    void setCalculateAgeOnDateAdd(ICalcAgeElement p_date);
    
    /**
     * Форма "Комплектование", если true = отображать очередь с текущим и предыдущим желаемыми
     * годами поступления, false = отображать всю очередь
     * @return 
     */
    Boolean isRestrictByCurrInUchYear();
    void setRestrictByCurrInUchYear(Boolean p_val);
    
    /**
     * Форма "Комплектование", если true = отображать очередь заявлений, у которых желаемая дата зачисления не превосходит
     * даты начала учебного года, false = отображать всю очередь
     * @return 
     */
    Boolean isRestrictByCurrInUchDate();
    void setRestrictByCurrInUchDate(Boolean p_val);
    
    Collection<QueueRule> getQueueRules();
    void setQueueRules(Collection<QueueRule> p_queue_rules_list);
    
    /**
     * @return
     * Правила для определения дат постановки, возвращения и выхода
     * заявления в/из очереди 
     */    
    QueueDatesRule getQueueDatesRule();
    void setQueueDatesRule(QueueDatesRule p_rule);
  
    /**
     * Как загружать номер заявления в очереди на форме "Комплектование"
     * @return 
     */
    LoadInqryPosType getLoadInqryPosType();
    void setLoadInqryPosType(LoadInqryPosType p_type);

    /**
     * Кол-во заявлений, для которых вычислять номер заявления в очереди в одном запросе,
     * используется для LoadInqryPosType=LAZY
     * @return 
     */
    int getLoadInqryPosReqSize();
    void setLoadInqryPosReqSize(int p_load_inqry_pos_req_size);  
    
    Collection<String> getCreateAllocSelectDouTime();
    void setCreateAllocSelectDouTime(Collection<String> p_val);
    
    Collection<IQueueDtRule> getQueueDtRules();
    void setQueueDtRules(Collection<IQueueDtRule> p_queue_dt_rules);
    
    boolean isSpecifyInqryHealthNeeds();
    void setSpecifyInqryHealthNeeds(boolean p_specify_inqry_health_needs);
    
    void set(IQueueAllocation p_val);      
}
