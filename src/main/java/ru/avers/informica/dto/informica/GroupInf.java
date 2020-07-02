package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация для тэга group
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupInf {
    private int id;            // id группы

    private String idCode,     // код группы (id)
            name,              // наименование (name)
            ageFrom,           // возраст от (age_from)
            ageTO,             // возраст до (age_to)
            orientation,       // ... (orientation)
            worktimeGroup,     // ... (worktime_group)
            activity,          // ... (activity)
            capacity,          // ... (capacity)
            enrolled,          // ... (enrolled)
            subgroup,          // ... (subgroup)
            ovzDeti,           // ... (ovz_deti)
            freeSpace,         // ... (free_space)
            addCont,           // ... (add_cont)
            transferSpace,     // ... (transfer_space)
            partnerGroup,      // ... (partner_group)
            partner,           // ... (partner)
            days,              // ... (days)
            educator,          // ... (educator)
            invalid,           // ... (invalid)
            size,              // ... (size)
            program,           // ... (program)
            reductionOther,    // ... (reduction_other)
            reductionSchool,   // ... (reduction_school)
            addContOvz,        // ... (add_cont_ovz)
            addContGkp,        // ... (add_cont_gkp)
            enrolledGkp,       // ... (enrolled_gkp)
            capacityGkp,       // ... (capacity_gkp)
            programOvz;        // ... (program_ovz)

}
