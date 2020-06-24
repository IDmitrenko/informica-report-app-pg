package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.UchInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UchMapper implements RowMapper<UchInf> {

    @Override
    public UchInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        UchInf uchInf = new UchInf();
        uchInf.setId(rs.getInt("id"));
        uchInf.setCode(rs.getString("code"));
        uchInf.setName(rs.getString("name"));
        uchInf.setShortName(rs.getString("shortName"));
        uchInf.setIdTer(rs.getInt("idTer"));
        uchInf.setTerName(rs.getString("terName"));
        uchInf.setChief(rs.getString("chief"));
        uchInf.setMunicipObrOktmo(rs.getString("municipObrOktmo"));
        uchInf.setEpguLink(rs.getString("epguLink"));
        uchInf.setRpguLink(rs.getString("rpguLink"));
        uchInf.setWorkDays(rs.getShort("workDays"));
        if (rs.getTime("timeFrom") != null) {
            uchInf.setWorkFrom(rs.getTime("timeFrom").toString());
        }
        if (rs.getTime("timeTo") != null) {
            uchInf.setWorkTo(rs.getTime("timeTo").toString());
        }
        uchInf.setMealServingType(rs.getShort("mealServingType"));
        uchInf.setOrgLegalFormName(rs.getString("orgLegalFormName"));
        uchInf.setOrgLegalFormCode(rs.getString("orgLegalFormCode"));
        uchInf.setStatusName(rs.getString("statusName"));
        uchInf.setStatusСode(rs.getString("statusCode"));
        uchInf.setAddEducation(rs.getString("addEducation"));
        uchInf.setFeatures(rs.getString("features"));
        uchInf.setFiasHouseGuid(rs.getString("fiasHouseGuid"));
        uchInf.setAddrKladr(rs.getString("addrKladr"));
        uchInf.setStructureName(rs.getString("structureName"));
        uchInf.setStructureCode(rs.getString("structureCode"));

        return uchInf;
    }
}
