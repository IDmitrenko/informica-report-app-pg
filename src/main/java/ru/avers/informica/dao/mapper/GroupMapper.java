package ru.avers.informica.dao.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.impl.GroupHealthDaoImpl;
import ru.avers.informica.dto.informica.GroupInf;
import ru.avers.informica.dto.inqry.AgeDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class GroupMapper implements RowMapper<GroupInf> {

    private final GroupHealthDaoImpl groupHealthDao;

    @Override
    public GroupInf mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupInf groupInf = new GroupInf();
        groupInf.setId(rs.getInt("id"));
        groupInf.setIdUch(rs.getInt("idUch"));
        groupInf.setIdBuilding(rs.getInt("idBuilding"));
        groupInf.setIdCode(rs.getString("idCodeBuilding") + "-" + groupInf.getId());
        groupInf.setName(rs.getString("name"));
        groupInf.setAgeFromYears(rs.getShort("age_from_years"));
        groupInf.setAgeFromMonths(rs.getShort("age_from_months"));
        groupInf.setAgeToYears(rs.getShort("age_to_years"));
        groupInf.setAgeToMonths(rs.getShort("age_to_months"));
        groupInf.setAgeFrom(AgeDto.calcAge(groupInf.getAgeFromYears(), groupInf.getAgeFromMonths()));
        groupInf.setAgeTO(AgeDto.calcAge(groupInf.getAgeToYears(), groupInf.getAgeToMonths()));
        groupInf.setOrientation(rs.getString("orientation"));
//        groupInf.setIdHealthCsp(rs.getInt("idHealthCsp"));
        groupInf.setIdHealthCsp(groupHealthDao.getGroupHealths(groupInf.getId()));
        groupInf.setWorktimeGroup(rs.getString("worktime_group"));
        groupInf.setIdWorkTimeCsp(rs.getInt("idWorkTimeCsp"));
        groupInf.setActivity(rs.getString("activity"));
        groupInf.setCapacity(rs.getShort("capacity"));
        groupInf.setEnrolled(rs.getShort("enrolled"));
        groupInf.setSubgroup(rs.getString("subgroup"));
        groupInf.setTransferSpace(rs.getShort("transfer_space"));
        groupInf.setPartnerGroup(rs.getString("partner_group"));
        groupInf.setPartner(rs.getString("partner"));
        groupInf.setDays(rs.getString("days"));
        groupInf.setEducator(rs.getString("educator"));
        groupInf.setInvalid(rs.getString("invalid"));
        groupInf.setSize(rs.getString("size"));
        groupInf.setProgram(rs.getString("program"));
        groupInf.setReductionOther(rs.getString("reduction_other"));
        groupInf.setReductionSchool(rs.getString("reduction_school"));
        groupInf.setAddContOvz(rs.getString("add_cont_ovz"));
        groupInf.setAddContGkp(rs.getString("add_cont_gkp"));
        groupInf.setEnrolledGkp(rs.getString("enrolled_gkp"));
        groupInf.setCapacityGkp(rs.getString("capacity_gkp"));
        groupInf.setProgramOvz(rs.getString("program_ovz"));
        if (rs.getString("ovz_type") != null) {
            String[] classType = rs.getString("ovz_type").split(" ");
            for (int i = 0; i < classType.length; i++) {
                switch (classType[i]) {
                    case "02.02":
                    case "04.05":
                        groupInf.setOvzType("1");
                        groupInf.setOvzTypeNew("1");
                        break;
                    case "02.01":
                    case "04.02":
                        groupInf.setOvzType("2");
                        groupInf.setOvzTypeNew("5");
                        break;
                    case "02.04":
                    case "04.03":
                        groupInf.setOvzType("3");
                        groupInf.setOvzTypeNew("3");
                        break;
                    case "02.07":
                    case "04.08":
                        groupInf.setOvzType("4");
                        groupInf.setOvzTypeNew("9");
                        break;
                    case "02.03":
                    case "04.06":
                        groupInf.setOvzType("5");
                        groupInf.setOvzTypeNew("7");
                        break;
                    case "02.05":
                    case "04.07":
                        groupInf.setOvzType("6");
                        groupInf.setOvzTypeNew("6");
                        break;
                    case "02.08":
                    case "04.10":
                        groupInf.setOvzType("7");
                        groupInf.setOvzTypeNew("10");
                        break;
                    case "02.06":
                    case "02.09":
                    case "02.10":
                    case "02.11":
                    case "04.04":
                    case "04.09":
                    case "04.11":
                    case "04.12":
                    case "04.13":
                        groupInf.setOvzType("8");
                        groupInf.setOvzTypeNew("11");
                        break;

                    default:
                        break;
                }
            }
            for (int i = 0; i < classType.length; i++) {
                switch (classType[i]) {
                    case "03.01":
                        groupInf.setWellness("1");
                        break;
                    case "03.02":
                        groupInf.setWellness("4");
                        break;
                    case "03.03":
                        groupInf.setWellness("2");
                        break;

                    default:
                        break;
                }
            }
        }
        if (rs.getString("ovz_type_dop") != null) {
            String[] classTypeOVZ = rs.getString("ovz_type_dop").split(" ");
            for (int i = 0; i < classTypeOVZ.length; i++) {
                switch (classTypeOVZ[i]) {
                    case "2":
                    case "3":
                        groupInf.setOvzTypeDop("0");
                        break;

                    default:
                        break;
                }
            }
        }

        return groupInf;
    }
/*
            "case when (select v08.code " +
            "           from app.v_dict_08_type_class v08 " +
            "           inner join class_types ct on v08.id = ct.class_type_csp " +
            "           where ct.classes_id = cl.id_classes limit 1) in ('02.02', '04.05') " +
            "then 1 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) in ('02.01', '04.02') " +
            "then 2 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) in ('02.04', '04.03') " +
            "then 3 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) in ('02.07', '04.08') " +
            "then 4 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) in ('02.03', '04.06') " +
            "then 5 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) in ('02.05', '04.07') " +
            "then 6 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) in ('02.08', '04.10') " +
            "then 7 " +
            "    when (select v08.code " +
            "          from app.v_dict_08_type_class v08 " +
            "          inner join class_types ct on v08.id = ct.class_type_csp " +
            "          where ct.classes_id = cl.id_classes limit 1) " +
            "          in ('02.06','02.09','02.10','02.11','04.04','04.09','04.11','04.12','04.13') " +
            "then 8 " +
            "else null " +
            "end as ovz_type " +

 */
}
