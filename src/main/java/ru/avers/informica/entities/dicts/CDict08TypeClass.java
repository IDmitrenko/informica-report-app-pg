package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

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
@XmlType(name = "TypeTypeClass")
@XmlSeeAlso({CAbstractDictsDetailBased.class})

@Entity
@Table(name="V_DICT_08_TYPE_CLASS")
@SequenceGenerator(name="SEQ_GEN")
public class CDict08TypeClass extends CAbstractDictsDetailBased implements ICodedDict {
    
    final static public String s_common_code = "01",
                               s_dict_code = "08",
                               s_common_value_code = "01.01";
    
    @Transient
    private String m_description;
    
    public CDict08TypeClass() {super();}
    public CDict08TypeClass(Integer p_id) { super(p_id); }
    public CDict08TypeClass(Integer p_id, String p_name) { super(p_id, p_name); }

    @Access(AccessType.PROPERTY)
    @Column(name="DESCR")
    @XmlElement(name="description")
    public String getDescription() { return m_description; }
    public void setDescription(String p_val) { m_description = p_val; }

    
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict08TypeClass m_parent;

    @XmlElement(name="parent")
    @Override
    public CDict08TypeClass getParent() { return m_parent; }
    public void setParent(CDict08TypeClass p_value) { m_parent = p_value; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict08TypeClass> m_childs = new ArrayList<CDict08TypeClass>();

    @XmlElement(name="child")
    @Override
    public List<CDict08TypeClass> getChilds() { return m_childs; }
    public void setChilds(List<CDict08TypeClass> p_value) { m_childs = p_value; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict08TypeClass) m_childs.add((CDict08TypeClass)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict08TypeClass x_item = (CDict08TypeClass)p_item;
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
    protected boolean hasChilds() { return true; }

    @Column(name="CODE")
    private String m_code;
    
    @XmlAttribute(name="code")
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }
    
    @Override
    public String toString() {
        return
                CDict08TypeClass.class.getName() + " {" +
                super.toString() +
                ", description=" + String.valueOf(m_description) +
                "}";
    }

}
