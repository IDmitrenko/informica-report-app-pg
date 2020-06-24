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

    static public void setFilterFieldType(List<IFieldFilterParams> p_filters, Class<UchInf> p_class) {
        for (IFieldFilterParams x_filter : p_filters) {
            if (x_filter instanceof GenericFilter)
                ((GenericFilter)x_filter).setFieldType(getFieldType(x_filter.getField(), p_class));
        }
    }
    
    static public Class<?> getFieldType(String p_field_name, Class<UchInf> p_class) {
        try {
            BeanInfo x_bean_info = Introspector.getBeanInfo(p_class);
            PropertyDescriptor[] x_descriptors = x_bean_info.getPropertyDescriptors();
            for (PropertyDescriptor x_prop_descr : x_descriptors) {
                if (x_prop_descr.getName().equals(p_field_name)) {
                    return x_prop_descr.getPropertyType();
                }
            }            
        } catch (IntrospectionException ex) {
            log.debug("getFieldType", ex);
        }   
        return null;
    }    
    
    static public IFieldFilterParams createUchIdFilter(Integer p_id_uch) {
        GenericFilter x_filter = new GenericFilter();
        x_filter.setField("id");
        x_filter.setComparison(IFieldFilterParams.ComparisonType.equal);
        x_filter.setStringValue(String.valueOf(p_id_uch));
        x_filter.setFieldType(Integer.class);
        return x_filter;
    }
    
}
