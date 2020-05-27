package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict05StupenObuch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO ��� ���� ������� ������� � ����� ��
/**
 * StageUch generated by hbm2java
 */
@Entity
@Table(name="STAGE_UCH")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_STAGE_UCH_ID")
public class StageUch extends CItem implements IUchRelated {

    public final static String S_UCH_FIELD = "m_uch";

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ID_UCH")
    private Uch m_uch;

    public Uch getUch() {
        return this.m_uch;
    }

    public void setUch(Uch uch) {
        this.m_uch = uch;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ID_STAGE")
    private CDict05StupenObuch m_stupen_obuch;

    @Column(name = "DESCR_STAGE")
    private String descrStage;

    @Column(name = "NSTAGE")
    private Integer nstage;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DS")
    private Date ds;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DF")
    private Date df;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_stage_uch")
    private List<Prll4stg> prll4stgs = new ArrayList<Prll4stg>(0);

    public StageUch() { super(); }
    public StageUch(Integer p_id) { super(p_id); }
    public StageUch(CDict05StupenObuch p_stupen_obuch, Uch p_uch) { this(null, p_stupen_obuch, p_uch); }
    public StageUch(Integer p_id, CDict05StupenObuch p_stupen_obuch, Uch p_uch) {
        super(p_id);
        this.m_stupen_obuch = p_stupen_obuch;
        setUch(p_uch);
    }
   
    public CDict05StupenObuch getStupenObuch() {
        return this.m_stupen_obuch;
    }
    
    public void setStupenObuch(CDict05StupenObuch dictsDetail) {
        this.m_stupen_obuch = dictsDetail;
    }
   
    public String getDescrStage() {
        return this.descrStage;
    }
    
    public void setDescrStage(String descrStage) {
        this.descrStage = descrStage;
    }
    
    public Integer getNstage() {
        return this.nstage;
    }
    
    public void setNstage(Integer nstage) {
        this.nstage = nstage;
    }
    public Date getDs() {
        return this.ds;
    }
    
    public void setDs(Date ds) {
        this.ds = ds;
    }
    public Date getDf() {
        return this.df;
    }
    
    public void setDf(Date df) {
        this.df = df;
    }
    public List<Prll4stg> getPrll4stgs() {
        return this.prll4stgs;
    }
    
    public void setPrll4stgs(List<Prll4stg> prll4stgs) {
        this.prll4stgs = prll4stgs;
    }

}