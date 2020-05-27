package ru.avers.informica.entities.dicts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="V_DICT_74_INQRY_DOC_TYPE")
@SequenceGenerator(name="SEQ_GEN")
public class CDict74InqryDocType extends CBaseDict implements ICodedDict, IHasDescr {
    public static final String s_dict_code = "74",
                               s_type_trustee_code = "03", // Документ об опекунстве
                               s_type_lgots_code = "04"; // Документ, подтверждающий льготу
    
    public CDict74InqryDocType() { super(); }
    public CDict74InqryDocType(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @Override
    public String getCode(){ return m_code; }
    @Override
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;
    @Override
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }

    @Override
    public String toString() {
        return
                CDict74InqryDocType.class.getName() + " {" +
                super.toString() +
                ", code=" + String.valueOf(m_code) +
                ", descr=" + String.valueOf(m_descr) +
                "}";
    }

}
