package ru.avers.informica.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.entities.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StatusMapper implements RowMapper<Status> {

    @Override
    public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
        Status status = new Status();
        status.setAppId(rs.getLong("app_id"));
        status.setId(rs.getLong("id_status"));
        status.setStatusesId(rs.getLong("statuses_id"));
        return status;
    }
}
