package ru.avers.informica.entities.abstraction;

import ru.avers.informica.dao.IMapFields;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dias
 */
@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public class CItemOnMap implements IItem<Integer>, Serializable {
    final public static String s_field_name_id = IMapFields.s_id;

    final private Map<String, Object> m_data = new HashMap<String, Object>();
    
    @javax.persistence.Transient
    public Map<String, Object> getDataMap() { return m_data; }
    protected void setDataMap(Map<String, Object> p_data) {
        m_data.clear();
        if(p_data != null) m_data.putAll(p_data);
    }
    
    public CItemOnMap() { this((Map<String, Object>)null); }
    public CItemOnMap(Map<String, Object> p_data) {
        if(p_data != null) m_data.putAll(p_data);
    }
    public CItemOnMap(Integer p_id) {
        if(p_id != null) m_data.put(s_field_name_id, p_id);
    }
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="SEQ_GEN")
    @Column(name="ID")
    @Override
    public Integer getId() { return (Integer)m_data.get(s_field_name_id); }
    @Override
    public void setId(Integer p_val) {
        if(p_val == null)   m_data.remove(s_field_name_id);
        else                m_data.put(s_field_name_id, p_val);
    }

    @Override
    public String toStrSpecJPA() {
        return CItemOnMap.class.getName() + "{" +
                s_field_name_id + "=" + String.valueOf(getId()) +
                "}"; 
    }
    
    @Override
    public String toString() { return getClass().getName() + "{" + String.valueOf(m_data) + "}"; }
}
