package ru.avers.informica.dao.entities;

import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict76InqryStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="INQRYSTATUS")  // status - новая таблица (статусы заявлений) [вторая реализация TODO]
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRYSTATUS_ID")
public class InqryStatus extends CItem {

    public InqryStatus() { super(); }
    public InqryStatus(Integer p_id) { super(p_id); }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_INQRY")
    private Inqry m_inqry;
    public Inqry getInqry() {
        return this.m_inqry;
    }    
    public void setInqry(Inqry p_inqry) {
        this.m_inqry = p_inqry;
    }

    @ManyToOne
    @JoinColumn(name="ID_STATUS")
    private CDict76InqryStatus m_inqry_status;
    public CDict76InqryStatus getStatus() {
        return this.m_inqry_status;
    }    
    public void setStatus(CDict76InqryStatus p_inqry_status) {
        this.m_inqry_status = p_inqry_status;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DT")
    private Date m_dt;
    public Date getDt() {
        return this.m_dt;
    }    
    public void setDt(Date dt) {
        this.m_dt = dt;
    }
    
    @Column(name = "DESCR")
    private String m_descr;
    public String getDescr() {
        return this.m_descr;
    }    
    public void setDescr(String descr) {
        this.m_descr = descr;
    }
    
}
