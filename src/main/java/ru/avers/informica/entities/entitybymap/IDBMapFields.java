package ru.avers.informica.entities.entitybymap;


import ru.avers.informica.dao.IMapFields;

/**
 * @author alexanderm
 */
public interface IDBMapFields extends IMapFields {

    public static interface IUsers {
        final public static String
                s_name = "m_login",
                s_password = "m_password",
                s_person = "m_person",
                s_user_groups = "m_groups",
                s_settings = "m_gxt_ui";
    }

    public static interface IPerson {
        final public static String
                s_name = "m_name",
                s_patronymic = "m_patronymic",
                s_surname = "m_surname",
                s_birthdate = "m_birthdate",
                s_sex = "m_sex",
                s_addresses = "addresses";
    }

    public static interface IUserGroup {
        final public static String
                s_name = "m_name",
                s_users = "m_users";
    }

    public static interface IAddrPerson {
        final public static String
                s_code_kladr = "codeKLADR",
                s_code_ocato = "codeOCATO",
                s_index = "index",
                s_house = "house",
                s_korpus = "korpus",
                s_flat = "flat",
                s_addr_str = "addrStr",
                s_addr_type = "addrType",
                s_person = "person";
    }

    public static interface IUchInfo4inqry {
        final public static String
                s_uch = "m_uch",
                s_prll4stg = "m_prll4stg",
                s_typeclass = "m_prll_type",
                s_dou_grp_type = "m_dou_grp_type",
                s_dt_to = "m_dt_to",
                s_n_plan_class = "NPlanClss",
                s_year = "m_year",
                s_dou_org_struct = "m_dou_org_struct",
                s_cnt_by_health_absent = "m_cnt_by_health_absent",
                s_n_vacant = "NVacant",
                s_nvacant_add = "m_nvacant_add",
                s_n_fact = "m_n_fact",
                s_info1 = "info1",
                s_info2 = "info2",
                s_info3 = "info3",
                s_info4 = "info4",
                s_info5 = "info5",
                s_info6 = "info6",
                s_info7 = "info7",
                s_info8 = "info8",
                s_info9 = "info9",
                s_info10 = "info10",
                s_info11 = "info11",
                s_info12 = "info12",
                s_info13 = "info13",
                s_info14 = "info14";
    }

    public static interface IUchPupilQuantity {
        final public static String
                s_uch = "uch",
                s_health_needs = "healthNeeds",
                s_dou_grp_time = "douGrpTime",
                s_chld_grp_time = "chldGrpTime",
                s_date_of_birth = "dateOfBirth",
                s_count_of_pupil = "cntPupil",
                s_is_family_group = "familyGroup",
                s_is_graduate = "graduate",
                s_is_invalid = "invalid",
        //                s_grp_code = "grpCode",
                s_date_of_enrolled = "dateOfEnrolled",
                s_count_of_pupil_expelled = "cntPupilExpelled",
                s_type_sobstven = "typeSobstven";
    }

    public static interface IUch {
        final public static String
                s_inn = "inn",
                s_kpp = "kpp",
                s_okpo = "okpo",
                s_ogrn = "ogrn",
                s_code = "m_code",
                s_uch_var = "uchVar",
                s_uch_info4inqries = "m_uchInfo4inqries",
                s_addresses = "m_addresses",
                s_contacts = "m_contacts",
                s_municip_obr_okato = "m_municip_obr_okato",
                s_municip_obr_oktmo = "m_municip_obr_oktmo",
                s_inqryuch = "m_inqryuch";
    }

    public static interface IUchVar {
        final public static String
                s_ter = "m_ter",
                s_tu = "m_tu",
                s_uch = "uch",
                s_num = "num",
                s_name = "nm",
                s_socr_name = "snm",
                s_url = "url",
                s_emails = "emails",
                s_chief = "chief",
                s_founder = "m_founder",

                s_ds = "ds",
                s_df = "df";
    }

    public static interface IInqrystatus {
        final public static String
                s_inqry = "m_inqry",
                s_uch = "m_uch",
                s_status = "m_inqry_status",
                s_dt = "m_dt",
                s_df = "m_df",
                s_timeout = "m_timeout",
                s_descr = "m_descr",
                s_status_reasons = "m_inqrystsrsns",
                s_prll4stg = "m_prll4stg",
                s_type_class = "m_prll_type",
                s_vacant_year = "m_year",
                s_voucher_num = "m_voucher_num",
                s_dou_grp_type = "m_dou_grp_type";
    }

