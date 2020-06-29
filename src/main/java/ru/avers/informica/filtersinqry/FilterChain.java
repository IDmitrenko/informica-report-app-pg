package ru.avers.informica.filtersinqry;

import org.springframework.stereotype.Component;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.utils.BeanUtil;
import ru.avers.informica.utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Dias
 */
@Component
public class FilterChain {

    private Date currDate,
            educDate;

    public boolean isPassed(List<IFilter> filters, Date pCurrDate,
                            Date pEducDate, Object pValue) throws FilterException {
        currDate = pCurrDate;
        educDate = pEducDate;
        if (filters == null) {
            return true;
        }
        for (IFilter filter : filters) {
            if (filter instanceof IsFilterDate) {
                IsFilterDate filterDate = (IsFilterDate) filter;
                if (filterDate.isEducDate())
                    filterDate.setRepDate(pEducDate);
                else
                    filterDate.setRepDate(pCurrDate);
            }
            String field = filter.getField();
            Object value;
            if (isReservedField(field)) {
                value = getReservedFieldValue(field);
            } else {
                try {
                    value = BeanUtil.getBeanValue(field, pValue);
                } catch (BeanUtil.BeanUtilException ex) {
                    throw new FilterException(ex.getMessage(), ex);
                }
            }
            if (!filter.isPassed(value))
                return false;
        }
        return true;
    }

    private boolean isReservedField(String pField) {
        return IsFilterDate.filterCurrentDate.equals(pField) ||
                IsFilterDate.filterCurrentTime.equals(pField);
    }

    private Object getReservedFieldValue(String pField) throws FilterException {
        if (IsFilterDate.filterCurrentDate.equals(pField)) {
            return DateUtil.clearDateTimePart(currDate);
        } else if (IsFilterDate.filterCurrentTime.equals(pField)) {
            return currDate;
        } else {
            throw new FilterException("Неизвестное зарезервированное поле " + String.valueOf(pField));
        }
    }

}
