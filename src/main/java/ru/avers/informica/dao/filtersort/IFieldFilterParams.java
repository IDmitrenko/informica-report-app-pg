package ru.avers.informica.dao.filtersort;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Dias
 */
public interface IFieldFilterParams extends IFilterParams {
    
    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(name = "TypeComparison", namespace="avers:common-ns")
    @XmlEnum(String.class)
    public enum ComparisonType {
        @XmlEnumValue("lt")
        less, 
        @XmlEnumValue("le")
        lessOrEqual, 
        @XmlEnumValue("gt")
        greater, 
        @XmlEnumValue("ge")
        greaterOrEqual, 
        @XmlEnumValue("eq")
        equal, 
        @XmlEnumValue("like")
        like, 
        @XmlEnumValue("is null")
        isnull(false), 
        @XmlEnumValue("is not null")
        isnotnull(false),
        @XmlEnumValue("in")
        in,
        @XmlEnumValue("not in")
        notIn,
        @XmlEnumValue("neq")
        notEqual;
        
        private final boolean m_is_need_param;
        
        private ComparisonType() { this(true); }
        private ComparisonType(boolean p_val) { m_is_need_param = p_val; }
        
        public boolean isNeedParam() { return m_is_need_param; }
    };

    String getField();
    ComparisonType getComparison();
    Object getValue();
}
