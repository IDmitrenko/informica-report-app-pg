package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;
import ru.avers.informica.dto.IDateElement;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Настройка. дата по компонентам год, месяц, день
 * @author Dias
 */
public class DateElement implements IDateElement, IDTO {
    private static final String S_ATTR_YEAR = "year",
                                S_ATTR_MONTH = "month",
                                S_ATTR_DAY = "day";

    private Integer year, month, day;
    
    public DateElement() { 
    }

    public DateElement(Integer pYear) {
        this(pYear, null, null);
    }        
    
    public DateElement(Integer pYear, Integer pMonth) {
        this(pYear, pMonth, null);
    }    
    
    public DateElement(Integer pYear, Integer pMonth, Integer pDay) {
        year = pYear;
        month = pMonth;
        day = pDay;
    }
    
    @XmlAttribute(name = S_ATTR_YEAR)
    @Override
    public Integer getYear() {
        return year;
    }
    @Override
    public void setYear(Integer pVal) {
        year = pVal;
    }
    
    @XmlAttribute(name = S_ATTR_MONTH)
    @Override
    public Integer getMonth() {
        return month;
    }
    @Override
    public void setMonth(Integer pVal) {
        month = pVal;
    }
    
    @XmlAttribute(name = S_ATTR_DAY)
    @Override
    public Integer getDay() {
        return day;
    }
    @Override
    public void setDay(Integer pVal) {
        day = pVal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.year != null ? this.year.hashCode() : 0);
        hash = 41 * hash + (this.month != null ? this.month.hashCode() : 0);
        hash = 41 * hash + (this.day != null ? this.day.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!getClass().isAssignableFrom(obj.getClass()) &&
                !obj.getClass().isAssignableFrom(getClass())) {
            return false;
        }        
        final DateElement other = (DateElement) obj;
        if (this.year != other.year &&
                (this.year == null || !this.year.equals(other.year))) {
            return false;
        }
        if (this.month != other.month &&
                (this.month == null || !this.month.equals(other.month))) {
            return false;
        }
        if (this.day != other.day && (this.day == null || !this.day.equals(other.day))) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                        .append(" [getYear=").append(getYear())
                        .append(", getMonth=").append(getMonth())
                        .append(", getDay=").append(getDay())
                        .append("]")
                .toString();
    }        
    
    @Override
    public void set(IDateElement pVal) {
        if (pVal == null) throw new IllegalArgumentException();

        setYear(pVal.getYear());
        setMonth(pVal.getMonth());
        setDay(pVal.getDay());
    }
    
}
