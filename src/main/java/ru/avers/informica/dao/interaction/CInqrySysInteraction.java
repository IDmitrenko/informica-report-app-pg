package ru.avers.informica.dao.interaction;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeInqrySysInteraction")
@XmlSeeAlso({CItem.class})

@Access(AccessType.PROPERTY)
@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class CInqrySysInteraction extends CItem {
    private CDict87SysInteraction m_type;
    
    public CInqrySysInteraction() { this((Integer)null); }
    public CInqrySysInteraction(Integer p_id) { this(p_id, null); }
    public CInqrySysInteraction(CDict87SysInteraction p_type) { this(null, p_type); }
    public CInqrySysInteraction(Integer p_id, CDict87SysInteraction p_type) {
        super(p_id);
        m_type = p_type;
    }
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_SYS_INTERACT")
    @XmlElement(name="type")
    public CDict87SysInteraction getType() { return m_type; }    
    public void setType(CDict87SysInteraction p_val) { m_type = p_val; }
    
    @Override
    public String toString() {
        return CInqrySysInteraction.class.getName() + "{" +
                    super.toString() +
                    "; type = " + String.valueOf(m_type) +
                    "}";
    }
}
