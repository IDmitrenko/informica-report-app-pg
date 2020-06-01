package ru.avers.informica.entities.dicts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeGrpActivity")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Table(name="V_DICT_93_GRP_ACTIVITY")
@SequenceGenerator(name="SEQ_GEN")
public class CDict93GrpActivity extends CBaseDict implements ICodedDict {
    final static public String s_dict_code = "93",
            s_code_education = "01",
            s_code_care = "02";
    

    public CDict93GrpActivity() { super(); }
    public CDict93GrpActivity(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @XmlAttribute(name="code")
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return
                CDict93GrpActivity.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) + "}";
    }    
    
}
