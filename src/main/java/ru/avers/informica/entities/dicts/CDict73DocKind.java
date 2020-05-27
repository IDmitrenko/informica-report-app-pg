package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeDocKind")
@XmlSeeAlso({CAbstractDictsDetailBased.class})

@Entity
@Access(AccessType.PROPERTY)
@Table(name="V_DICT_73_DOC_KIND")
@SequenceGenerator(name="SEQ_GEN")
public class CDict73DocKind extends CAbstractDictsDetailBased implements ICodedDict, IHasDescr {
    final static public String s_dict_code = "73";
    
    public CDict73DocKind(){ super(); }
    public CDict73DocKind(Integer p_id){ super(p_id); }

    private CDict73DocKind m_parent;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    @Override
    @XmlElement(name="parent")
    public CDict73DocKind getParent() { return m_parent; }
    public void setParent(CDict73DocKind p_val) { m_parent = p_val; }

    private List<CDict73DocKind> m_childs = new ArrayList<CDict73DocKind>();
    @OneToMany(mappedBy="parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @Override
    @XmlElement(name="child")
    public List<CDict73DocKind> getChilds() { return m_childs; }
    public void setChilds(List<CDict73DocKind> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict73DocKind) m_childs.add((CDict73DocKind)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict73DocKind x_item = (CDict73DocKind)p_item;
        if(x_item != null) {
            x_item.setParent(this);
            m_childs.add(x_item);
        }
    }

    @Override
    protected boolean hasChilds() { return true; }

    @Transient
    @Override
    protected CDicts getDICT() {
        //  TODO
        return null;
    }

    private String m_code;
    @Column(name="CODE")
    @XmlAttribute(name="code")
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    private String m_descr;
    @Column(name="DESCR")
    public String getDescription(){ return m_descr; }
    public void setDescription(String p_val) { m_descr = p_val; }
    
    @Transient
    public String getDescr() { return getDescription(); }
    
    
    @Override
    public String toString() {
        return
            CDict73DocKind.class.getName() + " {" +
            super.toString() +
            ", code=" + String.valueOf(getCode()) +
            ", descr=" + String.valueOf(getDescription()) +
            "}";
    }
}
