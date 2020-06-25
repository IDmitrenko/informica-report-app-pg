package ru.avers.informica.common.config;


import ru.avers.informica.dao.filtersort.IFieldSortingParams;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class QueueSortingItem {
    private IFieldSortingParams.SortDir sortDir;
    private String field;

    public QueueSortingItem() {
    }    

    public QueueSortingItem(IFieldSortingParams.SortDir pSortDir, String pField) {
        this.sortDir = pSortDir;
        this.field = pField;
    }        
    
    @XmlAttribute(name="sort-dir", required=true)
    public IFieldSortingParams.SortDir getSortDir() {
        return sortDir;
    }
    public void setSortDir(IFieldSortingParams.SortDir pVal) {
        sortDir = pVal;
    }

    @XmlAttribute(name="fld", required=false)
    public String getSortField() {
        return field;
    }
    public void setSortField(String pVal) {
        field = pVal;
    }

    @Override
    public String toString() {
        return
            QueueSortingItem.class.getName() + " {" +
            "sort_dir = " + String.valueOf(sortDir) +
            "; field = " + String.valueOf(field) +
            "}";
    }
    
}
