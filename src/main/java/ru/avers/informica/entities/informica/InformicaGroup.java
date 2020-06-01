package ru.avers.informica.entities.informica;

import ru.avers.informica.entities.abstraction.EntityProp;
import ru.avers.informica.entities.dicts.CDict08TypeClass;
import ru.avers.informica.entities.dicts.CDict85DouGrpTime;
import ru.avers.informica.entities.dicts.CDict93GrpActivity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="INF_GROUP")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INF_GROUP_ID")
@SuppressWarnings("PersistenceUnitPresent")
public class InformicaGroup extends EntityProp {
    
    private InformicaBuilding m_building;    
    private String m_code,
                   m_name;
    private Short m_from_year,
                  m_from_month,
                  m_to_year,
                  m_to_month,
                  m_n_norm,
                  m_n_fact;
    private Integer m_cnt_ovz, m_cnt_free_space;
    private CDict08TypeClass m_type;
    private CDict85DouGrpTime m_dou_grp_time;
    private CDict93GrpActivity m_activity;
    
    public InformicaGroup() {
    }
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_BUILDING", nullable=false)
    public InformicaBuilding getBuilding() { return m_building; }
    public void setBuilding(InformicaBuilding p_val) { m_building = p_val; }
    
    @Column(name = "CODE")
    public String getCode() { return m_code; }
    public void setCode(String p_code) { m_code = p_code; }
    
    @Min(0)
    @Max(99)
    @Column(name="FROM_YEAR")
    public Short getFromYear() { return m_from_year; }
    public void setFromYear(Short p_year) { m_from_year = p_year; }

    @Min(0)
    @Max(12)
    @Column(name="FROM_MONTH")
    public Short getFromMonth() { return m_from_month; }
    public void setFromMonth(Short p_month) { m_from_month = p_month; }

    @Min(0)
    @Max(99)
    @Column(name="TO_YEAR")
    public Short getToYear() { return m_to_year; }
    public void setToYear(Short p_year) { m_to_year = p_year; }

    @Min(0)
    @Max(12)
    @Column(name="TO_MONTH")
    public Short getToMonth() { return m_to_month; }
    public void setToMonth(Short p_month) { m_to_month = p_month; }
            
    @Column(name = "NAME")
    public String getName() { return m_name; }
    public void setName(String p_name) { m_name = p_name; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TCLS")
    public CDict08TypeClass getType() { return m_type; }
    public void setType(CDict08TypeClass p_value) { m_type = p_value; }
    
    @Column(name = "N_NORM")
    public Short getNorm() { return m_n_norm; }
    public void setNorm(Short p_val) { m_n_norm = p_val; }
    
    @Column(name = "N_FACT")
    public Short getNFact() { return m_n_fact; }
    public void setNFact(Short p_val) { m_n_fact = p_val; }

    @Column(name="N_OVZ")
    public Integer getCntOvz() { return m_cnt_ovz; }
    public void setCntOvz(Integer p_val) { m_cnt_ovz = p_val; }
    
    @Column(name="N_FREE_SPACE")
    public Integer getCntFreeSpace() { return m_cnt_free_space; }
    public void setCntFreeSpace(Integer p_val) { m_cnt_free_space = p_val; }

    @ManyToOne
    @JoinColumn(name = "ID_DOU_GRP_TIME")
    public CDict85DouGrpTime getDouGrpTime() { return m_dou_grp_time; }
    public void setDouGrpTime(CDict85DouGrpTime p_val) { m_dou_grp_time = p_val; }

    @ManyToOne
    @JoinColumn(name = "ID_GRP_ACTIVITY")
    public CDict93GrpActivity getActivity() { return m_activity; }
    public void setActivity(CDict93GrpActivity p_val) { m_activity = p_val; }
    
    
}
