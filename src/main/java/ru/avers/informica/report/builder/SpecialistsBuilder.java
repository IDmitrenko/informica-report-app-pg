package ru.avers.informica.report.builder;

import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.SpecialistsInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.report.xml.Specialists;

@Component
public class SpecialistsBuilder {
    public Specialists build(UchInf uchInf) {

            Specialists sp = new Specialists();
            SpecialistsInf specialist = uchInf.getSpecialistsInf();

            sp.setNum_S_Psychologist(specialist.getNumSPsychologist());
            sp.setNum_F_Psychologist(specialist.getNumFPsychologist());
            sp.setNum_S_Logopedist(specialist.getNumSLogopedist());
            sp.setNum_F_Logopedist(specialist.getNumFLogopedist());
            sp.setNum_S_Defectologist(specialist.getNumSDefectologist());
            sp.setNum_F_Defectologist(specialist.getNumFDefectologist());
            sp.setNum_S_Oligophren(specialist.getNumSOligophren());
            sp.setNum_F_Oligophren(specialist.getNumFOligophren());
            sp.setNum_S_Surdo(specialist.getNumSSurdo());
            sp.setNum_F_Surdo(specialist.getNumFSurdo());
            sp.setNum_S_Tiflo(specialist.getNumSTiflo());
            sp.setNum_F_Tiflo(specialist.getNumFTiflo());
            sp.setNum_S_Lfk(specialist.getNumSLfk());
            sp.setNum_F_Lfk(specialist.getNumFLfk());
            sp.setNum_S_Afk(specialist.getNumSAfk());
            sp.setNum_F_Afk(specialist.getNumFAfk());
            sp.setNum_S_Social(specialist.getNumSSocial());
            sp.setNum_F_Social(specialist.getNumFSocial());
            sp.setNum_S_Med(specialist.getNumSMed());
            sp.setNum_F_Med(specialist.getNumFMed());
            sp.setNum_S_Pediatr(specialist.getNumSPediatr());
            sp.setNum_F_Pediatr(specialist.getNumFPediatr());
            sp.setNum_S_Neurolog(specialist.getNumSNeurolog());
            sp.setNum_F_Neurolog(specialist.getNumFNeurolog());
            sp.setNum_S_Ophthalmologist(specialist.getNumSOphthalmologist());
            sp.setNum_F_Ophthalmologist(specialist.getNumFOphthalmologist());
            sp.setNum_S_Audiologist(specialist.getNumSAudiologist());
            sp.setNum_F_Audiologist(specialist.getNumFAudiologist());

        return sp;
    }

}
