package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.impl.StatusesDaoImpl;
import ru.avers.informica.entities.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class StatusMapper implements RowMapper<Status> {

    private final StatusesDaoImpl statusesDao;

    @Override
    public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
        Status status = new Status();
        status.setAppId(rs.getLong("app_id"));
        status.setId(rs.getLong("id_status"));
        status.setStatusesId(rs.getLong("statuses_id"));
        status.setStatuses(statusesDao.getStatus(status.getStatusesId()));
        return status;
    }
}
