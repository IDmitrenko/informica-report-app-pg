package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.IWeightedDict;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

 TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="V_DICT_89_INQRY_CATEG")
@SequenceGenerator(name="SEQ_GEN")
public class CDict89InqryCateg extends CBaseDict implements ICodedDict, IHasDescr, IWeightedDict {
    
    public final static String s_no_categ = "04"; // Категория: на общих основаниях
    
    public CDict89InqryCateg() { super(); }

    public CDict89InqryCateg(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;
    @Override
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }

    @Column(name="WEIGHT")
    private Integer m_weight;
    @Override
    public Integer getWeight(){ return m_weight; }
    public void setWeight(Integer p_val) { m_weight = p_val; }
    
    @Override
    public String toString() {
        return CDict81InqryChildLgot.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }

}
