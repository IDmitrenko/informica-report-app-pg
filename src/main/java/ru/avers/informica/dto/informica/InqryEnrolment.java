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
public class InqryEnrolment {
    private int id,             // id applications (заявления)
                idUch;          // id (InqryUch.id_uch - ссылка на таблицу Uch
                                // id = buildings.uch_buildings_id
                                // - ссылка на таблицу uch_buildings
                                // uch_buildings.uch - ссылка на таблицу uch (Основная информация)

    private Integer idHealthCsp,    // id направленности группы
                    idGrpTimeCsp;   // id режима посещения группы

    private String uchCode;         // код учреждения

    private Date bDt;           // applications.d_birth (DATE) - Дата рождения ребенка

    private String statusCode;  // Old - InqryStatus.id_status   CDict76InqryStatus.Code
                                // New - Status.statuses_id  statuses.code

}
