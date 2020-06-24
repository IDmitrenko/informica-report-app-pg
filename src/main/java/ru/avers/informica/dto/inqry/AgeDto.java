package ru.avers.informica.dto.inqry;

import ru.avers.informica.dto.IDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Dias
 */
public class AgeDto implements IDTO {
    private short years;
    private short months;
    private short days;

    public AgeDto() {
    }    
    
    public AgeDto(short pYears, short pMonths, short pDays) {
        years = pYears;
        months = pMonths;
        days = pDays;
    }

    public short getDays() {
        return days;
    }

    public void setDays(short pDays) {
        this.days = pDays;
    }            
    
    public short getMonths() {
        return months;
    }

    public void setMonths(short pMonths) {
        this.months = pMonths;
    }    
    
    public short getYears() {
        return years;
    }

    public void setYears(short pYears) {
        this.years = pYears;
    }

    public Date getBirthDate(Date currDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currDate);
        calendar.add(Calendar.YEAR, -years);
        calendar.add(Calendar.MONTH, -months);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return calendar.getTime();
    }
    
    /**
     * Вычислить возраст
     * @param pDt дата на которую вычисляется возраст
     * @param pBirthDt дата рождения
     * @return возраст
     */    
    public static AgeDto calculateAge(Date pDt, Date pBirthDt) {
        Calendar dtCalendar = new GregorianCalendar();
        dtCalendar.setTime(pDt);
        Calendar bdtCalendar = new GregorianCalendar();
        bdtCalendar.setTime(pBirthDt);
        
        // День рождения в году из даты pDt
        Calendar x_this_bdt_calendar = new GregorianCalendar(
                dtCalendar.get(Calendar.YEAR),
                bdtCalendar.get(Calendar.MONTH),
                bdtCalendar.get(Calendar.DAY_OF_MONTH));

        // Поправка для года если день рождения в году даты pDt еще не достигнут
        Integer ageYearsDelta = 0;
        if (dtCalendar.before(x_this_bdt_calendar)) ageYearsDelta = 1;
        // Возвраст. лет
        Integer ageYears = dtCalendar.get(Calendar.YEAR) - bdtCalendar.get(Calendar.YEAR) -
                ageYearsDelta;
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

        }
        else
            ageDays = dtCalendar.get(Calendar.DAY_OF_MONTH) - bdtCalendar.get(Calendar.DAY_OF_MONTH);
        Integer x_age_months = 12 * ageYearsDelta - bdtCalendar.get(Calendar.MONTH) +
                dtCalendar.get(Calendar.MONTH) - ageMonthsDelta;
        return new AgeDto(ageYears.shortValue(), x_age_months.shortValue(), ageDays.shortValue());
    }       
    
    public Integer compactAge() {
        return 10000 * getYears() + 100 * getMonths() + getDays();
    }        
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.years;
        hash = 53 * hash + this.months;
        hash = 53 * hash + this.days;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AgeDto other = (AgeDto) obj;
        if (this.years != other.years) {
            return false;
        }
        if (this.months != other.months) {
            return false;
        }
        if (this.days != other.days) {
            return false;
        }
        return true;
    }        

    @Override
    public String toString() {
        return AgeDto.class.getName()
                + "{" + "years=" + String.valueOf(years)
                + ", months=" + String.valueOf(months)
                + ", days=" + String.valueOf(days)
                + '}';
    }
        
}
