package ru.avers.informica.dto;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeAgeInterval", propOrder={"begin", "end"})
@XmlSeeAlso({CAge.class})
public class CAgeInterval implements IDTO {
    private CAge begin, end;
    
    @XmlElement(name="begin")
    public CAge getBegin() {
        return begin;
    }
    public void setBegin(CAge pVal) {
        begin = pVal;
    }
    
    @XmlElement(name="end")
    public CAge getEnd() {
        return end;
    }
    public void setEnd(CAge pVal) {
        end = pVal;
    }
    
    @XmlTransient
    public boolean isEmpty() {
        return (begin == null && end == null);
    }
    
    public boolean contains(CAge pItem) {
        return contains(pItem, false, false);
    }
    public boolean containsLeft(CAge pItem) {
        return contains(pItem, true, false);
    }
    public boolean containsRight(CAge pItem) {
        return contains(pItem, false, true);
    }
    public boolean containsFull(CAge pItem) {
        return contains(pItem, true, true);
    }
    
    private boolean contains(CAge pItem, boolean pIsLeft, boolean pIsRight) {
        if(pItem == null) {
            return false;
        }
        if(isEmpty()) {
            return true;
        }
        
        int res = begin == null ? 1 : pItem.compareTo(begin);
        if(res < 0) {
            return false;
        }
        if(res == 0) {
            return pIsLeft;
        }
            
        res = end == null ? -1 : pItem.compareTo(end);
        return (res < 0 || (res == 0 && pIsRight));
    }
    
    public CAgeInterval() {
        this(null);
    }
    public CAgeInterval(CAge pBegin) {
        this(pBegin, null);
    }
    public CAgeInterval(Integer pBeginY, Integer pBeginM, Integer pEndY, Integer pEndM) {
        this(new CAge(pBeginY, pBeginM), new CAge(pEndY, pEndM));
    }
    public CAgeInterval(CAge pBegin, CAge pEnd) {
        begin = pBegin;
        end = pEnd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (begin != null ? begin.hashCode() : 0);
        hash = 79 * hash + (end != null ? end.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object pObj) {
        if(pObj == null) {
            return false;
        }
        if(getClass() != pObj.getClass()) {
            return false;
        }

        final CAgeInterval other = (CAgeInterval)pObj;
        if (begin != other.begin && (begin == null || !begin.equals(other.begin))) {
            return false;
        }
        if (end != other.end && (end == null || !end.equals(other.end))) {
            return false;
        }

        return true;
    }
    
    @Override
    public String toString() {
        return CAgeInterval.class.getName() + " {" + 
                "begin = " + String.valueOf(begin) +
                "; end = " + String.valueOf(end) +
                "}";
    }
    
}
