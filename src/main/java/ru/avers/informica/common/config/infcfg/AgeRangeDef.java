package ru.avers.informica.common.config.infcfg;

import ru.avers.informica.dto.CAge;
import ru.avers.informica.entities.Prll4stgAges;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AgeRangeDef {
    private String m_id;
    private List<TypeAgeRange> m_age_ranges;

    @XmlAttribute
    @XmlID
    public String getId() {
        return m_id;
    }

    public void setId(String m_id) {
        this.m_id = m_id;
    }

    @XmlElement(name = "range")
    public List<TypeAgeRange> getAgeRanges() {
        if (m_age_ranges == null) m_age_ranges = new ArrayList<TypeAgeRange>();
        return m_age_ranges;
    }

    public List<TypeAgeRange> getAgeRangesForAge(CAge p_age) {
        List<TypeAgeRange> x_res = new ArrayList<TypeAgeRange>();
        for (TypeAgeRange x_item : getAgeRanges()) {
            if (x_item.getAgeInterval().containsLeft(p_age))
                x_res.add(x_item);
        }
        return x_res;
    }

    Collection<TypeAgeRange> getAgeRangesForPrllAges(List<Prll4stgAges> p_ages) {
        Set<TypeAgeRange> x_res = EnumSet.noneOf(TypeAgeRange.class);
        for (Prll4stgAges x_prll_ages : p_ages) {
            for (TypeAgeRange x_item : getAgeRanges()) {
                if (intersectsAgeRange(x_prll_ages, x_item))
                    x_res.add(x_item);
            }                        
        }
        return x_res;
    }

    private boolean intersectsAgeRange(Prll4stgAges p_prll_ages, TypeAgeRange p_item) {
        // (b1 - e1) (b2 - e2)
        // b1 <= e2 and b2 <= e1
        CAge x_from = new CAge(getInt(p_prll_ages.getFromYear()), getInt(p_prll_ages.getFromMonth()), getInt(p_prll_ages.getFromDay()));
        CAge x_to = new CAge(getInt(p_prll_ages.getToYear()), getInt(p_prll_ages.getToMonth()), getInt(p_prll_ages.getToDay()));
        //CAgeInterval x_prll_interval = new CAgeInterval(x_from, x_to);        
        CAge x_item_from = p_item.getAgeInterval().getBegin();
        CAge x_item_to = p_item.getAgeInterval().getEnd();
        
        boolean x_res = x_item_to == null || x_item_to.compareTo(x_from) >= 0;
        x_res &= x_item_from == null || x_to.compareTo(x_item_from) >= 0;        
        return x_res;
    }
    
    private Integer getInt(Short p_value) {
        return p_value == null ? null : p_value.intValue();
    }
}
