package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;
import ru.avers.informica.dto.IDateElement;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Настройка. дата по компонентам год, месяц, день
 * @author Dias
 */
public class DateElement implements IDateElement, IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(DateElement.class);
    private static final String s_attr_year = "year",
                                s_attr_month = "month",
                                s_attr_day = "day";

    private Integer m_year, m_month, m_day;
    
    public DateElement() { 
    }

    public DateElement(Integer p_year) {
        this(p_year, null, null);
    }        
    
    public DateElement(Integer p_year, Integer p_month) {
        this(p_year, p_month, null);
    }    
    
    public DateElement(Integer p_year, Integer p_month, Integer p_day) {
        m_year = p_year;
        m_month = p_month;
        m_day = p_day;
    }
    
    @XmlAttribute(name = s_attr_year)
    @Override
    public Integer getYear() {
        return m_year;
    }
    @Override
    public void setYear(Integer p_val) {
        m_year = p_val;
    }
    
    @XmlAttribute(name = s_attr_month)
    @Override
    public Integer getMonth() {
        return m_month;
    }
    @Override
    public void setMonth(Integer p_val) {
        m_month = p_val;
    }
    
    @XmlAttribute(name = s_attr_day)
    @Override
    public Integer getDay() {
        return m_day;
    }
    @Override
    public void setDay(Integer p_val) {
        m_day = p_val;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.m_year != null ? this.m_year.hashCode() : 0);
        hash = 41 * hash + (this.m_month != null ? this.m_month.hashCode() : 0);
        hash = 41 * hash + (this.m_day != null ? this.m_day.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!getClass().isAssignableFrom(obj.getClass()) && !obj.getClass().isAssignableFrom(getClass())) {
            return false;
        }        
        final DateElement other = (DateElement) obj;
        if (this.m_year != other.m_year && (this.m_year == null || !this.m_year.equals(other.m_year))) {
            return false;
        }
        if (this.m_month != other.m_month && (this.m_month == null || !this.m_month.equals(other.m_month))) {
            return false;
        }
        if (this.m_day != other.m_day && (this.m_day == null || !this.m_day.equals(other.m_day))) {
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
    public void set(IDateElement p_val) {
        if (p_val == null) throw new IllegalArgumentException();

        setYear(p_val.getYear());
        setMonth(p_val.getMonth());
        setDay(p_val.getDay());        
    }
    
}
