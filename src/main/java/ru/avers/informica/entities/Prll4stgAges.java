package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

 TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="PRLL4STG_AGES")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_PRLL4STG_AGES_ID")
public class Prll4stgAges extends CItem {

    public Prll4stgAges() { super(); }
    
    public Prll4stgAges(Short p_from_y, Short p_from_m, Short p_to_y, Short p_to_m) {
        super(); 
        m_from_year = p_from_y;
        m_from_month = p_from_m;
        m_to_year = p_to_y;
        m_to_month = p_to_m;
    }
    
//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="ID_PRLL4STG")
//    private Prll4stg m_prll4stg;
//    public Prll4stg getPrll4stg() { return m_prll4stg; }
//    public void setPrll4stg(Prll4stg p_prll4stg) { m_prll4stg = p_prll4stg; }
    
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="ID_TCLS")
//    private CDict08TypeClass m_prll_type;
//    public CDict08TypeClass getPrllType() { return m_prll_type; }
//    public void setPrllType(CDict08TypeClass p_prll_type) { m_prll_type = p_prll_type; }    

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_UCH_INFO4INQRY")
    private UchInfo4inqry m_uch_info4inqry;
    public UchInfo4inqry getUchInfo4inqry() { return m_uch_info4inqry; }
    public void setUchInfo4inqry(UchInfo4inqry p_uch_info4inqry) { m_uch_info4inqry = p_uch_info4inqry; }
    
    @Min(0)
    @Max(99)
    @Column(name="FROM_YEAR")
    private Short m_from_year;
    public Short getFromYear() { return m_from_year; }
    public void setFromYear(Short p_year) { m_from_year = p_year; }

    @Min(0)
    @Max(12)
    @Column(name="FROM_MONTH")
    private Short m_from_month;
    public Short getFromMonth() { return m_from_month; }
    public void setFromMonth(Short p_month) { m_from_month = p_month; }

    @Min(0)
    @Max(31)
    @Column(name="FROM_DAY")
    private Short m_from_day;
    public Short getFromDay() { return m_from_day; }
    public void setFromDay(Short p_day) { m_from_day = p_day; }

    @Min(0)
    @Max(99)
    @Column(name="TO_YEAR")
    private Short m_to_year;
    public Short getToYear() { return m_to_year; }
    public void setToYear(Short p_year) { m_to_year = p_year; }

    @Min(0)
    @Max(12)
    @Column(name="TO_MONTH")
    private Short m_to_month;
    public Short getToMonth() { return m_to_month; }
    public void setToMonth(Short p_month) { m_to_month = p_month; }

    @Min(0)
    @Max(31)
    @Column(name="TO_DAY")
    private Short m_to_day;
    public Short getToDay() { return m_to_day; }
    public void setToDay(Short p_day) { m_to_day = p_day; }
    
}
