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
        
        private final boolean isNeedParam;
        
        private ComparisonType() {
            this(true);
        }
        private ComparisonType(boolean pVal) {
            isNeedParam = pVal;
        }
        public boolean isNeedParam() {
            return isNeedParam;
        }
    };    
    
    private String field;
    private ComparisonType comparison;
    private Collection<String> values;
    
    @Override
    @XmlAttribute(required = true)
    public String getField() {
        return field;
    }
    public void setField(String pField) {
        this.field = pField;
    }
    
    @XmlAttribute(name = "cmp", required = true)
    public ComparisonType getComparison() {
        return comparison;
    }
    public void setComparison(ComparisonType pComparison) {
        this.comparison = pComparison;
    }
    
    @XmlList
    @XmlAttribute
    public Collection<String> getValue() {
        if (values == null) {
            values = new ArrayList<String>();
        }
        return values;
    }
    
    @Override
    public boolean isPassed(Object pFieldValue) throws FilterException {
        if (comparison == null) {
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", field));
        }
        Collection<String> fieldValue = (Collection<String>) pFieldValue;
        switch(comparison) {
            case isEmpty:
                return fieldValue == null || fieldValue.isEmpty();
            case isNotEmpty:
                return fieldValue != null && !fieldValue.isEmpty();
            case any:
                return intersectAny(fieldValue);
            case none:
                return !intersectAny(fieldValue);
            case equal:
                return CUtil.equals(getValue() != null ?
                    new HashSet<String>(getValue()) : null, fieldValue != null ?
                    new HashSet<String>(fieldValue) : null);
            case notEqual:
                return !CUtil.equals(getValue() != null ?
                    new HashSet<String>(getValue()) : null, fieldValue != null ?
                    new HashSet<String>(fieldValue) : null);
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s",
                String.valueOf(comparison), field));
    }    

    private boolean intersectAny(Collection<String> pFieldList) {
        Collection<String> filterList = getValue();
        // значение фильтра=пусто
        if (filterList == null || filterList.isEmpty())
            return true;
        // значение фильтра=не пусто, значение поля = пусто
        if (pFieldList == null || pFieldList.isEmpty())
            return false;
        // и фильтр и поле заполнено
        for (String fieldItem : pFieldList) {
            if (filterList.contains(fieldItem))
                return true;
        }
        return false;
    }
    
}
