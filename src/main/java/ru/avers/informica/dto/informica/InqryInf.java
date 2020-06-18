package ru.avers.informica.dto.informica;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dias
 */
public class InqryInf implements IInformicaChildCountable {
    private Integer count = 1;
    private Integer m_id,               // id applications (заявления)
                    m_id_uch;           // id (InqryUch.id_uch - ссылка на таблицу Uch
                                        // нужно брать учреждение с минимальным значением prty
                                        // id = buildings.uch_buildings_id (prty)
                                        // - ссылка на таблицу uch_buildings
                                        // uch_buildings.uch - ссылка на таблицу uch (Основная информация)
    private Short priorityCount = 0;    // Old - InqryUch.prty
                                        // New - buildings.prty
    private Short minPriority = 0;      // min prty  buildings.prty

//    private Short m_type_code;          // new - applications.statement_type (smallint - 1,2)
//                                        // тип заявления (Прием, перевод)
    private String m_type_code;         // new - applications.statement_type (smallint - 1,2) ?? нужен код
                                        // Old - InqryChldInUch.id_type_inqry  CDict75InqryType.Code


    private Date m_inuch_dt,        // желаемая дата зачисления applications.d_plan
                 m_enter_queue_dt,  // queue_info.d_enter - (CDATETIME) Дата, время постановки в очередь
                 m_reg_dt,          // queue_info.d_reg - (CDATETIME) Дата, время постановки на учет.
                                    // Дата первого статуса "Принято" заявления
                 m_childBirthDt,    // applications.d_birth (DATE) - Дата рождения ребенка
                 m_status_set_date; // InqryStatus.dt -> status.d_status - (CDATETIME) Дата присвоения статуса
    
    private String m_num,           // номер заявления applications.num
                   m_status_code,   // Old - InqryStatus.id_status   CDict76InqryStatus.Code
                                    // New - Status.statuses_id  statuses. ?? нужен код

                   m_grp_type_code, // Old - InqryChldInUch.id_dou_grp_time  CDict85DouGrpTime.Code
                                    // New - applications.grp_time_csp - ссылка на spr_b (?? поле для кода)
                                    // этот вариант теперь не используется
                                    // ===== рабочий вариант - Время пребывания в группе =====
                                    // app.grp_time.grp_time_csp (app_id) -> public.spr_b.sp (cname) ?? Нет кода в справочнике

                   m_health_needs_code, // Old - InqryChldInUch.id_health_needs   CDict08TypeClass.Code
                                        // New - applications.health_csp - ссылка на spr_b (?? поле для кода)
                                        // Подтвержденная потребность по здоровью
                   m_health_needs_parent_code;  // Old - CDict08TypeClass.id_parent   CDict08TypeClass.Code
                                                // New - spr_b.spra_id    spr_b.sp (?? поле для кода)

    private Collection<String> m_lgots,
                               m_lgots_types,
                               m_grp_times;     // Old - (LIST)InqryDouGrpTime.id__dou_grp_time  CDict85DouGrpTime.Code
                                                // New - (LIST)Grp_time.grp_time_csp - ссылка на spr_b (?? поле для кода)
    
    private boolean m_from_pgu,           
                    m_have_refused_status;      // true - не явился или отказ (определяется подзапросом)
    
    public InqryInf() {
    }

    public Integer getId() {
        return m_id;
    }
    public void setId(Integer p_id) {
        m_id = p_id; 
    }
    
    @Override
    public Integer getIdUch() {
        return m_id_uch;
    }
    public void setIdUch(Integer p_id_uch) {
        m_id_uch = p_id_uch; 
    } 
    
    public String getNum() {
        return m_num; 
    }
    public void setNum(String p_num) {
        m_num = p_num; 
    }
    
    public Date getInUchDt() {
        return m_inuch_dt;
    }
    public void setInUchDt(Date inuch_dt) {
        this.m_inuch_dt = inuch_dt;
    }       

    public Date getEnterQueueDt() {
        return m_enter_queue_dt;
    }
    public void setEnterQueueDt(Date p_enter_queue_dt) {
        this.m_enter_queue_dt = p_enter_queue_dt;
    }        

    public Date getRegDt() {
        return m_reg_dt;
    }

    public void setRegDt(Date p_reg_dt) {
        this.m_reg_dt = p_reg_dt;
    }           
    
    @Override
    public Date getBdt() {
        return m_childBirthDt;
    }
    public void setBdt(Date p_bdt) {
        this.m_childBirthDt = p_bdt;
    }        
    
    public String getTypeCode() {
        return m_type_code;
    }    
    public void setTypeCode(String p_code) {
        m_type_code = p_code;
    }    

    public String getStatusCode() {
        return m_status_code;
    }
    public void setStatusCode(String p_status_code) {
        this.m_status_code = p_status_code;
    }

    public Date getStatusSetDate() {
        return m_status_set_date;
    }
    public void setStatusSetDate(Date p_status_set_date) {
        this.m_status_set_date = p_status_set_date;
    }

    public String getGrpTypeCode() {
        return m_grp_type_code;
    }    
    public void setGrpTypeCode(String p_grp_type_code) {
        m_grp_type_code = p_grp_type_code;
    }

    public String getHealthNeedsCode() {
        return m_health_needs_code;
    }
    public void setHealthNeedsCode(String p_health_needs_code) {
        this.m_health_needs_code = p_health_needs_code;
    }

    public String getHealthNeedsRootCode() {
        return m_health_needs_parent_code;
    }
    public void setHealthNeedsRootCode(String p_health_needs_parent_code) {
        this.m_health_needs_parent_code = p_health_needs_parent_code;
    }       

    public boolean isFromPortal() {
        return m_from_pgu;
    }    
    public void setFromPortal(boolean p_from_portal) {
        m_from_pgu = p_from_portal;
    }
    
    public boolean isHaveRefusedStatus() {
        return m_have_refused_status;
    }
    public void setHaveRefusedStatus(boolean p_have_refused_status) {
        m_have_refused_status = p_have_refused_status;
    }

    public Collection<String> getLgots() {
        return m_lgots;
    }
    public void setLgots(Collection<String> p_lgots) {
        this.m_lgots = p_lgots;
    }

    public Collection<String> getGrpTimes() {
        return m_grp_times;
    }
    public void setGrpTimes(Collection<String> p_grp_times) {
        this.m_grp_times = p_grp_times;
    }

    public Collection<String> getLgotsType() {
        return m_lgots_types;
    }
    public void setLgotsType(Collection<String> p_lgots_types) {
        this.m_lgots_types = p_lgots_types;
    }
    
    @Override
    public int getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public Short getPriorityCount() {
        return priorityCount;
    }

    public void setPriorityCount(Short priorityCount) {
        this.priorityCount = priorityCount;
    }

    public Short getMinPriority() {
        return minPriority;
    }

    public void setMinPriority(Short minPriority) {
        this.minPriority = minPriority;
    }

}
