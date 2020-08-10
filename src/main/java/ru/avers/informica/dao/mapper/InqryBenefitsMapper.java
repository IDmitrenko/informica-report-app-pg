package ru.avers.informica.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryBenefitsInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class InqryBenefitsMapper implements RowMapper<InqryBenefitsInf> {

    @Override
    public InqryBenefitsInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryBenefitsInf inqryBenefits = new InqryBenefitsInf();
        inqryBenefits.setId(rs.getInt("id"));
        inqryBenefits.setIdBenefits(rs.getInt("idBenefitsCsp"));
        inqryBenefits.setBenefitCode(rs.getString("benefitCode"));
        inqryBenefits.setBenefitTyp(rs.getString("benefitTyp"));
        return inqryBenefits;
    }
}
