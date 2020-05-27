package ru.avers.informica.dao.entities;

import ru.avers.informica.dao.interaction.CInqrySysInteraction;
import ru.avers.informica.entities.Inqrychldinuch;

import javax.persistence.*;

/**
 *
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="INQRY_SYS_INTERACT")  // es - новая таблица
// (Данные по взаимодействию с внешними системами)
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRY_SYS_INTERACT_ID")
public class CntrInteract extends CInqrySysInteraction {
    private Inqrychldinuch m_inqry;
    private String m_ext01;

    public CntrInteract() { }
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_INQRY")
    public Inqrychldinuch getInqry() { return m_inqry; }
    public void setInqry(Inqrychldinuch p_val) { m_inqry = p_val; }

    @Basic(fetch= FetchType.LAZY)
    @Column(name="EXTERNAL01")
    public String getExternal01() { return m_ext01; }
    public void setExternal01(String p_val) { m_ext01 = p_val; }
    
    @Override
    public String toString() {
        return CntrInteract.class.getName() + "{" +
                    super.toString() +
                    "; inqry = " + String.valueOf(m_inqry) +
                    "; external-01 = " + String.valueOf(m_inqry) +
                    "}";
    }
    
}
