package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="INQRY_QUEUE_WINDOW")  // periods - новая таблица
// (Периоды пребывания заявления в очереди)
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRY_QUEUE_WINDOW_ID")
public class InqryQueueWindow extends CItem {
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_INQRY")
    private Inqrychldinuch m_inqry;

    public Inqrychldinuch getInqry() {
        return this.m_inqry;
    }
    
    public void setInqry(Inqrychldinuch p_inqry) {
        this.m_inqry = p_inqry;
    }        
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DT_IN")
    private Date m_dt_in;
    
    public Date getDtIn() {
        return this.m_dt_in;
    }
    
    public void setDtIn(Date p_dt_in) {
        this.m_dt_in = p_dt_in;
    }    
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DT_OUT")
    private Date m_dt_out;

    public Date getDtOut() {
        return this.m_dt_out;
    }
    
    public void setDtOut(Date p_dt_out) {
        this.m_dt_out = p_dt_out;
    }    
    
    public boolean isInQueue(Date p_curr_dt) {
        return m_dt_in != null && (m_dt_in.before(p_curr_dt) || m_dt_in.equals(p_curr_dt)) && (m_dt_out == null || m_dt_out.after(p_curr_dt));        
    }
    
}
