package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.CommonDao;
import ru.avers.informica.dao.MunicipalityDao;
import ru.avers.informica.dao.mapper.MunicipalityMapper;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.dto.inqry.AgeDto;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;
import ru.avers.informica.utils.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MunicipalityDaoImpl implements MunicipalityDao {

    private final JdbcTemplate jdbcTemplate;
    private final MunicipalityMapper municipalityMapper;
    private final CommonDao commonDao;

    @Override
    public List<MunicipalityInf> getMunicipalitys(Date currDate, Date currEducDate) {
        CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);

        try {

            List<MunicipalityInf> municipalityInfs = jdbcTemplate.query("select m.m_id as id, " +
                            "m.regulation as regulation, " +
                            "m.oktmo as oktmo, " +
                            "(select count(*) " +
                            " from public.uch u " +
                            " inner join public.cons_common cc on cc.uch = u.domen_uch " +
                            " where u.uch_ter_csp = m.m_id and (ea_forma_1 = '+' or ea_forma_2 = '+' " +
                            "    or ea_forma_3 = '+' or ea_forma_4 = '+')) as num_early_assistance, " +
                            "(select count(*) " +
                            " from public.uch u " +
                            " inner join public.cons_common cc on cc.uch = u.domen_uch " +
                            " where u.uch_ter_csp = m.m_id and " +
                            "       cc_num_staff_member > 0) as num_advisory_centr, " +
                            "m.max_doo as max_doo, " +
                            "m.fix_area as fix_area, " +
                            "m.time_mouo as time_mouo, " +
                            "m.phones_mouo as phones_mouo, " +
                            "m.email_mouo as email_mouo, " +
                            "m.site_mouo as site_mouo, " +
                            "m.address_mouo as address_mouo, " +
                            "m.name_mouo as name_mouo, " +
                            "m.rpgu_link as rpgu_link, " +
                            "m.epgu_link as epgu_link " +
                    "from public.municip m " +
                    "where m.oktmo is not null",
                    municipalityMapper);

                // Муниципальные показатели
                AgeDto age0 = new AgeDto((short) 0, (short) 0, (short) 0);
                AgeDto age3 = new AgeDto((short) 3, (short) 0, (short) 0);
                AgeDto age7 = new AgeDto((short) 7, (short) 0, (short) 0);

                // Данные о детях, стоящих на учете в связи с отсутствием ДОО, передаются
                // в тэге noDooAct для детей, желающих получить место в текущем учебном году
                Map<Integer, Integer> noDooAct_0_3 = commonDao.getNoDooCounter(currDate,
                        DateUtil.adjustDate(currEducDate, 1),
                        true,
                        age0, age3);
                Map<Integer, Integer> noDooAct_3_7 = commonDao.getNoDooCounter(currDate,
                        DateUtil.adjustDate(currEducDate, 1),
                        true,
                        age3, age7);

                // или в тэге nooDooDef для детей, желающих получить место в последующие годы.
                Map<Integer, Integer> noDooDef_0_3 = commonDao.getNoDooCounter(currDate,
                        DateUtil.adjustDate(currEducDate, 1),
                        false,
                        age0, age3);
                Map<Integer, Integer> noDooDef_3_7 = commonDao.getNoDooCounter(currDate,
                        DateUtil.adjustDate(currEducDate, 1),
                        false,
                        age3, age7);

                // Данные о детях, не посещающих ДОО по медицинским показаниям
                Map<Integer, Integer> medic_0_3 = commonDao.getMedicCounter(currDate,
                        age0, age3);
                Map<Integer, Integer> medic_3_7 = commonDao.getMedicCounter(currDate,
                        age3, age7);

                // Данные о детях, получающих дошкольное образование в семейной форме
                Map<Integer, Integer> family_0_3 = commonDao.getFamilyCounter(currDate,
                        DateUtil.getYearPart(currEducDate),
                        age0, age3);
                Map<Integer, Integer> family_3_7 = commonDao.getFamilyCounter(currDate,
                        DateUtil.getYearPart(currEducDate),
                        age3, age7);


                for (MunicipalityInf municipalityInf : municipalityInfs) {
                    Integer idTer = municipalityInf.getIdTer();
                    if (noDooAct_0_3.containsKey(idTer)) {
                        municipalityInf.setNoDooAct_0_3(noDooAct_0_3.get(idTer));
                    } else {
                        municipalityInf.setNoDooAct_0_3(0);
                    }
                    if (noDooAct_3_7.containsKey(idTer)) {
                        municipalityInf.setNoDooAct_3_7(noDooAct_3_7.get(idTer));
                    } else {
                        municipalityInf.setNoDooAct_3_7(0);
                    }
                    if (noDooDef_0_3.containsKey(idTer)) {
                        municipalityInf.setNoDooDef_0_3(noDooDef_0_3.get(idTer));
                    } else {
                        municipalityInf.setNoDooDef_0_3(0);
                    }
                    if (noDooDef_3_7.containsKey(idTer)) {
                        municipalityInf.setNoDooDef_3_7(noDooDef_3_7.get(idTer));
                    } else {
                        municipalityInf.setNoDooDef_3_7(0);
                    }
                    if (medic_0_3.containsKey(idTer)) {
                        municipalityInf.setMedic_0_3(medic_0_3.get(idTer));
                    } else {
                        municipalityInf.setMedic_0_3(0);
                    }
                    if (medic_3_7.containsKey(idTer)) {
                        municipalityInf.setMedic_3_7(medic_3_7.get(idTer));
                    } else {
                        municipalityInf.setMedic_3_7(0);
                    }
                    if (family_0_3.containsKey(idTer)) {
                        municipalityInf.setFamily_0_3(family_0_3.get(idTer));
                    } else {
                        municipalityInf.setFamily_0_3(0);
                    }
                    if (family_3_7.containsKey(idTer)) {
                        municipalityInf.setFamily_3_7(family_3_7.get(idTer));
                    } else {
                        municipalityInf.setFamily_3_7(0);
                    }
                }

            return municipalityInfs;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса Municipalitys.", ex);
            throw ex;
        }
    }

}
