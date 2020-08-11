package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.GroupHealthDao;
import ru.avers.informica.dao.mapper.IdMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class GroupHealthDaoImpl implements GroupHealthDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final IdMapper idMapper;

    @Override
    public Collection<Integer> getGroupHealths(Integer idClasses) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            parameterSource.addValue("idClasses", idClasses);

            String sql = "select ct.class_type_csp as id " +
                    "from public.class_types ct " +
                    "where ct.classes_id = :idClasses";

            List<Integer> idGroupHealthsList = jdbcTemplate
                    .query(sql, parameterSource, idMapper);

            Collection<Integer> idGroupHealths = new HashSet<>();
            for (Integer idh : idGroupHealthsList) {
                idGroupHealths.add(idh.intValue());
            }
            return idGroupHealths;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса GroupHealths с idClasses = {}", idClasses, ex);
            throw ex;
        }
    }

}
