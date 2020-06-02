package ru.avers.informica.infcfg;

import ru.avers.informica.dto.CAge;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
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

    private Integer getInt(Short p_value) {
        return p_value == null ? null : p_value.intValue();
    }
}
