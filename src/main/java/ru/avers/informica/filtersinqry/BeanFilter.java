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

    private String m_field,
            m_str_value;
    private ComparisonType m_comparison;
    private Class<?> m_field_type;
    private boolean m_useOR;

    public BeanFilter() {
    }

    public BeanFilter(String p_field, ComparisonType p_comparison, String p_str_value, boolean p_useOR) {
        this.m_field = p_field;
        this.m_comparison = p_comparison;
        this.m_str_value = p_str_value;
        this.m_useOR = p_useOR;
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

        return getStringValue();
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

    @XmlAttribute(name = "useOR")
    public boolean getUseOR() {
        return m_useOR;
    }

    public void setUseOR(boolean m_useOR) {
        this.m_useOR = m_useOR;
    }

    @Override
    public boolean isPassed(Object item) throws FilterException {
        if (m_comparison == null) {
            throw new FilterException(String.format("Не задана операция сравнения для поля %s", m_field));
        }
        Object a = null;
        Object b = null;
        try {
            a = BeanUtils.getBeanValue(getField(), item);
            b = BeanUtils.getBeanValue(getValue().toString(), item);
        } catch (Exception e) {
            throw new FilterException(e);
        }
        switch (m_comparison) {
            case equal:
                return CUtil.equals(a, b);
            case notEqual:
                return !CUtil.equals(a, b);
        }
        throw new FilterException(String.format("Неизвестная операция сравнения %s для поля %s", String.valueOf(m_comparison), m_field));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanFilter that = (BeanFilter) o;

        if (m_useOR != that.m_useOR) return false;
        if (m_field != null ? !m_field.equals(that.m_field) : that.m_field != null) return false;
        if (m_str_value != null ? !m_str_value.equals(that.m_str_value) : that.m_str_value != null) return false;
        if (m_comparison != that.m_comparison) return false;
        return m_field_type != null ? m_field_type.equals(that.m_field_type) : that.m_field_type == null;
    }

    @Override
    public int hashCode() {
        int result = m_field != null ? m_field.hashCode() : 0;
        result = 31 * result + (m_str_value != null ? m_str_value.hashCode() : 0);
        result = 31 * result + (m_comparison != null ? m_comparison.hashCode() : 0);
        result = 31 * result + (m_field_type != null ? m_field_type.hashCode() : 0);
        result = 31 * result + (m_useOR ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BeanFilter{" +
                "m_field='" + m_field + '\'' +
                ", m_str_value='" + m_str_value + '\'' +
                ", m_comparison=" + m_comparison +
                ", m_field_type=" + m_field_type +
                ", m_useOR=" + m_useOR +
                '}';
    }

}
