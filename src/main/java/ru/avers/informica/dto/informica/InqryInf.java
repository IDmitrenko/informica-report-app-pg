package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class InqryInf implements IInformicaChildCountable, Application {
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
    private String typeCode;          // тип заявления (Прием, перевод)
    // Old - InqryChldInUch.id_type_inqry  CDict75InqryType.Code

    private Date inUchDt,        // желаемая дата зачисления applications.d_plan
            enterQueueDt,   // queue_info.d_enter - (CDATETIME) Дата, время постановки в очередь
            regDt,          // queue_info.d_reg - (CDATETIME) Дата, время постановки на учет.
    // Дата первого статуса "Принято" заявления
    bDt,            // applications.d_birth (DATE) - Дата рождения ребенка
            statusSetDate;  // InqryStatus.dt -> status.d_status - (CDATETIME) Дата присвоения статуса
    /* TODO  нужно реализовать из аудита
        private Date prevInUchDt;   // желаемая дата зачисления на начало текущего учебного года

     */
    private String num,           // номер заявления applications.num
            statusCode,           // Old - InqryStatus.id_status   CDict76InqryStatus.Code
                                  // New - Status.statuses_id  statuses.code
/*                   // старый вариант
                   grpTypeCode,   // Old - InqryChldInUch.id_dou_grp_time  CDict85DouGrpTime.Code
                                  // New - applications.grp_time.grp_time_csp - ссылка на spr_b_fspeo.spare_01
                                  // этот вариант теперь не используется
                                  // ===== рабочий вариант - Время пребывания в группе =====
                                  // app.grp_time.grp_time_csp (app_id) -> public.spr_b_fspeo.spare_01
*/
    healthNeedsCode; // Old - InqryChldInUch.id_health_needs   CDict08TypeClass.Code
    // New - applications.health_csp - ссылка на spr_b_fspeo.spare_01
    // Подтвержденная потребность по здоровью
/* Отказались от использования родительских кодов в конфиге
                   healthNeedsRootCode;  // Old - CDict08TypeClass.id_parent   CDict08TypeClass.Code
                                         // New - spr_b.spra_id    spr_b.sp (?? поле для кода)
*/
/* TODO нужно реализовать из аудита
    private String prevHealthNeedsCode;     // направленность на начало текущего учебного года
 */
    private List<Collection<String>> inqryLgots;   // информация по кодам и типам льгот для заявления
    private Collection<String> lgots,       //app.applications.id_app -> app.benefits.app_id ->           | code
            lgotsType,   // app.benefits.benefit_csp -> app.v_dict_81_inqry_child_lgot | typ
    // Информация по режиму посещения
    grpTimes;    // Old - (LIST)InqryDouGrpTime.id__dou_grp_time  CDict85DouGrpTime.Code
    //app.applications.id_app -> app.grp_time.app_id   ->
    // app.grp_time.grp_time_csp -> app.v_dict_85_dou_grp_time.code

    private boolean fromPortal,
            haveRefusedStatus;      // true - не явился или отказ (определяется подзапросом)

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
