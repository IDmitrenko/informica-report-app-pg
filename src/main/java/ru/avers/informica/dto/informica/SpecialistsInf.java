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

    private Long idUch;                 // id учреждения
    private String uchCode;             // код учреждения

    private String numSPsychologist,    // количество штатных педагогов-психологов (num_s_psychologist)
            numFPsychologist,           // количество внештатных педагогов-психологов (num_f_psychologist)
            numSLogopedist,             // количество штатных учителей-логопедов (num_s_logopedist)
            numFLogopedist,             // количество внештатных учителей-логопедов (num_f_logopedist)
            numSDefectologist,          // количество штатных учителей-дефектологов (num_s_defectologist)
            numFDefectologist,          // количество внештатных учителей-дефектологов (num_f_defectologist)
            numSOligophren,             // количество штатных олигофрено-педагогов (num_s_oligophren)
            numFOligophren,             // количество внештатных олигофрено-педагогов (num_f_oligophren)
            numSSurdo,                  // количество штатных сурдопедагогов (num_s_surdo)
            numFSurdo,                  // количество внештатных сурдопедагогов (num_f_surdo)
            numSTiflo,                  // количество штатных тифлопедагогов (num_s_tiflo)
            numFTiflo,                  // количество внештатных тифлопедагогов (num_f_tiflo)
            numSLfk,                    // количество штатных инструкторов-методистов ЛФК (num_s_lfk)
            numFLfk,                    // количество внештатных инструкторов-методистов ЛФК (num_f_lfk)
            numSAfk,                    // количество штатных инструкторов по АФК (num_s_afk)
            numFAfk,                    // количество внештатных инструкторов по АФК (num_f_afk)
            numSSocial,                 // количество штатных социальных педагогов (num_s_social)
            numFSocial,                 // количество внештатных социальных педагогов (num_f_social)
            numSMed,                    // количество штатных медицинских работников (num_s_med)
            numFMed,                    // количество внештатных медицинских работников (num_f_med)
            numSPediatr,                // количество штатных врачей-педиатров (num_s_pediatr)
            numFPediatr,                // количество внештатных врачей-педиатров (num_f_pediatr)
            numSNeurolog,               // количество штатных врачей-неврологов (num_s_neurolog)
            numFNeurolog,               // количество внештатных врачей-неврологов (num_f_neurolog)
            numSOphthalmologist,        // количество штатных врачей-офтальмологов (num_s_ophthalmologist)
            numFOphthalmologist,        // количество внештатных врачейофтальмологов (num_f_ophthalmologist)
            numSAudiologist,            // количество штатных врачей-сурдологов (num_s_audiologist)
            numFAudiologist;            // количество внештатных врачей-сурдологов (num_f_audiologist)
}
