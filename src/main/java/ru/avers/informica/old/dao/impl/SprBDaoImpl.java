package ru.avers.informica.old.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.dao.SprBDao;
import ru.avers.informica.old.dao.mapper.SprBMapper;
import ru.avers.informica.old.entities.SprB;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

@Component
@Slf4j
@RequiredArgsConstructor
public class SprBDaoImpl implements SprBDao {

    private final JdbcTemplate jdbcTemplate;
    private final SprBMapper sprBMapper;

    @Override
    public SprB getIndicator(Long idBenefit) {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            return jdbcTemplate.queryForObject("select sb.sp as id, sb.cname as descr " +
                            "from public.spr_b sb where sb.sp = ?",
                    sprBMapper,
                    idBenefit);

        } catch (EmptyResultDataAccessException ex) {
            log.info("Неверная ссылка на льготу. id_benefit_csp={}", idBenefit);
            return null;
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса getIndicator({}).", idBenefit);
            log.error("Ошибка выполнения запроса getIndicator().", ex);
            throw ex;
        }
    }

}
