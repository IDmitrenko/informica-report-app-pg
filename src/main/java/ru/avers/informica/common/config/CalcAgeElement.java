package ru.avers.informica.common.config;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class CalcAgeElement extends DateElement {
    private static final String s_attr_curr_date = "curr-date",
                                s_attr_educ_year_offset = "offset";
    private Boolean m_curr_date;
    private Integer m_educ_year_offset;

    public CalcAgeElement() {
    }

    public CalcAgeElement(boolean p_curr_date) {
        m_curr_date = p_curr_date;
    }    
    
    public CalcAgeElement(Integer p_year) {
        super(p_year);
    }

    public CalcAgeElement(Integer p_year, Integer p_month) {
        super(p_year, p_month);
    }

    public CalcAgeElement(Integer p_year, Integer p_month, Integer p_day) {
        super(p_year, p_month, p_day);
    }        

    @XmlAttribute(name = s_attr_curr_date)
    public Boolean isCurrDate() {
        return m_curr_date;
    }

    public void setCurrDate(Boolean p_curr_date) {
        m_curr_date = p_curr_date;
    }

    @XmlAttribute(name = s_attr_educ_year_offset)
    public Integer getYearOffset() {
        return m_educ_year_offset;
    }

    public void setYearOffset(Integer p_offset) {
        m_educ_year_offset = p_offset;
    }

    @Override
    public int hashCode() {
        int hash = 5 + super.hashCode();
        hash = 37 * hash + (this.m_curr_date != null ? this.m_curr_date.hashCode() : 0);
        hash = 37 * hash + (this.m_educ_year_offset != null ? this.m_educ_year_offset.hashCode() : 0);
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
        if (!super.equals(obj)) {
            return false;
        }        
        final CalcAgeElement other = (CalcAgeElement) obj;
        if (this.m_curr_date != other.m_curr_date && (this.m_curr_date == null || !this.m_curr_date.equals(other.m_curr_date))) {
            return false;
        }
        if (this.m_educ_year_offset != other.m_educ_year_offset && (this.m_educ_year_offset == null || !this.m_educ_year_offset.equals(other.m_educ_year_offset))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CalcAgeElement [");
        builder.append("super=").append(super.toString());
        builder.append(", getYearOffset=").append(getYearOffset());
        builder.append(", isCurrDate=").append(isCurrDate());
        builder.append("]");
        return builder.toString();
    }        
    
}
