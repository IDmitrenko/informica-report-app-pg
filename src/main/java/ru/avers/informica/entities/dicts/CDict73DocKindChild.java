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
@Table(name="V_DICT_73_DOC_KIND_CHLD")
@SequenceGenerator(name="SEQ_GEN")
public class CDict73DocKindChild extends CAbstractDictsDetailBased implements ICodedDict, IHasDescr {
    public static final String s_dict_doc_kind_parent_child = "03",
                               s_dict_doc_kind_chld_crtf_rf = "03.01",
                               s_dict_doc_kind_chld_crtf_frgn = "03.02";
    
    public CDict73DocKindChild(){ super(); }
    public CDict73DocKindChild(Integer p_id){ super(p_id); }

    private CDict73DocKindChild m_parent;
    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    @Override
    public CDict73DocKindChild getParent() { return m_parent; }
    public void setParent(CDict73DocKindChild p_val) { m_parent = p_val; }

    private List<CDict73DocKindChild> m_childs = new ArrayList<CDict73DocKindChild>();
    @OneToMany(mappedBy="parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @Override
    public List<CDict73DocKindChild> getChilds() { return m_childs; }
    public void setChilds(List<CDict73DocKindChild> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict73DocKindChild) m_childs.add((CDict73DocKindChild)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict73DocKindChild x_item = (CDict73DocKindChild)p_item;
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

    private String m_descr;
    @Column(name="DESCR")
    public String getDescription(){ return m_descr; }
    public void setDescription(String p_val) { m_descr = p_val; }

    @Transient
    public String getDescr() {return getDescription(); }
    
    
    @Override
    public String toString() {
        return
            CDict73DocKindChild.class.getName() + " {" +
            super.toString() +
            ", code=" + String.valueOf(getCode()) +
            ", descr=" + String.valueOf(getDescription()) +
            "}";
    }
}
