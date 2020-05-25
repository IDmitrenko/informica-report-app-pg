package ru.avers.informica.dao.entities;

import ru.avers.informica.entities.CUsers;
import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dias
 */
@Entity
@Table(name="INQRYCHLDINUCH") // applications - новая таблица [вторая реализация TODO]
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRYCHLDINUCH_ID")
public class Inqry extends CItem {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private CUsers m_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OTHER_RODSTVO")
    private CDict25Rodstvo m_other_person_rodstvo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TYPE_INQRY")
    private CDict75InqryType m_type_inqry;

    @NotNull
    @Min(value=1)
    @Column(name = "NCLS")
    private short m_ncls = 1;

    @Size(max=128)
    @Column(name = "NUM")
    private String m_num;

    @NotNull
    @Size(max=1024)
    @Column(name = "CHLD_SRNM")
    private String m_chldSrnm;

    @NotNull
    @Size(max=1024)
    @Column(name = "CHLD_FRSTNM")
    private String m_chldFrstnm;

    @NotNull
    @Size(max=1024)
    @Column(name = "CHLD_SCNDNM")
    private String m_chldScndnm;

    @NotNull
    @Temporal(value = TemporalType.DATE)
    @Column(name = "CHLD_BIRTHDT")
    private Date m_chldBirthdt;

    @Size(max=1024)
    @Column(name = "CHLD_BIRTHPLC")
    private String m_chldBirthplc;

//    @NotNull
    @Size(max=32)
    @Column(name = "CHLDCRTF_SER")
    private String m_chldcrtfSer;

//    @NotNull
    @Size(max=128)
    @Column(name = "CHLDCRTF_NUM")
    private String m_chldcrtfNum;

//    @NotNull
    @Size(max=1024)
    @Column(name = "CHLDCRTF_GAVE")
    private String m_chldcrtfGave;

//    @NotNull
    @Temporal(value = TemporalType.DATE)
    @Column(name = "CHLDCRTF_DT")
    private Date m_chldcrtfDt;

//    @NotNull
    @Size(max=256)
    @Column(name = "CHLDADDRREGSTR")
    private String m_chldaddrregstr;

    @Size(max=15)
    @Column(name = "CHLDADDRREG_CODE_KLADR_STREET")
    private String m_chldaddrregCodeKladrStreet;

    @Size(max=10)
    @Column(name = "CHLDADDREG_INDX")
    private String m_chldaddregIndx;

    @Size(max=10)
    @Column(name = "CHLDADDRREG_NHOUSE")
    private String m_chldaddrregNhouse;

    @Size(max=10)
    @Column(name = "CHLDADDRREG_KORPUS")
    private String m_chldaddrregKorpus;

