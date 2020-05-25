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
@Table(name="V_DICT_06_TYPE_SOBSTVEN")
//@Immutable
@SequenceGenerator(name="SEQ_GEN")
public class CDict06TypeOfSobstven extends CBaseDict implements ICodedDict {
    final static public String s_dict_code = "06";
    public final static String
            s_code_legal_municip = "01",
            s_code_legal_state = "02",
            s_code_legal_non_state = "03",
            s_code_legal_non_state_wout_license = "04",
            s_code_legal_ip="05",
            s_code_legal_ip_wout_license="06",
            s_code_legal_deps ="07",
            s_code_legal_other = "08";
    
    public CDict06TypeOfSobstven() { super(); }
    public CDict06TypeOfSobstven(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return
                CDict06TypeOfSobstven.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) + "}";
    }
    
}
