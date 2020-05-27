package ru.avers.informica.dao.interaction;

import ru.avers.informica.entities.dicts.CBaseDict;
import ru.avers.informica.entities.dicts.CBaseDictDetail;
import ru.avers.informica.entities.dicts.CDicts;
import ru.avers.informica.entities.dicts.IHasDescr;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.HashSet;

 //TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeSysInteraction")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Access(AccessType.PROPERTY)
@Table(name="V_DICT_87_SYS_INTERACTION")
@SequenceGenerator(name="SEQ_GEN")
public class CDict87SysInteraction extends CBaseDict implements IHasDescr {
    
    static final public String s_smev_interaction_name = "SMEV";
    static final public String s_informica_interaction_name = "INFORMICA";
    static final public String s_fspeo_v3_interaction_name = "FSPEO3";
    static final public String s_fspeo_v4_interaction_name = "FSPEO3";
    static final public String s_concentrator_interaction_name = "CONCENTRATOR";
    
    static final public Collection<String> s_portal_names = new HashSet<String>();
    static {
        s_portal_names.add(s_smev_interaction_name);
        s_portal_names.add(s_concentrator_interaction_name);
    }
    
    private String m_cfg_str, m_descr;
    
    public CDict87SysInteraction() { super(); }
    public CDict87SysInteraction(Integer p_id) { super(p_id); }
    public CDict87SysInteraction(Integer p_id, String p_name) { this(p_id, p_name, null); }
    public CDict87SysInteraction(Integer p_id, String p_name, String p_cfg_str) { this(p_id, p_name, p_cfg_str, null); }
    public CDict87SysInteraction(Integer p_id, String p_name, String p_cfg_str, String p_descr) {
        super(p_id, p_name); 
        m_cfg_str = p_cfg_str;
        m_descr = p_descr;
    }
    
    @Basic(fetch= FetchType.LAZY)
    @XmlAttribute(name="cfg")
    @Column(name="CFG")
    public String getCfg(){ return m_cfg_str; }
    public void setCfg(String p_val) { m_cfg_str = p_val; }

    @XmlElement(name="description")
    @Column(name="DESCR")
    public String getDescr() {return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }
    
    @Override
    public String toStrSpecJPA() {
        return CDict87SysInteraction.class.getName() + " {" +
                super.toStrSpecJPA() +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }
    
    @Override
    public String toString() {
        return CDict87SysInteraction.class.getName() + " {" +
                super.toString() +
                "; cfg=" + String.valueOf(m_cfg_str) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }
    
    @Entity
    @Access(AccessType.PROPERTY)
    @Table(name="DICTS_DETAIL")
    @SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_DICTS_DETAIL_ID")
    static public class CUpdatable extends CBaseDictDetail implements IHasDescr {
        static final public String s_dict_code = "87";
        private String m_cfg_str, m_descr;

        public CUpdatable() { super(); }
        public CUpdatable(Integer p_id) { super(p_id); }
        public CUpdatable(Integer p_id, String p_name, CDicts p_dict) { this(p_id, p_name, p_dict, null); }
        public CUpdatable(Integer p_id, String p_name, CDicts p_dict, String p_cfg_str) {
            this(p_id, p_name, p_dict, p_cfg_str, null); 
        }
        public CUpdatable(Integer p_id, String p_name, CDicts p_dict, String p_cfg_str, String p_descr) {
            super(p_id, p_name, p_dict); 
            m_cfg_str = p_cfg_str;
            m_descr = p_descr;
        }

        @Basic(fetch= FetchType.LAZY)
        @Column(name="SPARE_05")
        public String getCfg(){ return m_cfg_str; }
        public void setCfg(String p_val) { m_cfg_str = p_val; }

        @Column(name="SPARE_01")
        public String getDescr() {return m_descr; }
        public void setDescr(String p_val) { m_descr = p_val; }
        
        @Override
        public String toStrSpecJPA() {
            return CUpdatable.class.getName() + " {" +
                    super.toStrSpecJPA() +
                    "; descr=" + String.valueOf(m_descr) +
                    "}";
        }

        @Override
        public String toString() {
            return CUpdatable.class.getName() + " {" +
                    super.toString() +
                    "; cfg=" + String.valueOf(m_cfg_str) +
                    "; descr=" + String.valueOf(m_descr) +
                    "}";
        }
        
    }
}
