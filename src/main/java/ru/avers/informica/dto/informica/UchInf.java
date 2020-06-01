package ru.avers.informica.dto.informica;

/**
 *
 * @author Dias
 */
public class UchInf {
    private int m_id;    
    private String m_code,
                   m_name,
                   m_short_name,
                   m_ter_name,
                   m_chief,
                   m_municipObrOktmo,
                   m_epgu_link,
                   m_rpgu_link,
                   m_work_time,
                   m_meal_serving_type,
                   m_fias_house_guid,
                   m_str_addr,
                   m_org_legal_form_name,
                   m_org_legal_form_code,
                   m_status_name,
                   m_status_code,
                   m_add_education,
                   m_features,
                   m_structure_name,
                   m_structure_code;
    private Integer m_id_main_uch,
                    m_id_ter,
                    m_no_doo_act_0_3,
                    m_no_doo_act_3_7,
                    m_no_doo_def_0_3,
                    m_no_doo_def_3_7,
                    m_medic_0_3,
                    m_medic_3_7,
                    m_family_0_3,
                    m_family_3_7;
            
    public UchInf() {
    }
    
    public int getId() {
        return m_id;
    }    
    public void setId(int p_id) {
        m_id = p_id;
    }

    public Integer getIdMainUch() {
        return m_id_main_uch;
    }

    public void setIdMainUch(Integer p_id_main_uch) {
        this.m_id_main_uch = p_id_main_uch;
    }
    
    public String getCode() {
        return this.m_code;
    }    
    public void setCode(String code) {
        this.m_code = code;
    }    

    public String getName() {
        return m_name;
    }    
    public void setName(String p_name) {
        m_name = p_name;
    }

    public String getShortName() {
        return m_short_name;
    }    
    public void setShortName(String p_sname) {
        m_short_name = p_sname;
    }    

    public Integer getIdTer() {
        return m_id_ter;
    }

    public void setIdTer(Integer p_id_ter) {
        this.m_id_ter = p_id_ter;
    }        
    
    public String getTerName() {
        return m_ter_name;
    }    
    public void setTerName(String p_ter_name) {
        m_ter_name = p_ter_name;
    }
    
    public String getChief() {
        return m_chief;
    }    
    public void setChief(String p_name) {
        m_chief = p_name;
    }

    public String getOrgLegalFormName() {
        return m_org_legal_form_name;
    }
    public void setOrgLegalFormName(String p_org_legal_form_name) {
        this.m_org_legal_form_name = p_org_legal_form_name;
    }

    public String getOrgLegalFormCode() {
        return m_org_legal_form_code;
    }
    public void setOrgLegalFormCode(String p_org_legal_form_code) {
        this.m_org_legal_form_code = p_org_legal_form_code;
    }

    public String getStatusName() {
        return m_status_name;
    }
    public void setStatusName(String p_status_name) {
        this.m_status_name = p_status_name;
    }

    public String getStatusCode() {
        return m_status_code;
    }
    public void setStatusCode(String p_status_code) {
        this.m_status_code = p_status_code;
    }    
    
    public String getWorkTime() {
        return m_work_time;
    }    
    public void setWorkTime(String p_work_time) {
        m_work_time = p_work_time;
    }
    
    public String getMealServingType() {
        return m_meal_serving_type;
    }    
    public void setMealServingType(String p_meal_serving_type) {
        m_meal_serving_type = p_meal_serving_type;
    }

    public String getFiasHouseGuid() {
        return m_fias_house_guid;
    }
    public void setFiasHouseGuid(String p_fias_house_guid) {
        this.m_fias_house_guid = p_fias_house_guid;
    }

    public String getStrAddr() {
        return m_str_addr;
    }
    public void setStrAddr(String p_str_addr) {
        this.m_str_addr = p_str_addr;
    }       
    
    public String getMunicipObrOktmo() {
        return this.m_municipObrOktmo;
    }    
    public void setMunicipObrOktmo(String municipObrOktmo) {
        this.m_municipObrOktmo = municipObrOktmo;
    }

    public String getEpguLink() {
        return m_epgu_link;
    }
    public void setEpguLink(String p_epgu_link) {
        this.m_epgu_link = p_epgu_link;
    }

    public String getRpguLink() {
        return m_rpgu_link;
    }
    public void setRpguLink(String p_rpgu_link) {
        this.m_rpgu_link = p_rpgu_link;
    }

    public String getAddEducation() {
        return m_add_education;
    }
    public void setAddEducation(String p_add_education) {
        this.m_add_education = p_add_education;
    }

    public String getFeatures() {
        return m_features;
    }
    public void setFeatures(String p_features) {
        this.m_features = p_features;
    }    

    public String getStructureName() {
        return m_structure_name;
    }

    public void setStructureName(String m_structure_name) {
        this.m_structure_name = m_structure_name;
    }

    public String getStructureCode() {
        return m_structure_code;
    }

    public void setStructureCode(String m_structure_code) {
        this.m_structure_code = m_structure_code;
    }    
    
    public Integer getNoDooAct_0_3() {
        return m_no_doo_act_0_3;
    }

    public void setNoDooAct_0_3(Integer no_doo_act_0_3) {
        this.m_no_doo_act_0_3 = no_doo_act_0_3;
    }

    public Integer getNoDooAct_3_7() {
        return m_no_doo_act_3_7;
    }

    public void setNoDooAct_3_7(Integer no_doo_act_3_7) {
        this.m_no_doo_act_3_7 = no_doo_act_3_7;
    }

    public Integer getNoDooDef_0_3() {
        return m_no_doo_def_0_3;
    }

    public void setNoDooDef_0_3(Integer no_doo_def_0_3) {
        this.m_no_doo_def_0_3 = no_doo_def_0_3;
    }

    public Integer getNoDooDef_3_7() {
        return m_no_doo_def_3_7;
    }

    public void setNoDooDef_3_7(Integer no_doo_def_3_7) {
        this.m_no_doo_def_3_7 = no_doo_def_3_7;
    }

    public Integer getMedic_0_3() {
        return m_medic_0_3;
    }

    public void setMedic_0_3(Integer medic_0_3) {
        this.m_medic_0_3 = medic_0_3;
    }

    public Integer getMedic_3_7() {
        return m_medic_3_7;
    }

    public void setMedic_3_7(Integer medic_3_7) {
        this.m_medic_3_7 = medic_3_7;
    }

    public Integer getFamily_0_3() {
        return m_family_0_3;
    }

    public void setFamily_0_3(Integer family_0_3) {
        this.m_family_0_3 = family_0_3;
    }

    public Integer getFamily_3_7() {
        return m_family_3_7;
    }

    public void setFamily_3_7(Integer family_3_7) {
        this.m_family_3_7 = family_3_7;
    }       
    
}
