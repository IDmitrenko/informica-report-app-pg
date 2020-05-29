package ru.avers.informica.filtersinqry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.utils.CUtil;
import ru.avers.informica.utils.DateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FilterDate implements IFilter<Date>, IFieldFilterParams, IsFilterDate {
    private static final Logger s_logger = LoggerFactory.getLogger(FilterDate.class);

    protected String m_field;
    protected ComparisonType m_comparison;
    protected Integer m_year,
                      m_month,
                      m_day,
                      m_year_offset;
    protected Date m_rep_date;
    protected boolean m_use_educ_date = false;

    @XmlAttribute
    public Integer getYear() {
        return m_year;
    }
    public void setYear(Integer m_year) {
        this.m_year = m_year;
    }

    @XmlAttribute
    public Integer getMonth() {
        return m_month;
    }
    public void setMonth(Integer m_month) {
        this.m_month = m_month;
    }

    @XmlAttribute
    public Integer getDay() {
        return m_day;
    }
    public void setDay(Integer m_day) {
        this.m_day = m_day;
    }

    @XmlAttribute
    public Integer getYearOffset() {
        return m_year_offset;
    }   
    public void setYearOffset(Integer p_rep_year_inc) {
        this.m_year_offset = p_rep_year_inc;
    }

    @Override
    @XmlAttribute(required = true)
    public String getField() {
        return m_field;
    }    
    public void setField(String p_field) {
        m_field = p_field;
    }

    @Override
    @XmlAttribute(name = "cmp", required = true)
    public ComparisonType getComparison() {
        return m_comparison;
    }    
    public void setComparison(ComparisonType p_comparison) {
        m_comparison = p_comparison;
    }
    
    @Override
    @XmlAttribute
    public boolean isEducDate() {
        return m_use_educ_date;
    }
    
    public void setEducDate(boolean p_use_educ_date) {
        m_use_educ_date = p_use_educ_date;                
    }
    
    @Override
    public Date getRepDate() {
        return m_rep_date;
    }
    @Override
    public void setRepDate(Date p_date) {
        m_rep_date = p_date;
    }   
    
    @Override
    public Date getValue() {
        if (m_year == null && m_month == null && m_day == null && m_year_offset == null) {
            return m_rep_date;
        }
        Calendar x_calendar = Calendar.getInstance();
        x_calendar.setTime(m_rep_date);
        if (m_year != null) x_calendar.set(Calendar.YEAR, m_year);
        if (m_year_offset != null) x_calendar.add(Calendar.YEAR, m_year_offset);
        if (m_month != null) x_calendar.set(Calendar.MONTH, m_month);
        if (m_day != null) x_calendar.set(Calendar.DAY_OF_MONTH, m_day);
        return x_calendar.getTime();
    }
    
    @Override
    @SuppressWarnings("null")
    public boolean isPassed(Date p_field_value) throws FilterException {
        if (m_comparison == null) 
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", m_field));
        s_logger.debug("Calc filter for field={}, m_comparison={}, field_value={}", m_field, m_comparison, p_field_value);

        Date x_field_value = p_field_value;
        if (x_field_value != null)
            x_field_value = prepDate(p_field_value);
        Date x_filter_date = prepDate(getValue());
        switch(m_comparison) {
            case isnull: 
                return x_field_value == null;
            case isnotnull: 
                return x_field_value != null;
            case equal: 
                return CUtil.equals(x_field_value, x_filter_date);
            case notEqual: 
                return !CUtil.equals(x_field_value, x_filter_date);
            case greater: 
                return x_field_value != null && x_field_value.after(x_filter_date);
            case greaterOrEqual: 
                return x_field_value != null 
                        && (x_field_value.after(x_filter_date) || x_field_value.equals(x_filter_date));
            case less: 
                return x_field_value != null && x_field_value.before(x_filter_date);
            case lessOrEqual: 
                return x_field_value != null 
                        && (x_field_value.before(x_filter_date) || x_field_value.equals(x_filter_date));
            case like: 
            case in: 
            case notIn: 
                throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                                            String.valueOf(m_comparison), m_field));
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s",
                                        String.valueOf(m_comparison), m_field));
    }    

    protected Date prepDate(Date p_date) {
        return DateUtil.clearDateTimePart(p_date);
    }
    
}
