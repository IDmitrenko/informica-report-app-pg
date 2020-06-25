package ru.avers.informica.old.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.dao.impl.SprBDaoImpl;
import ru.avers.informica.old.entities.Benefits;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class BenefitsMapper implements RowMapper<Benefits> {

    private final SprBDaoImpl sprBDao;

    @Override
    public Benefits mapRow(ResultSet rs, int rowNum) throws SQLException {
        Benefits benefits = new Benefits();
        benefits.setAppId(rs.getLong("app_id"));
        benefits.setDateConfirm(rs.getDate("dtConfirm"));
        benefits.setIdBenefits(rs.getLong("id_benefits"));
        benefits.setIdBenefitsCsp(rs.getLong("id_benefit_csp"));
        benefits.setBenefitIndicator(sprBDao.getIndicator(benefits.getIdBenefitsCsp()));
        return benefits;
    }
}
