package ru.avers.informica.filtersinqry;

import java.util.Date;

/**
 *
 * @author Dias
 */
public interface IsFilterDate {
    public static final String filterCurrentDate = "current_date",
                               filterCurrentTime = "current_time";
    
    /**
     * если true - то для вычисления значения фильтра используется дата начала текущего учебного года,
     * иначе - текущая календарная дата
     * @return 
     */
    boolean isEducDate();
    
    Date getRepDate();
    void setRepDate(Date pDate);
}
