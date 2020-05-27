package ru.avers.informica.entities.dicts;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeInqryChildLgot")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Table(name="V_DICT_81_INQRY_CHILD_LGOT")
@SequenceGenerator(name="SEQ_GEN")
public class CDict81InqryChildLgot extends CBaseDict implements ICodedDict, IHasDescr {
    public static final String s_dict_code = "81";
    
    public CDict81InqryChildLgot() { super(); }

    public CDict81InqryChildLgot(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @XmlAttribute(name="code")
    @Override
    public String getCode(){ return m_code; }
    @Override
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;
    @XmlElement(name="description")
    @Override
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }
    
    @Column(name="TYP")
    private Integer m_type;
    @XmlElement(name="type")
    public Integer getType(){ return m_type; }
    public void setType(Integer p_val) { m_type = p_val; }
    
    @ManyToMany(fetch= FetchType.LAZY, targetEntity=CDict89InqryCateg.class)
    @JoinTable(name="INQRY_QUEUE_CATEG", joinColumns=@JoinColumn(name="ID_LGOT"),
        inverseJoinColumns=@JoinColumn(name="ID_CATEG"))
    private List<CDict89InqryCateg> m_categs = new ArrayList<CDict89InqryCateg>(0);
    public List<CDict89InqryCateg> getCategs() { return m_categs; }
    public void setCategs(List<CDict89InqryCateg> p_categs) { m_categs = p_categs; }
    
    @ManyToMany(fetch= FetchType.LAZY, targetEntity = CDict04MunicipObrazov.class)
    @JoinTable(name="LGOT_MUNICIP_OBRAZOV", joinColumns = @JoinColumn(name="ID_LGOT"),
            inverseJoinColumns = @JoinColumn(name="ID_TER"))
    private List<CDict04MunicipObrazov> m_ters = new ArrayList<CDict04MunicipObrazov>();
    public List<CDict04MunicipObrazov> getTers() { return m_ters; }
    public void setTers(List<CDict04MunicipObrazov> p_ters) { m_ters = p_ters; }
    
    @Override
    public String toString() {
        return CDict81InqryChildLgot.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) +
                "; type=" + String.valueOf(m_type) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }

}
