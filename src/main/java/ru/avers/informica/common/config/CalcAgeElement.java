package ru.avers.informica.common.config;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class CalcAgeElement extends DateElement {
    private static final String S_ATTR_CURR_DATE = "curr-date",
                                S_ATTR_EDUC_YEAR_OFFSET = "offset";
    private Boolean currDate;
    private Integer educYearOffset;

    public CalcAgeElement() {
    }

    public CalcAgeElement(boolean pCurrDate) {
        currDate = pCurrDate;
    }    
    
    public CalcAgeElement(Integer pYear) {
        super(pYear);
    }

    public CalcAgeElement(Integer pYear, Integer pMonth) {
        super(pYear, pMonth);
    }

    public CalcAgeElement(Integer pYear, Integer pMonth, Integer pDay) {
        super(pYear, pMonth, pDay);
    }        

    @XmlAttribute(name = S_ATTR_CURR_DATE)
    public Boolean isCurrDate() {
        return currDate;
    }

    public void setCurrDate(Boolean pCurrDate) {
        currDate = pCurrDate;
    }

    @XmlAttribute(name = S_ATTR_EDUC_YEAR_OFFSET)
    public Integer getYearOffset() {
        return educYearOffset;
    }

    public void setYearOffset(Integer pOffset) {
        educYearOffset = pOffset;
    }

    @Override
    public int hashCode() {
        int hash = 5 + super.hashCode();
        hash = 37 * hash + (this.currDate != null ? this.currDate.hashCode() : 0);
        hash = 37 * hash + (this.educYearOffset != null ? this.educYearOffset.hashCode() : 0);
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
        if (this.currDate != other.currDate &&
                (this.currDate == null || !this.currDate.equals(other.currDate))) {
            return false;
        }
        if (this.educYearOffset != other.educYearOffset &&
                (this.educYearOffset == null || !this.educYearOffset.equals(other.educYearOffset))) {
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
