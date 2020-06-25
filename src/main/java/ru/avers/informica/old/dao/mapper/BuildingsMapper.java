package ru.avers.informica.old.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.old.entities.Buildings;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class BuildingsMapper implements RowMapper<Buildings> {

    @Override
    public Buildings mapRow(ResultSet rs, int rowNum) throws SQLException {
        Buildings buildings = new Buildings();
        buildings.setAppId(rs.getLong("app_id"));
        buildings.setIdBuildings(rs.getLong("id_buildings"));
        buildings.setPrty(rs.getShort("prty"));
        buildings.setIdUch(rs.getLong("idUch"));
        return buildings;
    }
}
