/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

import org.avers.inqry.config.IDateElement;

/**
 *
 * @author lavrov
 */
public interface IInqryCheckStatus {
    
    IDateElement getCalculateOnDate();
    void setCalculateOnDate(IDateElement p_date);    
    
    /**
     * ¬озможность проверки статуса за€влени€ без регистрации по номеру за€влени€ и св-ву о рождении
     * @return 
     */
    Boolean isStatusCheckWoutRegEnabled();
    void setStatusCheckWoutRegEnabled(Boolean p_val);

    /**
     * ¬озрастные интервалы, используютс€ если номер в очереди отображаетс€ по
     * возрастным интервалам
     * @return 
     */
    IAgeIntervals getAgeIntervals();
    void setAgeIntervals(IAgeIntervals p_age_intervals);
    
    /**
     * ѕроверка статуса в приложении "за€витель" по нажатию отдельной кнопки "ѕолучить номер в очереди"
     * @return 
     */
    IInquirerCheckTotalByBtn getInquirerCheckTotalByBtn();
    void setInquirerCheckTotalByBtn(IInquirerCheckTotalByBtn p_value);
    
    /**
     * ќтображение номеров в очереди указанных типов
     * @return 
     */
    IViewQueues getInquirerViewQueues();
    void setInquirerViewQueues(IViewQueues p_value);
    
    IViewQueue getAllocViewQueue();
    void setAllocViewQueue(IViewQueue p_val);
    
    void set(IInqryCheckStatus p_val);
    
}
