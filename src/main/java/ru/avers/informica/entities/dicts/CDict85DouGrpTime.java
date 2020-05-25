package ru.avers.informica.entities.dicts;

import ru.avers.informica.entities.IWeightedDict;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

 TODO ��� ���� ������� ������� � ����� ��
/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TypeDouGrpTime")
@XmlSeeAlso({CBaseDict.class})

@Entity
@Access(AccessType.PROPERTY)
@Table(name="V_DICT_85_DOU_GRP_TIME")
@SequenceGenerator(name="SEQ_GEN")
public class CDict85DouGrpTime extends CBaseDict implements ICodedDict, IHasDescr, IWeightedDict {
    public static final String s_dict_code = "85",
                               s_full_10d5_12 = "01", // ������� ��� (10,5-12 �����)
                               s_short_8_10 = "02", // ������������ ��� (8-10 �����)                               
                               s_extended_13_14 = "03", // ����������� ���  (13-14 �����)
                               s_brief_3_5 = "04", // ���������������� ���������� (3-5 �����) 
                               s_const_24 = "05"; // ��������������� ���������� (24 ����)
    
    public CDict85DouGrpTime() { super(); }
    public CDict85DouGrpTime(Integer p_id) { super(p_id); }
    public CDict85DouGrpTime(Integer p_id, String p_name) { super(p_id, p_name); }

    private String m_code;
    @XmlAttribute(name="code")
    @Column(name="CODE")
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    private String m_descr;
    @XmlElement(name="description")
    @Column(name="DESCR")
    public String getDescription() { return m_descr; }
    public void setDescription(String p_val) { m_descr = p_val; }
    
    @Transient
    @Override
    public String getDescr() {return getDescription(); }
    
    private Integer m_weight;
    @XmlTransient
    @Column(name="WEIGHT")
    @Override
    public Integer getWeight(){ return m_weight; }
    public void setWeight(Integer p_val) { m_weight = p_val; }
    
    @Override
    public String toString() {
        return CDict85DouGrpTime.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }
}
