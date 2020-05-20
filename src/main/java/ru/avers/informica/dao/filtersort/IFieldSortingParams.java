package ru.avers.informica.dao.filtersort;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Dias
 */
public interface IFieldSortingParams {

    @XmlType(name="TypeSortDirection")
    @XmlEnum(String.class)
    public enum SortDir { asc, desc };

    SortDir getSortDir();
    String getSortField();
}
