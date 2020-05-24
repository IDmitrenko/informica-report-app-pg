package ru.avers.informica.entities.abstraction;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeItem")

@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class CItem implements IItem<Integer>, Serializable {

    public CItem() { this(null); }
    public CItem(Integer p_id) { m_id = p_id; }

    public final static String S_ID = "m_id";
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="SEQ_GEN")
    @Column(name="ID")
    private Integer m_id;

    @XmlAttribute(name="id")
    public Integer getId() { return m_id; }
    public void setId(Integer p_val) { m_id = p_val; }
    
    @Override
    public int hashCode() { return (m_id == null ? 0 : m_id.hashCode()); }
    @Override
    public boolean equals(Object p_obj) {
        if(p_obj == null) return false;
        // hibernate при lazy-loading создает прокси классы (класс прокси наследует от реального класса), для которых сравнение не проходит
        //if(getClass() != p_obj.getClass()) return false;
        if (!getClass().isAssignableFrom(p_obj.getClass()) && !p_obj.getClass().isAssignableFrom(getClass())) return false;            
        final CItem p_other = (CItem)p_obj;
        return getId() == p_other.getId() || (getId() != null && getId().equals(p_other.getId()));
    }
    

    static public String toStrSpecJPA(CItem p_val) { return p_val == null ? "null" : p_val.toStrSpecJPA(); }
    
    @Override
    public String toStrSpecJPA() { return toStr(); }
    @Override
    public String toString() { return toStr(); }
    
    private String toStr() { return CItem.class.getName() + " {id = " + String.valueOf(m_id) + "}"; }
    
}
