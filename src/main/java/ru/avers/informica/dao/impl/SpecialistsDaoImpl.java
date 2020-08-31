package ru.avers.informica.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.SpecialistsDao;
import ru.avers.informica.dao.mapper.SpecialistsMapper;
import ru.avers.informica.dto.dictcode.SpecialistsCode;
import ru.avers.informica.dto.informica.SpecialistsInf;
import ru.avers.informica.report.ReportSetting;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SpecialistsDaoImpl implements SpecialistsDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SpecialistsMapper specialistsMapper;
    private final ReportSetting reportSetting;

    @Override
    public SpecialistsInf getSpecialistsUch(Long idUch) {

        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            Date currDate = reportSetting.getCurrDate();
            parameterSource.addValue("idUch", idUch);
            parameterSource.addValue("currDate", currDate);
            parameterSource.addValue("basic", SpecialistsCode.POSITION_CATEGORY);
            parameterSource.addValue("service", SpecialistsCode.POSITION_CATEGORY_SERVICE);
            parameterSource.addValue("workerExternal", SpecialistsCode.POSITION_TYPE_BY_WORKER_EXTERNAL);
            parameterSource.addValue("psychologist", SpecialistsCode.getPOSITION_psychologist());
            parameterSource.addValue("logopedist", SpecialistsCode.getPOSITION_logopedist());
            parameterSource.addValue("defectologist", SpecialistsCode.getPOSITION_defectologist());
            parameterSource.addValue("oligophren", SpecialistsCode.UNKNOWN1_oligophren);
            parameterSource.addValue("surdo", SpecialistsCode.UNKNOWN2_surdo);
            parameterSource.addValue("tiflo", SpecialistsCode.UNKNOWN3_tiflo);
            parameterSource.addValue("lfk", SpecialistsCode.getPOSITION_lfk());
            parameterSource.addValue("afk", SpecialistsCode.UNKNOWN4_afk);
            parameterSource.addValue("social", SpecialistsCode.POSITION_social);
            parameterSource.addValue("med", SpecialistsCode.getPOSITION_med());
            parameterSource.addValue("pediatr", SpecialistsCode.POSITION_pediatr);
            parameterSource.addValue("neurolog", SpecialistsCode.POSITION_neurolog);
            parameterSource.addValue("ophthalmologist", SpecialistsCode.POSITION_ophthalmologist);
            parameterSource.addValue("audiologist", SpecialistsCode.UNKNOWN5_audiologist);

            SpecialistsInf specialistsInf = jdbcTemplate.queryForObject("select u.domen_uch as id, " +
                            "u.comp_code as code, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:psychologist) and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_psychologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:psychologist) and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_psychologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:logopedist) and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_logopedist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:logopedist) and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_logopedist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:defectologist) and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_defectologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:defectologist) and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_defectologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :oligophren and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_oligophren, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :oligophren and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_oligophren, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :surdo and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_surdo, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :surdo and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_surdo, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :tiflo and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_tiflo, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :tiflo and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_tiflo, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:lfk) and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_lfk, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:lfk) and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_lfk, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :afk and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_afk, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :afk and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_afk, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :social and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_social, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :social and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_social, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:med) and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_med, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp in (:med) and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_med, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :pediatr and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_pediatr, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :pediatr and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_pediatr, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :neurolog and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_neurolog, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :neurolog and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_neurolog, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :ophthalmologist and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_ophthalmologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :ophthalmologist and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_ophthalmologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :audiologist and " +
                            "       j.combine != :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_s_audiologist, " +
                            "(select count (s.dlg_sp) " +
                            " from public.job j " +
                            " inner join states s on (s.id_states = j.states_id) " +
                            " where s.categ_sp in (:basic, :service) and " +
                            "       s.dlg_sp = :audiologist and " +
                            "       j.combine = :workerExternal and " +
                            "       (j.d_out is null or j.d_out > :currDate) and " +
                            "        s.uch = u.domen_uch) " +
                            " as num_f_audiologist " +
                            "from public.uch u " +
                    "where u.domen_uch = :idUch",
                    parameterSource,
                    specialistsMapper);

            return specialistsInf;

        } catch (Exception ex) {
            log.error("Ошибка выполнения запроса SpecialistsUch с idUch = {}", idUch, ex);
            throw ex;
        }
    }

}
