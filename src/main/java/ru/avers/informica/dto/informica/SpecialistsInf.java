package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация для тэга specialists
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class SpecialistsInf {

    private String numSPsychologist,    // ... (num_s_psychologist)
            numFPsychologist,           // ... (num_f_psychologist)
            numSLogopedist,             // ... (num_s_logopedist)
            numFLogopedist,             // ... (num_f_logopedist)
            numSDefectologist,          // ... (num_s_defectologist)
            numFDefectologist,          // ... (num_f_defectologist)
            numSOligophren,             // ... (num_s_oligophren)
            numFOligophren,             // ... (num_f_oligophren)
            numSSurdo,                  // ... (num_s_surdo)
            numFSurdo,                  // ... (num_f_surdo)
            numSTiflo,                  // ... (num_s_tiflo)
            numFTiflo,                  // ... (num_f_tiflo)
            numSLfk,                    // ... (num_s_lfk)
            numFLfk,                    // ... (num_f_lfk)
            numSAfk,                    // ... (num_s_afk)
            numFAfk,                    // ... (num_f_afk)
            numSSocial,                 // ... (num_s_social)
            numFSocial,                 // ... (num_f_social)
            numSMed,                    // ... (num_s_med)
            numFMed,                    // ... (num_f_med)
            numSPediatr,                // ... (num_s_pediatr)
            numFPediatr,                // ... (num_f_pediatr)
            numSNeurolog,               // ... (num_s_neurolog)
            numFNeurolog,               // ... (num_f_neurolog)
            numSOphthalmologist,        // ... (num_s_ophthalmologist)
            numFOphthalmologist,        // ... (num_f_ophthalmologist)
            numSAudiologist,            // ... (num_s_audiologist)
// TODO этого поля нет в XSD схеме
            numFAudiologist;            // ... (num_f_audiologist)
}
