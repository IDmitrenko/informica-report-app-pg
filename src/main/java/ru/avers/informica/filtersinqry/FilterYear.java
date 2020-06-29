package ru.avers.informica.filtersinqry;

import ru.avers.informica.dao.filtersort.IFieldFilterParams;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Dias
 */
public class FilterYear extends GenericFilter implements IFieldFilterParams, IsFilterDate {

    protected String field;
    protected IFieldFilterParams.ComparisonType comparison;
    protected Integer year,
                      yearOffset;
    protected Date repDate;
    protected boolean isEducDate = false;

    @XmlAttribute
    public Integer getYearOffset() {
        return yearOffset;
    }   
    public void setYearOffset(Integer pYearOffset) {
        this.yearOffset = pYearOffset;
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
    public Integer getValue() {
        int filterYear;
        if (year != null) {
            filterYear = year;
        } else {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(repDate);
            filterYear = calendar.get(Calendar.YEAR);
        }                
        if (yearOffset != null) {
            filterYear += yearOffset;
        }
        return filterYear;
    }
    
}
