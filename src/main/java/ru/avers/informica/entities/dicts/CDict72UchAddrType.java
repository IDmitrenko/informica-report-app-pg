package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="V_DICT_72_UCH_ADDR_TYPE")
@SequenceGenerator(name="SEQ_GEN")
public class CDict72UchAddrType extends CAbstractDictsDetailBased implements ICodedDict {
    
    final static public String
                                s_code_juri = "01",
                                s_code_fact = "02";
    
    public CDict72UchAddrType(){ super(); }
    public CDict72UchAddrType(int p_id) { super(p_id); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict72UchAddrType m_parent;

    @Override
    public CAbstractDictsDetailBased getParent() { return m_parent; }
    public void setParent(CDict72UchAddrType p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict72UchAddrType> m_childs = new ArrayList<CDict72UchAddrType>();
    @Override
    public List<? extends CAbstractDictsDetailBased> getChilds() { return m_childs; }
    public void setChilds(List<CDict72UchAddrType> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict72UchAddrType) m_childs.add((CDict72UchAddrType)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict72UchAddrType x_item = (CDict72UchAddrType)p_item;
        if(x_item != null) {
            x_item.setParent(this);
            m_childs.add(x_item);
        }
    }

    @Override
    protected boolean hasChilds() { return true; }

    @Override
    protected CDicts getDICT() {
        //  TODO
        return null;
    }

    @Column(name="CODE")
    private String m_code;
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return 
            CDict72UchAddrType.class.getName() + " {" +
            super.toString() +
            ", code = " + String.valueOf(getCode()) +
            "}";
    }
}
