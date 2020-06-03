package ru.avers.informica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ApplicationsEntity {
    private Long id_app;                // id заявления
    private String num;                 // номер заявления
    private Date d_plan;                // желаемая дата поступления
    private Date d_birth;               // дата рождения ребенка
//    private QueueInfo queueInfo;        // Таблица времени вхождения заявления в очередь (INQRY_QUEUE_INFO)
    private List<Status> statusList;    // Статусы заявления (INQRYSTATUS)
//    private StatementType id_statementType; // Тип заявления (V_DICT_75_INQRY_TYPE)
//    private List<Buildings> buildingsList;  // Организации (INQRYUCH)
//    private ClassType classType;        // Тип класса (CDict08TypeClass)
//    private ResidenceTime residenceTime;    // Группа ДОУ: время пребывания (CDict85DouGrpTime)



}
