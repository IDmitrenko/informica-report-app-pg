package ru.avers.informica.common.config;


import ru.avers.informica.dao.filtersort.IFieldSortingParams;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Dias
 */
public class QueueSortingItem {
    private IFieldSortingParams.SortDir m_sort_dir;
    private String m_field;

    public QueueSortingItem() {
    }    

    public QueueSortingItem(IFieldSortingParams.SortDir p_sort_dir, String p_field) {
        this.m_sort_dir = p_sort_dir;
        this.m_field = p_field;
    }        
    
    @XmlAttribute(name="sort-dir", required=true)
    public IFieldSortingParams.SortDir getSortDir() { return m_sort_dir; }
    public void setSortDir(IFieldSortingParams.SortDir p_val) { m_sort_dir = p_val; }

    @XmlAttribute(name="fld", required=false)
    public String getSortField() { return m_field; }
    public void setSortField(String p_val) { m_field = p_val; }

    @Override
    public String toString() {
        return
            QueueSortingItem.class.getName() + " {" +
            "sort_dir = " + String.valueOf(m_sort_dir) +
            "; field = " + String.valueOf(m_field) +
            "}";
    }
    
}
