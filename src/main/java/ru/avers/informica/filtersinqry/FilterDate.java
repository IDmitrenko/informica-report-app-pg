package ru.avers.informica.filtersinqry;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FilterDate implements IFilter<Date>, IFieldFilterParams, IsFilterDate {

    protected String field;
    protected ComparisonType comparison;
    protected Integer year,
            month,
            day,
                      yearOffset;
    protected Date repDate;
    protected boolean isEducDate = false;

    @XmlAttribute
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer pYear) {
        this.year = pYear;
    }

    @XmlAttribute
    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer pMonth) {
        this.month = pMonth;
    }

    @XmlAttribute
    public Integer getDay() {
        return day;
    }
    public void setDay(Integer pDay) {
        this.day = pDay;
    }

    @XmlAttribute
    public Integer getYearOffset() {
        return yearOffset;
    }   
    public void setYearOffset(Integer pYearOffset) {
        this.yearOffset = pYearOffset;
    }

    @Override
    @XmlAttribute(required = true)
    public String getField() {
        return field;
    }    
    public void setField(String pField) {
        field = pField;
    }

    @Override
    @XmlAttribute(name = "cmp", required = true)
    public ComparisonType getComparison() {
        return comparison;
    }    
    public void setComparison(ComparisonType pComparison) {
        comparison = pComparison;
    }
    
    @Override
    @XmlAttribute
    public boolean isEducDate() {
        return isEducDate;
    }
    
    public void setEducDate(boolean pIsEducDate) {
        isEducDate = pIsEducDate;
    }
    
    @Override
    public Date getRepDate() {
        return repDate;
    }
    @Override
    public void setRepDate(Date pDate) {
        repDate = pDate;
    }   
    
    @Override
    public Date getValue() {
        if (year == null && month == null && day == null && yearOffset == null) {
            return repDate;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(repDate);
        if (year != null) {
            calendar.set(Calendar.YEAR, year);
        }
        if (yearOffset != null) {
            calendar.add(Calendar.YEAR, yearOffset);
        }
        if (month != null) {
            calendar.set(Calendar.MONTH, month);
        }
        if (day != null) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }
        return calendar.getTime();
    }
    
    @Override
    @SuppressWarnings("null")
    public boolean isPassed(Date pFieldValue) throws FilterException {
        if (comparison == null)
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", field));
        log.debug("Calc filter for field={}, m_comparison={}, field_value={}",
                field, comparison, pFieldValue);

        Date fieldValue = pFieldValue;
        if (fieldValue != null) {
            fieldValue = prepDate(pFieldValue);
        }
        Date filterDate = prepDate(getValue());
        switch(comparison) {
            case isnull: 
                return fieldValue == null;
            case isnotnull: 
                return fieldValue != null;
            case equal: 
                return CUtil.equals(fieldValue, filterDate);
            case notEqual: 
                return !CUtil.equals(fieldValue, filterDate);
            case greater: 
                return fieldValue != null && fieldValue.after(filterDate);
            case greaterOrEqual: 
                return fieldValue != null
                        && (fieldValue.after(filterDate) || fieldValue.equals(filterDate));
            case less: 
                return fieldValue != null && fieldValue.before(filterDate);
            case lessOrEqual: 
                return fieldValue != null
                        && (fieldValue.before(filterDate) || fieldValue.equals(filterDate));
            case like: 
            case in: 
            case notIn: 
                throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                                            String.valueOf(comparison), field));
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s",
                                        String.valueOf(comparison), field));
    }    

    protected Date prepDate(Date pDate) {
        return DateUtil.clearDateTimePart(pDate);
    }
    
}
