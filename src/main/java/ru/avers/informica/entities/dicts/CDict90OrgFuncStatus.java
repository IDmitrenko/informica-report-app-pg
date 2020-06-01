package ru.avers.informica.entities.dicts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeOrgFuncStatus")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Table(name="V_DICT_90_ORG_FUNC_STATUS")
@SequenceGenerator(name="SEQ_GEN")
public class CDict90OrgFuncStatus extends CBaseDict implements ICodedDict {
    final static public String s_dict_code = "90",
            s_code_org_status_working = "01",
            s_code_org_status_repairs = "02",
            s_code_org_status_renovation = "03",
            s_code_org_status_halted = "04",
            s_code_org_status_wout_contingent = "05",
            s_code_org_status_prep_to_open = "06";
    

    public CDict90OrgFuncStatus() { super(); }
    public CDict90OrgFuncStatus(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    
    @XmlAttribute(name="code")
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return
                CDict90OrgFuncStatus.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) + "}";
    }    
    
}
