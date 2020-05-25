package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

 TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="V_DICT_05_STUPEN_OBUCH")
//@Immutable
@SequenceGenerator(name="SEQ_GEN")
public class CDict05StupenObuch extends CAbstractDictsDetailBased {
    public CDict05StupenObuch() { super(); }

    public CDict05StupenObuch(int p_id) { super(p_id); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict05StupenObuch m_parent;
    @Override
    public CDict05StupenObuch getParent() { return m_parent; }
    public void setParent(CDict05StupenObuch p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict05StupenObuch> m_childs = new ArrayList<CDict05StupenObuch>();
    @Override
    public List<CDict05StupenObuch> getChilds() { return m_childs; }
    public void setChilds(List<CDict05StupenObuch> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict05StupenObuch) m_childs.add((CDict05StupenObuch)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict05StupenObuch x_item = (CDict05StupenObuch)p_item;
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
                CDict05StupenObuch.class.getName() + " {" +
                super.toString() +
                "}";
    }
}
