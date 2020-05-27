package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

//TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeTypeOfUch")
@XmlSeeAlso({CAbstractDictsDetailBased.class})

@Entity
@Table(name="V_DICT_11_TYPE_UCH")
//@Immutable
@SequenceGenerator(name="SEQ_GEN")
public class CDict11TypeOfUch extends CAbstractDictsDetailBased {

    public CDict11TypeOfUch() { super(); }
    public CDict11TypeOfUch(Integer p_id) {super(p_id); }
    public CDict11TypeOfUch(Integer p_id, String p_name) {super(p_id, p_name); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict11TypeOfUch m_parent;
    
    @XmlElement(name="parent")
    @Override
    public CDict11TypeOfUch getParent() { return m_parent; }
    public void setParent(CDict11TypeOfUch p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict11TypeOfUch> m_childs = new ArrayList<CDict11TypeOfUch>();

    @XmlElement(name="child")
    @Override
    public List<CDict11TypeOfUch> getChilds() { return m_childs; }
    public void setChilds(List<CDict11TypeOfUch> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict11TypeOfUch) m_childs.add((CDict11TypeOfUch)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict11TypeOfUch x_item = (CDict11TypeOfUch)p_item;
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

    @Override
    public String toString() {
        return
                CDict11TypeOfUch.class.getName() + " {" +
                super.toString() +
                "}";
    }
}
