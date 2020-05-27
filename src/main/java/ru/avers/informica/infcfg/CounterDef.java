package ru.avers.informica.infcfg;

import ru.avers.informica.dao.filtersort.IFilter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CounterDef {
    private String m_id,
                   m_descr, 
                   m_name;
    private List<IFilter> m_filters;
    private AgeRangeBdt m_age_range;

    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                .append("{id=").append(m_id)
                .append(", name=").append(m_name)
                .append(", descr=").append(m_descr)
                .append("}")
            .toString();
    }
    
    @XmlAttribute
    @XmlID
    public String getId() {
        return m_id;
    }
    public void setId(String p_id) {
        this.m_id = p_id;
    }

    @XmlAttribute
    public String getDescr() {
        return m_descr;
    }
    public void setDescr(String p_descr) {
        this.m_descr = p_descr;
    }    
    
    @XmlAttribute
    public String getName() {
        return m_name;
    }
    public void setName(String p_name) {
        this.m_name = p_name;
    }    
    
    @XmlElements(value = {
        @XmlElement(name = "filter-date", type=FilterDate.class),
        @XmlElement(name = "filter-date-time", type=FilterDateTime.class),
        @XmlElement(name = "filter-year", type=FilterYear.class),
        @XmlElement(name = "filter", type=GenericFilter.class),
        @XmlElement(name = "filter-codes-list", type=CodesCollectionFilter.class),
            @XmlElement(name = "increment-by-priorities", type=IncrementByPriorities.class),
            @XmlElement(name = "filter-bean", type=BeanFilter.class)
    })
    public List<IFilter> getFilters() {
        if (m_filters == null) m_filters = new ArrayList<IFilter>();
        return m_filters;
    }
    
    @XmlElement(name = "age-range")
    public AgeRangeBdt getAgeRange() {
        return m_age_range;
    }    
    public void setAgeRange(AgeRangeBdt p_age_range) {
        m_age_range = p_age_range;
    }
    
}
