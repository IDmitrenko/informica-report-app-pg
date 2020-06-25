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
    public static final String S_INUCH = "inuch",
                               S_WEIGHT = "weight",
                               S_QUEUE_ENTER = "queue_enter",
                               S_BDT = "bdt",
                               S_NUM = "num";
    
    private final static String S_ATTR_TER_CODE = "ter-code";
    
    private String terCode;
    private List<QueueSortingItem> queueSortingItems;

    public QueueRule() {
    }    
    
    public QueueRule(String pTerCode, List<QueueSortingItem> pSortingList) {
        this.terCode = pTerCode;
        queueSortingItems = pSortingList;
    }

    @XmlAttribute(name= S_ATTR_TER_CODE)
    public String getTerCode() {
        return terCode;
    }
    public void setTerCode(String pTerCode) {
        terCode = pTerCode;
    }
            
    @XmlElement(name = "item")
    public List<QueueSortingItem> getSortingList() {
        if(queueSortingItems == null) queueSortingItems = new ArrayList<QueueSortingItem>();
        return queueSortingItems;
    }
    public void setSortingList(List<QueueSortingItem> pVal) {
        queueSortingItems = pVal;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.terCode != null ? this.terCode.hashCode() : 0);
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
        if ((this.terCode == null) ? (other.terCode != null) :
                !this.terCode.equals(other.terCode)) {
            return false;
        }
        return true;
    }    
    
}
