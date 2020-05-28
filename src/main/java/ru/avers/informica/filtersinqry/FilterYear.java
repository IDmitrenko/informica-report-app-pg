package ru.avers.informica.filtersinqry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger s_logger = LoggerFactory.getLogger(FilterYear.class);

    protected String m_field;
    protected IFieldFilterParams.ComparisonType m_comparison;
    protected Integer m_year,
                      m_year_offset;
    protected Date m_rep_date;
    protected boolean m_use_educ_date = false;

    @XmlAttribute
    public Integer getYearOffset() {
        return m_year_offset;
    }   
    public void setYearOffset(Integer p_rep_year_inc) {
        this.m_year_offset = p_rep_year_inc;
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
    public Integer getValue() {
        int x_filter_year;
        if (m_year != null) x_filter_year = m_year;
        else {
            Calendar x_calendar = GregorianCalendar.getInstance();
            x_calendar.setTime(m_rep_date);            
            x_filter_year = x_calendar.get(Calendar.YEAR);
        }                
        if (m_year_offset != null) x_filter_year += m_year_offset;
        return x_filter_year;
    }
    
}