    @Size(max=10)
    @Column(name = "CHLDADDRREG_FLAT")
    private String m_chldaddrregFlat;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "CHLDADDRREG_DT_FROM")
    private Date m_chldaddrreg_dt_from;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "CHLDADDRREG_DT_TO")
    private Date m_chldaddrreg_dt_to;
    
    @NotNull
    @Size(max=256)
    @Column(name = "CHLDADDRFACTSTR")
    private String m_chldaddrfactstr;

    @Size(max=15)
    @Column(name = "CHLDADDRFACT_CODE_KLADR_STREET")
    private String m_chldaddrfactCodeKladrStreet;

    @Size(max=10)
    @Column(name = "CHLDADDRFACT_INDX")
    private String m_chldaddrfactIndx;

    @Size(max=10)
    @Column(name = "CHLDADDRFACT_NHOUSE")
    private String m_chldaddrfactNhouse;

    @Size(max=10)
    @Column(name = "CHLDADDRFACT_KORPUS")
    private String m_chldaddrfactKorpus;

    @Size(max=10)
    @Column(name = "CHLDADDRFACT_FLAT")
    private String m_chldaddrfactFlat;

    @Size(max=1024)
    @Column(name = "CHLD_PREV_UCH")
    private String m_chldPrevUch;

    @Size(max=4096)
    @Column(name = "CHLD_DESCR")
    private String m_chldDescr;
    
    //  2014-06 OTHER_* рассматривается как данные заявителя
    @Size(max=1024)
    @Column(name = "OTHER_SRNM")
    private String m_otherSrnm;

    @Size(max=1024)
    @Column(name = "OTHER_FRSTNM")
    private String m_otherFrstnm;

    @Size(max=1024)
    @Column(name = "OTHER_SCNDNM")
    private String m_otherScndnm;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "OTHER_BIRTHDT")
    private Date m_otherBirthdt;

    @Size(max=32)
    @Column(name = "OTHERPSPRT_SER")
    private String m_otherpsprtSer;

    @Size(max=128)
    @Column(name = "OTHERPSPRT_NUM")
    private String m_otherpsprtNum;

    @Size(max=1024)
    @Column(name = "OTHERPSPRT_GAVE")
    private String m_otherpsprtGave;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "OTHERPSPRT_DT")
    private Date m_otherpsprtDt;

    @Size(max=1024)
    @Column(name = "OTHER_JOB")
    private String m_otherJob;

    @Size(max=1024)
    @Column(name = "OTHER_DLGN")
    private String m_otherDlgn;

    @Size(max=1024)
    @Column(name = "OTHER_PHONES")
    private String m_otherPhones;

    @Size(max=1024)
    @Column(name = "OTHER_EMAILS")
    private String m_otherEmails;

    @Size(max=4096)
    @Column(name = "OTHER_DESCR")
    private String m_otherDescr;

    @Size(max=4096)
    @Column(name = "DESCR")
    private String m_descr;
    
    @Column(name = "IS_PRIVILEGE")
    private Boolean m_is_privilege;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_CHLDTER")
    private CDict04MunicipObrazov m_chld_ter;

    @Size(max=254)
    @Column(name="NOTIFY_EMAIL")
    private String m_notify_email;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "INUCH_DT")
    private Date m_inuch_dt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true, mappedBy = "m_inqrychldinuch")
    @OrderBy("m_prty")
    private List<InqryUch> m_inqryuchs = new ArrayList<InqryUch>(0);

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="m_inqry")
    private List<InqryStatus> m_inqrystatuses = new ArrayList<InqryStatus>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_inqry", orphanRemoval=true)
    private List<CInqryDocs> m_inqrydocses = new ArrayList<CInqryDocs>(0);
       
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "m_inqry", fetch = FetchType.LAZY, orphanRemoval=true)
    private List<InqryLgot> m_inqry_lgots = new ArrayList<InqryLgot>(0);
            
    @ManyToOne
    @JoinColumn(name="ID_DOU_GRP_TIME")
    private CDict85DouGrpTime m_dou_grp_type;
    public CDict85DouGrpTime getDouGrpTime() { return m_dou_grp_type; }
    public void setDouGrpTime(CDict85DouGrpTime p_val) { m_dou_grp_type = p_val; }
    
    @ManyToOne
    @JoinColumn(name="ID_DOC_KIND_CHILD")
    private CDict73DocKindChild m_doc_kind_child;
    public CDict73DocKindChild getDocKindChild() { return m_doc_kind_child; }
    public void setDocKindChild(CDict73DocKindChild p_val) { m_doc_kind_child = p_val; }
    
    @ManyToOne
    @JoinColumn(name="ID_DOC_KIND_OTHER")
    private CDict73DocKind m_doc_kind_other;
    public CDict73DocKind getDocKindOther() { return m_doc_kind_other; }
    public void setDocKindOther(CDict73DocKind p_val) { m_doc_kind_other = p_val; }
       
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_HEALTH_NEEDS")
    private CDict08TypeClass m_health_needs;
    public CDict08TypeClass getHealthNeeds() { return m_health_needs; }
    public void setHealthNeeds(CDict08TypeClass p_health_needs) { m_health_needs = p_health_needs; }
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_INQRY_HEALTH_NEEDS")
    private CDict88InqryHealthNeeds m_inqry_health_needs;
    public CDict88InqryHealthNeeds getInqryHealthNeeds() { return m_inqry_health_needs; }
    public void setInqryHealthNeeds(CDict88InqryHealthNeeds p_inqry_health_needs) { m_inqry_health_needs = p_inqry_health_needs; }    

    @OneToOne(cascade= CascadeType.ALL, mappedBy="m_inqry", fetch= FetchType.LAZY)
    private QueueInfo m_queue_info;
    public QueueInfo getInqryQueueInfo() { return m_queue_info; }
    public void setInqryQueueInfo(QueueInfo p_val) { m_queue_info = p_val; }        

    @OneToMany(cascade= CascadeType.ALL, mappedBy="m_inqry", fetch= FetchType.LAZY)
    private List<QueueWindow> m_queue_windows = new ArrayList<QueueWindow>(0);
    public List<QueueWindow> getInqryQueueWindow() { return m_queue_windows; }
    public void setInqryQueueWindow(List<QueueWindow> p_val) { m_queue_windows = p_val; }
        
    @OneToMany(cascade= CascadeType.ALL, mappedBy="inqry", fetch= FetchType.LAZY)
    private List<CntrInteract> m_extern_sys = new ArrayList<CntrInteract>();
    public List<CntrInteract> getCntrInteract() { return m_extern_sys; }
    public void setCntrInteract(List<CntrInteract> p_val) { m_extern_sys = p_val; }
    
    @Size(max=128)
    @Column(name = "HEALTH_NEEDS_DOC_INFO")
    private String m_health_needs_doc_info;
    public String getHealthNeedsDocInfo() {
        return m_health_needs_doc_info;
    }
    public void setHealthNeedsDocInfo(String p_val) {
        m_health_needs_doc_info = p_val;
    }
    
    @Size(max=256)
    @Column(name = "BENEFITS_DOC_INFO")
    private String m_benefits_doc_info;
    public String getBenefitsDocInfo() { return m_benefits_doc_info; }
    public void setBenefitsDocInfo(String p_val) { m_benefits_doc_info = p_val; }

    @Size(max=32)
    @Column(name = "OTHERPSPRT_ISSUER_DEPARTMENT")
    private String m_others_psprt_issuer_dep;
    public String getOtherpsprtIssuerDep() {
        return m_others_psprt_issuer_dep;
    }
    public void setOtherpsprtIssuerDep(String p_val) {
        m_others_psprt_issuer_dep = p_val;
    }
    
    @Size(max=32)
    @Column(name = "OTHER_SNILS")
    private String m_others_snils;
    public String getOtherSnils() { return m_others_snils; }
    public void setOtherSnils(String p_val) { m_others_snils = p_val; }

    @Size(max=128)
    @Column(name = "OTHER_RODSTVO_NAME")
    private String m_other_rodstvo_name;
    public String getOtherRodstvoName() { return m_other_rodstvo_name; }
    public void setOtherRodstvoName(String p_val) { m_other_rodstvo_name = p_val; }
    
    @Size(max=128)
    @Column(name = "OTHER_RODSTVO_DOC_NUMBER")
    private String m_other_rodstvo_doc_number;
    public String getOtherRodstvoDocNumber() {
        return m_other_rodstvo_doc_number;
    }
    public void setOtherRodstvoDocNumber(String p_val) {
        m_other_rodstvo_doc_number = p_val;
    }    

    @Size(max=32)
    @Column(name = "CHLDCRTF_ACT_NUMBER")
    private String m_chldcrtf_act_number;
    public String getChldcrtfActNumber() {
        return m_chldcrtf_act_number;
    }
    public void setChldcrtfActNumber(String p_val) {
        m_chldcrtf_act_number = p_val;
    }    

    @Size(max=256)
    @Column(name = "CHLDCRTF_FRGN_NAME")
    private String m_chldcrtf_frgn_name;
    public String getChldcrtFrgnName() {
        return m_chldcrtf_frgn_name;
    }
    public void setChldcrtFrgnName(String p_val) {
        m_chldcrtf_frgn_name = p_val;
    }    
    
    @Size(max=128)
    @Column(name = "CHLDCRTF_FRGN_NUM")
    private String m_chldcrtf_frgn_num;
    public String getChldcrtfFrgnNum() {
        return m_chldcrtf_frgn_num;
    }
    public void setChldcrtfFrgnNum(String p_val) {
        m_chldcrtf_frgn_num = p_val;
    }                            
    
    @Size(max=32)
    @Column(name = "CHLD_SNILS")
    private String m_chld_snils;
    public String getChldSnils() {
        return m_chld_snils;
    }
    public void setChldSnils(String p_val) {
        m_chld_snils = p_val;
    }      
    
    public static enum TSex { FEMALE, MALE };
    
    @Column(name = "SEX")
    private Boolean m_sex;
    public TSex getSex() { return m_sex == null ? null : (m_sex ? TSex.MALE : TSex.FEMALE); }
    public void setSex(TSex p_val) { m_sex = (p_val == null ? null : TSex.MALE.equals(p_val)); }
    
    @Size(max=512)
    @Column(name = "PRIVILEGE_OTHER")
    private String m_privilege_other;
    public String getPrivilegeOther() {
        return m_privilege_other;
    }
    public void setPrivilegeOther(String p_val) {
        m_privilege_other = p_val;
    }    
    
    @Column(name="ALLOW_OFFER_OTHER_UCHS")
    private Boolean m_allow_offer_other_uchs;
    public Boolean isAllowOfferOtherUchs() { return (m_allow_offer_other_uchs != null) ? m_allow_offer_other_uchs : false; }
    public void setAllowOfferOtherUchs(Boolean p_val) { m_allow_offer_other_uchs = p_val; }
    
    @Column(name = "DOC_REFS")
    private String m_doc_refs;
    public String getDocRefs() { return m_doc_refs; }
    public void setDocRefs(String p_doc_refs) { m_doc_refs = p_doc_refs; }
   
    @ManyToMany(fetch= FetchType.LAZY, targetEntity=CDict85DouGrpTime.class)
    @JoinTable(name="INQRYDOUGRPTIME", joinColumns=@JoinColumn(name="ID_INQRY"),
        inverseJoinColumns=@JoinColumn(name="ID_DOU_GRP_TIME"))
    private List<CDict85DouGrpTime> m_dou_grp_times = new ArrayList<CDict85DouGrpTime>(0);
    public List<CDict85DouGrpTime> getDouGrpTimes() {
        if(m_dou_grp_times == null) m_dou_grp_times = new ArrayList<CDict85DouGrpTime>(0);
        return m_dou_grp_times; 
    }
    public void setDouGrpTimes(List<CDict85DouGrpTime> p_val) { m_dou_grp_times = p_val; }

    @Size(max=256)
    @Column(name = "FIAS_ADDR_STR")
    private String m_addrFiasStr;

    @Size(max=128)
    @Column(name = "FIAS_CODE")
    private String m_fiasCodeStr;

    @Column(name="IS_NATIVE_FIAS")
    private Boolean m_nativeFias;

    @Size(max=64)
    @Column(name = "FIAS_COORD_X")
    private String m_fiasCoordX;

    @Size(max=64)
    @Column(name = "FIAS_COORD_Y")
    private String m_fiasCoordY;

    public Inqry() { super(); }
    public Inqry(int p_id) { super(p_id); }

    public String getAddrFiasStr() {
        return this.m_addrFiasStr;
    }

    public void setAddrFiasStr(String addrFiasStr) {
        this.m_addrFiasStr = addrFiasStr;
    }

    public String getFiasCodeStr() {
        return this.m_fiasCodeStr;
    }

    public void setFiasCodeStr(String fiasCodeStr) {
        this.m_fiasCodeStr = fiasCodeStr;
    }

    public Boolean isNativeFias() { return m_nativeFias; }
    public void setNativeFias(Boolean p_val) { m_nativeFias = p_val; }

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

    public CUsers getUsers() {
        return this.m_user;
    }
    
    public void setUsers(CUsers p_users) {
        this.m_user = p_users;
    }

    public CDict25Rodstvo getOtherPersonRodstvo() {
        return this.m_other_person_rodstvo;
    }
    
    public void setOtherPersonRodstvo(CDict25Rodstvo p_value) {
        this.m_other_person_rodstvo = p_value;
    }

    public CDict75InqryType getTypeInqry() {
        return this.m_type_inqry;
    }
    
    public void setTypeInqry(CDict75InqryType p_value) {
        this.m_type_inqry = p_value;
    }
    
    public short getNcls() {
        return this.m_ncls;
    }
    
    public void setNcls(short ncls) {
        this.m_ncls = ncls;
    }
    
    public String getNum() {
        return this.m_num;
    }
    
    public void setNum(String num) {
        this.m_num = num;
    }

    public String getChldSrnm() {
        return this.m_chldSrnm;
    }
    
    public void setChldSrnm(String chldSrnm) {
        this.m_chldSrnm = chldSrnm;
    }
    
    public String getChldFrstnm() {
        return this.m_chldFrstnm;
    }
    
    public void setChldFrstnm(String chldFrstnm) {
        this.m_chldFrstnm = chldFrstnm;
    }
    
    public String getChldScndnm() {
        return this.m_chldScndnm;
    }
    
    public void setChldScndnm(String chldScndnm) {
        this.m_chldScndnm = chldScndnm;
    }

    public Date getChldBirthdt() {
        return this.m_chldBirthdt;
    }
    
    public void setChldBirthdt(Date chldBirthdt) {
        this.m_chldBirthdt = chldBirthdt;
    }
    
    public String getChldBirthplc() {
        return this.m_chldBirthplc;
    }
    
    public void setChldBirthplc(String chldBirthplc) {
        this.m_chldBirthplc = chldBirthplc;
    }
    
    public String getChldcrtfSer() {
        return this.m_chldcrtfSer;
    }
    
    public void setChldcrtfSer(String chldcrtfSer) {
        this.m_chldcrtfSer = chldcrtfSer;
    }
    
    public String getChldcrtfNum() {
        return this.m_chldcrtfNum;
    }
    
    public void setChldcrtfNum(String chldcrtfNum) {
        this.m_chldcrtfNum = chldcrtfNum;
    }
    
    public String getChldcrtfGave() {
        return this.m_chldcrtfGave;
    }
    
    public void setChldcrtfGave(String chldcrtfGave) {
        this.m_chldcrtfGave = chldcrtfGave;
    }

    public Date getChldcrtfDt() {
        return this.m_chldcrtfDt;
    }
    
    public void setChldcrtfDt(Date chldcrtfDt) {
        this.m_chldcrtfDt = chldcrtfDt;
    }
    
    public String getChldaddrregstr() {
        return this.m_chldaddrregstr;
    }
    
    public void setChldaddrregstr(String chldaddrregstr) {
        this.m_chldaddrregstr = chldaddrregstr;
    }
    
    public String getChldaddrregCodeKladrStreet() {
        return this.m_chldaddrregCodeKladrStreet;
    }
    
    public void setChldaddrregCodeKladrStreet(String chldaddrregCodeKladrStreet) {
        this.m_chldaddrregCodeKladrStreet = chldaddrregCodeKladrStreet;
    }
    
    public String getChldaddrregIndx() {
        return this.m_chldaddregIndx;
    }
    
    public void setChldaddrregIndx(String chldaddregIndx) {
        this.m_chldaddregIndx = chldaddregIndx;
    }
    
    public String getChldaddrregNhouse() {
        return this.m_chldaddrregNhouse;
    }
    
    public void setChldaddrregNhouse(String chldaddrregNhouse) {
        this.m_chldaddrregNhouse = chldaddrregNhouse;
    }
    
    public String getChldaddrregKorpus() {
        return this.m_chldaddrregKorpus;
    }
    
    public void setChldaddrregKorpus(String chldaddrregKorpus) {
        this.m_chldaddrregKorpus = chldaddrregKorpus;
    }
    
    public String getChldaddrregFlat() {
        return this.m_chldaddrregFlat;
    }
    
    public void setChldaddrregFlat(String chldaddrregFlat) {
        this.m_chldaddrregFlat = chldaddrregFlat;
    }
    
    public Date getChldaddrregDtFrom() {
        return this.m_chldaddrreg_dt_from;
    }
    
    public void setChldaddrregDtFrom(Date p_date) {
        this.m_chldaddrreg_dt_from = p_date;
    }    
    
    public Date getChldaddrregDtTo() {
        return this.m_chldaddrreg_dt_to;
    }

    public void setChldaddrregDtTo(Date p_date) {
        this.m_chldaddrreg_dt_to = p_date;
    }    
    
    public String getChldaddrfactstr() {
        return this.m_chldaddrfactstr;
    }
    
    public void setChldaddrfactstr(String chldaddrfactstr) {
        this.m_chldaddrfactstr = chldaddrfactstr;
    }
    
    public String getChldaddrfactCodeKladrStreet() {
        return this.m_chldaddrfactCodeKladrStreet;
    }
    
    public void setChldaddrfactCodeKladrStreet(String chldaddrfactCodeKladrStreet) {
        this.m_chldaddrfactCodeKladrStreet = chldaddrfactCodeKladrStreet;
    }
    
    public String getChldaddrfactIndx() {
        return this.m_chldaddrfactIndx;
    }
    
    public void setChldaddrfactIndx(String chldaddrfactIndx) {
        this.m_chldaddrfactIndx = chldaddrfactIndx;
    }
    
    public String getChldaddrfactNhouse() {
        return this.m_chldaddrfactNhouse;
    }
    
    public void setChldaddrfactNhouse(String chldaddrfactNhouse) {
        this.m_chldaddrfactNhouse = chldaddrfactNhouse;
    }
    
    public String getChldaddrfactKorpus() {
        return this.m_chldaddrfactKorpus;
    }
    
    public void setChldaddrfactKorpus(String chldaddrfactKorpus) {
        this.m_chldaddrfactKorpus = chldaddrfactKorpus;
    }
    
    public String getChldaddrfactFlat() {
        return this.m_chldaddrfactFlat;
    }
    
    public void setChldaddrfactFlat(String chldaddrfactFlat) {
        this.m_chldaddrfactFlat = chldaddrfactFlat;
    }
    
    public String getChldPrevUch() {
        return this.m_chldPrevUch;
    }
    
    public void setChldPrevUch(String chldPrevUch) {
        this.m_chldPrevUch = chldPrevUch;
    }
    
    public String getChldDescr() {
        return this.m_chldDescr;
    }
    
    public void setChldDescr(String chldDescr) {
        this.m_chldDescr = chldDescr;
    }
    
    public String getOtherSrnm() {
        return this.m_otherSrnm;
    }
    
    public void setOtherSrnm(String otherSrnm) {
        this.m_otherSrnm = otherSrnm;
    }
    
    public String getOtherFrstnm() {
        return this.m_otherFrstnm;
    }
    
    public void setOtherFrstnm(String otherFrstnm) {
        this.m_otherFrstnm = otherFrstnm;
    }
    
    public String getOtherScndnm() {
        return this.m_otherScndnm;
    }
    
    public void setOtherScndnm(String otherScndnm) {
        this.m_otherScndnm = otherScndnm;
    }
    public Date getOtherBirthdt() {
        return this.m_otherBirthdt;
    }
    
    public void setOtherBirthdt(Date otherBirthdt) {
        this.m_otherBirthdt = otherBirthdt;
    }
    
    public String getOtherpsprtSer() {
        return this.m_otherpsprtSer;
    }
    
    public void setOtherpsprtSer(String otherpsprtSer) {
        this.m_otherpsprtSer = otherpsprtSer;
    }
    
    public String getOtherpsprtNum() {
        return this.m_otherpsprtNum;
    }
    
    public void setOtherpsprtNum(String otherpsprtNum) {
        this.m_otherpsprtNum = otherpsprtNum;
    }
    
    public String getOtherpsprtGave() {
        return this.m_otherpsprtGave;
    }
    
    public void setOtherpsprtGave(String otherpsprtGave) {
        this.m_otherpsprtGave = otherpsprtGave;
    }
    public Date getOtherpsprtDt() {
        return this.m_otherpsprtDt;
    }
    
    public void setOtherpsprtDt(Date otherpsprtDt) {
        this.m_otherpsprtDt = otherpsprtDt;
    }
    
    public String getOtherJob() {
        return this.m_otherJob;
    }
    
    public void setOtherJob(String otherJob) {
        this.m_otherJob = otherJob;
    }
    
    public String getOtherDlgn() {
        return this.m_otherDlgn;
    }
    
    public void setOtherDlgn(String otherDlgn) {
        this.m_otherDlgn = otherDlgn;
    }
    
    public String getOtherPhones() {
        return this.m_otherPhones;
    }
    
    public void setOtherPhones(String otherPhones) {
        this.m_otherPhones = otherPhones;
    }
    
    public String getOtherEmails() {
        return this.m_otherEmails;
    }
    
    public void setOtherEmails(String otherEmails) {
        this.m_otherEmails = otherEmails;
    }
    
    public String getOtherDescr() {
        return this.m_otherDescr;
    }
    
    public void setOtherDescr(String otherDescr) {
        this.m_otherDescr = otherDescr;
    }
    
    public String getDescr() {
        return this.m_descr;
    }
    
    public void setDescr(String descr) {
        this.m_descr = descr;
    }

    public CDict04MunicipObrazov getChldTer() {
        return m_chld_ter;
    }

    public void setChldTer(CDict04MunicipObrazov p_value)
    {
        m_chld_ter = p_value;
    }

    public String getNotifyEmail() {
        return m_notify_email;
    }

    public void setNotifyEmail(String p_notify_email) {
        m_notify_email = p_notify_email;
    }

    public List<InqryUch> getInqryuchs() {
        return m_inqryuchs;
    }

    public void setInqryuchs(List<InqryUch> p_inqryuchs) {
        m_inqryuchs = p_inqryuchs;
    }

    public List<InqryStatus> getInqrystatuses() {
        return m_inqrystatuses;
    }

    public void setInqrystatuses(List<InqryStatus> p_inqrystatuses) {
        m_inqrystatuses = p_inqrystatuses;
    }

    public List<CInqryDocs> getInqryDocses() { return m_inqrydocses; }
    public void setInqryDocses(List<CInqryDocs> p_inqrydocses) { m_inqrydocses = p_inqrydocses; }

    public List<InqryLgot> getInqrylgots() { return m_inqry_lgots; }
    public void setInqrylgots(List<InqryLgot> p_inqry_lgots) { m_inqry_lgots = p_inqry_lgots; }

    public Date getInUchDt() { return m_inuch_dt; }
    public void setInUchDt(Date p_inuch_dt) { m_inuch_dt = p_inuch_dt; }

    /**
     * Указал ли заявитель при подаче заявления наличие льготы 
     * (в зависимости от настроек может не заполняться, тогда указываются сразу конкретные льготы setLgots)
     * @return 
     */
    public Boolean getInquirerPrivilege() { return m_is_privilege; }
    public void setInquirerPrivilege(Boolean p_val) { m_is_privilege = p_val; }
    
} 
