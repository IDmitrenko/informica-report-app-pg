package ru.avers.informica.entities;

import ru.avers.informica.dao.IMapFields;
import ru.avers.informica.dao.entities.CntrInteract;
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
 * @author Dias
 */
@Entity
@Table(name="INQRYCHLDINUCH")  // applications - новая таблица
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_INQRYCHLDINUCH_ID")
public class Inqrychldinuch extends CItem {

//    @Override
//    public String toString() {
//        return "Inqrychldinuch [" + "getChild " + getChild() + " " + "getChldBirthdt " + getChldBirthdt() + " " + "getChldBirthplc " + getChldBirthplc() + " " + "getChldDescr " + getChldDescr() + " " + "getChldFrstnm " + getChldFrstnm() + " " + "getChldPrevUch " + getChldPrevUch() + " " + "getChldScndnm " + getChldScndnm() + " " + "getChldSrnm " + getChldSrnm() + " " + "getChldTer " + getChldTer() + " " + "getChldaddrfactCodeKladrStreet " + getChldaddrfactCodeKladrStreet() + " " + "getChldaddrfactFlat " + getChldaddrfactFlat() + " " + "getChldaddrfactIndx " + getChldaddrfactIndx() + " " + "getChldaddrfactKorpus " + getChldaddrfactKorpus() + " " + "getChldaddrfactNhouse " + getChldaddrfactNhouse() + " " + "getChldaddrfactstr " + getChldaddrfactstr() + " " + "getChldaddrregCodeKladrStreet " + getChldaddrregCodeKladrStreet() + " " + "getChldaddrregFlat " + getChldaddrregFlat() + " " + "getChldaddrregIndx " + getChldaddrregIndx() + " " + "getChldaddrregKorpus " + getChldaddrregKorpus() + " " + "getChldaddrregNhouse " + getChldaddrregNhouse() + " " + "getChldaddrregstr " + getChldaddrregstr() + " " + "getChldcrtfDt " + getChldcrtfDt() + " " + "getChldcrtfGave " + getChldcrtfGave() + " " + "getChldcrtfNum " + getChldcrtfNum() + " " + "getChldcrtfSer " + getChldcrtfSer() + " " + "getDescr " + getDescr() + " " + "getDocKindChild " + getDocKindChild() + " " + "getDocKindFather " + getDocKindFather() + " " + "getDocKindMother " + getDocKindMother() + " " + "getDocKindOther " + getDocKindOther() + " " + "getDouGrpTime " + getDouGrpTime() + " " + "getFthrBirthdt " + getFthrBirthdt() + " " + "getFthrDlgn " + getFthrDlgn() + " " + "getFthrEmails " + getFthrEmails() + " " + "getFthrFrstnm " + getFthrFrstnm() + " " + "getFthrJob " + getFthrJob() + " " + "getFthrPhones " + getFthrPhones() + " " + "getFthrScndnm " + getFthrScndnm() + " " + "getFthrSrnm " + getFthrSrnm() + " " + "getFthrpsprtDt " + getFthrpsprtDt() + " " + "getFthrpsprtGave " + getFthrpsprtGave() + " " + "getFthrpsprtNum " + getFthrpsprtNum() + " " + "getFthrpsprtSer " + getFthrpsprtSer() + " " + "getInUchDt " + getInUchDt() + " " + "getInqryDocses " + getInqryDocses() + " " + "getInqrystatuses " + getInqrystatuses() + " " + "getInqryuchs " + getInqryuchs() + " " + "getLgots " + getLgots() + " " + "getMthrBirthdt " + getMthrBirthdt() + " " + "getMthrDlgn " + getMthrDlgn() + " " + "getMthrEmails " + getMthrEmails() + " " + "getMthrFrstnm " + getMthrFrstnm() + " " + "getMthrJob " + getMthrJob() + " " + "getMthrPhones " + getMthrPhones() + " " + "getMthrScndnm " + getMthrScndnm() + " " + "getMthrSrnm " + getMthrSrnm() + " " + "getMthrpsprtDt " + getMthrpsprtDt() + " " + "getMthrpsprtGave " + getMthrpsprtGave() + " " + "getMthrpsprtNum " + getMthrpsprtNum() + " " + "getMthrpsprtSer " + getMthrpsprtSer() + " " + "getNcls " + getNcls() + " " + "getNotifyEmail " + getNotifyEmail() + " " + "getNum " + getNum() + " " + "getOtherBirthdt " + getOtherBirthdt() + " " + "getOtherDescr " + getOtherDescr() + " " + "getOtherDlgn " + getOtherDlgn() + " " + "getOtherEmails " + getOtherEmails() + " " + "getOtherFrstnm " + getOtherFrstnm() + " " + "getOtherJob " + getOtherJob() + " " + "getOtherPerson " + getOtherPerson() + " " + "getOtherPersonRodstvo " + getOtherPersonRodstvo() + " " + "getOtherPhones " + getOtherPhones() + " " + "getOtherScndnm " + getOtherScndnm() + " " + "getOtherSrnm " + getOtherSrnm() + " " + "getOtherpsprtDt " + getOtherpsprtDt() + " " + "getOtherpsprtGave " + getOtherpsprtGave() + " " + "getOtherpsprtNum " + getOtherpsprtNum() + " " + "getOtherpsprtSer " + getOtherpsprtSer() + " " + "getTrustedDescr " + getTrustedDescr() + " " + "getTrustedDoc " + getTrustedDoc() + " " + "getTrustedEmails " + getTrustedEmails() + " " + "getTrustedFrstnm " + getTrustedFrstnm() + " " + "getTrustedPerson " + getTrustedPerson() + " " + "getTrustedPhones " + getTrustedPhones() + " " + "getTrustedScndnm " + getTrustedScndnm() + " " + "getTrustedSrnm " + getTrustedSrnm() + " " + "getTypeInqry " + getTypeInqry() + " " + "getUsers " + getUsers() + "]";
//    }

