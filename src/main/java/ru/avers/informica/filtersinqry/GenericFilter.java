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

    private String m_field,
            m_str_value;
    private ComparisonType m_comparison;
    private Class<?> m_field_type;

    public GenericFilter() {
    }

    public GenericFilter(String p_field, ComparisonType p_comparison, String p_str_value) {
        this.m_field = p_field;
        this.m_comparison = p_comparison;
        this.m_str_value = p_str_value;
    }

    @Override
    @XmlAttribute(required = true)
    public String getField() {
        return m_field;
    }

    public void setField(String p_field) {
        this.m_field = p_field;
    }

    @Override
    @XmlAttribute(name = "cmp", required = true)
    public ComparisonType getComparison() {
        return m_comparison;
    }

    public void setComparison(ComparisonType p_comparison_type) {
        this.m_comparison = p_comparison_type;
    }

    @Override
    @XmlTransient
    public Object getValue() {
        if (m_str_value == null) return null;

        if (ComparisonType.in.equals(m_comparison) || ComparisonType.notIn.equals(m_comparison)) {
            // В качестве значения ожидается список
            String[] x_str_values = m_str_value.split(" ");
            List<Object> x_list = new ArrayList<Object>();
            for (String x_str_value : x_str_values)
                x_list.add(castValue(x_str_value));
            return x_list;
        } else {
            return castValue(getStringValue());
        }
    }

    private Object castValue(String p_value) {
        if (m_field_type == null) return p_value;
        if (int.class.equals(m_field_type) || Integer.class.equals(m_field_type))
            return Integer.parseInt(p_value);
        else if (short.class.equals(m_field_type) || Short.class.equals(m_field_type))
            return Short.parseShort(p_value);
        else if (boolean.class.equals(m_field_type) || Boolean.class.equals(m_field_type))
            return Boolean.parseBoolean(p_value);
        else return p_value;
    }

    public Class<?> getFieldType() {
        return m_field_type;
    }

    public void setFieldType(Class<?> p_field_type) {
        m_field_type = p_field_type;
    }

    @XmlAttribute(name = "value")
    public String getStringValue() {
        return m_str_value;
    }

    public void setStringValue(String p_str_value) {
        m_str_value = p_str_value;
    }

    @Override
    public boolean isPassed(Object p_field_value) throws FilterException {
        if (m_comparison == null)
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", m_field));
        if (p_field_value != null) setFieldType(p_field_value.getClass());
        switch (m_comparison) {
            case isnull:
                return p_field_value == null;
            case isnotnull:
                return p_field_value != null;
            case equal:
                return CUtil.equals(p_field_value, getValue());
            case notEqual:
                return !CUtil.equals(p_field_value, getValue());
            case greater:
                if (p_field_value instanceof Comparable)
                    return ((Comparable) p_field_value).compareTo(getValue()) > 0;
                else
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
            case greaterOrEqual:
                if (p_field_value instanceof Comparable)
                    return ((Comparable) p_field_value).compareTo(getValue()) >= 0;
                else
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
            case less:
                if (p_field_value instanceof Comparable)
                    return ((Comparable) p_field_value).compareTo(getValue()) < 0;
                else
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
            case lessOrEqual:
                if (p_field_value instanceof Comparable)
                    return ((Comparable) p_field_value).compareTo(getValue()) <= 0;
                else
                    throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
            case in:
                return ((List) getValue()).contains(p_field_value);
            case notIn:
                return !((List) getValue()).contains(p_field_value);
            case like:
                throw new FilterException(String.format("Недопустимая операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.m_field != null ? this.m_field.hashCode() : 0);
        hash = 23 * hash + (this.m_comparison != null ? this.m_comparison.hashCode() : 0);
        hash = 23 * hash + (this.m_str_value != null ? this.m_str_value.hashCode() : 0);
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
        if ((this.m_field == null) ? (other.m_field != null) : !this.m_field.equals(other.m_field)) {
            return false;
        }
        if (this.m_comparison != other.m_comparison) {
            return false;
        }
        if (this.m_str_value != other.m_str_value && (this.m_str_value == null || !this.m_str_value.equals(other.m_str_value))) {
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
