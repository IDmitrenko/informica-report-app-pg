package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryInd20_1Inf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class InqryInd20_1Mapper implements RowMapper<InqryInd20_1Inf> {

    @Override
    public InqryInd20_1Inf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryInd20_1Inf inqryInd20_1Inf = new InqryInd20_1Inf();
        inqryInd20_1Inf.setCount(rs.getInt("cntInqry"));
        inqryInd20_1Inf.setIdUch(rs.getInt("idUch"));
        inqryInd20_1Inf.setBdt(rs.getDate("birthDt"));

        return inqryInd20_1Inf;
    }
}
