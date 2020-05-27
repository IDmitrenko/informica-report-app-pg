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
            s_install_type_region = "������������",
            s_install_type_municipal = "�������������";
        
    final static String
            s_age_category_all = "���",
            
            s_age_category_m2_to_y3 = "�� 2 ������� �� 3 ���",
            s_age_category_y3_to_y5 = "�� 3 �� 5 ���",
            s_age_category_y5_to_y7m6 = "�� 5 �� 7,5 ���",
            
            s_age_category_m2_to_m6 = "�� 2 ������� �� 6 �������",
            s_age_category_m6_to_y1 = "�� 6 ������� �� 1 ����",
            s_age_category_y1_to_y1m6 = "�� 1 ���� �� 1,5 ����",
            s_age_category_y1m6_to_y2 = "�� 1,5 �� 2 ���",
            s_age_category_y2_to_y2m6 = "�� 2 �� 2,5 ���",
            s_age_category_y2m6_to_y3 = "�� 2,5 �� 3 ���",
            s_age_category_y3_to_y3m6 = "�� 3 �� 3,5 ���",
            s_age_category_y3m6_to_y4 = "�� 3,5 �� 4 ���",
            s_age_category_y4_to_y4m6 = "�� 4 �� 4,5 ���",
            s_age_category_y4m6_to_y5 = "�� 4,5 �� 5 ���",
            s_age_category_y5_to_y5m6 = "�� 5 �� 5,5 ���",
            s_age_category_y5m6_to_y6 = "�� 5,5 �� 6 ���",
            s_age_category_y6_to_y6m6 = "�� 6 �� 6,5 ���",
            s_age_category_y6m6_to_y7 = "�� 6,5 �� 7 ���",
            s_age_category_y7_to_y7m6 = "�� 7 �� 7,5 ���",
            s_age_category_y7m6_plus = "�� 7,5 ��� � ������",
    
            s_age_category_y5_to_y7 = "�� 5 �� 7 ���",

            s_age_category_m2_to_y1 = "�� 2 ������� �� 1 ����",
            s_age_category_y1_to_y2 = "�� 1 ���� �� 2 ���",
            s_age_category_y2_to_y3 = "�� 2 �� 3 ���",
            s_age_category_y3_to_y4 = "�� 3 �� 4 ���",
            s_age_category_y4_to_y5 = "�� 4 �� 5 ���",
            s_age_category_y5_to_y6 = "�� 5 �� 6 ���",
            s_age_category_y6_to_y7 = "�� 6 �� 7 ���",
            s_age_category_y7_plus = "�� 7 ��� � ������",
            
            s_age_category_y0_to_y3 = "�� 0 �� 3 ���",
            s_age_category_y3_to_y7 = "�� 3 �� 7 ���";
    
    final static String
            s_municip_counter_noo_doo_act = "0.1",
            s_municip_counter_noo_doo_def = "0.2",
            s_municip_counter_medic = "0.3",
            s_municip_counter_family = "0.4";
    
    final static String
            s_type_legal_municip = "�������������",
            s_type_legal_state = "���������������",
            s_type_legal_non_state = "�����������������",
            s_type_legal_non_state_wout_license = "����������������� ��� �������� �� ������������� ��������������� ������������",
            s_type_legal_ip = "�������������� ��������������� (��)",
            s_type_legal_ip_wout_license = "�������������� ��������������� (��) ��� �������� �� ������������� ��������������� ������������",
            s_type_legal_deps = "�������������",
            s_type_legal_other = "������ ����";

    final static String
            s_type_legal_municip3 = "�������������",
            s_type_legal_state3 = "���������������",
            s_type_legal_non_state3 = "������� (���� ��������)",
            s_type_legal_non_state_wout_license3 = "������� (��� ��������)",
            s_type_legal_ip3 = "�������������� ���������������, �� (���� ��������)",
            s_type_legal_ip_wout_license3 = "�������������� ���������������, �� (��� ��������)",
            s_type_legal_deps3 = "�������������",
            s_type_legal_other3 = "������ ����";
    
    final static String
            s_org_status_working = "�������������",
            s_org_status_repairs = "����������� ������",
            s_org_status_renovation = "�������������",
            s_org_status_halted = "������������ ��������������",
            s_org_status_wout_contingent = "��� �����������",
            s_org_status_prep_to_open = "��� �� �������";

    final static String
            s_org_status_working3 = "�������������",
            s_org_status_repairs3 = "����������� ������",
            s_org_status_renovation3 = "�������������",
            s_org_status_halted3 = "������������ ��������������",
            s_org_status_wout_contingent3 = "���������� �����������",
            s_org_status_prep_to_open3 = "������� ��������";
    
    final static String
            s_ovz_no_ovz_groups = "��� ����� ���",
            s_ovz_sluha = "��������� �����",
            s_ovz_rechi = "��������� ����",
            s_ovz_zrenia = "��������� ������",
            s_ovz_intellect = "���������� ����������",
            s_ovz_psihich_razv = "�������� ������������ ��������",            
            s_ovz_oda = "��������� ������-������������� ��������",
            s_ovz_sl_defect = "������� �������",
            s_ovz_other = "���� ��������� � ��������",
            s_ovz_combined = "��������������� ������ ���";
    
    final static String
            s_struct_doo = "1", //���������� ��������������� �����������*
            s_struct_groups_in_school = "2", //���������� ������ ��� �����
            s_struct_groups_in_other_org = "3", //���������� ������ ��� ������ �����������
            s_struct_department = "4"; //������������ ����������� ������������� (������) �����������, � ������� �������������� ������������ �� ���������� �������� ����������� ����������� �/��� �������� � ����*

    final static String
            s_name_struct_doo = "���������� ��������������� �����������",
            s_name_struct_school_ds = "��������� �����-������� ���",
            s_name_struct_groups_in_school = "���������� ������ ��� �����",
            s_name_struct_groups_in_other_org = "���������� ������ ��� ������ �����������",
            s_name_struct_department = "������������ ����������� ������������� (������) �����������";
    
    final static String
            s_brief_3_5 = "1", // ���������������� ���������� (3-5 �����) 
            s_short_8_10 = "2", // ������������ ��� (8-10 �����)
            s_full_10d5_12 = "3", // ������� ��� (10,5-12 �����)
            s_extended_13_14 = "4", // ����������� ���  (13-14 �����)
            s_const_24 = "5", // ��������������� ���������� (24 ����)
            s_str_brief_3_5 = "���������������� ���������� (3-5 �����)",
            s_str_short_8_10 = "������������ ��� (8-10 �����)",
            s_str_full_10d5_12 = "������� ��� (10,5-12 �����)",
            s_str_extended_13_14 = "����������� ���  (13-14 �����)",
            s_str_const_24 = "��������������� ���������� (24 ����)";    
    
    final static String
            s_orientation_1 = "���������������",
            s_orientation_2 = "��������������",
            s_orientation_3 = "���������������",
            s_orientation_4 = "���������������",
            s_orientation_5 = "��� ����� ������� ��������",
            s_orientation_6 = "������ �� ��������� � �����",
            s_orientation_7 = "�������� ���������� ������";
    
    final static String
            s_education = "1", // ����������� ��������������� ��������� ����������� �����������
            s_care = "2", // �������������� ������ ������������ �� ��������� � �����
            s_str_education = " ���������� ��������������� �������� ����������� �����������",
            s_str_care = "�������� � ���� �� ������";
    
    final static String
            s_ovz3_1_sluha = "� ���������� �����",
            s_ovz3_2_rechi = "� ���������� ����",
            s_ovz3_3_zrenia = "� ���������� ������",
            s_ovz3_4_intellect = "� ���������� ����������",
            s_ovz3_5_psihich_razv = "� ��������� ������������ ��������",            
            s_ovz3_6_oda = "� ���������� ������-������������� ��������",
            s_ovz3_7_sl_defect = "�� ������� ��������",
            s_ovz3_8_other = "������� �������";
    
    final static String
            s_wellness_1 = "������ ��� ����� � ������������� �������������",
            s_wellness_2 = "������ ��� ����� �������� �����",
            s_wellness_3 = "���� �������";
            
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
