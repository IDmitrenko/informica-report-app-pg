package ru.avers.informica.infcfg;

import ru.avers.informica.dto.CAge;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AgeRangeDef {
    private String id;
    private List<TypeAgeRange> ageRanges;

    @XmlAttribute
    @XmlID
    public String getId() {
        return id;
    }

    public void setId(String pId) {
        this.id = pId;
    }

    @XmlElement(name = "range")
    public List<TypeAgeRange> getAgeRanges() {
        if (ageRanges == null) ageRanges = new ArrayList<TypeAgeRange>();
        return ageRanges;
    }

    public List<TypeAgeRange> getAgeRangesForAge(CAge pAge) {
        List<TypeAgeRange> res = new ArrayList<TypeAgeRange>();
        for (TypeAgeRange item : getAgeRanges()) {
            if (item.getAgeInterval().containsLeft(pAge))
                res.add(item);
        }
        return res;
    }

    private Integer getInt(Short pValue) {
        return pValue == null ? null : pValue.intValue();
    }
}
