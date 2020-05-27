package ru.avers.informica.infcfg;

/**
 *
 * @author Dias
 */
public interface IConst {
    final static String s_tag_push_data_request = "push_data_request";
    final static String s_ns = "http://Fed.edu.segment/gisdo";
    final static String s_schema_location = "http://Fed.edu.segment/gisdo ../../main/castor/gisdo_types_2014_05_12.xsd";
    public final static String s_ns3 = "http://eo.edu.ru/";
    public final static String s_schema_location3 = "http://eo.edu.ru/ http://eo.edu.ru/gisdo_types_v3.xsd";
    
    final static javax.xml.namespace.QName s_qname_push_data_request = 
                 new javax.xml.namespace.QName(s_ns, s_tag_push_data_request);
    
    public final static javax.xml.namespace.QName s_qname_push_data_request3 = 
                        new javax.xml.namespace.QName(s_ns, s_tag_push_data_request);
    
    
    final static String
            s_install_type_region = "Региональная",
            s_install_type_municipal = "Муниципальная";
        
    final static String
            s_age_category_all = "Все",
            
            s_age_category_m2_to_y3 = "от 2 месяцев до 3 лет",
            s_age_category_y3_to_y5 = "от 3 до 5 лет",
            s_age_category_y5_to_y7m6 = "от 5 до 7,5 лет",
            
            s_age_category_m2_to_m6 = "от 2 месяцев до 6 месяцев",
            s_age_category_m6_to_y1 = "от 6 месяцев до 1 года",
            s_age_category_y1_to_y1m6 = "от 1 года до 1,5 года",
            s_age_category_y1m6_to_y2 = "от 1,5 до 2 лет",
            s_age_category_y2_to_y2m6 = "от 2 до 2,5 лет",
            s_age_category_y2m6_to_y3 = "от 2,5 до 3 лет",
            s_age_category_y3_to_y3m6 = "от 3 до 3,5 лет",
            s_age_category_y3m6_to_y4 = "от 3,5 до 4 лет",
            s_age_category_y4_to_y4m6 = "от 4 до 4,5 лет",
            s_age_category_y4m6_to_y5 = "от 4,5 до 5 лет",
            s_age_category_y5_to_y5m6 = "от 5 до 5,5 лет",
            s_age_category_y5m6_to_y6 = "от 5,5 до 6 лет",
            s_age_category_y6_to_y6m6 = "от 6 до 6,5 лет",
            s_age_category_y6m6_to_y7 = "от 6,5 до 7 лет",
            s_age_category_y7_to_y7m6 = "от 7 до 7,5 лет",
            s_age_category_y7m6_plus = "от 7,5 лет и старше",
    
            s_age_category_y5_to_y7 = "от 5 до 7 лет",

            s_age_category_m2_to_y1 = "от 2 месяцев до 1 года",
            s_age_category_y1_to_y2 = "от 1 года до 2 лет",
            s_age_category_y2_to_y3 = "от 2 до 3 лет",
            s_age_category_y3_to_y4 = "от 3 до 4 лет",
            s_age_category_y4_to_y5 = "от 4 до 5 лет",
            s_age_category_y5_to_y6 = "от 5 до 6 лет",
            s_age_category_y6_to_y7 = "от 6 до 7 лет",
            s_age_category_y7_plus = "от 7 лет и старше",
            
            s_age_category_y0_to_y3 = "от 0 до 3 лет",
            s_age_category_y3_to_y7 = "от 3 до 7 лет";
    
    final static String
            s_municip_counter_noo_doo_act = "0.1",
            s_municip_counter_noo_doo_def = "0.2",
            s_municip_counter_medic = "0.3",
            s_municip_counter_family = "0.4";
    
    final static String
            s_type_legal_municip = "Муниципальная",
            s_type_legal_state = "Государственная",
            s_type_legal_non_state = "Негосударственная",
            s_type_legal_non_state_wout_license = "Негосударственная без лицензии на осуществление образовательной деятельности",
            s_type_legal_ip = "Индивидуальный предприниматель (ИП)",
            s_type_legal_ip_wout_license = "Индивидуальный предприниматель (ИП) без лицензии на осуществление образовательной деятельности",
            s_type_legal_deps = "Ведомственная",
            s_type_legal_other = "Другие типы";

    final static String
            s_type_legal_municip3 = "Муниципальная",
            s_type_legal_state3 = "Государственная",
            s_type_legal_non_state3 = "Частная (есть лицензия)",
            s_type_legal_non_state_wout_license3 = "Частная (без лицензии)",
            s_type_legal_ip3 = "Индивидуальный предприниматель, ИП (есть лицензия)",
            s_type_legal_ip_wout_license3 = "Индивидуальный предприниматель, ИП (без лицензии)",
            s_type_legal_deps3 = "Ведомственная",
            s_type_legal_other3 = "Другие типы";
    
