package ru.avers.informica.entities.informica;

import ru.avers.informica.entities.AddrUch;
import ru.avers.informica.entities.UchInfo4inqry;
import ru.avers.informica.entities.abstraction.EntityProp;
import ru.avers.informica.entities.dicts.CDict04MunicipObrazov;
import ru.avers.informica.entities.dicts.CDict06TypeOfSobstven;
import ru.avers.informica.entities.dicts.CDict90OrgFuncStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Только для чтения
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="UCH")
@SecondaryTables({
    @SecondaryTable(name = "UCH_VAR", pkJoinColumns = {
        @PrimaryKeyJoinColumn(name = "id_uch", referencedColumnName = "id")
    })
})
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_UCH_ID")
@SuppressWarnings("PersistenceUnitPresent")
public class InformicaUch extends EntityProp {

    private String m_code,
                   m_name,
                   m_short_name,
                   m_chief,
                   m_municipObrOktmo,
                   m_epgu_link,
                   m_rpgu_link,
                   m_meal_serving_type;
    private InformicaUch m_main_uch;
    private Integer m_kind;
    private CDict06TypeOfSobstven m_type_of_sobstven;
    private CDict90OrgFuncStatus m_func_status;
    private CDict04MunicipObrazov m_ter;
    private List<InformicaUchContact> m_contacts = new ArrayList<InformicaUchContact>(0);
    private List<UchInfo4inqry> m_uchInfo4inqries = new ArrayList<UchInfo4inqry>(0);
    private List<AddrUch> m_addresses = new ArrayList<AddrUch>(0);
    private List<InformicaBuilding> m_buildings = new ArrayList<InformicaBuilding>(0);

    public InformicaUch() { this(null); }
    public InformicaUch(Integer p_id) { super(p_id); }
    
    @ManyToOne(cascade= CascadeType.PERSIST, fetch= FetchType.EAGER)
    @JoinColumn(name="ID_MAIN_UCH")
    public InformicaUch getMainUch() { return m_main_uch; }
    public void setMainUch(InformicaUch p_value) { m_main_uch = p_value; }
    
    @Column(name = "CODE")
    public String getCode() { return this.m_code; }
    private void setCode(String code) { this.m_code = code; }

    @Column(name = "NM", table = "UCH_VAR")
    public String getName() { return m_name; }
    private void setName(String p_name) { m_name = p_name; }
    
    @Column(name = "SNM", table = "UCH_VAR")
    public String getShortName() {
        return m_short_name;
    }    
    private void setShortName(String p_sname) {
        m_short_name = p_sname;
    }    
    
    @Column(name = "CHIEF", table = "UCH_VAR")
    public String getChief() { return m_chief; }
    private void setChief(String p_name) { m_chief = p_name; }
    
    @ManyToOne(cascade= CascadeType.PERSIST, fetch= FetchType.EAGER)
    @JoinColumn(name="ID_TER", table = "UCH_VAR")
    public CDict04MunicipObrazov getTer() {
        return m_ter;
    }
    public void setTer(CDict04MunicipObrazov p_ter) {
        m_ter = p_ter;
    }
    
    @ManyToOne(cascade= CascadeType.PERSIST, fetch= FetchType.EAGER)
    @JoinColumn(name = "ID_FSOBSTV", table = "UCH_VAR")
    public CDict06TypeOfSobstven getTypeOfSobstven() { return m_type_of_sobstven; }
    public void setTypeOfSobstven(CDict06TypeOfSobstven p_contact_kind) { m_type_of_sobstven = p_contact_kind; }

    @ManyToOne(cascade= CascadeType.PERSIST, fetch= FetchType.EAGER)
    @JoinColumn(name = "ID_FUNC_STATUS", table = "UCH_VAR")
    public CDict90OrgFuncStatus getFuncStatus() { return m_func_status; }    
    public void setFuncStatus(CDict90OrgFuncStatus p_func_status) { m_func_status = p_func_status;}
        
    @Column(name = "MEAL_SERVING_TYPE", table = "UCH_VAR")
    public String getMealServingType() { return m_meal_serving_type; }
    public void setMealServingType(String p_meal_serving_type) { m_meal_serving_type = p_meal_serving_type; }
    
    @Column(name = "MUNICIP_OBR_OKTMO")
    public String getMunicipObrOktmo() { return this.m_municipObrOktmo; }
    public void setMunicipObrOktmo(String municipObrOktmo) { this.m_municipObrOktmo = municipObrOktmo; }

    @Column(name = "KIND")
    public Integer getKind() { return this.m_kind; }
    private void setKind(Integer kind) { this.m_kind = kind; }

    @Column(name = "EPGU_LINK")
    public String getEpguLink() { return m_epgu_link; }
    public void setEpguLink(String p_epgu_link) { this.m_epgu_link = p_epgu_link; }

    @Column(name = "RPGU_LINK")
    public String getRpguLink() { return m_rpgu_link; }
    public void setRpguLink(String p_rpgu_link) { this.m_rpgu_link = p_rpgu_link; }
        
    @OneToMany(fetch= FetchType.LAZY, mappedBy="uch")
    public List<InformicaUchContact> getContacts() { return m_contacts; }
    private void setContacts(List<InformicaUchContact> p_val) { m_contacts = p_val; }
 
    @OneToMany(fetch= FetchType.LAZY, mappedBy="m_uch")
    public List<UchInfo4inqry> getUchInfo() { return this.m_uchInfo4inqries; }
    private void setUchInfo(List<UchInfo4inqry> p_uchInfo4inqries) { this.m_uchInfo4inqries = p_uchInfo4inqries; }
 
    @OneToMany(fetch= FetchType.LAZY, mappedBy="m_uch")
    public List<AddrUch> getAddresses() { return m_addresses; }
    private void setAddresses(List<AddrUch> p_val) { m_addresses = p_val; }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "uch", orphanRemoval=true)
    public List<InformicaBuilding> getBuildings() { return m_buildings; }
    public void setBuildings(List<InformicaBuilding> p_buildings) { this.m_buildings = p_buildings; }
    
}
