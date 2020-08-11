package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.impl.InqryGrpTimeDaoImpl;
import ru.avers.informica.dto.informica.InqryEnrolmentInf;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class InqryEnrolmentMapper implements RowMapper<InqryEnrolmentInf> {

    private final InqryGrpTimeDaoImpl inqryGrpTimeDao;

    @Override
    public InqryEnrolmentInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        InqryEnrolmentInf inqryEnrolmentInf = new InqryEnrolmentInf();
        inqryEnrolmentInf.setId(rs.getInt("id"));
        inqryEnrolmentInf.setIdUch(rs.getInt("uch_id"));
        inqryEnrolmentInf.setUchCode(rs.getString("uch_code"));
        inqryEnrolmentInf.setBDt(rs.getDate("dtBirth"));
        inqryEnrolmentInf.setIdHealthCsp(rs.getInt("healthCsp"));
        inqryEnrolmentInf.setStatusCode(rs.getString("statusCode"));
        inqryEnrolmentInf.setIdGrpTimesCsp(inqryGrpTimeDao.getGrpTimeIdsInqry(inqryEnrolmentInf.getId()));

        return inqryEnrolmentInf;
    }
}
