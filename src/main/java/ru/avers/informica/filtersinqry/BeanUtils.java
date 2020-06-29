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
    public static <T> Object getBeanValue(String pProp, T pBean) throws Exception {
        if (pProp == null) {
            return null;
        }
        try {
            if (!pProp.contains(".")) {
                return invokeGetter(pProp, pBean);
            } else {
                String[] props = pProp.split("\\.");
                Object nestedBean = pBean;
                for (String nestedProp : props) {
                    nestedBean = invokeGetter(nestedProp, nestedBean);
                    if (nestedBean == null) {
                        return null;
                    }
                }
                return nestedBean;
            }
        } catch (IllegalAccessException ex) {
            throw new Exception("Нет доступа к указанному полю", ex);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Недопустимый или несоответствующий параметр", ex);
        } catch (IntrospectionException ex) {
            throw new Exception("Не найден метод для свойства " + pProp, ex);
        } catch (InvocationTargetException ex) {
            throw new Exception("Ошибка при вызове метода для свойства " + pProp, ex);
        }
    }

    private static <T> Object invokeGetter(String pProp, T pBean)
            throws IntrospectionException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // Не использовать new PropertyDescriptor(pProp, pBean.getClass()),
        // java 1.7 не находит getter, унаследованный от базового класса, (валится
        // с ошибкой, что не находит соответствующий setter)
        // https://community.oracle.com/thread/1160697?tstart=0
        BeanInfo beanInfo = Introspector.getBeanInfo(pBean.getClass());
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        PropertyDescriptor found = null;
        for (PropertyDescriptor propDescr : descriptors) {
            if (propDescr.getName().equals(pProp)) {
                found = propDescr;
                break;
            }
        }
        if (found == null || found.getReadMethod() == null) {
            throw new IntrospectionException(pProp);
        }
        return found.getReadMethod().invoke(pBean, (Object[]) null);
    }
}
