package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.impl.StatusDaoImpl;
import ru.avers.informica.entities.ApplicationsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ApplicationsMapper implements RowMapper<ApplicationsEntity> {

    private final StatusDaoImpl statusDao;

    @Override
    public ApplicationsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApplicationsEntity applications = new ApplicationsEntity();
        applications.setId_app(rs.getLong("id"));
        applications.setNum(rs.getString("num"));
        applications.setD_birth(rs.getDate("dtBirth"));
        applications.setD_plan(rs.getDate("dtPlan"));
        applications.setStatusList(statusDao.getStatuses(applications.getId_app()));
        return applications;
    }
}
