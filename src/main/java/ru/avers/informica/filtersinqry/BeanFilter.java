package ru.avers.informica.filtersinqry;

import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.utils.CUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Dmitrenko
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BeanFilter implements IFieldFilterParams, IFilter<Object> {

    private String field,
            stringValue;
    private ComparisonType comparison;
    private Class<?> fieldType;
    private boolean useOR;

    public BeanFilter() {
    }

    public BeanFilter(String pField, ComparisonType pComparison,
                      String pStringValue, boolean pUseOR) {
        this.field = pField;
        this.comparison = pComparison;
        this.stringValue = pStringValue;
        this.useOR = pUseOR;
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
        if (stringValue == null) return null;

        return getStringValue();
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

    @XmlAttribute(name = "useOR")
    public boolean getUseOR() {
        return useOR;
    }

    public void setUseOR(boolean m_useOR) {
        this.useOR = m_useOR;
    }

    @Override
    public boolean isPassed(Object item) throws FilterException {
        if (comparison == null) {
            throw new FilterException(String.format("Не задана операция сравнения для поля %s",
                    field));
        }
        Object a = null;
        Object b = null;
        try {
            a = BeanUtils.getBeanValue(getField(), item);
            b = BeanUtils.getBeanValue(getValue().toString(), item);
        } catch (Exception e) {
            throw new FilterException(e);
        }
        switch (comparison) {
            case equal:
                return CUtil.equals(a, b);
            case notEqual:
                return !CUtil.equals(a, b);
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s",
                String.valueOf(comparison), field));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BeanFilter that = (BeanFilter) o;

        if (useOR != that.useOR) {
            return false;
        }
        if (field != null ? !field.equals(that.field) : that.field != null) {
            return false;
        }
        if (stringValue != null ? !stringValue.equals(that.stringValue) : that.stringValue != null) {
            return false;
        }
        if (comparison != that.comparison) {
            return false;
        }
        return fieldType != null ?
                fieldType.equals(that.fieldType) : that.fieldType == null;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
        result = 31 * result + (comparison != null ? comparison.hashCode() : 0);
        result = 31 * result + (fieldType != null ? fieldType.hashCode() : 0);
        result = 31 * result + (useOR ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BeanFilter{" +
                "field='" + field + '\'' +
                ", stringValue='" + stringValue + '\'' +
                ", comparison=" + comparison +
                ", fieldType=" + fieldType +
                ", useOR=" + useOR +
                '}';
    }

}
