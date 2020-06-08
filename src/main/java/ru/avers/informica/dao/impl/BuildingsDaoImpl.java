package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.BuildingsDao;
import ru.avers.informica.dao.mapper.BuildingsMapper;
import ru.avers.informica.entities.Buildings;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BuildingsDaoImpl implements BuildingsDao {

    private final JdbcTemplate jdbcTemplate;
    private final BuildingsMapper buildingsMapper;

    @Override
    public List<Buildings> getBuildings(Long id) {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            return jdbcTemplate.query("select b.id_buildings as id_buildings, b.app_id as app_id," +
                            " b.prty as prty, b.uch_buildings_id as idUch " +
                            "from app.buildings b " +
                            "where b.app_id = ? " +
                            "order by b.prty asc",
                    buildingsMapper,
                    id);
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса getBuildings(id).", ex);
            throw ex;
        }
    }

}
