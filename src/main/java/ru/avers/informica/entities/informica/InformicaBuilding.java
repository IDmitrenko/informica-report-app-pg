package ru.avers.informica.entities.informica;

import ru.avers.informica.entities.abstraction.EntityProp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="INF_BUILDING")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INF_BUILDING_ID")
@SuppressWarnings("PersistenceUnitPresent")
public class InformicaBuilding extends EntityProp {
    
    private InformicaUch m_uch;
    private String m_code,
                   m_name,
                   m_fias_house_guid,
                   m_addr_str;
    private List<InformicaGroup> m_groups = new ArrayList<InformicaGroup>(0);

    public InformicaBuilding() {
        super();
    }       
    public InformicaBuilding(Integer p_id) {
        super(p_id);
    }       
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_UCH", nullable=false)
    public InformicaUch getUch() {
        return m_uch;
    }
    public void setUch(InformicaUch uch) {
        this.m_uch = uch;
    }
    
    @Column(name = "CODE")
    public String getCode() {
        return m_code;
    }    
    public void setCode(String p_code) {
        m_code = p_code;
    }
    
    @Column(name = "NAME")
    public String getName() {
        return m_name;
    }    
    public void setName(String p_name) {
        m_name = p_name;
    }

    @Column(name = "FIAS_HOUSE_GUID")
    public String getFiasHouseGuid() {
        return m_fias_house_guid;
    }

    public void setFiasHouseGuid(String p_fias_house_guid) {
        this.m_fias_house_guid = p_fias_house_guid;
    }

    @Column(name = "ADDR_STR")
    public String getAddrStr() {
        return m_addr_str;
    }

    public void setAddrStr(String p_addr_str) {
        this.m_addr_str = p_addr_str;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "building", orphanRemoval=true)
    public List<InformicaGroup> getGroups() {
        return m_groups;
    }

    public void setGroups(List<InformicaGroup> p_groups) {
        this.m_groups = p_groups;
    }
            
}
