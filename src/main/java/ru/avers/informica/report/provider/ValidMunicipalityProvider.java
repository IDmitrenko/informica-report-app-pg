package ru.avers.informica.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.MunicipalityDao;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidMunicipalityProvider {
    private final MunicipalityDao municipalityDao;
    private final ReportSetting reportSetting;

    public List<MunicipalityInf> validMunicipalities(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        List<MunicipalityInf> allMunicipalityInfs = municipalityDao
                .getMunicipalitys(reportSetting.getCurrDate(),
                        reportSetting.getCurrEducDate());
        // отобрать муниципалитеты для которых есть учреждения
        List<Integer> noValidMunicipalitys = new ArrayList<>();
        municip:
        for (MunicipalityInf municipalityInf : allMunicipalityInfs) {
            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                if (municipalityInf.getIdTer().equals(uchInfSchema.getUchInf().getIdTer())) {
                    continue municip;
                }
            }
            noValidMunicipalitys.add(municipalityInf.getIdTer());
        }

        List<MunicipalityInf> municipalityInfs = new ArrayList<>();
        for (MunicipalityInf municipalityInf : allMunicipalityInfs) {
            if ((noValidMunicipalitys != null &&
                    !noValidMunicipalitys.contains(municipalityInf.getIdTer())) ||
                    noValidMunicipalitys == null) {
                municipalityInfs.add(municipalityInf);
            }
        }
        return municipalityInfs;
    }
}
