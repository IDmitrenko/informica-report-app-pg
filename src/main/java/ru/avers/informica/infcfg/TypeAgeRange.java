package ru.avers.informica.infcfg;

import ru.avers.informica.dto.CAge;
import ru.avers.informica.dto.CAgeInterval;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
@XmlType(name = "")
@XmlEnum
public enum TypeAgeRange {
    
    @XmlEnumValue(IConst.s_age_category_all)
    tAll(IConst.s_age_category_all, new CAgeInterval(-99, 0, 99, 0)),
    
    @XmlEnumValue(IConst.s_age_category_m2_to_y3)
    t_m2_to_y3(IConst.s_age_category_m2_to_y3, new CAgeInterval(-99, 0, 3, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y3_to_y5)
    t_y3_to_y5(IConst.s_age_category_y3_to_y5, new CAgeInterval(3, 0, 5, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y5_to_y7m6)
    t_y5_to_y7m6(IConst.s_age_category_y5_to_y7m6, new CAgeInterval(5, 0, 7, 6)),
    
    @XmlEnumValue(IConst.s_age_category_m2_to_m6)
    t_m2_to_m6(IConst.s_age_category_m2_to_m6, new CAgeInterval(-99, 0, 0, 6)),
    
    @XmlEnumValue(IConst.s_age_category_m6_to_y1)
    t_m6_to_y1(IConst.s_age_category_m6_to_y1, new CAgeInterval(0, 6, 1, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y1_to_y1m6)
    t_y1_to_y1m6(IConst.s_age_category_y1_to_y1m6, new CAgeInterval(1, 0, 1, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y1m6_to_y2)
    t_y1m6_to_y2(IConst.s_age_category_y1m6_to_y2, new CAgeInterval(1, 6, 2, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y2_to_y2m6)
    t_y2_to_y2m6(IConst.s_age_category_y2_to_y2m6, new CAgeInterval(2, 0, 2, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y2m6_to_y3)
    t_y2m6_to_y3(IConst.s_age_category_y2m6_to_y3, new CAgeInterval(2, 6, 3, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y3_to_y3m6)
    t_y3_to_y3m6(IConst.s_age_category_y3_to_y3m6, new CAgeInterval(3, 0, 3, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y3m6_to_y4)
    t_y3m6_to_y4(IConst.s_age_category_y3m6_to_y4, new CAgeInterval(3, 6, 4, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y4_to_y4m6)
    t_y4_to_y4m6(IConst.s_age_category_y4_to_y4m6, new CAgeInterval(4, 0, 4, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y4m6_to_y5)
    t_y4m6_to_y5(IConst.s_age_category_y4m6_to_y5, new CAgeInterval(4, 6, 5, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y5_to_y5m6)
    t_y5_to_y5m6(IConst.s_age_category_y5_to_y5m6, new CAgeInterval(5, 0, 5, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y5m6_to_y6)
    t_y5m6_to_y6(IConst.s_age_category_y5m6_to_y6, new CAgeInterval(5, 6, 6, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y6_to_y6m6)
    t_y6_to_y6m6(IConst.s_age_category_y6_to_y6m6, new CAgeInterval(6, 0, 6, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y6m6_to_y7)
    t_y6m6_to_y7(IConst.s_age_category_y6m6_to_y7, new CAgeInterval(6, 6, 7, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y7_to_y7m6)
    t_y7_to_y7m6(IConst.s_age_category_y7_to_y7m6, new CAgeInterval(7, 0, 7, 6)),
    
    @XmlEnumValue(IConst.s_age_category_y7m6_plus)
    t_y7m6_plus(IConst.s_age_category_y7m6_plus, new CAgeInterval(7, 6, 99, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y5_to_y7)
    t_y5_to_y7(IConst.s_age_category_y5_to_y7, new CAgeInterval(5, 0, 7, 0)),

    @XmlEnumValue(IConst.s_age_category_m2_to_y1)
    t_m2_to_y1(IConst.s_age_category_m2_to_y1, new CAgeInterval(-99, 0, 1, 0)),

    @XmlEnumValue(IConst.s_age_category_y1_to_y2)
    t_y1_to_y2(IConst.s_age_category_y1_to_y2, new CAgeInterval(1, 0, 2, 0)),

    @XmlEnumValue(IConst.s_age_category_y2_to_y3)
    t_y2_to_y3(IConst.s_age_category_y2_to_y3, new CAgeInterval(2, 0, 3, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y3_to_y4)
    t_y3_to_y4(IConst.s_age_category_y3_to_y4, new CAgeInterval(3, 0, 4, 0)),

    @XmlEnumValue(IConst.s_age_category_y4_to_y5)
    t_y4_to_y5(IConst.s_age_category_y4_to_y5, new CAgeInterval(4, 0, 5, 0)),

    @XmlEnumValue(IConst.s_age_category_y5_to_y6)
    t_y5_to_y6(IConst.s_age_category_y5_to_y6, new CAgeInterval(5, 0, 6, 0)),

    @XmlEnumValue(IConst.s_age_category_y6_to_y7)
    t_y6_to_y7(IConst.s_age_category_y6_to_y7, new CAgeInterval(6, 0, 7, 0)),
    
    @XmlEnumValue(IConst.s_age_category_y7_plus)
    t_y7_plus(IConst.s_age_category_y7_plus, new CAgeInterval(7, 0, 99, 0)),    
    
    @XmlEnumValue(IConst.s_age_category_y0_to_y3)
    t_y0_to_y3(IConst.s_age_category_y0_to_y3, new CAgeInterval(-99, 0, 3, 0)),

    @XmlEnumValue(IConst.s_age_category_y3_to_y7)
    t_y3_to_y7(IConst.s_age_category_y3_to_y7, new CAgeInterval(3, 0, 7, 0));
    
    private final String value;
    private final CAgeInterval ageInterval;
    
    private TypeAgeRange(String pVal) {
        this(pVal, null);
    }
    private TypeAgeRange(String pVal, CAgeInterval pAgeInterval) {
        value = pVal;
        ageInterval = pAgeInterval;
    }
    public String value() {
        return value;
    }
    public CAgeInterval getAgeInterval() {
        return ageInterval;
    }

    public static TypeAgeRange fromValue(String pVal) {
        for(TypeAgeRange item: TypeAgeRange.values())
            if(item.value.equals(pVal)) return item;
        throw new IllegalArgumentException(pVal);
    }
    
    public static List<TypeAgeRange> fromAge(CAge pAge) {
        List<TypeAgeRange> categories = new ArrayList<TypeAgeRange>();
        for(TypeAgeRange item: TypeAgeRange.values()) {
            if (item.getAgeInterval().containsLeft(pAge))
                categories.add(item);
        }
        return categories;
    }    
        
}