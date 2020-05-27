package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="V_DICT_25_RODSTVO")
@SequenceGenerator(name="SEQ_GEN")
public class CDict25Rodstvo extends CAbstractDictsDetailBased implements ICodedDict {
    public static final String s_dict_code = "25",
                               s_mthr_code = "04",
                               s_fthr_code = "03",
                               s_trustee_code = "56", // опекун
                               s_other_code = "70";

    public CDict25Rodstvo(){ super(); }
    public CDict25Rodstvo(Integer p_id) { super(p_id); }

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDict25Rodstvo m_parent;

    @Override
    public CAbstractDictsDetailBased getParent() { return m_parent; }
    public void setParent(CDict25Rodstvo p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDict25Rodstvo> m_childs = new ArrayList<CDict25Rodstvo>();
    @Override
    public List<? extends CAbstractDictsDetailBased> getChilds() { return m_childs; }
    public void setChilds(List<CDict25Rodstvo> p_val) { m_childs = p_val; }

    @Override
    public void addChildOnly(ITree p_item) {
        if(p_item instanceof CDict25Rodstvo) m_childs.add((CDict25Rodstvo)p_item);
    }
    @Override
    public void addChild(ITree p_item) {
        CDict25Rodstvo x_item = (CDict25Rodstvo)p_item;
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
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return
            CDict25Rodstvo.class.getName() + " {" +
            super.toString() +
            ", code = " + String.valueOf(getCode()) +
            "}";
    }
}
