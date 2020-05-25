package ru.avers.informica.dao.entities;

import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict81InqryChildLgot;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="INQRYLGOT")  // benefits - новая таблица (льготы) [вторая реализация TODO]
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRYLGOT_ID")
public class InqryLgot extends CItem {
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_INQRY")
    private Inqry m_inqry;

    public Inqry getInqry() {
        return this.m_inqry;
    }
    
    public void setInqry(Inqry p_inqry) {
        this.m_inqry = p_inqry;
    }  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LGOT")
    private CDict81InqryChildLgot m_lgot;

    public CDict81InqryChildLgot getLgot() {
        return m_lgot;
    }

    public void setLgot(CDict81InqryChildLgot p_lgot) {
        this.m_lgot = p_lgot;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "CONFIRM_DT")
    private Date m_confirm_dt;

    public Date getConfirmDt() {
        return this.m_confirm_dt;
    }

    public void setConfirmDt(Date p_dt) {
        this.m_confirm_dt = p_dt;
    }

    @Column(name = "IS_CONFIRMED")
    private Boolean m_is_confirmed;

    public Boolean getLgotConfirmed() {
        return m_is_confirmed;
    }

    public void setLgotConfirmed(Boolean m_is_confirmed) {
        this.m_is_confirmed = m_is_confirmed;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name = "VALID_UNTIL")
    private Date m_valid_until;

    public Date getValidUntil() {
        return this.m_valid_until;
    }

    public void setValidUntil(Date p_dt) {
        this.m_valid_until = p_dt;
    }

    @Column(name = "IS_UNLIMITED")
    private Boolean m_is_unlimited;

    public Boolean getLgotUnlimited() {
        return m_is_unlimited;
    }

    public void setLgotUnlimited(Boolean m_is_unlimited) {
        this.m_is_unlimited = m_is_unlimited;
    }

}
