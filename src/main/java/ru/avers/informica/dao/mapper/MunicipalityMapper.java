package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.MunicipalityInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class MunicipalityMapper implements RowMapper<MunicipalityInf> {

    @Override
    public MunicipalityInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        MunicipalityInf municipalityInf = new MunicipalityInf();
        municipalityInf.setIdTer(rs.getInt("id"));
        municipalityInf.setRegulation(rs.getString("regulation"));
        municipalityInf.setOktmo(rs.getString("oktmo"));
        municipalityInf.setNumEarlyAssistance(rs.getString("num_early_assistance"));
        municipalityInf.setNumAdvisoryCentr(rs.getString("num_advisory_centr"));
        municipalityInf.setMaxDoo(rs.getString("max_doo"));
        municipalityInf.setFixArea(rs.getString("fix_area"));
        municipalityInf.setTimeMouo(rs.getString("time_mouo"));
        municipalityInf.setPhonesMouo(rs.getString("phones_mouo"));
        municipalityInf.setEmailMouo(rs.getString("email_mouo"));
        municipalityInf.setSiteMouo(rs.getString("site_mouo"));
        municipalityInf.setAddressMouo(rs.getString("address_mouo"));
        municipalityInf.setNameMouo(rs.getString("name_mouo"));
        municipalityInf.setRpguLink(rs.getString("rpgu_link"));
        municipalityInf.setEpguLink(rs.getString("epgu_link"));

        return municipalityInf;
    }
}
