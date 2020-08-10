package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.InqryBenefitsDao;
import ru.avers.informica.dao.mapper.InqryBenefitsMapper;
import ru.avers.informica.dto.informica.InqryBenefitsInf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InqryBenefitsDaoImpl implements InqryBenefitsDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final InqryBenefitsMapper inqryBenefitsMapper;

    @Override
    public List<Collection<String>> getBenefitsInqry(Integer idApplications) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();

            parameterSource.addValue("idApplications", idApplications);


            List<InqryBenefitsInf> inqryBenefits = jdbcTemplate
                    .query("select :idApplications as id, " +
                            "b.benefit_csp as idBenefitsCsp, " +
                            "v81.code as benefitCode, " +
                            "v81.typ as benefitTyp " +
                    "from app.benefits b " +
                    "inner join app.v_dict_81_inqry_child_lgot v81 on v81.id = b.benefit_csp " +
                    "where b.app_id = :idApplications",
                    parameterSource,
                    inqryBenefitsMapper);

            Collection<String> benefitsCode = new HashSet<>();
            Collection<String> benefitsTyp = new HashSet<>();
            List<Collection<String>> twoValuesInqryBenefits = new ArrayList<>();
            for (InqryBenefitsInf ib : inqryBenefits) {
                benefitsCode.add(ib.getBenefitCode());
                benefitsTyp.add(ib.getBenefitTyp());
            }
            if (benefitsCode.size() > 0) {
                twoValuesInqryBenefits.add(benefitsCode);
            }
            if (benefitsTyp.size() > 0) {
                twoValuesInqryBenefits.add(benefitsTyp);
            }

            return twoValuesInqryBenefits;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса BenefitsInqry с idApplications = {}", idApplications, ex);
            throw ex;
        }
    }

}
