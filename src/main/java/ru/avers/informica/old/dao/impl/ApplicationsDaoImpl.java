package ru.avers.informica.old.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.dao.ApplicationsDao;
import ru.avers.informica.old.dao.mapper.ApplicationsMapper;
import ru.avers.informica.old.entities.ApplicationsEntity;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApplicationsDaoImpl implements ApplicationsDao {

    private final JdbcTemplate jdbcTemplate;
    private final ApplicationsMapper applicationsMapper;

    @Override
    public List<ApplicationsEntity> getAllApplications() {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            return jdbcTemplate.query("select a.id_app as id, a.num as num," +
                            "a.d_birth as dtBirth, a.d_plan as dtPlan " +
                    "from app.applications a",
                    applicationsMapper);
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса AllApplications.", ex);
            throw ex;
        }
/*
        try {
            return jdbcTemplate.query("select a.id_app as id, a.num as num " +
                            "from app.applications a ",
                    (resultSet, i) -> applicationRowMapper(resultSet, i));
        } catch (Exception e) {
            log.error("", e);
            throw e;
        }
*/
    }

}
