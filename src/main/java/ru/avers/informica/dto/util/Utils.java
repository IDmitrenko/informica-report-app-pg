package ru.avers.informica.dto.util;

import ru.avers.informica.dto.inqry.AgeDto;

import java.util.*;

/**
 *
 * @author Dias
 */
public class Utils {
    public static boolean isStringNullOrEmpty(String pVal) {
        return pVal == null || "".equals(pVal);
    }

    public static String concatCollection(Collection pCol, String pSeparator) {
        StringBuilder builder = new StringBuilder();
        String separator = "";
        for (Object item : pCol) {
            builder.append(separator).append(item);
            separator = pSeparator;
        }
        return builder.toString();
    }

    public static String concatListOfMaps(List<Map<String, Object>> pList,
                                          String pKey, String pSeparator) {
        StringBuilder builder = new StringBuilder();
        String separator = "";
        for (Map<String, Object> item : pList) {
            if (item.get(pKey) != null) {
                builder.append(separator).append(item.get(pKey));
            }
            separator = pSeparator;
        }
        return builder.toString();
    }
    
    /**
     * Вычислить возраст
     * @param pDt дата на которую вычисляется возраст
     * @param pBirthDt дата рождения
     * @return возраст
     */    
    public static AgeDto calculateAge(Date pDt, Date pBirthDt) {
        //if (pDt.before(pBirthDt)) return null;
        
        Calendar dtCalendar = new GregorianCalendar();
        dtCalendar.setTime(pDt);
        Calendar bdtCalendar = new GregorianCalendar();
        bdtCalendar.setTime(pBirthDt);
        
        // День рождения в году из даты pDt
        Calendar thisBdtCalendar = new GregorianCalendar(
                dtCalendar.get(Calendar.YEAR),
                bdtCalendar.get(Calendar.MONTH),
                bdtCalendar.get(Calendar.DAY_OF_MONTH));

        // Поправка для года если день рождения в году даты pDt еще не достигнут
        Integer ageYearsDelta = 0;
        if (dtCalendar.before(thisBdtCalendar)) {
            ageYearsDelta = 1;
        }
        // Возвраст. лет
        Integer ageYears = dtCalendar.get(Calendar.YEAR) -
                bdtCalendar.get(Calendar.YEAR) - ageYearsDelta;
        // Поправка для месяца, если не достигнуто дней до полного месяца
        Integer ageMonthsDelta = 0;
        Integer ageDays;
        if (dtCalendar.get(Calendar.DAY_OF_MONTH) < bdtCalendar.get(Calendar.DAY_OF_MONTH)) {
            ageMonthsDelta = 1;
            Calendar prevMonthBdtCalendar = (Calendar) dtCalendar.clone();
            prevMonthBdtCalendar.roll(Calendar.MONTH, false);
            ageDays = prevMonthBdtCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) -
                    bdtCalendar.get(Calendar.DAY_OF_MONTH) +
                    dtCalendar.get(Calendar.DAY_OF_MONTH);

        } else {
            ageDays = dtCalendar.get(Calendar.DAY_OF_MONTH) - bdtCalendar.get(Calendar.DAY_OF_MONTH);
        }
        Integer ageMonths = 12 * ageYearsDelta - bdtCalendar.get(Calendar.MONTH) +
                dtCalendar.get(Calendar.MONTH) - ageMonthsDelta;
        return new AgeDto(ageYears.shortValue(), ageMonths.shortValue(), ageDays.shortValue());
    }
    
}