    public static interface IInqrychldinuch {
        final public static String
                //  заявление
                s_type_inqry = "m_type_inqry",
                s_num = "m_num",
                s_n_class = "m_ncls",
                s_notify_email = "m_notify_email",
                s_wish_date = "m_inuch_dt",
                s_descr = "m_descr",
                s_user = "m_user",
                s_uchs = "m_inqryuchs",
                s_statuses = "m_inqrystatuses",
                s_documents = "m_inqrydocses",
                s_lgots = "m_inqry_lgots",
                s_dou_grp_type = "m_dou_grp_type",
                s_health_needs = "m_health_needs",
                s_inqry_health_needs = "m_inqry_health_needs",
                s_transfer_uch = "m_transfer_uch",
                s_is_inquirer_privilege = "m_is_privilege",
                s_lgots_doc_info = "m_lgots_doc_info",
                s_lgots_confirm_info = "m_lgots_confirm_info",
                s_lgots_date_confirm = "m_lgots_date_confirm",
                s_health_needs_doc_info = "m_health_needs_doc_info",
                s_pmpk_conclusion = "m_pmpk_conclusion",
                s_dou_grp_times = "m_dou_grp_times",
                s_categ = "m_categ",
        //  заявитель
        //      мать
                s_mother_surname = "m_mthrSrnm",
                s_mother_1st_name = "m_mthrFrstnm",
                s_mother_2nd_name = "m_mthrScndnm",
                s_mother_birth_date = "m_mthrBirthdt",
                s_mother_passport_ser = "m_mthrpsprtSer",
                s_mother_passport_num = "m_mthrpsprtNum",
                s_mother_passport_gave_by = "m_mthrpsprtGave",
                s_mother_passport_dt = "m_mthrpsprtDt",
                s_mother_job = "m_mthrJob",
                s_mother_dlgn = "m_mthrDlgn",
                s_mother_phones = "m_mthrPhones",
                s_mother_emails = "m_mthrEmails",
                s_doc_kind_mother = "m_doc_kind_mother",
        //      отец
                s_father_surname = "m_fthrSrnm",
                s_father_1st_name = "m_fthrFrstnm",
                s_father_2nd_name = "m_fthrScndnm",
                s_father_birth_date = "m_fthrBirthdt",
                s_father_passport_ser = "m_fthrpsprtSer",
                s_father_passport_num = "m_fthrpsprtNum",
                s_father_passport_gave_by = "m_fthrpsprtGave",
                s_father_passport_dt = "m_fthrpsprtDt",
                s_father_job = "m_fthrJob",
                s_father_dlgn = "m_fthrDlgn",
                s_father_phones = "m_fthrPhones",
                s_father_emails = "m_fthrEmails",
                s_doc_kind_father = "m_doc_kind_father",
        //      законный представитель
                s_other_surname = "m_otherSrnm",
                s_other_1st_name = "m_otherFrstnm",
                s_other_2nd_name = "m_otherScndnm",
                s_other_birth_date = "m_otherBirthdt",
                s_other_passport_ser = "m_otherpsprtSer",
                s_other_passport_num = "m_otherpsprtNum",
                s_other_passport_gave_by = "m_otherpsprtGave",
                s_other_passport_dt = "m_otherpsprtDt",
                s_other_job = "m_otherJob",
                s_other_dlgn = "m_otherDlgn",
                s_other_phones = "m_otherPhones",
                s_other_emails = "m_otherEmails",
                s_other_descr = "m_otherDescr",
                s_other_person = "m_other_person",
                s_other_person_rodstvo = "m_other_person_rodstvo",
                s_doc_kind_other = "m_doc_kind_other",
        //      доверенное лицо
                s_trusted_person = "m_trusted_person",
                s_trusted_surname = "m_trustedSrnm",
                s_trusted_1st_name = "m_trustedFrstnm",
                s_trusted_2nd_name = "m_trustedScndnm",
                s_trusted_phones = "m_trustedPhones",
                s_trusted_emails = "m_trustedEmails",
                s_trusted_doc = "m_trustedDoc",
                s_trusted_descr = "m_trustedDescr",

        //  ребенок
                s_child_surname = "m_chldSrnm",
                s_child_1st_name = "m_chldFrstnm",
                s_child_2nd_name = "m_chldScndnm",
                s_child_birth_date = "m_chldBirthdt",
                s_child_birth_place = "m_chldBirthplc",
                s_child_doc_kind = "m_doc_kind_child",
                s_child_certificate_ser = "m_chldcrtfSer",
                s_child_certificate_num = "m_chldcrtfNum",
                s_child_certificate_gave_by = "m_chldcrtfGave",
                s_child_certificate_dt = "m_chldcrtfDt",
                s_child_addr_reg_full = "m_chldaddrregstr",
                s_child_addr_reg_code_kladr = "m_chldaddrregCodeKladrStreet",
                s_child_addr_reg_index = "m_chldaddregIndx",
                s_child_addr_reg_num_house = "m_chldaddrregNhouse",
                s_child_addr_reg_korpus = "m_chldaddrregKorpus",
                s_child_addr_reg_flat = "m_chldaddrregFlat",
                s_child_addr_reg_dt_from = "m_chldaddrreg_dt_from",
                s_child_addr_reg_dt_to = "m_chldaddrreg_dt_to",
                s_child_addr_fact_full = "m_chldaddrfactstr",
                s_child_addr_fact_code_kladr = "m_chldaddrfactCodeKladrStreet",
                s_child_addr_fact_index = "m_chldaddrfactIndx",
                s_child_addr_fact_num_house = "m_chldaddrfactNhouse",
                s_child_addr_fact_korpus = "m_chldaddrfactKorpus",
                s_child_addr_fact_flat = "m_chldaddrfactFlat",
                s_child_prev_uch = "m_chldPrevUch",
                s_child_descr = "m_chldDescr",
                s_child_ter = "m_chld_ter",
                s_child_person = "m_child";

    }

