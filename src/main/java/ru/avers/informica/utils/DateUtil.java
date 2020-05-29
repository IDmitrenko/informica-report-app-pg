package ru.avers.informica.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Dias
 */
public class DateUtil {
    
        // Дата начала текущего учебного года
    public static Date getCurrEducDateByParts(Integer p_educ_year, int p_educ_year_begin_month, int p_educ_year_begin_day) {
        Date x_rv;
        if(p_educ_year == null)
            x_rv = getCurrEducDate(new Date(), p_educ_year_begin_month, p_educ_year_begin_day);
        else
            x_rv = createCalendar(p_educ_year, p_educ_year_begin_month, p_educ_year_begin_day).getTime();
        return x_rv;
    }

    static public int getYearPart(Date p_date) { return getPartFromDate(p_date, Calendar.YEAR); }
    
    static private int getPartFromDate(Date p_date, int p_part) {
        Calendar x_calendar = GregorianCalendar.getInstance();
        x_calendar.setTime(p_date);
        return x_calendar.get(p_part);
    }
    
    public static Date getCurrEducDate(Date p_rep_date, int p_educ_year_begin_month, int p_educ_year_begin_day) {
        Calendar x_cal_rep_date = GregorianCalendar.getInstance();
        x_cal_rep_date.setTime(p_rep_date);
        int x_rep_year = x_cal_rep_date.get(Calendar.YEAR);
        clearCalendarTimePart(x_cal_rep_date);
        
        // Дата начала учебного года в текущем году
        Calendar x_curr_year_educ_begin = createCalendar(x_rep_year, p_educ_year_begin_month, p_educ_year_begin_day);
        
//        Calendar x_curr_year_educ_begin = Calendar.getInstance();
//        x_curr_year_educ_begin.set(Calendar.YEAR, x_rep_year);
//        x_curr_year_educ_begin.set(Calendar.MONTH, p_educ_year_begin_month);
//        x_curr_year_educ_begin.set(Calendar.DAY_OF_MONTH, p_educ_year_begin_day);
//        clearCalendarTimePart(x_curr_year_educ_begin);
        
        // Определить текущий учебный год
        if (p_rep_date.before(x_curr_year_educ_begin.getTime())) {
            x_curr_year_educ_begin.add(Calendar.YEAR, -1);
            return x_curr_year_educ_begin.getTime();
        }
        else 
            return x_curr_year_educ_begin.getTime();
    }

    public static Date getCurrentDate(Boolean zero) {
        Date date = new Date();                      // текущее время
        if (zero) {
            Calendar cal = Calendar.getInstance();       // экземпляр календаря
            cal.setTime(date);                           // набор временных параметров
            clearCalendarTimePart(cal);                  // обнуляем часы, минуты, секунды, милисекунды
            return cal.getTime();
        } else {
            return date;
        }
    }
    
    public static void clearCalendarTimePart(Calendar p_calendar) {
        p_calendar.set(Calendar.HOUR_OF_DAY, 0);
        p_calendar.set(Calendar.MINUTE, 0);
        p_calendar.set(Calendar.SECOND, 0);
        p_calendar.set(Calendar.MILLISECOND, 0);
    }
    
    public static Date clearDateTimePart(Date p_date) {
        Calendar x_calendar = GregorianCalendar.getInstance();
        x_calendar.setTime(p_date);
        clearCalendarTimePart(x_calendar);
        return x_calendar.getTime();
    }    
    
    public static Date adjustDate(Date p_date, int p_year) { return adjustDate(p_date, p_year, 0, 0); }
    public static Date adjustDate(Date p_date, int p_year, int p_month) { return adjustDate(p_date, p_year, p_month, 0); }
    /**
     * Изменить дату на указанное кол-во лет, месяцев и дней
     * @param p_date дата
     * @param p_year лет
     * @param p_month месяцев
     * @param p_days дней
     * @return 
     */    
    public static Date adjustDate(Date p_date, int p_year, int p_month, int p_days) {
        Calendar x_calendar = GregorianCalendar.getInstance();
        x_calendar.setTime(p_date);
        x_calendar.add(Calendar.YEAR, p_year);
        x_calendar.add(Calendar.MONTH, p_month);
        x_calendar.add(Calendar.DAY_OF_MONTH, p_days);
        return x_calendar.getTime();
    }    
    
    public static Calendar createCalendar(Integer p_month, Integer p_days) {
        return createCalendar(null, p_month, p_days);
    }
    public static Calendar createCalendar(Integer p_year, Integer p_month, Integer p_days) {
        Calendar x_calendar = GregorianCalendar.getInstance();
        clearCalendarTimePart(x_calendar);
        if(p_year != null) x_calendar.set(Calendar.YEAR, p_year);
        if(p_month != null) x_calendar.set(Calendar.MONTH, p_month);
        if(p_days != null) x_calendar.set(Calendar.DAY_OF_MONTH, p_days);
        return x_calendar;
    }
        
}
