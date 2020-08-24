package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryInd8Inf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class InqryInd8Mapper implements RowMapper<InqryInd8Inf> {

    @Override
    public InqryInd8Inf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryInd8Inf inqryInd8Inf = new InqryInd8Inf();
        inqryInd8Inf.setIdInqry(rs.getInt("id"));
        inqryInd8Inf.setIdUch(rs.getInt("idUch"));
        inqryInd8Inf.setWishDt(rs.getDate("wishDt"));
        inqryInd8Inf.setBirthDt(rs.getDate("birthDt"));
        inqryInd8Inf.setStsDt(rs.getDate("stsDt"));
        inqryInd8Inf.setLgot(rs.getBoolean("lgot"));
        inqryInd8Inf.setOvz(rs.getBoolean("ovz"));

        return inqryInd8Inf;
    }
}
