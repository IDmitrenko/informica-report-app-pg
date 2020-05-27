package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.*;

//TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author alexanderm
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeBaseDict")
@XmlSeeAlso({CItem.class})

@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class CBaseDict extends CItem {

    public CBaseDict() { super(); }
    public CBaseDict(String p_name) { this(null, p_name); }
    public CBaseDict(Integer p_id) { super(p_id); }
    public CBaseDict(Integer p_id, String p_name) {
        super(p_id); 
        m_name = p_name;
    }

    @Column(name="NM")
    private String m_name;

    @XmlElement(name="name")
    public String getName() { return m_name; }
    public void setName(String p_val) { m_name = p_val; }

    @Override
    public String toStrSpecJPA() { return toStr(); }
    @Override
    public String toString() { return toStr(); }
    
    private String toStr() {
        return
            CBaseDict.class.getName() + "{" +
            super.toString() +
            ", name = " + String.valueOf(getName()) +
            "}";
    }
}
