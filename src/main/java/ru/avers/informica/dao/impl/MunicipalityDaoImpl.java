package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.MunicipalityDao;
import ru.avers.informica.dao.mapper.MunicipalityMapper;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.exception.CustomSQLErrorCodeTranslator;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MunicipalityDaoImpl implements MunicipalityDao {

    private final JdbcTemplate jdbcTemplate;
    private final MunicipalityMapper municipalityMapper;

    @Override
    public List<MunicipalityInf> getMunicipalitys() {
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

            return municipalityInfs;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса Municipalitys.", ex);
            throw ex;
        }
    }

}
