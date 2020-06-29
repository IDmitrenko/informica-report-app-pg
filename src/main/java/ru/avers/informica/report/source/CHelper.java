package ru.avers.informica.report.source;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.filtersinqry.GenericFilter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

/**
 *
 * @author Dias
 */
@Slf4j
public class CHelper {

    static public void setFilterFieldType(List<IFieldFilterParams> pFilters, Class<UchInf> pClass) {
        for (IFieldFilterParams filter : pFilters) {
            if (filter instanceof GenericFilter)
                ((GenericFilter)filter).setFieldType(getFieldType(filter.getField(), pClass));
        }
    }
    
    static public Class<?> getFieldType(String pFieldName, Class<UchInf> pClass) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(pClass);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propDescr : descriptors) {
                if (propDescr.getName().equals(pFieldName)) {
                    return propDescr.getPropertyType();
                }
            }            
        } catch (IntrospectionException ex) {
            log.debug("getFieldType", ex);
        }   
        return null;
    }    
    
    static public IFieldFilterParams createUchIdFilter(Integer pIdUch) {
        GenericFilter filter = new GenericFilter();
        filter.setField("id");
        filter.setComparison(IFieldFilterParams.ComparisonType.equal);
        filter.setStringValue(String.valueOf(pIdUch));
        filter.setFieldType(Integer.class);
        return filter;
    }
    
}
