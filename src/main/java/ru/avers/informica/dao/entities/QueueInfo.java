package ru.avers.informica.dao.entities;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="INQRY_QUEUE_INFO")  // queue_info - новая таблица (Время вхождения заявления в очередь)
// [вторая реализация  TODO]
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRY_QUEUE_INFO_ID")
public class QueueInfo extends CItem {
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_INQRY")
    private Inqry m_inqry;

    public Inqry getInqry() {
        return this.m_inqry;
    }
    
    public void setInqry(Inqry p_inqry) {
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
        
}
