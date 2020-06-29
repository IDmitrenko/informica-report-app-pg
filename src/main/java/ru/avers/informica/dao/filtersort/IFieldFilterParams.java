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

    String getField();
    ComparisonType getComparison();
    Object getValue();
}
