package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.BenefitsDao;
import ru.avers.informica.dao.mapper.BenefitsMapper;
import ru.avers.informica.entities.Benefits;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BenefitsDaoImpl implements BenefitsDao {

    private final JdbcTemplate jdbcTemplate;
    private final BenefitsMapper benefitsMapper;

    @Override
    public List<Benefits> getBenefits(Long id) {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {
            return jdbcTemplate.query("select bl.id_benefits as id_benefits, bl.app_id as app_id," +
                            " bl.benefit_csp as id_benefit_csp, bl.d_confirm as dtConfirm " +
                            "from app.benefits bl where bl.app_id = ?",
                    benefitsMapper,
                    id);
        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса getBenefits(id).", ex);
            throw ex;
        }
    }

}
