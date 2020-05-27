package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author lavrov
 */

@Entity
@Table(name="V_DICT_70_CONTACT_KIND")
@SequenceGenerator(name="SEQ_GEN")
public class CDict70ContactKind extends CAbstractDictsDetailBased implements ICodedDict {

    public final static String s_phone_code = "01";
    public final static String s_email_code = "02";
    public final static String s_website_code = "03";
    
    public CDict70ContactKind() { super(); }
    public CDict70ContactKind(Integer p_id) { super(p_id); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict70ContactKind m_parent;
    @Override
    public CDict70ContactKind getParent() { return m_parent; }
    public void setParent(CDict70ContactKind p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict70ContactKind> m_childs = new ArrayList<CDict70ContactKind>();
    @Override
    public List<CDict70ContactKind> getChilds() { return m_childs; }
    public void setChilds(List<CDict70ContactKind> p_val) { m_childs = p_val; }

    @Column(name="CODE")
    private String m_code;
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }
    
    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict70ContactKind) m_childs.add((CDict70ContactKind)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict70ContactKind x_item = (CDict70ContactKind)p_item;
        if(x_item != null) {
            x_item.setParent(this);
            m_childs.add(x_item);
        }
    }

    @Override
    protected CDicts getDICT() {
        //  TODO
        return null;
//        return CDICTS.dictContactType70();
    }
    @Override
    protected boolean hasChilds() { return true; };

    @Override
    public String toString() {
        return
                CDict70ContactKind.class.getName() + " {" +
                super.toString() +
                "}";
    }
}
