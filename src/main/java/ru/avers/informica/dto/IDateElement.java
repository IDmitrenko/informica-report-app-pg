package ru.avers.informica.dto;

/**
 * Настройка. дата по компонентам год, месяц, день
 * @author lavrov
 */
public interface IDateElement {
        
    Integer getYear();
    void setYear(Integer p_year);
    
    Integer getMonth();
    void setMonth(Integer p_month);

    Integer getDay();
    void setDay(Integer p_day);
    
    void set(IDateElement p_val);
}
