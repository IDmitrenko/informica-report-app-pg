package ru.avers.informica.common.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
public class QueueRule {
    public static final String s_inuch = "inuch",
                               s_weight = "weight",
                               s_queue_enter = "queue_enter",
                               s_bdt = "bdt",
                               s_num = "num";
    
    private final static String s_attr_ter_code = "ter-code";
    
    private String m_ter_code;
    private List<QueueSortingItem> m_data;

    public QueueRule() {
    }    
    
    public QueueRule(String p_ter_code, List<QueueSortingItem> p_sorting_list) {
        this.m_ter_code = p_ter_code;
        m_data = p_sorting_list;
    }

    @XmlAttribute(name=s_attr_ter_code)
    public String getTerCode() {
        return m_ter_code;
    }
    public void setTerCode(String p_ter_code) {
        m_ter_code = p_ter_code;
    }
            
    @XmlElement(name = "item")
    public List<QueueSortingItem> getSortingList() {
        if(m_data == null) m_data = new ArrayList<QueueSortingItem>();
        return m_data; 
    }
    public void setSortingList(List<QueueSortingItem> p_val) { m_data = p_val; }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.m_ter_code != null ? this.m_ter_code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QueueRule other = (QueueRule) obj;
        if ((this.m_ter_code == null) ? (other.m_ter_code != null) : !this.m_ter_code.equals(other.m_ter_code)) {
            return false;
        }
        return true;
    }    
    
}
