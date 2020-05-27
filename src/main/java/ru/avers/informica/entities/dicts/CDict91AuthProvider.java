package ru.avers.informica.entities.dicts;

import ru.avers.informica.dto.user.CExtUserId;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeAuthProvider")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Access(AccessType.PROPERTY)
@Table(name="V_DICT_91_AUTH_PROVIDER")
@SequenceGenerator(name="SEQ_GEN")
public class CDict91AuthProvider extends CBaseDict implements ICodedDict, IHasDescr {
    
    final static public String s_dict_code = "91";
    
    final static public String
            s_code_dailymotion = CExtUserId.s_code_dailymotion,
            s_code_google = CExtUserId.s_code_google,
            s_code_yandex = CExtUserId.s_code_yandex,
            s_code_esia = CExtUserId.s_code_esia;
    
    public CDict91AuthProvider() { super(); }
    public CDict91AuthProvider(Integer p_id) { super(p_id); }
    public CDict91AuthProvider(Integer p_id, String p_name) { super(p_id, p_name); }

    private String m_code;
    @XmlAttribute(name="code")
    @Column(name="CODE")
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    private String m_info;
    @XmlAttribute(name="info")
    @Column(name="INFO")
    public String getInfo(){ return m_info; }
    public void setInfo(String p_val) { m_info = p_val; }

    private String m_descr;
    @XmlElement(name="description")
    @Column(name="DESCR")
    public String getDescription() { return m_descr; }
    public void setDescription(String p_val) { m_descr = p_val; }
    
    @Transient
    @Override
    public String getDescr() {return getDescription(); }
    
    @Override
    public String toString() {
        return CDict91AuthProvider.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) +
                "; info=" + String.valueOf(m_info) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }
}
