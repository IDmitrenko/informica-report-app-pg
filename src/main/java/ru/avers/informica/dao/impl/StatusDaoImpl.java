package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.StatusDao;
import ru.avers.informica.dao.mapper.StatusMapper;
import ru.avers.informica.entities.Status;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class StatusDaoImpl implements StatusDao {

    private final JdbcTemplate jdbcTemplate;
    private final StatusMapper statusMapper;

    @Override
    public List<Status> getStatuses(Long id) {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            return jdbcTemplate.query("select s.id_status as id_status, s.app_id as app_id," +
                            " s.statuses_id as statuses_id " +
                            "from app.status s where s.app_id = ?",
                    statusMapper,
                    id);
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса getStatuses(id).", ex);
            throw ex;
        }
    }

}
