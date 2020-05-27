package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="INQRY_QUEUE_INFO")  // queue_info (Время вхождения заявления в очередь)
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRY_QUEUE_INFO_ID")
public class InqryQueueInfo extends CItem {
    
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
    @Column(name = "DT_ENTER")
    private Date m_dt_enter;
    
    public Date getDtEnter() {
        return this.m_dt_enter;
    }
    
    public void setDtEnter(Date p_dt_in) {
        this.m_dt_enter = p_dt_in;
    }    

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DT_REG")
    private Date m_dt_reg;
    
    public Date getDtReg() {
        return this.m_dt_reg;
    }
    
    public void setDtReg(Date p_dt_in) {
        this.m_dt_reg = p_dt_in;
    }        
    
}
