package ru.avers.informica.dto;

/**
 * Настройка. дата по компонентам год, месяц, день
 * @author lavrov
 */
public interface IDateElement {
        
    Integer getYear();
    void setYear(Integer pYear);
    
    Integer getMonth();
    void setMonth(Integer pMonth);

    Integer getDay();
    void setDay(Integer pDay);
    
    void set(IDateElement pVal);
}