    public static interface IInqrydocs {
        final public static String
                s_inqry = "m_inqrychldinuch",
                s_doc_type = "m_inqry_doc_type",
                s_descr = "m_descr",
                s_data = "m_data",
                s_mime_data_type = "m_data_type";
    }

    public static interface IInqryuch {
        final public static String
                s_inqry = "m_inqrychldinuch",
                s_uch = "m_uch",
                s_priority = "m_prty",
                s_descr = "m_descr";
    }

    public static interface IInqrylgot {
        final public static String
                s_inqry = "m_inqry",
                s_lgot = "m_lgot";
    }

    public static interface IAddrUch {
        final public static String
                s_uch = "m_uch",
                s_code_kladr = "codeKladrStreet",
                s_code_ocato = "codeOcato",
                s_index = "indx",
                s_house = "nhouse",
                s_korpus = "korpus",
                s_addr_str = "m_addr_str",
                s_fias_house_guid = "m_fias_house_guid",
                s_addr_type = "m_dicts_detail";
    }

    public static interface IContact {
        final public static String
                s_uch = "m_uch",
                s_person = "m_person",
                s_type = "m_contact_type",
                s_kind = "m_contact_kind",
                s_contact = "m_contact";
    }

    public static interface IGpd {
        //  TODO
    }

    public static interface IInqrystsrsn {
        final public static String
                s_inqrystatus = "m_inqrystatus",
                s_inqry_status_reason = "m_inqry_status_reason",
                s_descr = "m_descr";
    }

    public static interface IPrll4stg {
        final public static String
                s_stage_uch = "m_stage_uch",
                s_parallel = "m_parallel",
                s_nprll = "m_nprll",
                s_uch_info4inqries = "m_uchInfo4inqries",
                s_ages = "m_ages";
    }

    public static interface IPrll4stgAges {
        final public static String
//                s_prll4stg = "m_prll4stg",
//                s_prll_type = "m_prll_type",
                s_ui4i = "m_uch_info4inqry",
                s_from_year = "m_from_year",
                s_from_month = "m_from_month",
                s_from_day = "m_from_day",
                s_to_year = "m_to_year",
                s_to_month = "m_to_month",
                s_to_day = "m_to_day";
    }

    public static interface IStageUch {
        final public static String
                s_uch = "m_uch",
                s_stupen_obuch = "m_stupen_obuch",
                s_descr_stage = "descrStage",
                s_nstage = "nstage",
                s_prll4stgs = "prll4stgs";

    }

    public static interface IInformicaUch {
        final public static String
                s_code = "code",
                s_name = "name",
                s_chief = "chief",
                s_type_of_sobstven = "typeOfSobstven",
                s_func_status = "funcStatus",
                s_meal_serving_type = "mealServingType",
                s_municip_obr_oktmo = "municipObrOktmo",
                s_kind = "kind",
                s_epgu_link = "epguLink",
                s_rpgu_link = "rpguLink",
                s_contacts = "contacts",
                s_uch_info = "uchInfo",
                s_addresses = "addresses",
                s_buildings = "buildings";
    }

    public static interface IInformicaBuilding {
        final public static String
                s_uch = "uch",
                s_code = "code",
                s_name = "name",
                s_fias_house_guid = "fiasHouseGuid",
                s_addr_str = "addrStr",
                s_groups = "groups";
    }

    public static interface IInformicaGroup {
        final public static String
                s_building = "building",
                s_code = "code",
                s_from_year = "fromYear",
                s_from_month = "fromMonth",
                s_to_year = "toYear",
                s_to_month = "toMonth",
                s_name = "name",
                s_type_of_health = "type",
                s_norm = "norm",
                s_cnt_ovz = "cntOvz",
                s_cnt_free_space = "cntFreeSpace",
                s_dou_grp_time = "douGrpTime",
                s_activity = "activity",
                s_fact = "NFact";
    }

