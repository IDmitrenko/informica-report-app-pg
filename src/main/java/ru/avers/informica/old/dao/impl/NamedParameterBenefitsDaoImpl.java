package ru.avers.informica.old.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.dao.BenefitsDao;
import ru.avers.informica.old.dao.mapper.BenefitsMapper;
import ru.avers.informica.old.entities.Benefits;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class NamedParameterBenefitsDaoImpl implements BenefitsDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BenefitsMapper benefitsMapper;

    @Override
    public List<Benefits> getBenefits(Long id) {
//        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
//        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("app_id", id);
            return jdbcTemplate.query("select bl.id_benefits as id_benefits, bl.app_id as app_id," +
                            " bl.benefit_csp as id_benefit_csp, bl.d_confirm as dtConfirm " +
                            "from app.benefits bl where bl.app_id = :app_id",
                    parameterSource,
                    benefitsMapper);
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса getBenefits(id).", ex);
            throw ex;
        }
    }

}
