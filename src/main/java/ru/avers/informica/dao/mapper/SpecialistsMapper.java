package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.SpecialistsInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class SpecialistsMapper implements RowMapper<SpecialistsInf> {

    @Override
    public SpecialistsInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        SpecialistsInf specialistsInf = new SpecialistsInf();
        specialistsInf.setIdUch(rs.getLong("id"));
        specialistsInf.setUchCode(rs.getString("code"));
        specialistsInf.setNumSPsychologist(rs.getString("num_s_psychologist"));
        specialistsInf.setNumFPsychologist(rs.getString("num_f_psychologist"));
        specialistsInf.setNumSLogopedist(rs.getString("num_s_logopedist"));
        specialistsInf.setNumFLogopedist(rs.getString("num_f_logopedist"));
        specialistsInf.setNumSDefectologist(rs.getString("num_s_defectologist"));
        specialistsInf.setNumFDefectologist(rs.getString("num_f_defectologist"));
        specialistsInf.setNumSOligophren(rs.getString("num_s_oligophren"));
        specialistsInf.setNumFOligophren(rs.getString("num_f_oligophren"));
        specialistsInf.setNumSSurdo(rs.getString("num_s_surdo"));
        specialistsInf.setNumFSurdo(rs.getString("num_f_surdo"));
        specialistsInf.setNumSTiflo(rs.getString("num_s_tiflo"));
        specialistsInf.setNumFTiflo(rs.getString("num_f_tiflo"));
        specialistsInf.setNumSLfk(rs.getString("num_s_lfk"));
        specialistsInf.setNumFLfk(rs.getString("num_f_lfk"));
        specialistsInf.setNumSAfk(rs.getString("num_s_afk"));
        specialistsInf.setNumFAfk(rs.getString("num_f_afk"));
        specialistsInf.setNumSSocial(rs.getString("num_s_social"));
        specialistsInf.setNumFSocial(rs.getString("num_f_social"));
        specialistsInf.setNumSMed(rs.getString("num_s_med"));
        specialistsInf.setNumFMed(rs.getString("num_f_med"));
        specialistsInf.setNumSPediatr(rs.getString("num_s_pediatr"));
        specialistsInf.setNumFPediatr(rs.getString("num_f_pediatr"));
        specialistsInf.setNumSNeurolog(rs.getString("num_s_neurolog"));
        specialistsInf.setNumFNeurolog(rs.getString("num_f_neurolog"));
        specialistsInf.setNumSOphthalmologist(rs.getString("num_s_ophthalmologist"));
        specialistsInf.setNumFOphthalmologist(rs.getString("num_f_ophthalmologist"));
        specialistsInf.setNumSAudiologist(rs.getString("num_s_audiologist"));
        specialistsInf.setNumFAudiologist(rs.getString("num_f_audiologist"));

        return specialistsInf;
    }
}
