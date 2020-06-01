package ru.avers.informica.entities.abstraction;

import ru.avers.informica.entities.abstraction.IItem;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Dias
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class EntityProp implements IItem<Integer>, Serializable {

    public EntityProp() {
    }

    public EntityProp(Integer p_id) {
        this.m_id = p_id;
    }        
    
    private Integer m_id;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="SEQ_GEN")
    @Column(name="ID")
    @Override
    public Integer getId() { return m_id; }
    @Override
    public void setId(Integer p_val) {
        m_id = p_val;
    }

    @Override
    public int hashCode() { return (m_id == null ? 0 : m_id.hashCode()); }
    @Override
    public boolean equals(Object p_obj) {
        if(p_obj == null) return false;
        // hibernate при lazy-loading создает прокси классы (класс прокси наследует от реального класса), для которых сравнение не проходит
        //if(getClass() != p_obj.getClass()) return false;
        if (!getClass().isAssignableFrom(p_obj.getClass()) && !p_obj.getClass().isAssignableFrom(getClass())) return false;            
        final IItem p_other = (IItem)p_obj;
        return getId() == p_other.getId() || (getId() != null && getId().equals(p_other.getId()));
    }    
    
    @Override
    public String toStrSpecJPA() {
        return CItemOnMap.class.getName() + "{ id=" + String.valueOf(getId()) + "}";
    }    
}
