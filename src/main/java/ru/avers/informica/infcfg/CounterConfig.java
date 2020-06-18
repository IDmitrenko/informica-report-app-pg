package ru.avers.informica.infcfg;

import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.filtersinqry.BeanFilter;
import ru.avers.informica.filtersinqry.IFilter;
import ru.avers.informica.filtersinqry.IsFilterDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CounterConfig {
    private CounterDef m_counter_def;

    @XmlAttribute(name = "def", required = true)
    @XmlIDREF
    public CounterDef getCounterDef() {
        return m_counter_def;
    }

    public void setCounterDef(CounterDef p_counter_def) {
        this.m_counter_def = p_counter_def;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                .append("{counter_def=").append(m_counter_def)
                .append("}")
                .toString();
    }

/* продолжить...
    public boolean isPassed(Date p_dt, Date p_curr_educ_date, Object x_ds_item) throws ReportExceprion {
        if (m_counter_def == null ||
                m_counter_def.getFilters() == null ||
                m_counter_def.getFilters().isEmpty())
            return true;

      */
/* if (!hasIncrementByPriority(m_counter_def.getFilters())) {
            if (x_ds_item instanceof InqryInf
                    && ((InqryInf) x_ds_item).getPriorityCount().shortValue() != ((InqryInf) x_ds_item).getMinPriority().shortValue()) {
                return false;
            }
        }*//*

        // Для проверки фильтра по условию ИЛИ (нужно прогнать по всем фильтрам)
        boolean isAllOrBeanFilter = true;
        if (x_ds_item instanceof InqryTransfer) {
            ((InqryTransfer) x_ds_item).setCount(0);
        }
        for (IFilter x_filter : m_counter_def.getFilters()) {
            if (x_filter instanceof IsFilterDate) {
                IsFilterDate x_date_filter = (IsFilterDate) x_filter;
                if (x_date_filter.isEducDate())
                    x_date_filter.setRepDate(p_curr_educ_date);
                else
                    x_date_filter.setRepDate(p_dt);
            }
            if (x_filter instanceof BeanFilter) {
                try {
                    if (x_filter.isPassed(x_ds_item)) {
                        if (x_ds_item instanceof InqryTransfer) {
                            if (isAllOrBeanFilter && ((BeanFilter) x_filter).getUseOR()) {
                                ((InqryTransfer) x_ds_item)
                                        .setCount(((InqryTransfer) x_ds_item).getCount() + 1);
                            } else {
                                ((InqryTransfer) x_ds_item).setCount(1);
                                isAllOrBeanFilter = false;
                            }
                        }
                    } else {
                        if (!((BeanFilter) x_filter).getUseOR()) {
                            return false;
                        }
                    }
                } catch (ReportExceprion ex) {
                    throw new ReportExceprion(ex.getMessage(), ex);
                }

            } else {
                isAllOrBeanFilter = false;
                String x_field = x_filter.getField();
                Object x_value = getBeanValue(x_field, x_ds_item);
                try {
                    if (!x_filter.isPassed(x_value)) {
                        return false;
                    }
                } catch (ReportExceprion ex) {
                    throw new ReportExceprion(ex.getMessage(), ex);
                }
            }
        }
        return true;
    }

    private boolean hasIncrementByPriority(List<IFilter> filters) {
        for (IFilter filter : filters) {
            if (filter instanceof IncrementByPriorities) {
                return true;
            }
        }
        return false;
    }

    public static <T> Object getBeanValue(String p_prop, T p_bean) throws ReportExceprion {
        if (p_prop == null) {
            return null;
        }
        try {
            if (!p_prop.contains(".")) {
                return invokeGetter(p_prop, p_bean);
            } else {
                String[] x_props = p_prop.split("\\.");
                Object x_nested_bean = p_bean;
                for (String x_nested_prop : x_props) {
                    x_nested_bean = invokeGetter(x_nested_prop, x_nested_bean);
                    if (x_nested_bean == null) return null;
                }
                return x_nested_bean;
            }
        } catch (IllegalAccessException ex) {
            throw new ReportExceprion("Нет доступа к указанному полю", ex);
        } catch (IllegalArgumentException ex) {
            throw new ReportExceprion("Недопустимый или несоответствующий параметр", ex);
        } catch (IntrospectionException ex) {
            throw new ReportExceprion("Не найден метод для свойства " + p_prop, ex);
        } catch (InvocationTargetException ex) {
            throw new ReportExceprion("Ошибка при вызове метода для свойства " + p_prop, ex);
        }
    }

    private static <T> Object invokeGetter(String p_prop, T p_bean) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Не использовать new PropertyDescriptor(p_prop, p_bean.getClass()),
        // java 1.7 не находит getter, унаследованный от базового класса, (валится
        // с ошибкой, что не находит соответствующий setter)
        // https://community.oracle.com/thread/1160697?tstart=0
        BeanInfo x_bean_info = Introspector.getBeanInfo(p_bean.getClass());
        PropertyDescriptor[] x_descriptors = x_bean_info.getPropertyDescriptors();
        PropertyDescriptor x_found = null;
        for (PropertyDescriptor x_prop_descr : x_descriptors) {
            if (x_prop_descr.getName().equals(p_prop)) {
                x_found = x_prop_descr;
                break;
            }
        }
        if (x_found == null || x_found.getReadMethod() == null)
            throw new IntrospectionException(p_prop);
        return x_found.getReadMethod().invoke(p_bean, (Object[]) null);
    }
*/

}
