package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.ITree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="DICTS_DETAIL")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_DICTS_DETAIL_ID")
public class CDictsDetail extends CBaseDict implements ITree {

    public final static String S_ID = "m_id",
                               S_PARENT = "m_parent",
                               S_CODE = "m_dict.m_code";

    public CDictsDetail() { super(); }
    public CDictsDetail(Integer p_id) { super(p_id); }
    public CDictsDetail(String p_name) { super(p_name); }
    public CDictsDetail(Integer p_id, String p_name) { super(p_id, p_name); }

    @Column(name="SPARE_01")
    private String m_spare01;
    public String getSpare01() { return m_spare01; }
    public void setSpare01(String p_val) { m_spare01 = p_val; }

    @Column(name="SPARE_02")
    private String m_spare02;
    public String getSpare02() { return m_spare02; }
    public void setSpare02(String p_val) { m_spare02 = p_val; }

    @Column(name="SPARE_03")
    private Short m_spare03;
    public Short getSpare03() { return m_spare03; }
    public void setSpare03(Short p_val) { m_spare03 = p_val; }
    
    @Column(name="SPARE_04")
    private Integer m_spare04;
    public Integer getSpare04() { return m_spare04; }
    public void setSpare04(Integer p_val) { m_spare04 = p_val; }

//    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_DICTS")
    private CDicts m_dict;
    public CDicts getDict() {return m_dict;}
    public void setDict(CDicts p_val) { m_dict = p_val;}

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name="ID_PARENT")
    private CDictsDetail m_parent;
    @Override
    public CDictsDetail getParent() { return m_parent; }
    public void setParent(CDictsDetail p_val) { m_parent = p_val; }

    @OneToMany(mappedBy="m_parent", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @OrderBy("m_name")
    private List<CDictsDetail> m_childs = new ArrayList<CDictsDetail>();
    @Override
    public List<CDictsDetail> getChilds() { return m_childs; }
    public void setChilds(List<CDictsDetail> p_val) { m_childs = p_val; }

    @Override
    public void addChild(ITree p_item) {
        CDictsDetail x_item = (CDictsDetail)p_item;
        if(x_item != null) {
            x_item.setParent(this);
            m_childs.add(x_item);
        }
    }

    @Override
    public String toString() {
        return 
            CDictsDetail.class.getName() + " {" +
            super.toString() +
            ", spare01: " + String.valueOf(getSpare01()) +
            ", spare02: " + String.valueOf(getSpare02()) +
            ", spare03: " + String.valueOf(getSpare03()) +
            ", spare04: " + String.valueOf(getSpare04()) +
            "}";
    }
}
