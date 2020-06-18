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
        uchInf.setIdUch(rs.getInt("uch_id"));
        uchInf.setNum(rs.getString("num"));
        uchInf.setInUchDt(rs.getDate("dtPlan"));
        uchInf.setBdt(rs.getDate("dtBirth"));
        uchInf.setEnterQueueDt(rs.getTimestamp("enterQueueDt"));
        uchInf.setRegDt(rs.getTimestamp("regDt"));
        uchInf.setGrpTypeCode(rs.getString("grpTypeCode"));
        uchInf.setHealthNeedsCode(rs.getString("healthNeedsCode"));
        uchInf.setHealthNeedsRootCode(rs.getString("healthNeedsRootCode"));
        uchInf.setStatusCode(rs.getString("statusCode"));
        uchInf.setStatusSetDate(rs.getTimestamp("statusSetDate"));
        uchInf.setTypeCode(String.valueOf(rs.getShort("typeInqry")));
        uchInf.setPriorityCount(rs.getShort("uch_prty"));
        uchInf.setMinPriority(rs.getShort("uch_minprty"));
        uchInf.setHaveRefusedStatus(rs.getBoolean("haveRefusedStatus"));
        return uchInf;
    }
}
