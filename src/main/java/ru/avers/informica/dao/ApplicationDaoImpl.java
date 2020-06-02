package ru.avers.informica.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.mapper.StatusMapper;
import ru.avers.informica.entities.ApplicationEntity;
import ru.avers.informica.entities.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationDaoImpl implements ApplicationDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ApplicationEntity> getAllApplications() {
        try {
            return jdbcTemplate.query("select a.id_app as id, a.num as num, a.f_name as firstName " +
                            "from app.applications a ",
                    (resultSet, i) -> applicationRowMapper(resultSet, i));
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
    }

    private ApplicationEntity applicationRowMapper(ResultSet rs, int i) throws SQLException {
        ApplicationEntity app = new ApplicationEntity();
        app.setId(rs.getLong("id"));
        app.setFirstName(rs.getString("firstName"));
        app.setNumber(rs.getString("num"));
        app.setStatusList(getStatuses(app.getId()));
        return app;
    }

    private List<Status> getStatuses(Long id) {
        return jdbcTemplate.query("select s.id_status as id_status, s.app_id as app_id, s.statuses_id as statuses_id " +
                        "from app.status s where s.app_id = ?",
                new StatusMapper(),
                id);
    }
}
