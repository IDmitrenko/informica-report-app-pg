package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.GroupInf;
import ru.avers.informica.dto.inqry.AgeDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class GroupMapper implements RowMapper<GroupInf> {

    @Override
    public GroupInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupInf groupInf = new GroupInf();
        groupInf.setId(rs.getInt("id"));
        groupInf.setIdCode(rs.getString("idCodeBuilding") + "-" + groupInf.getId());
        groupInf.setName(rs.getString("name"));
        groupInf.setAgeFromYears(rs.getShort("age_from_years"));
        groupInf.setAgeFromMonths(rs.getShort("age_from_months"));
        groupInf.setAgeToYears(rs.getShort("age_to_years"));
        groupInf.setAgeToMonths(rs.getShort("age_to_months"));
        groupInf.setAgeFrom(AgeDto.calcAge(groupInf.getAgeFromYears(), groupInf.getAgeFromMonths()));
        groupInf.setAgeTO(AgeDto.calcAge(groupInf.getAgeToYears(), groupInf.getAgeToMonths()));
        groupInf.setOrientation(rs.getString("orientation"));
        groupInf.setWorktimeGroup(rs.getString("worktime_group"));
        groupInf.setActivity(rs.getString("activity"));
        groupInf.setCapacity(rs.getString("capacity"));

        return groupInf;
    }
}
