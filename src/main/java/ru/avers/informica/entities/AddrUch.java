package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict72UchAddrType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

TODO ��� ���� ������� ������� � ����� ��
/**
 * AddrUch generated by hbm2java
 */
@Entity
@Table(name="ADDR_UCH")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_ADDR_UCH_ID")
public class AddrUch extends CItem implements IUchRelated {

    public final static String S_UCH_FIELD = "m_uch";

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="ID_UCH")
    private Uch m_uch;

    public Uch getUch() {
        return this.m_uch;
    }

    public void setUch(Uch uch) {
        this.m_uch = uch;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ADDR_TYPE")
    private CDict72UchAddrType m_dicts_detail;

    @Column(name = "CODE_KLADR_STREET")
    private String codeKladrStreet;

    @Column(name = "CODE_OCATO")
    private String codeOcato;

    @Column(name = "INDX")
    private String indx;

    @Column(name = "NHOUSE")
    private String nhouse;

    @Column(name = "KORPUS")
    private String korpus;

    @Column(name = "ADDR_STR")
    private String m_addr_str;
/*
 *  �� ������������, ��. ����������� � ��
    @Column(name = "ID_STREET")
    private Integer m_street;
*/
    @Column(name="IS_UR_ADDR")
    private short m_isUrAddr;
    
    @Column(name = "FIAS_HOUSE_GUID")
    private String m_fias_house_guid;

    @Size(max=64)
    @Column(name = "FIAS_COORD_X")
    private String m_fiasCoordX;

    @Size(max=64)
    @Column(name = "FIAS_COORD_Y")
    private String m_fiasCoordY;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DS")
    private Date ds;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DF")
    private Date df;

    public AddrUch() {
        super();
//        this.m_street = -1;
        this.m_isUrAddr = 1;
    }

    public AddrUch(Uch p_uch, CDict72UchAddrType p_dicts_detail, String p_addr_str) {
        this(null, p_uch, p_dicts_detail, p_addr_str);
    }
	
    public AddrUch(Integer p_id, Uch p_uch, CDict72UchAddrType p_dicts_detail, String p_addr_str) {
        super();
        setId(p_id);
        setUch(p_uch);
//        this.m_street = -1;
        this.m_isUrAddr = 1;
        this.m_dicts_detail = p_dicts_detail;
        this.m_addr_str = p_addr_str;
    }
  
    public CDict72UchAddrType getUchAddrType() {
        return this.m_dicts_detail;
    }
    
    public void setUchAddrType(CDict72UchAddrType dictsDetail) {
        this.m_dicts_detail = dictsDetail;
    }
    
    public String getCodeKladrStreet() {
        return this.codeKladrStreet;
    }
    
    public void setCodeKladrStreet(String codeKladrStreet) {
        this.codeKladrStreet = codeKladrStreet;
    }
    
    public String getCodeOcato() {
        return this.codeOcato;
    }
    
    public void setCodeOcato(String codeOcato) {
        this.codeOcato = codeOcato;
    }
    
    public String getIndx() {
        return this.indx;
    }
    
    public void setIndx(String indx) {
        this.indx = indx;
    }
    
    public String getNhouse() {
        return this.nhouse;
    }
    
    public void setNhouse(String nhouse) {
        this.nhouse = nhouse;
    }
    
    public String getKorpus() {
        return this.korpus;
    }
    
    public void setKorpus(String korpus) {
        this.korpus = korpus;
    }
    
    public String getAddrStr() {
        return this.m_addr_str;
    }
    
    public void setAddrStr(String addrStr) {
        this.m_addr_str = addrStr;
    }

    public String getFiasHouseGuid() {
        return m_fias_house_guid;
    }

    public void setFiasHouseGuid(String p_fias_house_guid) {
        this.m_fias_house_guid = p_fias_house_guid;
    }

    public String getFiasCoordX() {
        return this.m_fiasCoordX;
    }

    public void setFiasCoordX(String fiasCoordX) {
        this.m_fiasCoordX = fiasCoordX;
    }

    public String getFiasCoordY() {
        return this.m_fiasCoordY;
    }

    public void setFiasCoordY(String fiasCoordY) {
        this.m_fiasCoordY = fiasCoordY;
    }

    public Date getDs() {
        return this.ds;
    }
    
    public void setDs(Date ds) {
        this.ds = ds;
    }

    public Date getDf() {
        return this.df;
    }
    
    public void setDf(Date df) {
        this.df = df;
    }

}
