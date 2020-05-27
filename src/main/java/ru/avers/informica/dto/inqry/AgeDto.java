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
    private short m_years;
    private short m_months;
    private short m_days;

    public AgeDto() {
    }    
    
    public AgeDto(short p_years, short p_months, short p_days) {
        m_years = p_years;
        m_months = p_months;
        m_days = p_days;
    }

    public short getDays() {
        return m_days;
    }

    public void setDays(short p_days) {
        this.m_days = p_days;
    }            
    
    public short getMonths() {
        return m_months;
    }

    public void setMonths(short p_months) {
        this.m_months = p_months;
    }    
    
    public short getYears() {
        return m_years;
    }

    public void setYears(short p_years) {
        this.m_years = p_years;
    }

    public Date getBirthDate(Date p_curr_date) {
        Calendar x_calendar = new GregorianCalendar();
        x_calendar.setTime(p_curr_date);
        x_calendar.add(Calendar.YEAR, -m_years);
        x_calendar.add(Calendar.MONTH, -m_months);
        x_calendar.add(Calendar.DAY_OF_MONTH, -m_days);
        return x_calendar.getTime();
    }
    
    /**
     * Вычислить возраст
     * @param p_dt дата на которую вычисляется возраст
     * @param p_birth_dt дата рождения
     * @return возраст
     */    
    public static AgeDto calculateAge(Date p_dt, Date p_birth_dt) {
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
    
    public Integer compactAge() {
        return 10000 * getYears() + 100 * getMonths() + getDays();
    }        
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.m_years;
        hash = 53 * hash + this.m_months;
        hash = 53 * hash + this.m_days;
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
        if (this.m_years != other.m_years) {
            return false;
        }
        if (this.m_months != other.m_months) {
            return false;
        }
        if (this.m_days != other.m_days) {
            return false;
        }
        return true;
    }        

    @Override
    public String toString() {
        return AgeDto.class.getName()
                + "{" + "m_years=" + String.valueOf(m_years)
                + ", m_months=" + String.valueOf(m_months)
                + ", m_days=" + String.valueOf(m_days)
                + '}';
    }
        
}