    final static String
            s_org_status_working = "Функционирует",
            s_org_status_repairs = "Капитальный ремонт",
            s_org_status_renovation = "Реконструкция",
            s_org_status_halted = "Деятельность приостановлена",
            s_org_status_wout_contingent = "Без контингента",
            s_org_status_prep_to_open = "Ещё не открыто";

    final static String
            s_org_status_working3 = "Функционирует",
            s_org_status_repairs3 = "Капитальный ремонт",
            s_org_status_renovation3 = "Реконструкция",
            s_org_status_halted3 = "Деятельность приостановлена",
            s_org_status_wout_contingent3 = "Контингент отсутствует",
            s_org_status_prep_to_open3 = "Ожидает открытия";
    
    final static String
            s_ovz_no_ovz_groups = "Нет групп ОВЗ",
            s_ovz_sluha = "Нарушения слуха",
            s_ovz_rechi = "Нарушения речи",
            s_ovz_zrenia = "Нарушения зрения",
            s_ovz_intellect = "Умственная отсталость",
            s_ovz_psihich_razv = "Задержка психического развития",            
            s_ovz_oda = "Нарушения опорно-двигательного аппарата",
            s_ovz_sl_defect = "Сложные дефекты",
            s_ovz_other = "Иные нарушения в развитии",
            s_ovz_combined = "Комбинированные группы ОВЗ";
    
    final static String
            s_struct_doo = "1", //Дошкольная образовательная организация*
            s_struct_groups_in_school = "2", //Дошкольные группы при школе
            s_struct_groups_in_other_org = "3", //Дошкольные группы при другой организации
            s_struct_department = "4"; //Обособленное структурное подразделение (филиал) организации, в котором осуществляется деятельность по реализации программ дошкольного образования и/или присмотр и уход*

    final static String
            s_name_struct_doo = "Дошкольная образовательная организация",
            s_name_struct_school_ds = "Начальная школа-детский сад",
            s_name_struct_groups_in_school = "Дошкольные группы при школе",
            s_name_struct_groups_in_other_org = "Дошкольные группы при другой организации",
            s_name_struct_department = "Обособленное структурное подразделение (филиал) организации";
    
    final static String
            s_brief_3_5 = "1", // кратковременного пребывания (3-5 часов) 
            s_short_8_10 = "2", // сокращенного дня (8-10 часов)
            s_full_10d5_12 = "3", // полного дня (10,5-12 часов)
            s_extended_13_14 = "4", // продленного дня  (13-14 часов)
            s_const_24 = "5", // круглосуточного пребывания (24 часа)
            s_str_brief_3_5 = "кратковременного пребывания (3-5 часов)",
            s_str_short_8_10 = "сокращенного дня (8-10 часов)",
            s_str_full_10d5_12 = "полного дня (10,5-12 часов)",
            s_str_extended_13_14 = "продленного дня  (13-14 часов)",
            s_str_const_24 = "круглосуточного пребывания (24 часа)";    
    
    final static String
            s_orientation_1 = "Общеразвивающая",
            s_orientation_2 = "Компенсирующая",
            s_orientation_3 = "Комбинированная",
            s_orientation_4 = "Оздоровительная",
            s_orientation_5 = "Для детей раннего возраста",
            s_orientation_6 = "Группы по присмотру и уходу",
            s_orientation_7 = "Семейные дошкольные группы";
    
    final static String
            s_education = "1", // реализуются образовательные программы дошкольного образования
            s_care = "2", // осуществляется только деятельность по присмотру и уходу
            s_str_education = " Реализация образовательных программ дошкольного образования",
            s_str_care = "Присмотр и уход за детьми";
    
    final static String
            s_ovz3_1_sluha = "С нарушением слуха",
            s_ovz3_2_rechi = "С нарушением речи",
            s_ovz3_3_zrenia = "С нарушением зрения",
            s_ovz3_4_intellect = "С нарушением интеллекта",
            s_ovz3_5_psihich_razv = "С задержкой психического развития",            
            s_ovz3_6_oda = "С нарушением опорно-двигательного аппарата",
            s_ovz3_7_sl_defect = "Со сложным дефектом",
            s_ovz3_8_other = "Другого профиля";
    
    final static String
            s_wellness_1 = "Группы для детей с туберкулезной интоксикацией",
            s_wellness_2 = "Группы для часто болеющих детей",
            s_wellness_3 = "Иной профиль";
            
    final static String
            s_age_unit_month = "MONTH",
            s_age_unit_year = "YEAR";
    
    final static String
            s_schema_ver_2_0 = "2.0",
            s_schema_ver_3_0 = "3.0",
            s_schema_ver_4_0 = "4.0",
            s_schema_ver_5_0 = "5.0";
    
    final static String s_code_delimiter = "-";
    
}
