package ru.avers.informica.old.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.dao.mapper.StatusesMapper;
import ru.avers.informica.old.dao.StatusesDao;
import ru.avers.informica.old.entities.Statuses;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

@Component
@Slf4j
@RequiredArgsConstructor
public class StatusesDaoImpl implements StatusesDao {

    private final JdbcTemplate jdbcTemplate;
    private final StatusesMapper statusesMapper;

    @Override
    public Statuses getStatus(Long idStatus) {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            return jdbcTemplate.queryForObject("select s.id as id, s.descr as descr " +
                            "from app.statuses s where s.id = ?",
                    statusesMapper,
                    idStatus);
/*
            List<Statuses> statusesList = jdbcTemplate.query("select s.id as id, s.descr as descr " +
                            "from app.statuses s where s.id = ?",
                    statusesMapper,
                    idStatus);
            if (statusesList.isEmpty()) {
                return null;
            } else {
                return statusesList.get(0);
            }
*/
        } catch (EmptyResultDataAccessException ex) {
            log.info("Неверная ссылка на статус. statuses_id={}", idStatus);
            return null;
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса getStatus({}).", idStatus);
            log.error("Ошибка выполнения запроса getStatus().", ex);
            throw ex;
        }
    }

}