    public final static String
        S_ID = "m_id",
        S_ID_USER = "m_user.m_id",
        S_NCLS = "m_ncls",
        S_NUM = "m_num",
        S_INQRY_TYPE = "m_type_inqry",
        S_INQRY_TYPE_ID = "m_type_inqry.m_id",
        S_CHLD_SRNM = "m_chldSrnm",
        S_CHLD_FRSTNM = "m_chldFrstnm",
        S_CHLD_SCNDNM = "m_chldScndnm",
        S_CHLD_BIRTHDT = "m_chldBirthdt",
        S_CHLD_BIRTHPLC = "m_chldBirthplc",
        S_CHLDCRTF_SER = "m_chldcrtfSer",
        S_CHLDCRTF_NUM = "m_chldcrtfNum",
        S_CHLDCRTF_GAVE = "m_chldcrtfGave",
        S_CHLDCRTF_DT = "m_chldcrtfDt",
        S_CHLDADDRREGSTR = "m_chldaddrregstr",
        S_CHLDADDRREG_CODE_KLADR_STREET = "m_chldaddrregCodeKladrStreet",
        S_CHLDADDREG_INDX = "m_chldaddregIndx",
        S_CHLDADDRREG_NHOUSE = "m_chldaddrregNhouse",
        S_CHLDADDRREG_KORPUS = "m_chldaddrregKorpus",
        S_CHLDADDRREG_FLAT = "m_chldaddrregFlat",
        S_CHLDADDRFACTSTR = "m_chldaddrfactstr",
        S_CHLDADDRFACT_CODE_KLADR_STREET = "m_chldaddrfactCodeKladrStreet",
        S_CHLDADDRFACT_INDX = "m_chldaddrfactIndx",
        S_CHLDADDRFACT_NHOUSE = "m_chldaddrfactNhouse",
        S_CHLDADDRFACT_KORPUS = "m_chldaddrfactKorpus",
        S_CHLDADDRFACT_FLAT = "m_chldaddrfactFlat",
        S_ADDR_FIAS = "m_addrFiasStr",
        S_FIAS_CODE = "m_fiasCodeStr",
        S_IS_NATIVE_FIAS = "m_nativeFias",
        S_CHLD_PREV_UCH = "m_chldPrevUch",
        S_CHLD_DESCR = "m_chldDescr",
        S_CHLD_TER_ID = "m_chld_ter.m_id",
        S_CHLD_TER_NAME = "m_chld_ter.m_name",
        S_MTHR_SRNM = "m_mthrSrnm",
        S_MTHR_FRSTNM = "m_mthrFrstnm",
        S_MTHR_SCNDNM = "m_mthrScndnm",
        S_MTHR_BIRTHDT = "m_mthrBirthdt",
        S_MTHRPSPRT_SER = "m_mthrpsprtSer",
        S_MTHRPSPRT_NUM = "m_mthrpsprtNum",
        S_MTHRPSPRT_GAVE = "m_mthrpsprtGave",
        S_MTHRPSPRT_DT = "m_mthrpsprtDt",
        S_MTHR_JOB = "m_mthrJob",
        S_MTHR_DLGN = "m_mthrDlgn",
        S_MTHR_PHONES = "m_mthrPhones",
        S_MTHR_EMAILS = "m_mthrEmails",
        S_FTHR_SRNM = "m_fthrSrnm",
        S_FTHR_FRSTNM = "m_fthrFrstnm",
        S_FTHR_SCNDNM = "m_fthrScndnm",
        S_FTHR_BIRTHDT = "m_fthrBirthdt",
        S_FTHRPSPRT_SER = "m_fthrpsprtSer",
        S_FTHRPSPRT_NUM = "m_fthrpsprtNum",
        S_FTHRPSPRT_GAVE = "m_fthrpsprtGave",
        S_FTHRPSPRT_DT = "m_fthrpsprtDt",
        S_FTHR_JOB = "m_fthrJob",
        S_FTHR_DLGN = "m_fthrDlgn",
        S_FTHR_PHONES = "m_fthrPhones",
        S_FTHR_EMAILS = "m_fthrEmails",
        S_OTHER_SRNM = "m_otherSrnm",
        S_OTHER_FRSTNM = "m_otherFrstnm",
        S_OTHER_SCNDNM = "m_otherScndnm",
        S_OTHER_BIRTHDT = "m_otherBirthdt",
        S_OTHERPSPRT_SER = "m_otherpsprtSer",
        S_OTHERPSPRT_NUM = "m_otherpsprtNum",
        S_OTHERPSPRT_GAVE = "m_otherpsprtGave",
        S_OTHERPSPRT_DT = "m_otherpsprtDt",
        S_OTHER_JOB = "m_otherJob",
        S_OTHER_DLGN = "m_otherDlgn",
        S_OTHER_PHONES = "m_otherPhones",
        S_OTHER_EMAILS = "m_otherEmails",
        S_OTHER_DESCR = "m_otherDescr",
        S_TRUSTED_SRNM = "m_trustedSrnm",
        S_TRUSTED_FRSTNM = "m_trustedFrstnm",
        S_TRUSTED_SCNDNM = "m_trustedScndnm",
        S_TRUSTED_PHONES = "m_trustedPhones",
        S_TRUSTED_EMAILS = "m_trustedEmails",
        S_TRUSTED_DOC = "m_trustedDoc",
        S_TRUSTED_DESCR = "m_trustedDescr",
        S_DESCR = "m_descr",
        S_INQRY_STATUS_ID = "m_inqrystatuses.m_inqry_status.m_id",
        S_INQRY_STATUS_NAME = "m_inqrystatuses.m_inqry_status.m_name",
        S_INQRY_STATUS_CODE = "m_inqrystatuses.m_inqry_status.m_code",
        S_INQRY_STATUS_SET_DATE = "m_inqrystatuses.m_dt",
        S_NOTIFY_EMAIL = "m_notify_email",
        S_INUCH_DT = "m_inuch_dt",
        S_LGOTS_NAME = "m_inqry_lgots.m_lgot.m_name",
        S_LGOTS_ID = "m_inqry_lgots.m_lgot.m_id",
        S_LGOTS_DESCR = "m_inqry_lgots.m_lgot.m_descr",
        S_INQRYUCHS = "m_inqryuchs",
        S_INQRYUCHS_ID = "m_inqryuchs.m_id",
        S_INQRYUCHS_TER_ID = "m_inqryuchs" + IMapFields.s_field_delimiter + Inqryuch.S_UCH_TER_ID,
        S_HEALTH_NEEDS = "m_health_needs",
        S_HEALTH_NEEDS_ID = "m_health_needs.m_id",
        S_INQRY_HEALTH_NEEDS_ID = "m_inqry_health_needs.m_id",
        S_INQUIRER_SET_PRIVILEGE = "m_is_privilege",
        S_LGOTS_DOC_INFO = "m_lgots_doc_info",
        S_HEALTH_NEEDS_DOC_INFO = "m_health_needs_doc_info",
        S_GROUP_TIMES = "m_dou_grp_times",            
        S_SELECTED_CATEG = "m_categ",
        S_PMPK_CONCLUSION = "m_pmpk_conclusion";
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON_OTHER")
    private CPerson m_other_person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private CUsers m_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON_TRUSTED")
    private CPerson m_trusted_person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSON_CHLD")
    private CPerson m_child;

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

