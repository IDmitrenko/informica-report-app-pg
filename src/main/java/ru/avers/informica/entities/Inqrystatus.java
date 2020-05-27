package ru.avers.informica.entities;

import ru.avers.informica.dao.IMapFields;
import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict08TypeClass;
import ru.avers.informica.entities.dicts.CDict76InqryStatus;
import ru.avers.informica.entities.dicts.CDict85DouGrpTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="INQRYSTATUS")  // status - новая таблица (Статусы заявлений)
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRYSTATUS_ID")
public class Inqrystatus extends CItem {

    public final static String S_INQRY = "m_inqry",
                               S_ID_INQRY = S_INQRY + IMapFields.s_field_delimiter + S_ID,
                               S_STATUS = "m_inqry_status",
                               S_STATUS_NM = "m_inqry_status.m_name",
                               S_STATUS_CODE = "m_inqry_status.m_code",            
                               S_STATUS_ID = S_STATUS + IMapFields.s_field_delimiter + S_ID,
                               S_PRLL4STG = "m_prll4stg",
                               S_PRLLTYPE = "m_prll_type",
                               S_DT = "m_dt";

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_INQRY")
    private Inqrychldinuch m_inqry;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_UCH")
    private Uch m_uch;

    //@ManyToOne(fetch=FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name="ID_STATUS")
    private CDict76InqryStatus m_inqry_status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DT")
    private Date m_dt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DF")
    private Date m_df;

    @Column(name = "TIMEOUT")
    private Integer m_timeout;

    @Column(name = "DESCR")
    private String m_descr;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_inqrystatus")
    private List<Inqrystsrsn> m_inqrystsrsns = new ArrayList<Inqrystsrsn>(0);

    @ManyToOne
    @JoinColumn(name="ID_PRLL4STG")
    private Prll4stg m_prll4stg;

    @ManyToOne
    @JoinColumn(name="ID_TCLS")
    private CDict08TypeClass m_prll_type;
    
    @ManyToOne
    @JoinColumn(name="ID_DOU_GRP_TIME")
    private CDict85DouGrpTime m_dou_grp_type;
    public CDict85DouGrpTime getDouGrpTime() { return m_dou_grp_type; }
    public void setDouGrpTime(CDict85DouGrpTime p_val) { m_dou_grp_type = p_val; }
    
    @Temporal(TemporalType.DATE)
    @Column(name="DT_TO")
    private Date m_dt_to;
    public Date getDtTo() { return m_dt_to; }
    public void setDtTo(Date p_dt_to) { m_dt_to = p_dt_to; }
    
    @Temporal(TemporalType.DATE)
    @Column(name="APPROVED_TO")
    private Date m_approved_to;
    public Date getApprovedTo() { return m_approved_to; }
    public void setApprovedTo(Date p_approved_to) { m_approved_to = p_approved_to; }
    
    @Column(name="VACANT_YEAR")
    private Integer m_year;
    public Integer getYear() {
        return m_year;
    }
    public void setYear(Integer p_year) {
        m_year = p_year;
    }
    
    @Column(name="VOUCHER_NUM")
    private Integer m_voucher_num;
    public Integer getVoucherNum() {
        return m_voucher_num;
    }
    public void setVoucherNum(Integer p_voucher_num) {
        m_voucher_num = p_voucher_num;
    }

    public Inqrystatus() { super(); }
    public Inqrystatus(Integer p_id) { super(p_id); }

    public Inqrychldinuch getInqry() {
        return this.m_inqry;
    }
    
    public void setInqry(Inqrychldinuch p_inqry) {
        this.m_inqry = p_inqry;
    }

    public Uch getUch() {
        return this.m_uch;
    }

    public void setUch(Uch p_uch) {
        this.m_uch = p_uch;
    }

    public CDict76InqryStatus getStatus() {
        return this.m_inqry_status;
    }
    
    public void setStatus(CDict76InqryStatus p_inqry_status) {
        this.m_inqry_status = p_inqry_status;
    }
    public Date getDt() {
        return this.m_dt;
    }
    
    public void setDt(Date dt) {
        this.m_dt = dt;
    }

    public Date getDf() {
        return this.m_df;
    }
    
    public void setDf(Date df) {
        this.m_df = df;
    }    
    
    public Integer getTimeout() {
        return this.m_timeout;
    }
    
    public void setTimeout(Integer timeout) {
        this.m_timeout = timeout;
    }
    
    public String getDescr() {
        return this.m_descr;
    }
    
    public void setDescr(String descr) {
        this.m_descr = descr;
    }

    public List<Inqrystsrsn> getInqrystsrsns() {
        return this.m_inqrystsrsns;
    }
    
    public void setInqrystsrsns(List<Inqrystsrsn> p_inqrystsrsns) {
        this.m_inqrystsrsns = p_inqrystsrsns;
    }

    public Prll4stg getPrll4stg()
    {
        return this.m_prll4stg;
    }

    public void setPrll4stg(Prll4stg p_prll4stg)
    {
        this.m_prll4stg = p_prll4stg;
    }

    public CDict08TypeClass getPrllType()
    {
        return this.m_prll_type;
    }

    public void setPrllType(CDict08TypeClass p_prll_type)
    {
        this.m_prll_type = p_prll_type;
    }

    public static Inqrystatus newStatus(Inqrychldinuch p_inqry, CDict76InqryStatus p_status, Date p_dt) {
        Inqrystatus x_inqry_status = new Inqrystatus();
        x_inqry_status.setInqry(p_inqry);
        x_inqry_status.setStatus(p_status);
        x_inqry_status.setDt(p_dt);
        p_inqry.getInqrystatuses().add(x_inqry_status);
        return x_inqry_status;
    }
    
} 