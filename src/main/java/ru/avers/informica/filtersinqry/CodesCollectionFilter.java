package ru.avers.informica.filtersinqry;

import ru.avers.informica.exception.FilterException;
import ru.avers.informica.utils.CUtil;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CodesCollectionFilter implements IFilter<Object> {
    
    @XmlEnum(String.class)
    public enum ComparisonType {
        @XmlEnumValue("is empty")
        isEmpty(false),
        @XmlEnumValue("is not empty")
        isNotEmpty(false),
        @XmlEnumValue("any")
        any,
        @XmlEnumValue("none")
        none,
        @XmlEnumValue("eq")
        equal,
        @XmlEnumValue("neq")
        notEqual;
        
        private final boolean m_is_need_param;
        
        private ComparisonType() { this(true); }
        private ComparisonType(boolean p_val) { m_is_need_param = p_val; }
        
        public boolean isNeedParam() { return m_is_need_param; }
    };    
    
    private String m_field;
    private ComparisonType m_comparison;    
    private Collection<String> m_values;
    
    @Override
    @XmlAttribute(required = true)
    public String getField() {
        return m_field;
    }
    public void setField(String p_field) {
        this.m_field = p_field;
    }
    
    @XmlAttribute(name = "cmp", required = true)
    public ComparisonType getComparison() {
        return m_comparison;
    }
    public void setComparison(ComparisonType p_comparison_type) {
        this.m_comparison = p_comparison_type;
    }
    
    @XmlList
    @XmlAttribute
    public Collection<String> getValue() {
        if (m_values == null) m_values = new ArrayList<String>();
        return m_values;
    }
    
    @Override
    public boolean isPassed(Object p_field_value) throws FilterException {
        if (m_comparison == null)
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", m_field));
        Collection<String> x_field_value = (Collection<String>) p_field_value;
        switch(m_comparison) {
            case isEmpty: return x_field_value == null || x_field_value.isEmpty();
            case isNotEmpty: return x_field_value != null && !x_field_value.isEmpty();
            case any: return intersectAny(x_field_value);
            case none: return !intersectAny(x_field_value);
            case equal: return CUtil.equals(getValue() != null ?
                    new HashSet<String>(getValue()) : null, x_field_value != null ?
                    new HashSet<String>(x_field_value) : null);
            case notEqual: return !CUtil.equals(getValue() != null ?
                    new HashSet<String>(getValue()) : null, x_field_value != null ?
                    new HashSet<String>(x_field_value) : null);
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s",
                String.valueOf(m_comparison), m_field));
    }    

    private boolean intersectAny(Collection<String> p_field_list) {
        Collection<String> x_filter_list = getValue();
        // значение фильтра=пусто
        if (x_filter_list == null || x_filter_list.isEmpty())
            return true;
        // значение фильтра=не пусто, значение поля = пусто
        if (p_field_list == null || p_field_list.isEmpty())
            return false;
        // и фильтр и поле заполнено
        for (String x_field_item : p_field_list) {
            if (x_filter_list.contains(x_field_item))
                return true;
        }
        return false;
    }
    
}
