package ru.avers.informica.infcfg;

import ru.avers.informica.dto.informica.InqryInd19_3Inf;
import ru.avers.informica.dto.informica.InqryInd20_1Inf;
import ru.avers.informica.dto.informica.InqryInf;
import ru.avers.informica.dto.informica.InqryTransferInf;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.filtersinqry.BeanFilter;
import ru.avers.informica.filtersinqry.IFilter;
import ru.avers.informica.filtersinqry.IncrementByPriorities;
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
    private CounterDef counterDef;

    @XmlAttribute(name = "def", required = true)
    @XmlIDREF
    public CounterDef getCounterDef() {
        return counterDef;
    }

    public void setCounterDef(CounterDef pCounterDef) {
        this.counterDef = pCounterDef;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                .append("{counter_def=").append(counterDef)
                .append("}")
                .toString();
    }

    public boolean isPassed(Date pDt, Date pCurrEducDate, Object dsItem)
            throws ReportExceprion, FilterException {
        if (counterDef == null ||
                counterDef.getFilters() == null ||
                counterDef.getFilters().isEmpty()) {
            return true;
        }

        if (!hasIncrementByPriority(counterDef.getFilters())) {
            if (dsItem instanceof InqryInf
                    && ((InqryInf) dsItem).getPriorityCount().shortValue() !=
                    ((InqryInf) dsItem).getMinPriority().shortValue()) {
                return false;
            }
        }

        if (dsItem instanceof InqryInd19_3Inf) {
            return true;
        }

        if (dsItem instanceof InqryInd20_1Inf) {
            return true;
        }

        // Для проверки фильтра по условию ИЛИ (нужно прогнать по всем фильтрам)
        boolean isAllOrBeanFilter = true;

        if (dsItem instanceof InqryTransferInf) {
            ((InqryTransferInf) dsItem).setCount(0);
        }
        for (IFilter x_filter : counterDef.getFilters()) {
            if (x_filter instanceof IsFilterDate) {
                IsFilterDate x_date_filter = (IsFilterDate) x_filter;
                if (x_date_filter.isEducDate())
                    x_date_filter.setRepDate(pCurrEducDate);
                else
                    x_date_filter.setRepDate(pDt);
            }
            if (x_filter instanceof BeanFilter) {
                if (x_filter.isPassed(dsItem)) {
                    if (dsItem instanceof InqryTransferInf) {
                        if (isAllOrBeanFilter && ((BeanFilter) x_filter).getUseOR()) {
                            ((InqryTransferInf) dsItem)
                                    .setCount(((InqryTransferInf) dsItem).getCount() + 1);
                        } else {
                            ((InqryTransferInf) dsItem).setCount(1);
                            isAllOrBeanFilter = false;
                        }
                    }
                } else {
                    if (!((BeanFilter) x_filter).getUseOR()) {
                        return false;
                    }
                }

            } else {
                isAllOrBeanFilter = false;
                String x_field = x_filter.getField();
                Object x_value = getBeanValue(x_field, dsItem);
                if (!x_filter.isPassed(x_value)) {
                    return false;
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
}
