package ru.avers.informica.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.CommonInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommonMapper implements RowMapper<CommonInf> {

    @Override
    public CommonInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommonInf commonInf = new CommonInf();
        commonInf.setId(rs.getInt("id"));
        commonInf.setCnt(rs.getLong("cnt"));
        return commonInf;
    }
}
