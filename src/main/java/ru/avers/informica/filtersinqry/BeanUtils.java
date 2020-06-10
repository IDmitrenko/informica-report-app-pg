package ru.avers.informica.filtersinqry;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Dmitrenko
 */
public class BeanUtils {
    public static <T> Object getBeanValue(String p_prop, T p_bean) throws Exception {
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
            throw new Exception("Нет доступа к указанному полю", ex);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Недопустимый или несоответствующий параметр", ex);
        } catch (IntrospectionException ex) {
            throw new Exception("Не найден метод для свойства " + p_prop, ex);
        } catch (InvocationTargetException ex) {
            throw new Exception("Ошибка при вызове метода для свойства " + p_prop, ex);
        }
    }

    private static <T> Object invokeGetter(String p_prop, T p_bean)
            throws IntrospectionException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
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
