package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryInd19_3Inf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class InqryInd19_3Mapper implements RowMapper<InqryInd19_3Inf> {

    @Override
    public InqryInd19_3Inf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryInd19_3Inf inqryInd19_3Inf = new InqryInd19_3Inf();
        inqryInd19_3Inf.setCount(rs.getInt("cntInqry"));
        inqryInd19_3Inf.setIdUch(rs.getInt("idUch"));
        inqryInd19_3Inf.setBdt(rs.getDate("birthDt"));

        return inqryInd19_3Inf;
    }
}
