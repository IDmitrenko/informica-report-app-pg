package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

//TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeAbstractDictsDetailBased")
@XmlSeeAlso({CBaseDict.class})

@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class CAbstractDictsDetailBased extends CBaseDict implements ITree {
    
    public CAbstractDictsDetailBased() { super(); }
    protected CAbstractDictsDetailBased(Integer p_id) { super(p_id); }
    protected CAbstractDictsDetailBased(Integer p_id, String p_name) { super(p_id, p_name); }
    
    @Transient
    final private CDictsDetail m_dicts_detail_item = new CDictsDetail();
    protected CDictsDetail getDictsDetailItem() {
        return m_dicts_detail_item;
    }

    public enum TActualizeKind {NONE, ONLYNODE, NODEANDSUBTREE, WHOLETREE}

    //==========================================================================
    //
    //  переопределяемые в наследнике методы
    //
    abstract public CAbstractDictsDetailBased getParent();
    abstract public List<? extends CAbstractDictsDetailBased> getChilds();
    abstract protected boolean hasChilds();
    abstract public void addChild(ITree p_item);
    abstract public void addChildOnly(ITree p_item);

    abstract protected CDicts getDICT();
    protected String getSpare01() { return null; }
    protected String getSpare02() { return null; }
    protected Short getSpare03() { return null; }
    //==========================================================================

    private CAbstractDictsDetailBased root() {
        CAbstractDictsDetailBased x_root = this;
        while(x_root.getParent() != null) x_root = x_root.getParent();
        return x_root;
    }

    private void actualizeDictsDetailItem(boolean p_is_recursive) {
        m_dicts_detail_item.setId(getId());
        m_dicts_detail_item.setDict(getDICT());
        m_dicts_detail_item.setName(getName());
        m_dicts_detail_item.setSpare01(getSpare01());
        m_dicts_detail_item.setSpare02(getSpare02());
        m_dicts_detail_item.setSpare03(getSpare03());

        //  актуализация родительского элемента
        CAbstractDictsDetailBased x_parent = getParent();
        m_dicts_detail_item.setParent( (x_parent == null ? null : x_parent.m_dicts_detail_item) );

        if(hasChilds()) {
            //  актуализация списка дочерних
            List<CDictsDetail> x_lst = new ArrayList<CDictsDetail>();
            for(CAbstractDictsDetailBased x_item: getChilds()) {
                CDictsDetail x_dd_item = x_item.m_dicts_detail_item;
                if(p_is_recursive)
                    x_item.actualizeDictsDetailItem(p_is_recursive);
                else
                    x_dd_item.setParent(m_dicts_detail_item);

                x_lst.add(x_dd_item);
            }
            m_dicts_detail_item.setChilds(x_lst);
        }
    }

    public CDictsDetail dictsDetailItem(TActualizeKind p_actualize_kind) {
        switch(p_actualize_kind) {
            case ONLYNODE:
                actualizeDictsDetailItem(false);
                break;
            case NODEANDSUBTREE:
                actualizeDictsDetailItem(true);
                break;
            case WHOLETREE:
                root().actualizeDictsDetailItem(true);
                break;
        }
        return m_dicts_detail_item;
    }

    @Override
    public String toString() {
        return
                CAbstractDictsDetailBased.class.getName() + " {" +
                super.toString() +
                "}";
    }

}
