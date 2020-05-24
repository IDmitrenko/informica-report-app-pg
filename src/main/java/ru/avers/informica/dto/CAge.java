package ru.avers.informica.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeAge", propOrder={"years", "months", "days"})
public class CAge implements IDTO, Comparable<CAge> {
    private Integer m_years, m_months, m_days;
    
    @XmlAttribute(name="years")
    public Integer getYears() { return m_years; }
    public void setYears(Integer p_val) { m_years = p_val; }
    
    @XmlAttribute(name="months")
    public Integer getMonths() { return m_months; }
    public void setMonths(Integer p_val) { m_months = p_val; }
    
    @XmlAttribute(name="days")
    public Integer getDays() { return m_days; }
    public void setDays(Integer p_val) { m_days = p_val; }
    
    public CAge() { this(null); }
    public CAge(Integer p_years) { this(p_years, null); }
    public CAge(Integer p_years, Integer p_months) { this(p_years, p_months, null); }
    public CAge(Integer p_years, Integer p_months, Integer p_days) {
        m_years = p_years;
        m_months = p_months;
        m_days = p_days;
    }

    @Override
    public int hashCode() {
        int x_hash = 7;
        x_hash = 83 * x_hash + (m_years != null ? m_years.hashCode() : 0);
        x_hash = 83 * x_hash + (m_months != null ? m_months.hashCode() : 0);
        x_hash = 83 * x_hash + (m_days != null ? m_days.hashCode() : 0);
        return x_hash;
    }

    @Override
    public boolean equals(Object p_obj) {
        if(p_obj == null) return false;
        if(getClass() != p_obj.getClass()) return false;

        final CAge p_other = (CAge)p_obj;
        return
            (compare(m_years, p_other.m_years) == 0) &&
            (compare(m_months, p_other.m_months) == 0) &&
            (compare(m_days, p_other.m_days) == 0);
    }
    
    @Override
    public int compareTo(CAge p_other) {
        if(p_other == null)
            throw new NullPointerException("compare object of CAge to null");
        int x_res = compare(m_years, p_other.m_years);
        if(x_res != 0) return x_res;
        x_res = compare(m_months, p_other.m_months);
        if(x_res != 0) return x_res;
        return compare(m_days, p_other.m_days);
    }
    
    static private int compare(Integer p_o1, Integer p_o2) {
        int x_o1 = (p_o1 == null ? 0 : p_o1), x_o2 = (p_o2 == null ? 0 : p_o2);
        if(x_o1 < x_o2) return -1;
        if(x_o1 > x_o2) return 1;
        return 0;
    }
    
    @Override
    public String toString() {
        return CAge.class.getName() + " {" + 
                "years = " + String.valueOf(m_years) +
                "; months = " + String.valueOf(m_months) +
                "; days = " + String.valueOf(m_days) +
                "}";
    }
    
    static public CAge get(Date p_start, Date p_finish) {
        CAge x_rv = null;
        //  TODO
        //  see AgeDto.calculateAge
        return x_rv;
    }
    
}
