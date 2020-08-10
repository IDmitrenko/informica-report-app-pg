package ru.avers.informica.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryGrpTimeInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class InqryGrpTimesMapper implements RowMapper<InqryGrpTimeInf> {

    @Override
    public InqryGrpTimeInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryGrpTimeInf inqryGrpTime = new InqryGrpTimeInf();
        inqryGrpTime.setId(rs.getInt("id"));
        inqryGrpTime.setIdGrpTime(rs.getInt("idGrpTimeCsp"));
        inqryGrpTime.setGrpTimeCode(rs.getString("grpTimeCode"));
        return inqryGrpTime;
    }
}
