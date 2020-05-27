package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="DRAFT_ALLOCATION")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_DRAFT_ALLOCATION_ID")
public class DraftAlloc extends CItem {

    public DraftAlloc() {
    }        
    
    private Inqrychldinuch m_inqry;
    private UchInfo4inqry m_uch_info4inqry;
    private Integer m_year;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_INQRY")
    public Inqrychldinuch getInqry() {
        return m_inqry;
    }
    public void setInqry(Inqrychldinuch p_inqry) {
        this.m_inqry = p_inqry;
    }
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_UCH_INFO4INQRY")
    public UchInfo4inqry getUchInfo4Inqry() {
        return m_uch_info4inqry;
    }
    public void setUchInfo4Inqry(UchInfo4inqry p_uch_info4inqry) {
        this.m_uch_info4inqry = p_uch_info4inqry;
    }        
            
    @Column(name="VACANT_YEAR")
    public Integer getYear() {
        return m_year;
    }
    public void setYear(Integer p_year) {
        m_year = p_year;
    }
    
}
