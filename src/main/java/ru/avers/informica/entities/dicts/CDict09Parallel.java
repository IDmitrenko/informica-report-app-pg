package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

 TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeParallel")
@XmlSeeAlso({CAbstractDictsDetailBased.class})
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="V_DICT_09_PARALLEL")
//@Immutable
@SequenceGenerator(name="SEQ_GEN")
public class CDict09Parallel extends CAbstractDictsDetailBased implements ICodedDict {
    public CDict09Parallel() { super(); }
    public CDict09Parallel(Integer p_id) { super(p_id); }
    public CDict09Parallel(Integer p_id, String p_name) { super(p_id, p_name); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict09Parallel m_parent;

    @XmlElement(name="parent")
    @Override
    public CDict09Parallel getParent() { return m_parent; }
    public void setParent(CDict09Parallel p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict09Parallel> m_childs = new ArrayList<CDict09Parallel>();

    @XmlElement(name="child")
    @Override
    public List<CDict09Parallel> getChilds() { return m_childs; }
    public void setChilds(List<CDict09Parallel> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict09Parallel) m_childs.add((CDict09Parallel)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict09Parallel x_item = (CDict09Parallel)p_item;
        if(x_item != null) {
            x_item.setParent(this);
            m_childs.add(x_item);
        }
    }

    @Column(name="CODE")
    private String m_code;
    
    @XmlAttribute(name="code")
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }
    
    @Override
    protected CDicts getDICT() {
            //  TODO
        return null;
}
    @Override
    protected boolean hasChilds() { return true; };

    @Override
    public String toString() {
        return
                CDict09Parallel.class.getName() + " {" +
                super.toString() +
                ", code=" + String.valueOf(m_code) +
                "}";
    }
}
