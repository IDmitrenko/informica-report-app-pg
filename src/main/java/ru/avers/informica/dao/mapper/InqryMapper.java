package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class InqryMapper implements RowMapper<InqryInf> {

    @Override
    public InqryInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryInf inqryInf = new InqryInf();
        inqryInf.setId(rs.getInt("id"));
        inqryInf.setIdUch(rs.getInt("uch_id"));
        inqryInf.setNum(rs.getString("num"));
        inqryInf.setInUchDt(rs.getDate("dtPlan"));
        inqryInf.setBdt(rs.getDate("dtBirth"));
        inqryInf.setEnterQueueDt(rs.getTimestamp("enterQueueDt"));
        inqryInf.setRegDt(rs.getTimestamp("regDt"));
        inqryInf.setGrpTypeCode(rs.getString("grpTypeCode"));
        inqryInf.setHealthNeedsCode(rs.getString("healthNeedsCode"));
//        inqryInf.setHealthNeedsRootCode(rs.getString("healthNeedsRootCode"));
        inqryInf.setStatusCode(rs.getString("statusCode"));
        inqryInf.setStatusSetDate(rs.getTimestamp("statusSetDate"));
        inqryInf.setTypeCode(String.valueOf(rs.getShort("typeInqry")));
        inqryInf.setPriorityCount(rs.getShort("uch_prty"));
        inqryInf.setMinPriority(rs.getShort("uch_minprty"));
        inqryInf.setHaveRefusedStatus(rs.getBoolean("haveRefusedStatus"));
        inqryInf.setFromPortal(rs.getBoolean("from_portal"));
        return inqryInf;
    }
}
