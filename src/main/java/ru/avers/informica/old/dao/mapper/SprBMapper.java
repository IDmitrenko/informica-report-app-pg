package ru.avers.informica.old.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.entities.SprB;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SprBMapper implements RowMapper<SprB> {

    @Override
    public SprB mapRow(ResultSet rs, int rowNum) throws SQLException {
        SprB sprB = new SprB();
        sprB.setId(rs.getLong("id"));
        sprB.setDesc(rs.getString("descr"));
        return sprB;
    }
}
