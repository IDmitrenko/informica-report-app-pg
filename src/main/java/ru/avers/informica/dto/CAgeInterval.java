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
    private CAge m_begin, m_end;
    
    @XmlElement(name="begin")
    public CAge getBegin() { return m_begin; }
    public void setBegin(CAge p_val) { m_begin = p_val; }
    
    @XmlElement(name="end")
    public CAge getEnd() { return m_end; }
    public void setEnd(CAge p_val) { m_end = p_val; }
    
    @XmlTransient
    public boolean isEmpty() { return (m_begin == null && m_end == null);}
    
    public boolean contains(CAge p_item) { return contains(p_item, false, false); }
    public boolean containsLeft(CAge p_item) { return contains(p_item, true, false); }
    public boolean containsRight(CAge p_item) { return contains(p_item, false, true); }
    public boolean containsFull(CAge p_item) { return contains(p_item, true, true); }
    
    private boolean contains(CAge p_item, boolean p_is_left, boolean p_is_right) {
        if(p_item == null) return false;
        if(isEmpty()) return true;
        
        int x_res = m_begin == null ? 1 : p_item.compareTo(m_begin);
        if(x_res < 0) return false;
        if(x_res == 0) return p_is_left;
            
        x_res = m_end == null ? -1 : p_item.compareTo(m_end);
        return (x_res < 0 || (x_res == 0 && p_is_right));
    }
    
    public CAgeInterval() { this(null); }
    public CAgeInterval(CAge p_begin) { this(p_begin, null); }
    public CAgeInterval(Integer p_begin_y, Integer p_begin_m, Integer p_end_y, Integer p_end_m) {
        this(new CAge(p_begin_y, p_begin_m), new CAge(p_end_y, p_end_m));
    }
    public CAgeInterval(CAge p_begin, CAge p_end) {
        m_begin = p_begin;
        m_end = p_end;
    }

    @Override
    public int hashCode() {
        int x_hash = 7;
        x_hash = 79 * x_hash + (m_begin != null ? m_begin.hashCode() : 0);
        x_hash = 79 * x_hash + (m_end != null ? m_end.hashCode() : 0);
        return x_hash;
    }

    @Override
    public boolean equals(Object p_obj) {
        if(p_obj == null) return false;
        if(getClass() != p_obj.getClass()) return false;

        final CAgeInterval x_other = (CAgeInterval)p_obj;
        if(m_begin != x_other.m_begin && (m_begin == null || !m_begin.equals(x_other.m_begin))) return false;
        if(m_end != x_other.m_end && (m_end == null || !m_end.equals(x_other.m_end))) return false;

        return true;
    }
    
    @Override
    public String toString() {
        return CAgeInterval.class.getName() + " {" + 
                "begin = " + String.valueOf(m_begin) +
                "; end = " + String.valueOf(m_end) +
                "}";
    }
    
}
