package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TimeZone;

/**
 * Информация по учреждению и тэг organization
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class UchInf {
    private int id;               // id учреждения

    private String code,          // код учреждения (code)
            name,          // наименование учреждения (name)
            shortName,     // краткое наименование учреждения
            terName,       // наименование территории
            chief,         // ФИО директора, заведующего (director)
            municipObrOktmo,   // ОКТМО учреждения
            epguLink,   // ссылка на ЕПГУ
            rpguLink,   // ссылка на Региональный ПГУ
            workTime,   // дни и время работы учреждения (worktime)
            fiasOrgGuid,        // юридический адрес через код здания ФИАС (fias_org_guid)
            addrKladr,          // юридический адрес по правилам КЛАДР (org_address)
            orgLegalFormName,   // наименование типа собственности
            orgLegalFormCode,   // код типа собственности (type)
            statusName,         // статус организации
            statusСode,         // код статуса организации (status)
            addEducation,   // перечень дополнительных образовательных услуг (additional_education)
            features,       // особенности образовательной деятельности (feature)
            structureName,  // организационная структура
            structureCode,  // код организационной структуры (structure)
            license,        // наличие лицензии на ведение образовательной деятельности
                            // (1 - да, 2 - нет)  (license)
            partnerDoo,     // наличие договора на оказание образовательных услуг с другой ДОО
                            // (1 - да, 2 - нет)  (partner_doo)
            typeArea,       // тип местности (1 - город, 2 - сельская) (type_area)
            mealServingType,    // тип питания (meal_serving_type)
            website,        // сайт ДОО (website)
            email,          // электронная почта (email)
            phone,          // телефон (phone)
            numBuilding,    // количество зданий ДОО (num_building)
            numGroup,       // количество дошкольных групп ДОО (num_group)
            numFilial,      // количество структурных подразделений (филиалов) ДОО (num_filial)
            lekoteka,       // сведения о наличии лекотеки (1 - да, 0 - нет) (lekoteka)
            centreGame,     // сведения о наличии центра игровой поддержки ребенка
                            // (1 - да, 0 - нет) (centre_game)
            commetStatus,   // комментарий к статусу организации
            passport;       // паспорт доступности (passport)

    private Integer idTer,
            noDooAct_0_3,       //
            noDooAct_3_7,       //
            noDooDef_0_3,       //
            noDooDef_3_7,       // для учреждения, чтобы потом
            medic_0_3,          //  перенести в municipality
            medic_3_7,          //
            family_0_3,         //
            family_3_7;         //
            
}