    @Size(max=256)
    @Column(name = "FIAS_ADDR_STR")
    private String m_addrFiasStr;

    @Size(max=128)
    @Column(name = "FIAS_CODE")
    private String m_fiasCodeStr;

    @Size(max=64)
    @Column(name = "FIAS_COORD_X")
    private String m_fiasCoordX;

    @Size(max=64)
    @Column(name = "FIAS_COORD_Y")
    private String m_fiasCoordY;

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

    @Size(max=1024)
    @Column(name = "MTHR_SRNM")
    private String m_mthrSrnm;

    @Size(max=1024)
    @Column(name = "MTHR_FRSTNM")
    private String m_mthrFrstnm;

    @Size(max=1024)
    @Column(name = "MTHR_SCNDNM")
    private String m_mthrScndnm;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "MTHR_BIRTHDT")
    private Date m_mthrBirthdt;

    @Size(max=32)
    @Column(name = "MTHRPSPRT_SER")
    private String m_mthrpsprtSer;

    @Size(max=128)
    @Column(name = "MTHRPSPRT_NUM")
    private String m_mthrpsprtNum;

    @Size(max=1024)
    @Column(name = "MTHRPSPRT_GAVE")
    private String m_mthrpsprtGave;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "MTHRPSPRT_DT")
    private Date m_mthrpsprtDt;

    @Size(max=1024)
    @Column(name = "MTHR_JOB")
    private String m_mthrJob;

    @Size(max=1024)
    @Column(name = "MTHR_DLGN")
    private String m_mthrDlgn;

    @Size(max=1024)
    @Column(name = "MTHR_PHONES")
    private String m_mthrPhones;

    @Size(max=1024)
    @Column(name = "MTHR_EMAILS")
    private String m_mthrEmails;

    @Size(max=1024)
    @Column(name = "FTHR_SRNM")
    private String m_fthrSrnm;

    @Size(max=1024)
    @Column(name = "FTHR_FRSTNM")
    private String m_fthrFrstnm;

    @Size(max=1024)
    @Column(name = "FTHR_SCNDNM")
    private String m_fthrScndnm;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "FTHR_BIRTHDT")
    private Date m_fthrBirthdt;

    @Size(max=32)
    @Column(name = "FTHRPSPRT_SER")
    private String m_fthrpsprtSer;

    @Size(max=128)
    @Column(name = "FTHRPSPRT_NUM")
    private String m_fthrpsprtNum;

    @Size(max=1024)
    @Column(name = "FTHRPSPRT_GAVE")
    private String m_fthrpsprtGave;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "FTHRPSPRT_DT")
    private Date m_fthrpsprtDt;

    @Size(max=1024)
    @Column(name = "FTHR_JOB")
    private String m_fthrJob;

    @Size(max=1024)
    @Column(name = "FTHR_DLGN")
    private String m_fthrDlgn;

    @Size(max=1024)
    @Column(name = "FTHR_PHONES")
    private String m_fthrPhones;

    @Size(max=1024)
    @Column(name = "FTHR_EMAILS")
    private String m_fthrEmails;
    
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

    @Size(max=1024)
    @Column(name = "TRUSTED_SRNM")
    private String m_trustedSrnm;

    @Size(max=1024)
    @Column(name = "TRUSTED_FRSTNM")
    private String m_trustedFrstnm;

    @Size(max=1024)
    @Column(name = "TRUSTED_SCNDNM")
    private String m_trustedScndnm;

    @Size(max=1024)
    @Column(name = "TRUSTED_PHONES")
    private String m_trustedPhones;

    @Size(max=1024)
    @Column(name = "TRUSTED_EMAILS")
    private String m_trustedEmails;

    @Size(max=4096)
    @Column(name = "TRUSTED_DOC")
    private String m_trustedDoc;

    @Size(max=4096)
    @Column(name = "TRUSTED_DESCR")
    private String m_trustedDescr;

    @Size(max=4096)
    @Column(name = "DESCR")
    private String m_descr;
    
    @Column(name = "IS_PRIVILEGE")
    private Boolean m_is_privilege;
    
    
    @Column(name = "IS_AGREE_DEL_QUEUE")
