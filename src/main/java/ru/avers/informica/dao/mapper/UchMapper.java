package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.impl.BuildingDaoImpl;
import ru.avers.informica.dao.impl.SpecialistsDaoImpl;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.report.ReportSetting;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UchMapper implements RowMapper<UchInf> {

    private final BuildingDaoImpl buildingDao;
    private final SpecialistsDaoImpl specialistsDao;
    private final ReportSetting reportSetting;

    @Override
    public UchInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        UchInf uchInf = new UchInf();
        uchInf.setId(rs.getLong("id"));
        uchInf.setCode(rs.getString("code"));
        uchInf.setName(rs.getString("name"));
        uchInf.setShortName(rs.getString("shortName"));
        uchInf.setIdTer(rs.getInt("idTer"));
        uchInf.setTerName(rs.getString("terName"));
        uchInf.setChief(rs.getString("chief"));
        uchInf.setMunicipObrOktmo(rs.getString("municipObrOktmo"));
        uchInf.setEpguLink(rs.getString("epguLink"));
        uchInf.setRpguLink(rs.getString("rpguLink"));
        uchInf.setWorkTime(rs.getString("worktime"));
        uchInf.setMealServingType(rs.getString("mealServingType"));
        uchInf.setOrgLegalFormName(rs.getString("orgLegalFormName"));
        uchInf.setOrgLegalFormCode(rs.getString("orgLegalFormCode"));
        uchInf.setStatusName(rs.getString("statusName"));
        uchInf.setStatusСode(rs.getString("statusCode"));
        if (rs.getString("addEducation") == null) {
            uchInf.setAddEducation("Нет");
        } else {
            uchInf.setAddEducation(rs.getString("addEducation"));
        }
        if (rs.getString("features") == null) {
            uchInf.setFeatures("Нет");
        } else {
            uchInf.setFeatures(rs.getString("features"));
        }
        uchInf.setFiasOrgGuid(rs.getString("fiasOrgGuid"));
        uchInf.setAddrKladr(rs.getString("addrKladr"));
        uchInf.setStructureName(rs.getString("structureName"));
        uchInf.setStructureCode(rs.getString("structureCode"));
        uchInf.setLicense(rs.getString("license"));
        uchInf.setTypeArea(rs.getString("type_area"));
        uchInf.setWebsite(rs.getString("website"));
        uchInf.setEmail(rs.getString("email"));
        uchInf.setPhone(rs.getString("phone"));
        uchInf.setNumFilial(rs.getString("num_filial"));
        uchInf.setNumBuilding(rs.getString("num_building"));
        uchInf.setNumGroup(rs.getString("num_group"));
        uchInf.setPartnerDoo(rs.getString("partner_doo"));
        if (rs.getString("passport") == null) {
            uchInf.setPassport("Нет");
        } else {
            uchInf.setPassport(rs.getString("passport"));
        }
        uchInf.setLekoteka(rs.getString("lekoteka"));
        uchInf.setCentreGame(rs.getString("centre_game"));
        uchInf.setCommetStatus(rs.getString("commet_status"));
        if (!reportSetting.isFirstOccurrence()) {
            uchInf.setBuildingInfs(buildingDao.getBuildingsUch(uchInf.getId()));
        }

        if (!reportSetting.isFirstOccurrence()) {
            uchInf.setSpecialistsInf(specialistsDao.getSpecialistsUch(uchInf.getId()));
        }
        return uchInf;
    }
}
