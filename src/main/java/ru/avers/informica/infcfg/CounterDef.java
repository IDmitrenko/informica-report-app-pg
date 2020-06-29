package ru.avers.informica.infcfg;

import ru.avers.informica.filtersinqry.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CounterDef {

    private String id,
            descr,
                   name;
    private List<IFilter> filters;
    private AgeRangeBdt ageRange;

    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                .append("{id=").append(id)
                .append(", name=").append(name)
                .append(", descr=").append(descr)
                .append("}")
            .toString();
    }
    
    @XmlAttribute
    @XmlID
    public String getId() {
        return id;
    }
    public void setId(String pId) {
        this.id = pId;
    }

    @XmlAttribute
    public String getDescr() {
        return descr;
    }
    public void setDescr(String pDescr) {
        this.descr = pDescr;
    }    
    
    @XmlAttribute
    public String getName() {
        return name;
    }
    public void setName(String pName) {
        this.name = pName;
    }    
    
    @XmlElements(value = {
        @XmlElement(name = "filter-date", type= FilterDate.class),
        @XmlElement(name = "filter-date-time", type= FilterDateTime.class),
        @XmlElement(name = "filter-year", type= FilterYear.class),
        @XmlElement(name = "filter", type= GenericFilter.class),
        @XmlElement(name = "filter-codes-list", type= CodesCollectionFilter.class),
            @XmlElement(name = "increment-by-priorities", type=IncrementByPriorities.class),
            @XmlElement(name = "filter-bean", type=BeanFilter.class)
    })
    public List<IFilter> getFilters() {
        if (filters == null) {
            filters = new ArrayList<IFilter>();
        }
        return filters;
    }
    
    @XmlElement(name = "age-range")
    public AgeRangeBdt getAgeRange() {
        return ageRange;
    }    
    public void setAgeRange(AgeRangeBdt pAgeRange) {
        ageRange = pAgeRange;
    }
    
}
