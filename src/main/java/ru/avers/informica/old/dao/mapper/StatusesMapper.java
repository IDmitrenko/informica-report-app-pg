package ru.avers.informica.old.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.entities.Statuses;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StatusesMapper implements RowMapper<Statuses> {

    @Override
    public Statuses mapRow(ResultSet rs, int rowNum) throws SQLException {
        Statuses statuses = new Statuses();
        statuses.setId(rs.getLong("id"));
        statuses.setDesc(rs.getString("descr"));
        return statuses;
    }
}
