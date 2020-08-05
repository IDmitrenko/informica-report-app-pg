package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Информация для тэга group
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupInf {
    private int id;            // id группы
    private int idUch;         // id учреждения
    private int idBuilding;    // id здания

    private String idCode,     // код группы (id)
            name,              // наименование (name)
            orientation,       // Направленность группы (orientation)
            worktimeGroup,     // Режим работы группы (worktime_group)
            activity,          // Вид деятельности (activity)
            capacity,          // Нормативная емкость группы (capacity)
            enrolled,          // Фактическая наполняемость группы (enrolled)
            subgroup,          // Количество подгрупп (subgroup)
            ovzDeti,           // Количество детей с ОВЗ в группе (ovz_deti)
// потребуется дополнительная обработка, будет поставлена отдельная задача
            freeSpace,         // Количество свободных мест в группе (free_space)
// потребуется дополнительная обработка, необходимо вычислять по формуле
// "free_space = capacity - enrolled - transfer_space - add_cont"
            addCont,           // Количество мест, на которые направлены дети (add_cont)
// потребуется дополнительная обработка, поставлена задача 23229
            transferSpace,     // Количество мест для перевода детей (transfer_space)
            partnerGroup,      // Получение образования в другой ДОО (partner_group)
            partner,           // Иное место получения образования (partner)
            days,              // Число дней, проведенных воспитанниками в группе в текущем месяце (days)
            educator,          // Количество воспитателей в группе (educator)
            invalid,           // Количество детей-инвалидов в группе (invalid)
            size,              // Площадь групповой ячейки (size)
            program,           // В группе осуществляется реализация основной общеобразовательной
                               // программы дошкольного образования (program)
            reductionOther,    // Прогнозируемое уменьшение контингента воспитанников в
                               // связи с их переходом в другие ДОО (reduction_other)
            reductionSchool,   // Прогнозируемое уменьшение контингента воспитанников в
                               // связи с их переходом в школу  (reduction_school)
            addContOvz,        // Количество мест, на которые направлены дети с ОВЗ (add_cont_ovz)
            addContGkp,        // Количество мест, на которые направлены дети на
                               // кратковременный режим пребывания (add_cont_gkp)
            enrolledGkp,       // Численность детей, посещающих группу в режиме
                               // кратковременного пребывания  (enrolled_gkp)
            capacityGkp,       // Количество мест в группе для детей с кратковременным
                               // режимом пребывания (capacity_gkp)
            programOvz;        // В группе осуществляется реализация адаптированной основной
                               // общеобразовательной программы дошкольного образования (program_ovz)
//TODO в схеме есть, в полном отчете нет!
    private String ovzType,     // Тип группы с ОВЗ сводный (ovz_type)
            wellness;           // Профиль оздоровительной группы (wellness)
//TODO - добавленные Вовой
    private String ovzTypeDop,  // Тип группы с ОВЗ (ovz_type_dop)
            ovzTypeNew;         // Дополнительный тип группы с ОВЗ (ovz_type_new)

    private Short ageFromYears, // количество лет - Возрастной диапазон группы (от) (age_from_years)
            ageFromMonths,      // количество месяцев - Возрастной диапазон группы (от) (age_from_months)
            ageToYears,         // количество лет - Возрастной диапазон группы (до) (age_to_years)
            ageToMonths;        // количество месяцев - Возрастной диапазон группы (до) (age_to_months)

    private BigDecimal ageFrom, // возраст от (age_from)
            ageTO;              // возраст до (age_to)

}
