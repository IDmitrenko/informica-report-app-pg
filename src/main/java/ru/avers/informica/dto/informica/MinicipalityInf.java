package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация по муниципалитету и тэг municipality
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class MinicipalityInf {

    private String Oktmo,   // ОКТМО муниципалитета (oktmo)
            epguLink,       // ссылка на ЕПГУ (epgu_link)
            rpguLink,       // ссылка на Региональный ПГУ (rpgu_link)
            nameMouo,       // наименование муниципалитета (name_mouo)
            addressMouo,    // адрес муниципалитета (address_mouo)
            siteMouo,       // сайт  (site_mouo)
            emailMouo,      // электронная почта (email_mouo)
            phonesMouo,     // телефон (phones_mouo)
            regulation,     // ... (regulation)
            fixArea,        // ... (fix_area)
            timeMouo,       // ... (time_mouo)
            maxDoo,         // ... (max_doo)
            numAdvisoryCentr,         // ... (num_advisory_centr)
            numEarlyAssistance;       // ... (num_early_assistance)

    private Integer idTer,      // id муниципалитета
            noDooAct_0_3,       //
            noDooAct_3_7,       //
            noDooDef_0_3,       //
            noDooDef_3_7,       //  данные перенесенные
            medic_0_3,          //  из учреждений
            medic_3_7,          //
            family_0_3,         //
            family_3_7;         //
            
}