    public static interface IUchtimetbl4inqry {
        //  TODO
    }

    /////////////////////////////////////////////////////////
    //
    //  словари
    //

    public static interface IBaseDict {
        final public static String
                s_name = "m_name";
    }

    public static interface IBaseDictDetail extends IBaseDict {
        final public static String
                s_dict = "dict";
    }

    public static interface ICodedDict {
        final public static String
                s_code = "m_code",
                s_code_prop = "code";
    }

    public static interface IHasDescrDict {
        final public static String s_descr = "m_descr";
    }


    public static interface IDict01Dolgnost extends IBaseDict, ICodedDict, IHasDescrDict {
    }

    public static interface IDict03ContactType extends IBaseDict, ITree, ICodedDict {
    }

    public static interface IDict04MunicipObrazov extends IBaseDict, ITree, ICodedDict, IHasDescrDict {
    }

    public static interface IDict05StupenObuch extends IBaseDict, ITree {
    }

    public static interface IDict06TypeOfSobstven extends IBaseDict, ICodedDict {
    }

    public static interface IDict08TypeClass extends IBaseDict, ITree, ICodedDict {
        final public static String
                s_descr = "description";
    }

    public static interface IDict09Parallel extends IBaseDict, ITree, ICodedDict {
    }

    public static interface IDict11TypeOfUch extends IBaseDict, ITree {
    }

    public static interface IDict14UOMValuta {
        //  TODO
    }

    public static interface IDict14Valuta {
        //  TODO
    }

    public static interface IDict25Rodstvo extends IBaseDict, ITree, ICodedDict {
    }

    public static interface IDict58TypeOfMestnost {
        //  TODO
    }

    public static interface IDict70ContactKind extends IBaseDict, ITree, ICodedDict {
    }

    public static interface IDict71PersonAddrType extends IBaseDict, ITree {
        final public static String
                s_parent = "parent",
                s_childs = "childs",
                s_code = "code";
    }

    public static interface IDict72UchAddrType extends IBaseDict, ITree, ICodedDict {
    }

    public static interface IDict73DocKind extends IBaseDict, ITree {
        final public static String
                s_parent = "parent",
                s_childs = "childs",
                s_code = "code",
                s_descr = "description";
    }

    public static interface IDict73DocKindChild extends IBaseDict, ITree {
        final public static String
                s_parent = "parent",
                s_childs = "childs",
                s_code = "code",
                s_descr = "description";
    }

    public static interface IDict74InqryDocType extends IBaseDict, ICodedDict, IHasDescrDict {
    }

    public static interface IDict75InqryType extends IBaseDict, ICodedDict, IHasDescrDict {
    }

    public static interface IDict76InqryStatus extends IBaseDict, ICodedDict, IHasDescrDict {
    }

    public static interface IDict77InqryStatusReason extends IBaseDict, ICodedDict, IHasDescrDict {
    }

    public static interface IDict81InqryChildLgot extends IBaseDict, ICodedDict, IHasDescrDict {
        final public static String
                s_type = "m_type";

    }

    public static interface IDict85DouGrpTime extends IBaseDict {
        final public static String
                s_code = "code",
                s_descr = "description",
                s_weight = "weight";
    }

    public static interface IDict87SysInteraction extends IBaseDict {
        final public static String
                s_cfg = "cfg",
                s_descr = "descr";
    }

    public static interface IDict90OrgFuncStatus extends IBaseDict, ICodedDict {
    }

    public static interface IDict92OrgStruct extends IBaseDict, ICodedDict {
    }

    public static interface IDict93GrpActivity extends IBaseDict, ICodedDict {
    }

    public static interface IDicts extends IBaseDict, ICodedDict, IHasDescrDict {
        final public static String
                s_cnt_level = "m_cnt_lvl",
                s_values = "m_values";
    }

    public static interface IDictsDetail extends IBaseDict, ITree {
        final public static String
                s_spare01 = "m_spare01",
                s_spare02 = "m_spare02",
                s_spare03 = "m_spare03",
                s_dict = "m_dict";
    }
    /////////////////////////////////////////////////////////


    public static interface IUserGroup2MunicipObrazov {
        final public static String
                s_group = "m_user_group",
                s_ter = "m_ter",
                s_uch = "m_uch";
    }

    public static interface IUserGroup2Entities {
        final public static String
                s_group = "m_user_group",
                s_ter = "m_ter",
                s_uch = "m_uch",
                s_rights = "m_rights";
    }

    public static interface IInqrySysInteraction {
        final public static String
                s_type = "type";
    }

    public static interface IInqrySmevInteraction extends IInqrySysInteraction {
        final public static String
                s_inqry = "inqry",
                s_request = "request",
                s_response = "response",
                s_external01 = "external01";
    }

    /////////////////////////////////////////////////////////

}
