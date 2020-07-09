package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class InqryInf implements IInformicaChildCountable {
    private Integer count = 1;
    private Integer id,               // id applications (заявления)
                    idUch;            // id (InqryUch.id_uch - ссылка на таблицу Uch
                                      // нужно брать учреждение с минимальным значением prty
                                      // id = buildings.uch_buildings_id (prty)
                                      // - ссылка на таблицу uch_buildings
                                      // uch_buildings.uch - ссылка на таблицу uch (Основная информация)
    private Short priorityCount = 0;  // Old - InqryUch.prty
                                      // New - buildings.prty
    private Short minPriority = 0;    // min prty  buildings.prty

//    private Short typeCode;         // new - applications.statement_type (smallint - 1,2)
//                                    // тип заявления (Прием, перевод)
    private String typeCode;          // new - applications.statement_type (smallint - 1,2) ?? нужен код
                                      // Old - InqryChldInUch.id_type_inqry  CDict75InqryType.Code

    private Date inUchDt,        // желаемая дата зачисления applications.d_plan
                 enterQueueDt,   // queue_info.d_enter - (CDATETIME) Дата, время постановки в очередь
                 regDt,          // queue_info.d_reg - (CDATETIME) Дата, время постановки на учет.
                                 // Дата первого статуса "Принято" заявления
                 bDt,            // applications.d_birth (DATE) - Дата рождения ребенка
                 statusSetDate;  // InqryStatus.dt -> status.d_status - (CDATETIME) Дата присвоения статуса
    
    private String num,           // номер заявления applications.num
                   statusCode,    // Old - InqryStatus.id_status   CDict76InqryStatus.Code
                                  // New - Status.statuses_id  statuses. ?? нужен код

                   grpTypeCode,   // Old - InqryChldInUch.id_dou_grp_time  CDict85DouGrpTime.Code
                                  // New - applications.grp_time_csp - ссылка на spr_b (?? поле для кода)
                                  // этот вариант теперь не используется
                                  // ===== рабочий вариант - Время пребывания в группе =====
                                  // app.grp_time.grp_time_csp (app_id) -> public.spr_b.sp (cname) ?? Нет кода в справочнике

                   healthNeedsCode, // Old - InqryChldInUch.id_health_needs   CDict08TypeClass.Code
                                    // New - applications.health_csp - ссылка на spr_b (?? поле для кода)
                                    // Подтвержденная потребность по здоровью
                   healthNeedsRootCode;  // Old - CDict08TypeClass.id_parent   CDict08TypeClass.Code
                                         // New - spr_b.spra_id    spr_b.sp (?? поле для кода)

    private Collection<String> lgots,
                               lgotsType,
                               grpTimes;     // Old - (LIST)InqryDouGrpTime.id__dou_grp_time  CDict85DouGrpTime.Code
                                             // New - (LIST)Grp_time.grp_time_csp - ссылка на spr_b (?? поле для кода)
    
    private boolean fromPortal,
                    haveRefusedStatus;       // true - не явился или отказ (определяется подзапросом)

    @Override
    public Integer getIdUch() {
        return idUch;
    }
    public void setIdUch(Integer pIdUch) {
        idUch = pIdUch;
    } 
    
    @Override
    public Date getBdt() {
        return bDt;
    }
    public void setBdt(Date pBdt) {
        this.bDt = pBdt;
    }        
    
    @Override
    public int getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

}
