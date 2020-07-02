package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация для тэга advisory_centr
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class AdvisoryCentrInf {
    private String fact,            // ... (fact)
            numChildNotEdu_7_8,     // ... (num_child_not_edu_7_8)
            numChildNotEdu_3_7,     // ... (num_child_not_edu_3_7)
            numChildNotEdu_0_3,     // ... (num_child_not_edu_0_3)
            numChildFamily_7_8,     // ... (num_child_family_7_8)
            numChildFamily_3_7,     // ... (num_child_family_3_7)
            numChildFamily_15_3,    // ... (num_child_family_15_3)
            numChildFamily_0_15,    // ... (num_child_family_0_15)
            numChild,               // ... (num_child)
            numParentNotEdu_7_8,    // ... (num_parent_not_edu_7_8)
            numParentNotEdu_3_7,    // ... (num_parent_not_edu_3_7)
            numParentNotEdu_0_3,    // ... (num_parent_not_edu_0_3)
            numParentFamily_7_8,    // ... (num_parent_family_7_8)
            numParentFamily_3_7,    // ... (num_parent_family_3_7)
            numParentFamily_15_3,   // ... (num_parent_family_15_3)
            numParentFamily_0_15,   // ... (num_parent_family_0_15)
            numParent,              // ... (num_parent)
            numFreelancer,          // ... (num_freelancer)
            numStaffMember,         // ... (num_staff_member)
            forma_4,                // ... (forma_4)
            forma_3,                // ... (forma_3)
            forma_2,                // ... (forma_2)
            forma_1,                // ... (forma_1)
            numHitsDistant,         // ... (num_hits_distant)
            numHitsPersonally;      // ... (num_hits_personally)

}
