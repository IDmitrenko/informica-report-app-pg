package ru.avers.informica.entities.dicts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeOrgStruct")
@XmlSeeAlso({CBaseDict.class})

// TODO нет пока аналога таблицы в новой БД
@Entity
@Table(name="V_DICT_92_ORG_STRUCT")
@SequenceGenerator(name="SEQ_GEN")
public class CDict92OrgStruct extends CBaseDict implements ICodedDict {
    final static public String s_dict_code = "92",
            s_code_org_struct_doo = "01",
            s_code_org_struct_grp_by_school = "03",
            s_code_org_struct_grp_by_other_org = "04",
            s_code_org_struct_filial = "05";
    

    public CDict92OrgStruct() { super(); }
    public CDict92OrgStruct(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @XmlAttribute(name="code")
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Override
    public String toString() {
        return
                CDict92OrgStruct.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) + "}";
    }    
    
}
