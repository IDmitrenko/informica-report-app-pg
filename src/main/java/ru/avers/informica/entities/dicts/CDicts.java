package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="DICTS")
//@Immutable
@SequenceGenerator(name="SEQ_GEN")
public class CDicts extends CItem {

    public CDicts() { super(); }
    public CDicts(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    public String getCode() { return m_code; }

    @Column(name="NM")
    private String m_name;
    public String getName() { return m_name; }

    @Column(name="COUNT_LEVEL")
    private Short m_cnt_lvl;
    public Short getCntLevel() { return m_cnt_lvl; }

    @Column(name="DESCR")
    /*@Lob*/
    private String m_descr;
    public String getDescription() { return m_descr; }

    @OneToMany(mappedBy="m_dict", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    private Set<CDictsDetail> m_values = new HashSet<CDictsDetail>();
    public Set<CDictsDetail> getValues() { return m_values; }

    @Override
    public String toString() {
        return 
            CDicts.class.getName() + " {" +
            super.toString() +
            ", code = " + String.valueOf(getCode()) +
            ", name = " + String.valueOf(getName()) +
            ", cnt_lvl = " + String.valueOf(getCntLevel()) +
            ", descr = " + String.valueOf(getDescription()) +
            "}";

    }

}
