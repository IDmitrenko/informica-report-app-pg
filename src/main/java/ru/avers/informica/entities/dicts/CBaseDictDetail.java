package ru.avers.informica.entities.dicts;

import javax.persistence.*;

/**
 *
 * @author Dias
 */
@Access(AccessType.PROPERTY)
@MappedSuperclass
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class CBaseDictDetail extends CBaseDict {
    private CDicts m_dict;
    
    public CBaseDictDetail() { this((Integer)null); }
    public CBaseDictDetail(Integer p_id) { this(p_id, null); }
    public CBaseDictDetail(String p_name) { this(null, p_name); }
    public CBaseDictDetail(Integer p_id, String p_name) { this(p_id, p_name, null); }
    public CBaseDictDetail(Integer p_id, String p_name, CDicts p_dict) {
        super(p_id, p_name); 
        m_dict = p_dict;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_DICTS")
    public CDicts getDict() {return m_dict;}
    public void setDict(CDicts p_val) { m_dict = p_val;}
    
    @Override
    public String toString() {
        return CBaseDictDetail.class.getName() + " {" +
                super.toString() + 
                "; dict=" + String.valueOf(m_dict) +
                "}"; 
    }
}
