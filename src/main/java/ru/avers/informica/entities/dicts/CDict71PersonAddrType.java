package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="V_DICT_71_PERSON_ADDR_TYPE")
@SequenceGenerator(name="SEQ_GEN")
public class CDict71PersonAddrType extends CAbstractDictsDetailBased {

    public CDict71PersonAddrType(){ super(); }

    private CDict71PersonAddrType m_parent;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    @Override
//    public CAbstractDictsDetailBased getParent() { return m_parent; }
    public CDict71PersonAddrType getParent() { return m_parent; }
    public void setParent(CDict71PersonAddrType p_val) { m_parent = p_val; }

    private List<CDict71PersonAddrType> m_childs = new ArrayList<CDict71PersonAddrType>();
    @OneToMany(mappedBy="parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    @Override
//    public List<? extends CAbstractDictsDetailBased> getChilds() { return m_childs; }
    public List<CDict71PersonAddrType> getChilds() { return m_childs; }
    public void setChilds(List<CDict71PersonAddrType> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict71PersonAddrType) m_childs.add((CDict71PersonAddrType)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict71PersonAddrType x_item = (CDict71PersonAddrType)p_item;
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
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return
            CDict71PersonAddrType.class.getName() + " {" +
            super.toString() +
            ", code = " + String.valueOf(getCode()) +
            "}";
    }

}
