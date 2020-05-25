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
@Table(name="V_DICT_77_INQRY_STATUS_REASON")
@SequenceGenerator(name="SEQ_GEN")
public class CDict77InqryStatusReason extends CBaseDict implements ICodedDict, IHasDescr {

    public final static String s_dict_code = "77",
                               s_auto_set_status_code = "09";
    
    public CDict77InqryStatusReason() { super(); }
    public CDict77InqryStatusReason(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }

    @Override
    public String toString() {
        return
                CDict77InqryStatusReason.class.getName() + " {" +
                super.toString() +
                "}";
    }

}
