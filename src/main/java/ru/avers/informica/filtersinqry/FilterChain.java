package ru.avers.informica.filtersinqry;

import org.springframework.stereotype.Component;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.utils.BeanUtil;
import ru.avers.informica.utils.DateUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dias
 */
@Component
public class FilterChain {

    private Date m_curr_dt,
            m_educ_dt;

    public boolean isPassed(List<IFilter> filters, Date p_curr_dt,
                            Date p_educ_date, Object p_value) throws FilterException {
        m_curr_dt = p_curr_dt;
        m_educ_dt = p_educ_date;
        if (filters == null) return true;
        for (IFilter x_filter : filters) {
            if (x_filter instanceof IsFilterDate) {
                IsFilterDate x_date_filter = (IsFilterDate) x_filter;
                if (x_date_filter.isEducDate())
                    x_date_filter.setRepDate(p_educ_date);
                else
                    x_date_filter.setRepDate(p_curr_dt);
            }
            String x_field = x_filter.getField();
            Object x_value;
            if (isReservedField(x_field)) {
                x_value = getReservedFieldValue(x_field);
            } else {
                try {
                    x_value = BeanUtil.getBeanValue(x_field, p_value);
                } catch (BeanUtil.BeanUtilException ex) {
                    throw new FilterException(ex.getMessage(), ex);
                }
            }
            if (!x_filter.isPassed(x_value))
                return false;
        }
        return true;
    }

    private boolean isReservedField(String p_field) {
        return IsFilterDate.filterCurrentDate.equals(p_field) ||
                IsFilterDate.filterCurrentTime.equals(p_field);
    }

    private Object getReservedFieldValue(String p_field) throws FilterException {
        if (IsFilterDate.filterCurrentDate.equals(p_field)) {
            return DateUtil.clearDateTimePart(m_curr_dt);
        } else if (IsFilterDate.filterCurrentTime.equals(p_field)) {
            return m_curr_dt;
        } else throw new FilterException("Неизвестное зарезервированное поле " + String.valueOf(p_field));
    }

}
