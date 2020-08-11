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
    public static Date getCurrEducDateByParts(Integer pEducYear,
                                              int pEducYearBeginMonth,
                                              int pEducYearBeginDay) {
        Date rv;
        if(pEducYear == null)
            rv = getCurrEducDate(new Date(), pEducYearBeginMonth, pEducYearBeginDay);
        else
            rv = createCalendar(pEducYear, pEducYearBeginMonth, pEducYearBeginDay).getTime();
        return rv;
    }

    static public int getYearPart(Date pDate) {
        return getPartFromDate(pDate, Calendar.YEAR);
    }

    static public int getMonthPart(Date pDate) {
        return getPartFromDate(pDate, Calendar.MONTH);
    }
    
    static private int getPartFromDate(Date pDate, int pPart) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(pDate);
        return calendar.get(pPart);
    }
    
    public static Date getCurrEducDate(Date pRepDate,
                                       int pEducYearBeginMonth,
                                       int pEducYearBeginDay) {
        Calendar calRepDate = GregorianCalendar.getInstance();
        calRepDate.setTime(pRepDate);
        int repYear = calRepDate.get(Calendar.YEAR);
        clearCalendarTimePart(calRepDate);
        
        // Дата начала учебного года в текущем году
        Calendar currYearEducBegin = createCalendar(repYear, pEducYearBeginMonth, pEducYearBeginDay);
        
        // Определить текущий учебный год
        if (pRepDate.before(currYearEducBegin.getTime())) {
            currYearEducBegin.add(Calendar.YEAR, -1);
            return currYearEducBegin.getTime();
        }
        else 
            return currYearEducBegin.getTime();
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
    
    public static void clearCalendarTimePart(Calendar pCalendar) {
        pCalendar.set(Calendar.HOUR_OF_DAY, 0);
        pCalendar.set(Calendar.MINUTE, 0);
        pCalendar.set(Calendar.SECOND, 0);
        pCalendar.set(Calendar.MILLISECOND, 0);
    }
    
    public static Date clearDateTimePart(Date pDate) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(pDate);
        clearCalendarTimePart(calendar);
        return calendar.getTime();
    }    
    
    public static Date adjustDate(Date pDate, int pYear) {
        return adjustDate(pDate, pYear, 0, 0);
    }

    public static Date adjustDate(Date pDate, int pYear, int pMonth) {
        return adjustDate(pDate, pYear, pMonth, 0);
    }
    /**
     * Изменить дату на указанное кол-во лет, месяцев и дней
     * @param pDate дата
     * @param pYear лет
     * @param pMonth месяцев
     * @param pDays дней
     * @return 
     */    
    public static Date adjustDate(Date pDate, int pYear, int pMonth, int pDays) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(pDate);
        calendar.add(Calendar.YEAR, pYear);
        calendar.add(Calendar.MONTH, pMonth);
        calendar.add(Calendar.DAY_OF_MONTH, pDays);
        return calendar.getTime();
    }    
    
    public static Calendar createCalendar(Integer pMonth, Integer pDays) {
        return createCalendar(null, pMonth, pDays);
    }
    public static Calendar createCalendar(Integer pYear, Integer pMonth, Integer pDays) {
        Calendar calendar = GregorianCalendar.getInstance();
        clearCalendarTimePart(calendar);
        if(pYear != null) calendar.set(Calendar.YEAR, pYear);
        if(pMonth != null) calendar.set(Calendar.MONTH, pMonth);
        if(pDays != null) calendar.set(Calendar.DAY_OF_MONTH, pDays);
        return calendar;
    }
        
}
