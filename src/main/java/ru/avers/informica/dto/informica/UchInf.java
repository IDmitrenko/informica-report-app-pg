package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TimeZone;

/**
 *
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class UchInf {
    private int id;               // id учреждения

    private String code,          // код учреждения
            name,          // наименование учреждения
            shortName,     // краткое наименование учреждения
            terName,       // наименование территории
            chief,         // ФИО директора, заведующего
            municipObrOktmo,   // ОКТМО учреждения
            epguLink,   // ссылка на ЕПГУ
            rpguLink,   // ссылка на Региональный ПГУ
            workTime,   // дни и время работы учреждения
            fiasHouseGuid,      // код здания ФИАС
            addrKladr,          // адрес по правилам КЛАДР
            orgLegalFormName,   // наименование типа собственности
            orgLegalFormCode,   // код типа собственности
            statusName,         // статус организации
            statusСode,         // код статуса организации
            addEducation,   // перечень дополнительных образовательных услуг
            features,       // особенности образовательной деятельности
            structureName,  // организационная структура
            structureCode;  // код организационной структуры

    private Short workDays,     // количество рабочих дней в неделе
            mealServingType;    // тип питания

    private String workFrom,  // время начала работы
            workTo;             // время окончания работы

    private Integer idTer,
            noDooAct_0_3,       //
            noDooAct_3_7,       //
            noDooDef_0_3,       //
            noDooDef_3_7,       // для учреждения, чтобы потом
            medic_0_3,            //  перенести в municipality
            medic_3_7,            //
            family_0_3,           //
            family_3_7;           //
            
}
