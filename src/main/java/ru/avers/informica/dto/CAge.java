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
    private Integer years, months, days;
    
    @XmlAttribute(name="years")
    public Integer getYears() {
        return years;
    }
    public void setYears(Integer pVal) {
        years = pVal;
    }
    
    @XmlAttribute(name="months")
    public Integer getMonths() {
        return months;
    }
    public void setMonths(Integer pVal) {
        months = pVal;
    }
    
    @XmlAttribute(name="days")
    public Integer getDays() {
        return days;
    }
    public void setDays(Integer pVal) {
        days = pVal;
    }
    
    public CAge() {
        this(null);
    }
    public CAge(Integer pYears) {
        this(pYears, null);
    }
    public CAge(Integer pYears, Integer pMonths) {
        this(pYears, pMonths, null);
    }
    public CAge(Integer pYears, Integer pMonths, Integer pDays) {
        years = pYears;
        months = pMonths;
        days = pDays;
    }

    @Override
    public int hashCode() {
        int x_hash = 7;
        x_hash = 83 * x_hash + (years != null ? years.hashCode() : 0);
        x_hash = 83 * x_hash + (months != null ? months.hashCode() : 0);
        x_hash = 83 * x_hash + (days != null ? days.hashCode() : 0);
        return x_hash;
    }

    @Override
    public boolean equals(Object pObj) {
        if(pObj == null) return false;
        if(getClass() != pObj.getClass()) return false;

        final CAge pOther = (CAge)pObj;
        return
            (compare(years, pOther.years) == 0) &&
            (compare(months, pOther.months) == 0) &&
            (compare(days, pOther.days) == 0);
    }
    
    @Override
    public int compareTo(CAge pOther) {
        if(pOther == null)
            throw new NullPointerException("compare object of CAge to null");
        int res = compare(years, pOther.years);
        if(res != 0) return res;
        res = compare(months, pOther.months);
        if(res != 0) return res;
        return compare(days, pOther.days);
    }
    
    static private int compare(Integer pO1, Integer pO2) {
        int o1 = (pO1 == null ? 0 : pO1), o2 = (pO2 == null ? 0 : pO2);
        if(o1 < o2) return -1;
        if(o1 > o2) return 1;
        return 0;
    }
    
    @Override
    public String toString() {
        return CAge.class.getName() + " {" + 
                "years = " + String.valueOf(years) +
                "; months = " + String.valueOf(months) +
                "; days = " + String.valueOf(days) +
                "}";
    }
    
    static public CAge get(Date pStart, Date pFinish) {
        CAge rv = null;
        //  TODO
        //  see AgeDto.calculateAge
        return rv;
    }
    
}
