package ru.avers.informica.entities.dicts;

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
@Table(name="V_DICT_88_INQRY_HEALTH_NEEDS")
@SequenceGenerator(name="SEQ_GEN")
public class CDict88InqryHealthNeeds extends CBaseDict implements ICodedDict, IHasDescr {
    
    public final static String s_dict_code = "88",
                               s_common_value_code = "01";
      
    public CDict88InqryHealthNeeds() {super();}
    public CDict88InqryHealthNeeds(Integer p_id) { super(p_id); }

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
    
    @Override
    public String toString() {
        return
                CDict88InqryHealthNeeds.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }

}
