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
@Table(name="V_DICT_03_CONTACT_TYPE")
//@Immutable
@SequenceGenerator(name="SEQ_GEN")
public class CDict03ContactType extends CAbstractDictsDetailBased implements ICodedDict {

    public final static String s_phone_zaved_code = "01";
    public final static String s_phone_priemn_code = "02";
    public final static String s_fax_code = "03";

    public CDict03ContactType() {super();}
    public CDict03ContactType(Integer p_id) { super(p_id); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict03ContactType m_parent;

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict03ContactType> m_childs = new ArrayList<CDict03ContactType>();

    @Column(name="CODE")
    private String m_code;

    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public CDict03ContactType getParent() { return m_parent; }
    public void setParent(CDict03ContactType p_value) { m_parent = p_value; }

    @Override
    public List<CDict03ContactType> getChilds() { return m_childs; }
    public void setChilds(List<CDict03ContactType> p_value) { m_childs = p_value; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict03ContactType) m_childs.add((CDict03ContactType)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict03ContactType x_item = (CDict03ContactType)p_item;
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

    @Override
    public String toString() {
        return
                CDict03ContactType.class.getName() + " {" +
                super.toString() +
                "}";
    }
}
