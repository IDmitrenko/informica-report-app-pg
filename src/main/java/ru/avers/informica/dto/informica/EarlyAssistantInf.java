package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация для тэга early_assistant
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class EarlyAssistantInf {
    private String fact,            // ... (fact)
//TODO в схеме есть, в полном отчете нет!
            numHitsPersonally,      // ... (num_hits_personally)
            numHitsDistant,         // ... (num_hits_distant)
            forma_1,                // ... (forma_1)
            forma_2,                // ... (forma_2)
            forma_3,                // ... (forma_3)
            forma_4,                // ... (forma_4)
            numParent_0_3,          // ... (num_parent_0_3)
            numParent_3_8,          // ... (num_parent_3_8)
            numChild_0_3,           // ... (num_child_0_3)
            numChild_3_8;           // ... (num_child_3_8)

}
