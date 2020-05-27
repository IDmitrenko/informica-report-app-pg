package ru.avers.informica.dto.util;

import ru.avers.informica.dto.inqry.AgeDto;

import java.util.*;

/**
 *
 * @author Dias
 */
public class Utils {
    public static boolean isStringNullOrEmpty(String p_val) {
        return p_val == null || "".equals(p_val);
    }

    public static String concatCollection(Collection p_col, String p_separator) {
        StringBuilder x_builder = new StringBuilder();
        String x_separator = "";
        for (Object x_item : p_col) {
            x_builder.append(x_separator).append(x_item);
            x_separator = p_separator;
        }
        return x_builder.toString();
    }

    public static String concatListOfMaps(List<Map<String, Object>> p_list, String p_key, String p_separator) {
        StringBuilder x_builder = new StringBuilder();
        String x_separator = "";
        for (Map<String, Object> x_item : p_list) {
            if (x_item.get(p_key) != null) x_builder.append(x_separator).append(x_item.get(p_key));
            x_separator = p_separator;
        }
        return x_builder.toString();
    }
    
    /**
     * Вычислить возраст
     * @param p_dt дата на которую вычисляется возраст
     * @param p_birth_dt дата рождения
     * @return возраст
     */    
    public static AgeDto calculateAge(Date p_dt, Date p_birth_dt) {
        //if (p_dt.before(p_birth_dt)) return null;
        
        Calendar x_dt_calendar = new GregorianCalendar();
        x_dt_calendar.setTime(p_dt);
        Calendar x_bdt_calendar = new GregorianCalendar();
        x_bdt_calendar.setTime(p_birth_dt);
        
        // День рождения в году из даты p_dt
        Calendar x_this_bdt_calendar = new GregorianCalendar(
                x_dt_calendar.get(Calendar.YEAR),
                x_bdt_calendar.get(Calendar.MONTH),
                x_bdt_calendar.get(Calendar.DAY_OF_MONTH));

        // Поправка для года если день рождения в году даты p_dt еще не достигнут
        Integer x_age_years_delta = 0;
        if (x_dt_calendar.before(x_this_bdt_calendar)) x_age_years_delta = 1;
        // Возвраст. лет
        Integer x_age_years = x_dt_calendar.get(Calendar.YEAR) - x_bdt_calendar.get(Calendar.YEAR) - x_age_years_delta;
        // Поправка для месяца, если не достигнуто дней до полного месяца
        Integer x_age_months_delta = 0;
        Integer x_age_days;
        if (x_dt_calendar.get(Calendar.DAY_OF_MONTH) < x_bdt_calendar.get(Calendar.DAY_OF_MONTH)) {
            x_age_months_delta = 1;                        
            Calendar x_prevmonth_bdt_calendar = (Calendar) x_dt_calendar.clone();
            x_prevmonth_bdt_calendar.roll(Calendar.MONTH, false);
            x_age_days = x_prevmonth_bdt_calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - x_bdt_calendar.get(Calendar.DAY_OF_MONTH) + x_dt_calendar.get(Calendar.DAY_OF_MONTH);

        }
        else
            x_age_days = x_dt_calendar.get(Calendar.DAY_OF_MONTH) - x_bdt_calendar.get(Calendar.DAY_OF_MONTH);
        Integer x_age_months = 12 * x_age_years_delta - x_bdt_calendar.get(Calendar.MONTH) + x_dt_calendar.get(Calendar.MONTH) - x_age_months_delta;
        return new AgeDto(x_age_years.shortValue(), x_age_months.shortValue(), x_age_days.shortValue());
    }
    
}
