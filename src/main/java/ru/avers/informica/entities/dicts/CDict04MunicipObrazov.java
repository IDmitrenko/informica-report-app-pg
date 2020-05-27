package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

//TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeMunicipObrazov")
@XmlSeeAlso({CAbstractDictsDetailBased.class})
@Entity
@Table(name="V_DICT_04_MUNICIP_OBRAZOV")
@SequenceGenerator(name="SEQ_GEN")
public class CDict04MunicipObrazov extends CAbstractDictsDetailBased implements ICodedDict, IHasDescr {

    public CDict04MunicipObrazov() { super(); }
    public CDict04MunicipObrazov(Integer p_id) { super(p_id); }
    public CDict04MunicipObrazov(Integer p_id, String p_name) { super(p_id, p_name); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict04MunicipObrazov m_parent;

    @XmlElement(name="parent")
    @Override
    public CDict04MunicipObrazov getParent() { return m_parent; }
    public void setParent(CDict04MunicipObrazov p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict04MunicipObrazov> m_childs = new ArrayList<CDict04MunicipObrazov>();

    @XmlElement(name="child")
    @Override
    public List<CDict04MunicipObrazov> getChilds() { return m_childs; }
    public void setChilds(List<CDict04MunicipObrazov> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict04MunicipObrazov) m_childs.add((CDict04MunicipObrazov)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict04MunicipObrazov x_item = (CDict04MunicipObrazov)p_item;
        if(x_item != null) {
            x_item.setParent(this);
            m_childs.add(x_item);
        }
    }

    @Override
    protected CDicts getDICT() {
        //  TODO
        return null;
    }
    @Override
    protected boolean hasChilds() { return true; };
    
    @Column(name="CODE")
    private String m_code;
    
    @XmlAttribute(name="code")
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;

    @XmlElement(name="description")
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }

    
    @Column(name="ADDRESS")
    private String m_address;
    @XmlAttribute(name="address")
    public String getAddress(){ return m_address; }
    public void setAddress(String p_val) { m_address = p_val; }
    
    @Column(name="EMAIL")
    private String m_email;
    @XmlAttribute(name="email")
    public String getEmail(){ return m_email; }
    public void setEmail(String p_val) { m_email = p_val; }
    
    @Column(name="FIXAREA")
    private String m_fixarea;
    @XmlAttribute(name="fixarea")
    public String getFixArea(){ return m_fixarea; }
    public void setFixArea(String p_val) { m_fixarea = p_val; }
    
    @Column(name="NAME_MOUO")
    private String m_name_mouo;
    @XmlAttribute(name="name_mouo")
    public String getNameMouo(){ return m_name_mouo; }
    public void setNameMouo(String p_val) { m_name_mouo = p_val; }
    
    @Column(name="PHONES")
    private String m_phones;
    @XmlAttribute(name="phones")
    public String getPhones(){ return m_phones; }
    public void setPhones(String p_val) { m_phones = p_val; }
    
    @Column(name="REGULATION")
    private String m_regulation;
    @XmlAttribute(name="regulation")
    public String getRegulation(){ return m_regulation; }
    public void setRegulation(String p_val) { m_regulation = p_val; }
    
    @Column(name="SITE")
    private String m_site;
    @XmlAttribute(name="site")
    public String getSite(){ return m_site; }
    public void setSite(String p_val) { m_site = p_val; }
    
    @Column(name="OKTMO")
    private String m_oktmo;
    @XmlAttribute(name="oktmo")
    public String getOktmo(){ return m_oktmo; }
    public void setOktmo(String p_val) { m_oktmo = p_val; }
    
    @Column(name="EPGU_LINK")
    private String m_epgu_link;
    @XmlAttribute(name="epgu-link")
    public String getEpguLink(){ return m_epgu_link; }
    public void setEpguLink(String p_val) { m_epgu_link = p_val; }
    
    @Column(name="RPGU_LINK")
    private String m_rpgu_link;
    @XmlAttribute(name="rpgu-link")
    public String getRpguLink(){ return m_rpgu_link; }
    public void setRpguLink(String p_val) { m_rpgu_link = p_val; }
    
    
    @Override
    public String toString() {
        return
                CDict04MunicipObrazov.class.getName() + " {" +
                super.toString() +
                "}";
    }
}
