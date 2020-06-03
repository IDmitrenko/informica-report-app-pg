package ru.avers.informica.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.mapper.StatusMapper;
import ru.avers.informica.entities.ApplicationsEntity;
import ru.avers.informica.entities.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationsDaoImpl implements ApplicationsDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ApplicationsEntity> getAllApplications() {
        try {
            return jdbcTemplate.query("select a.id_app as id, a.num as num " +
                            "from app.applications a ",
                    (resultSet, i) -> applicationRowMapper(resultSet, i));
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }

    private ApplicationsEntity applicationRowMapper(ResultSet rs, int i) throws SQLException {
        ApplicationsEntity app = new ApplicationsEntity();
        app.setId_app(rs.getLong("id"));
        app.setNum(rs.getString("num"));
        app.setStatusList(getStatuses(app.getId_app()));
        return app;
    }

    private List<Status> getStatuses(Long id) {
        return jdbcTemplate.query("select s.id_status as id_status, s.app_id as app_id, s.statuses_id as statuses_id " +
                        "from app.status s where s.app_id = ?",
                new StatusMapper(),
                id);
    }
}
