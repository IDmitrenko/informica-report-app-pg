package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict71PersonAddrType;

import javax.persistence.*;

 //TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name="ADDR_PERSON")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_ADDR_PERSON_ID")
public class CAddrPerson extends CItem {

    private String m_code_kladr;
    @Column(name = "CODE_KLADR_STREET")
    public String getCodeKLADR() { return m_code_kladr; }
    public void setCodeKLADR(String p_val) { m_code_kladr = p_val; }

    private String m_code_ocato;
    @Column(name = "CODE_OCATO")
    public String getCodeOCATO() { return m_code_ocato; }
    public void setCodeOCATO(String p_val) { m_code_ocato = p_val; }

    private String m_index;
    @Column(name = "INDX")
    public String getIndex() { return m_index; }
    public void setIndex(String p_val) { m_index = p_val; }

    private String m_num_house;
    @Column(name = "NHOUSE")
    public String getHouse() { return m_num_house; }
    public void setHouse(String p_val) { m_num_house = p_val; }

    private String m_korpus;
    @Column(name = "KORPUS")
    public String getKorpus() { return m_korpus; }
    public void setKorpus(String p_val) { m_korpus = p_val; }

    private String m_flat;
    @Column(name = "FLAT")
    public String getFlat() { return m_flat; }
    public void setFlat(String p_val) { m_flat = p_val; }

    private String m_addr_str;
    @Column(name = "ADDR_STR")
    public String getAddrStr() { return m_addr_str; }
    public void setAddrStr(String p_val) { m_addr_str = p_val; }

    private CDict71PersonAddrType m_addr_type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ADDR_TYPE")
    public CDict71PersonAddrType getAddrType() { return m_addr_type; }
    public void setAddrType(CDict71PersonAddrType p_val) { m_addr_type = p_val; }

    private CPerson m_person;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PERSON")
    public CPerson getPerson() { return m_person; }
    public void setPerson(CPerson p_val) { m_person = p_val; }

    public CAddrPerson() { super(); }

    @Override
    public String toString() {
        return CAddrPerson.class.getName() + " {" +
                super.toString() +
                "; type=" + String.valueOf(m_addr_type) +
                "; kladr=" + String.valueOf(m_code_kladr) +
                "; ocato=" + String.valueOf(m_code_ocato) +
                "; index=" + String.valueOf(m_index) +
                "; house=" + String.valueOf(m_num_house) +
                "; korpus=" + String.valueOf(m_korpus) +
                "; flat=" + String.valueOf(m_flat) +
                "; addr_str=" + String.valueOf(m_addr_str) +
                "}";
    }

}
