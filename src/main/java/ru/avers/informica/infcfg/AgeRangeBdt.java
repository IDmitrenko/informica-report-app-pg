package ru.avers.informica.infcfg;

import ru.avers.informica.dto.CAge;
import ru.avers.informica.dto.informica.IInformicaChildCountable;
import ru.avers.informica.dto.informica.IInformicaCountable;
import ru.avers.informica.dto.inqry.AgeDto;
import ru.avers.informica.dto.util.Utils;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AgeRangeBdt {
    private AgeRangeDef m_age_range_def;
    private Integer m_calc_year,
                    m_calc_month,
                    m_calc_day,
                    m_calc_year_inc;
    private String dateField;

    @XmlAttribute(name="def")
    @XmlIDREF
    public AgeRangeDef getAgeRangeDef() {
        return m_age_range_def;
    }
    public void setAgeRangeDef(AgeRangeDef m_age_range_def) {
        this.m_age_range_def = m_age_range_def;
    }

    @XmlAttribute
    public String getDateField(){return dateField;}

    public void setDateField(final String dateField) {
        this.dateField = dateField;
    }

    @XmlAttribute
    public Integer getCalcYear() {
        return m_calc_year;
    }
    public void setCalcYear(Integer m_year) {
        this.m_calc_year = m_year;
    }

    @XmlAttribute
    public Integer getCalcMonth() {
        return m_calc_month;
    }
    public void setCalcMonth(Integer m_month) {
        this.m_calc_month = m_month;
    }

    @XmlAttribute
    public Integer getCalcDay() {
        return m_calc_day;
    }
    public void setCalcDay(Integer m_day) {
        this.m_calc_day = m_day;
    }

    @XmlAttribute
    public Integer getCalcYearInc() {
        return m_calc_year_inc;
    }   
    public void setCalcYearInc(Integer p_rep_year_inc) {
        this.m_calc_year_inc = p_rep_year_inc;
    }
    
    public Collection<TypeAgeRange> getAgeRanges(Date p_dt, Date p_bdt) {
        AgeDto x_age_dto = Utils.calculateAge(getCalcDate(p_dt), p_bdt);
        CAge x_chld_age = new CAge((int)x_age_dto.getYears(), (int)x_age_dto.getMonths(), (int)x_age_dto.getDays());
        return m_age_range_def.getAgeRangesForAge(x_chld_age);
    }    
    
    public Date getCalcDate(Date p_dt) {
        if (m_calc_year == null && m_calc_month == null && m_calc_day == null && m_calc_year_inc == null)
            return p_dt;
        Calendar x_calendar = Calendar.getInstance();
        x_calendar.setTime(p_dt);
        if (m_calc_year != null) x_calendar.set(Calendar.YEAR, m_calc_year);
        if (m_calc_year_inc != null) x_calendar.add(Calendar.YEAR, m_calc_year_inc);
        if (m_calc_month != null) x_calendar.set(Calendar.MONTH, m_calc_month);
        if (m_calc_day != null) x_calendar.set(Calendar.DAY_OF_MONTH, m_calc_day);
        return x_calendar.getTime();
    }
    
    public Collection<TypeAgeRange> getAgeRanges(Date p_rep_date, IInformicaCountable p_countable)
            throws FspeoException, ReportExceprion {
        if (p_countable instanceof IInformicaChildCountable) {
            if (getDateField() != null && !getDateField().isEmpty()) {
                Object beanValue = new Object(); // временно заглушка
//убрал временно  Object beanValue = CounterConfig.getBeanValue(getDateField(), p_countable);
                if (beanValue != null) {
                    return getAgeRanges((Date) beanValue,((IInformicaChildCountable) p_countable).getBdt());
                }

            }
            return getAgeRanges(p_rep_date, ((IInformicaChildCountable) p_countable).getBdt());
        }
/* TODO понять что в новой БД вместо Prll4stgAges
        else if (p_countable instanceof IInformicaVacantCountable) {
            return getAgeRanges(((IInformicaVacantCountable)p_countable).getAges());
        }
*/
        else throw new FspeoException("Неизвестный тип элемента для подсчета: " + p_countable.getClass().getName());
    }

}