//    @Transient
    private Boolean m_is_agree_del_queue;
    public Boolean getAgreeDelQueue() { return m_is_agree_del_queue; }
    public void setAgreeDelQueue(Boolean p_val) { m_is_agree_del_queue = p_val; }

    
    @Column(name = "IS_NEED_FIN_CERT")
//    @Transient
    private Boolean m_is_need_fin_cert;
    public Boolean getNeedFinCert() { return m_is_need_fin_cert; }
    public void setNeedFinCert(Boolean p_val) { m_is_need_fin_cert = p_val; }
    
    @Column(name = "IS_AGREE_ADAPT_PRG")
//    @Transient
    private Boolean m_is_agree_adapt_program;
    public Boolean getAgreeAdaptProgram() { return m_is_agree_adapt_program; }
    public void setAgreeAdaptProgram(Boolean p_val) { m_is_agree_adapt_program = p_val; }

    @Column(name = "DECLARANT_ADDR_STR")
//    @Transient
    private String m_declarant_address_str;
    public String getDeclarantAddressStr() { return m_declarant_address_str; }
    public void setDeclarantAddressStr(String p_val) { m_declarant_address_str = p_val; }
    
    
//    @Lob
    @Column(name = "DESCRBLOB")
//    private String m_descrblob;
//    public String getDescrBlob() { return m_descrblob; }
//    public void setDescrBlob(String p_val) { m_descrblob = p_val; }
    private byte[] m_descrblob;
    public byte[] getDescrBlob() { return m_descrblob; }
    public void setDescrBlob(byte[] p_val) { m_descrblob = p_val; }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_CHLDTER")
    private CDict04MunicipObrazov m_chld_ter;

    @Size(max=254)
    @Column(name="NOTIFY_EMAIL")
    private String m_notify_email;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "INUCH_DT")
    private Date m_inuch_dt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRANSFER_UCH")
    private Uch m_transfer_uch;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true, mappedBy = "m_inqrychldinuch")
    @OrderBy("m_prty")
    private List<Inqryuch> m_inqryuchs = new ArrayList<Inqryuch>(0);

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="m_inqry")
    private List<Inqrystatus> m_inqrystatuses = new ArrayList<Inqrystatus>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_inqrychldinuch")
    private List<Inqrydocs> m_inqrydocses = new ArrayList<Inqrydocs>(0);
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "m_inqry", fetch = FetchType.LAZY, orphanRemoval=true)
    private List<Inqrylgot> m_inqry_lgots = new ArrayList<Inqrylgot>();
    public List<Inqrylgot> getInqrylgots() {
        return m_inqry_lgots;
    }
    public void setInqrylgots(List<Inqrylgot> p_inqry_lgots) {
        m_inqry_lgots = p_inqry_lgots;
    }
    
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
    @JoinColumn(name="ID_DOC_KIND_MTHR")
    private CDict73DocKind m_doc_kind_mother;
    public CDict73DocKind getDocKindMother() { return m_doc_kind_mother; }
    public void setDocKindMother(CDict73DocKind p_val) { m_doc_kind_mother = p_val; }
    
    @ManyToOne
    @JoinColumn(name="ID_DOC_KIND_FTHR")
    private CDict73DocKind m_doc_kind_father;
    public CDict73DocKind getDocKindFather() { return m_doc_kind_father; }
    public void setDocKindFather(CDict73DocKind p_val) { m_doc_kind_father = p_val; }
    
    @ManyToOne
    @JoinColumn(name="ID_DOC_KIND_OTHER")
    private CDict73DocKind m_doc_kind_other;
    public CDict73DocKind getDocKindOther() { return m_doc_kind_other; }
    public void setDocKindOther(CDict73DocKind p_val) { m_doc_kind_other = p_val; }
       
    @OneToOne(cascade= CascadeType.ALL, mappedBy="m_inqry", fetch= FetchType.LAZY)
    private InqryQueueInfo m_queue_info;
    public InqryQueueInfo getInqryQueueInfo() { return m_queue_info; }
    public void setInqryQueueInfo(InqryQueueInfo p_val) { m_queue_info = p_val; }

    @OneToMany(cascade= CascadeType.ALL, mappedBy="m_inqry", fetch= FetchType.LAZY)
    private List<InqryQueueWindow> m_queue_windows = new ArrayList<InqryQueueWindow>(0);
    public List<InqryQueueWindow> getInqryQueueWindow() { return m_queue_windows; }
    public void setInqryQueueWindow(List<InqryQueueWindow> p_val) { m_queue_windows = p_val; }

    @OneToMany(cascade= CascadeType.ALL, mappedBy="inqry", fetch= FetchType.LAZY)
    private List<CntrInteract> m_extern_sys = new ArrayList<CntrInteract>();
    public List<CntrInteract> getCntrInteract() { return m_extern_sys; }
    public void setCntrInteract(List<CntrInteract> p_val) { m_extern_sys = p_val; }

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
    
    @OneToMany(mappedBy="inqry", fetch= FetchType.LAZY)
    private List<DraftAlloc> m_draft_alloc;
    
    @Size(max=256)
    @Column(name = "BENEFITS_DOC_INFO")
    private String m_lgots_doc_info;
    public String getLgotsDocInfo() { return m_lgots_doc_info; }
    public void setLgotsDocInfo(String p_val) { m_lgots_doc_info = p_val; }
    
    @Size(max=256)
    @Column(name = "HEALTH_NEEDS_DOC_INFO")
    private String m_health_needs_doc_info;
    public String getHealthNeedsDocInfo() {
        return m_health_needs_doc_info;
    }
    public void setHealthNeedsDocInfo(String p_val) {
        m_health_needs_doc_info = p_val;
    }

    @Size(max=512)
    @Column(name = "PMPK_CONCLUSION")
    private String m_pmpk_conclusion;
    public String getPmpkConclusion() {
        return m_pmpk_conclusion;
    }
    public void setPmpkConclusion(String p_val) {
        m_pmpk_conclusion = p_val;
    }

    @ManyToMany(fetch= FetchType.LAZY, targetEntity=CDict85DouGrpTime.class)
    @JoinTable(name="INQRYDOUGRPTIME", joinColumns=@JoinColumn(name="ID_INQRY"),
        inverseJoinColumns=@JoinColumn(name="ID_DOU_GRP_TIME"))
    private List<CDict85DouGrpTime> m_dou_grp_times = new ArrayList<CDict85DouGrpTime>(0);
    public List<CDict85DouGrpTime> getDouGrpTimes() {
        if(m_dou_grp_times == null) m_dou_grp_times = new ArrayList<CDict85DouGrpTime>(0);
        return m_dou_grp_times; 
    }
    public void setDouGrpTimes(List<CDict85DouGrpTime> p_val) { m_dou_grp_times = p_val; }
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_CATEG")
    private CDict89InqryCateg m_categ;
    public CDict89InqryCateg getCateg() {
        return m_categ;
    }
    public void setCateg(CDict89InqryCateg p_categ) {
        this.m_categ = p_categ;
    }    
    
    
    //  -------------------------------------------------------------------
    //
    //  2016-02 для пгу2 курган (ранее было заведено для концентратора)
    //
    @Column(name="ALLOW_OFFER_OTHER_UCHS")
    private Boolean m_allow_offer_other_uchs;
    public Boolean isAllowOfferOtherUchs() { return m_allow_offer_other_uchs; }
    public void setAllowOfferOtherUchs(Boolean p_val) { m_allow_offer_other_uchs = p_val; }

    public static enum TSex { FEMALE, MALE };

    @Column(name = "SEX")
    private Boolean m_sex;
    public TSex getSex() { return m_sex == null ? null : (m_sex ? TSex.MALE : TSex.FEMALE); }
    public void setSex(TSex p_val) { m_sex = (p_val == null ? null : TSex.MALE.equals(p_val)); }

    //
    //  -------------------------------------------------------------------

    
    public Inqrychldinuch() { super(); }
    public Inqrychldinuch(int p_id) { super(p_id); }

    public CPerson getOtherPerson() {
        return this.m_other_person;
    }
    
    public void setOtherPerson(CPerson p_value) {
        this.m_other_person = p_value;
    }

    public CUsers getUsers() {
        return this.m_user;
    }
    
    public void setUsers(CUsers p_users) {
        this.m_user = p_users;
    }

    public CPerson getTrustedPerson() {
        return this.m_trusted_person;
    }
    
    public void setTrustedPerson(CPerson p_value) {
        this.m_trusted_person = p_value;
    }

    public CPerson getChild() {
        return this.m_child;
    }
    
    public void setChild(CPerson p_value) {
        this.m_child = p_value;
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

    @Column(name="IS_NATIVE_FIAS")
    private Boolean m_nativeFias;
    public Boolean isNativeFias() { return m_nativeFias; }
    public void setNativeFias(Boolean p_val) { m_nativeFias = p_val; }

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
    
    public String getMthrSrnm() {
        return this.m_mthrSrnm;
    }
    
    public void setMthrSrnm(String mthrSrnm) {
        this.m_mthrSrnm = mthrSrnm;
    }
    
    public String getMthrFrstnm() {
        return this.m_mthrFrstnm;
    }
    
    public void setMthrFrstnm(String mthrFrstnm) {
        this.m_mthrFrstnm = mthrFrstnm;
    }
    
    public String getMthrScndnm() {
        return this.m_mthrScndnm;
    }
    
    public void setMthrScndnm(String mthrScndnm) {
        this.m_mthrScndnm = mthrScndnm;
    }
    public Date getMthrBirthdt() {
        return this.m_mthrBirthdt;
    }
    
    public void setMthrBirthdt(Date mthrBirthdt) {
        this.m_mthrBirthdt = mthrBirthdt;
    }
    
    public String getMthrpsprtSer() {
        return this.m_mthrpsprtSer;
    }
    
    public void setMthrpsprtSer(String mthrpsprtSer) {
        this.m_mthrpsprtSer = mthrpsprtSer;
    }
    
    public String getMthrpsprtNum() {
        return this.m_mthrpsprtNum;
    }
    
    public void setMthrpsprtNum(String mthrpsprtNum) {
        this.m_mthrpsprtNum = mthrpsprtNum;
    }
    
    public String getMthrpsprtGave() {
        return this.m_mthrpsprtGave;
    }
    
    public void setMthrpsprtGave(String mthrpsprtGave) {
        this.m_mthrpsprtGave = mthrpsprtGave;
    }
    public Date getMthrpsprtDt() {
        return this.m_mthrpsprtDt;
    }
    
    public void setMthrpsprtDt(Date mthrpsprtDt) {
        this.m_mthrpsprtDt = mthrpsprtDt;
    }
    
    public String getMthrJob() {
        return this.m_mthrJob;
    }
    
    public void setMthrJob(String mthrJob) {
        this.m_mthrJob = mthrJob;
    }
    
    public String getMthrDlgn() {
        return this.m_mthrDlgn;
    }
    
    public void setMthrDlgn(String mthrDlgn) {
        this.m_mthrDlgn = mthrDlgn;
    }
    
    public String getMthrPhones() {
        return this.m_mthrPhones;
    }
    
    public void setMthrPhones(String mthrPhones) {
        this.m_mthrPhones = mthrPhones;
    }
    
    public String getMthrEmails() {
        return this.m_mthrEmails;
    }
    
    public void setMthrEmails(String mthrEmails) {
        this.m_mthrEmails = mthrEmails;
    }
    
    public String getFthrSrnm() {
        return this.m_fthrSrnm;
    }
    
    public void setFthrSrnm(String fthrSrnm) {
        this.m_fthrSrnm = fthrSrnm;
    }
    
    public String getFthrFrstnm() {
        return this.m_fthrFrstnm;
    }
    
    public void setFthrFrstnm(String fthrFrstnm) {
        this.m_fthrFrstnm = fthrFrstnm;
    }
    
    public String getFthrScndnm() {
        return this.m_fthrScndnm;
    }
    
    public void setFthrScndnm(String fthrScndnm) {
        this.m_fthrScndnm = fthrScndnm;
    }
    public Date getFthrBirthdt() {
        return this.m_fthrBirthdt;
    }
    
    public void setFthrBirthdt(Date fthrBirthdt) {
        this.m_fthrBirthdt = fthrBirthdt;
    }
    
    public String getFthrpsprtSer() {
        return this.m_fthrpsprtSer;
    }
    
    public void setFthrpsprtSer(String fthrpsprtSer) {
        this.m_fthrpsprtSer = fthrpsprtSer;
    }
    
    public String getFthrpsprtNum() {
        return this.m_fthrpsprtNum;
    }
    
    public void setFthrpsprtNum(String fthrpsprtNum) {
        this.m_fthrpsprtNum = fthrpsprtNum;
    }
    
    public String getFthrpsprtGave() {
        return this.m_fthrpsprtGave;
    }
    
    public void setFthrpsprtGave(String fthrpsprtGave) {
        this.m_fthrpsprtGave = fthrpsprtGave;
    }
    public Date getFthrpsprtDt() {
        return this.m_fthrpsprtDt;
    }
    
    public void setFthrpsprtDt(Date fthrpsprtDt) {
        this.m_fthrpsprtDt = fthrpsprtDt;
    }
    
    public String getFthrJob() {
        return this.m_fthrJob;
    }
    
    public void setFthrJob(String fthrJob) {
        this.m_fthrJob = fthrJob;
    }
    
    public String getFthrDlgn() {
        return this.m_fthrDlgn;
    }
    
    public void setFthrDlgn(String fthrDlgn) {
        this.m_fthrDlgn = fthrDlgn;
    }
    
    public String getFthrPhones() {
        return this.m_fthrPhones;
    }
    
    public void setFthrPhones(String fthrPhones) {
        this.m_fthrPhones = fthrPhones;
    }
    
    public String getFthrEmails() {
        return this.m_fthrEmails;
    }
    
    public void setFthrEmails(String fthrEmails) {
        this.m_fthrEmails = fthrEmails;
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
    
    public String getTrustedSrnm() {
        return this.m_trustedSrnm;
    }
    
    public void setTrustedSrnm(String trustedSrnm) {
        this.m_trustedSrnm = trustedSrnm;
    }
    
    public String getTrustedFrstnm() {
        return this.m_trustedFrstnm;
    }
    
    public void setTrustedFrstnm(String trustedFrstnm) {
        this.m_trustedFrstnm = trustedFrstnm;
    }
    
    public String getTrustedScndnm() {
        return this.m_trustedScndnm;
    }
    
    public void setTrustedScndnm(String trustedScndnm) {
        this.m_trustedScndnm = trustedScndnm;
    }
    
    public String getTrustedPhones() {
        return this.m_trustedPhones;
    }
    
    public void setTrustedPhones(String trustedPhones) {
        this.m_trustedPhones = trustedPhones;
    }
    
    public String getTrustedEmails() {
        return this.m_trustedEmails;
    }
    
    public void setTrustedEmails(String trustedEmails) {
        this.m_trustedEmails = trustedEmails;
    }
    
    public String getTrustedDoc() {
        return this.m_trustedDoc;
    }
    
    public void setTrustedDoc(String trustedDoc) {
        this.m_trustedDoc = trustedDoc;
    }
    
    public String getTrustedDescr() {
        return this.m_trustedDescr;
    }
    
    public void setTrustedDescr(String trustedDescr) {
        this.m_trustedDescr = trustedDescr;
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

    public List<Inqryuch> getInqryuchs() {
        return m_inqryuchs;
    }

    public void setInqryuchs(List<Inqryuch> p_inqryuchs) {
        m_inqryuchs = p_inqryuchs;
    }

    public List<Inqrystatus> getInqrystatuses() {
        return m_inqrystatuses;
    }

    public void setInqrystatuses(List<Inqrystatus> p_inqrystatuses) {
        m_inqrystatuses = p_inqrystatuses;
    }

    public List<Inqrydocs> getInqryDocses() {
        return m_inqrydocses;
    }

    public void setInqryDocses(List<Inqrydocs> p_inqrydocses) {
        m_inqrydocses = p_inqrydocses;
    }
    public void addInqryDocses(List<Inqrydocs> p_inqrydocses) {
        if(p_inqrydocses != null && !p_inqrydocses.isEmpty())
            for(Inqrydocs x_item: p_inqrydocses) {
                getInqryDocses().add(x_item);
                x_item.setInqrychldinuch(this);
            }
    }
    public void addInqryDoc(String p_data_type, Integer p_id_doc_type, byte[] p_data, String p_descr) {
        Inqrydocs x_doc = new Inqrydocs(p_data_type, p_id_doc_type, p_data, p_descr);
        getInqryDocses().add(x_doc);
        x_doc.setInqrychldinuch(this);
    }

    public Date getInUchDt() {
        return m_inuch_dt;
    }

    public void setInUchDt(Date p_inuch_dt) {
        m_inuch_dt = p_inuch_dt;
    }

    public Uch getTransferUch() {
        return m_transfer_uch;
    }
    
    public void setTransferUch(Uch p_uch) {
        m_transfer_uch = p_uch;
    }

    /**
     * Указал ли заявитель при подаче заявления наличие льготы 
     * (в зависимости от настроек может не заполняться, тогда указываются сразу конкретные льготы setLgots)
     * @return 
     */
    public Boolean getInquirerPrivilege() {
        return m_is_privilege;
    }
    
    public void setInquirerPrivilege(Boolean p_val) {
        m_is_privilege = p_val;
    }

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

    @Size(max=512)
    @Column(name = "PRIVILEGE_OTHER")
    private String m_privilege_other;
    public String getPrivilegeOther() {
        return m_privilege_other;
    }
    public void setPrivilegeOther(String p_val) {
        m_privilege_other = p_val;
    }

    @Column(name = "DOC_REFS")
    private String m_doc_refs;
    public String getDocRefs() { return m_doc_refs; }
    public void setDocRefs(String p_doc_refs) { m_doc_refs = p_doc_refs; }


} 
