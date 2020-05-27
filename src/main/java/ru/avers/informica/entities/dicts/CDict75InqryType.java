package ru.avers.informica.entities.dicts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

// TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeInqryType")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Table(name="V_DICT_75_INQRY_TYPE")
@SequenceGenerator(name="SEQ_GEN")
public class CDict75InqryType extends CBaseDict implements ICodedDict, IHasDescr {

    public static final String s_code_enroll = "01",  // Зачисление
                               s_code_transfer = "02", // Перевод
                               s_dict_code = "75";
    
    public CDict75InqryType() { super(); }
    public CDict75InqryType(Integer p_id) { super(p_id); }
    public CDict75InqryType(Integer p_id, String p_name) { super(p_id, p_name); }

    @Column(name="CODE")
    private String m_code;
    @XmlAttribute(name="code")
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;
    @XmlElement(name="description")
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }

    @Override
    public String toString() {
        return
                CDict75InqryType.class.getName() + " {" +
                super.toString() +
                ", code=" + String.valueOf(m_code) +
                ", descr=" + String.valueOf(m_descr) +
                "}";
    }
}
