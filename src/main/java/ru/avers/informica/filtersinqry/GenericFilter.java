package ru.avers.informica.filtersinqry;

import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.utils.CUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class GenericFilter implements IFieldFilterParams, IFilter<Object> {

    private String field,
            stringValue;
    private ComparisonType comparison;
    private Class<?> fieldType;

    public GenericFilter() {
    }

    public GenericFilter(String pField, ComparisonType pComparison, String pStringValue) {
        this.field = pField;
        this.comparison = pComparison;
        this.stringValue = pStringValue;
    }

    @Override
    @XmlAttribute(required = true)
    public String getField() {
        return field;
    }

    public void setField(String pField) {
        this.field = pField;
    }

    @Override
    @XmlAttribute(name = "cmp", required = true)
    public ComparisonType getComparison() {
        return comparison;
    }

    public void setComparison(ComparisonType pComparison) {
        this.comparison = pComparison;
    }

    @Override
    @XmlTransient
    public Object getValue() {
        if (stringValue == null) {
            return null;
        }

        if (ComparisonType.in.equals(comparison) || ComparisonType.notIn.equals(comparison)) {
            // В качестве значения ожидается список
            String[] stringValues = stringValue.split(" ");
            List<Object> list = new ArrayList<Object>();
            for (String stringValue : stringValues)
                list.add(castValue(stringValue));
            return list;
        } else {
            return castValue(getStringValue());
        }
    }

    private Object castValue(String pValue) {
        if (fieldType == null) {
            return pValue;
        }
        if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
            return Integer.parseInt(pValue);
        }
        else
            if (short.class.equals(fieldType) || Short.class.equals(fieldType)) {
            return Short.parseShort(pValue);
        }
        else
            if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) {
            return Boolean.parseBoolean(pValue);
        }
        else {
            return pValue;
        }
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> pFieldType) {
        fieldType = pFieldType;
    }

    @XmlAttribute(name = "value")
    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String pStringValue) {
        stringValue = pStringValue;
    }

    @Override
    public boolean isPassed(Object pFieldValue) throws FilterException {
        if (comparison == null) {
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", field));
        }
        if (pFieldValue != null) {
            setFieldType(pFieldValue.getClass());
        }
        switch (comparison) {
            case isnull:
                return pFieldValue == null;
            case isnotnull:
                return pFieldValue != null;
            case equal:
                return CUtil.equals(pFieldValue, getValue());
            case notEqual:
                return !CUtil.equals(pFieldValue, getValue());
            case greater:
                if (pFieldValue instanceof Comparable) {
                    return ((Comparable) pFieldValue).compareTo(getValue()) > 0;
                }
                else {
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                            String.valueOf(comparison), field));
                }
            case greaterOrEqual:
                if (pFieldValue instanceof Comparable) {
                    return ((Comparable) pFieldValue).compareTo(getValue()) >= 0;
                }
                else {
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                            String.valueOf(comparison), field));
                }
            case less:
                if (pFieldValue instanceof Comparable) {
                    return ((Comparable) pFieldValue).compareTo(getValue()) < 0;
                }
                else {
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                            String.valueOf(comparison), field));
                }
            case lessOrEqual:
                if (pFieldValue instanceof Comparable) {
                    return ((Comparable) pFieldValue).compareTo(getValue()) <= 0;
                }
                else {
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                            String.valueOf(comparison), field));
                }
            case in:
                return ((List) getValue()).contains(pFieldValue);
            case notIn:
                return !((List) getValue()).contains(pFieldValue);
            case like:
                throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s",
                        String.valueOf(comparison), field));
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s",
                String.valueOf(comparison), field));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.field != null ? this.field.hashCode() : 0);
        hash = 23 * hash + (this.comparison != null ? this.comparison.hashCode() : 0);
        hash = 23 * hash + (this.stringValue != null ? this.stringValue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericFilter other = (GenericFilter) obj;
        if ((this.field == null) ? (other.field != null) : !this.field.equals(other.field)) {
            return false;
        }
        if (this.comparison != other.comparison) {
            return false;
        }
        if (this.stringValue != other.stringValue &&
                (this.stringValue == null || !this.stringValue.equals(other.stringValue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GenericFilter [");
        builder.append("getField=").append(getField());
        builder.append(", getComparison=").append(getComparison());
        builder.append(", getStringValue=").append(getStringValue());
        builder.append("]");
        return builder.toString();
    }

}
