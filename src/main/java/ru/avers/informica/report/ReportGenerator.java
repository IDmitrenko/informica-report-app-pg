package ru.avers.informica.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.infcfg.Config;
import ru.avers.informica.infcfg.TypeSchemaVersion;
import ru.avers.informica.report.builder.MunicipalityBuilder;
import ru.avers.informica.report.indicator.ReportIndicators;
import ru.avers.informica.report.provider.InqryCountersProvider;
import ru.avers.informica.report.provider.ReportDataProvider;
import ru.avers.informica.report.provider.ValidMunicipalityProvider;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;
import ru.avers.informica.report.xml.PushDataRequest;
import ru.avers.informica.report.xml.TagParentPay;
import ru.avers.informica.report.xml.TagReports;
import ru.avers.informica.report.xml.TagSystem;
import ru.avers.informica.utils.CHelper;

import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportGenerator {
    private final DataSourceUch sourceUch;
    private final ReportDataProvider reportDataProvider;
    private final ReportIndicators reportIndicators;
    private final ValidMunicipalityProvider validMunicipalityProvider;
    private final MunicipalityBuilder municipalityBuilder;
    private final InqryCountersProvider counterProvider;


    public synchronized PushDataRequest generateReport(CProfile cProfile)
            throws Exception {
        Config configInformica = CHelper.getInformicaConfig();
        reportDataProvider.loadData();
        PushDataRequest request = new PushDataRequest();
        request.setSystem(buildSystemInfo(configInformica));
        request.setSchemaVersion(schemaBuilder());
        request.setReports(reportBuilder(cProfile, configInformica));
        return request;
    }

    private TagReports reportBuilder(CProfile cProfile, Config configInformica)
            throws Exception {
        // Собрать базовую информацию по учреждениям и их схемы показателей
        Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas = sourceUch
                .getUchInfSchemas();
        //Рассчитать все индикаторы
        reportIndicators.calculateAllIndicators(uchInfSchemas);
        // Формирование выходного XML-файла
        TagReports tagReports = new TagReports();
        // считаем счетчики для всех учреждений (пока только Inqry)
        Map<Long, Map<String, Counter>> counterMap = counterProvider.provideCounters(uchInfSchemas);
        //Заносим информацию в выходной XML по municipality
        for (MunicipalityInf municipalityInf : validMunicipalityProvider.validMunicipalities(uchInfSchemas)) {
            tagReports.getMunicipality().add(municipalityBuilder.build(municipalityInf,
                    uchInfSchemas, counterMap));
        }
        //Заносим информацию по второму тэгу parent_pay
        tagReports.setParent_Pay(parentPayBuider());
        return tagReports;
    }

    private TagParentPay parentPayBuider() {
        return null;

    }

    private TypeSchemaVersion schemaBuilder() {
        return TypeSchemaVersion.tFiveDotO;
    }

    private TagSystem buildSystemInfo(Config configInformica) {
        TagSystem tagSystem = new TagSystem();
        tagSystem.setName(configInformica.getSystem().getName());
        tagSystem.setVendor(configInformica.getSystem().getVendor());
        tagSystem.setVersion(configInformica.getSystem().getVersion());
        tagSystem.setEmail(configInformica.getSystem().getEmail());
        tagSystem.setInstall_Type(configInformica.getSystem().getInstallType());
        tagSystem.setOwn_Server(configInformica.getSystem().getOwn_server());
        return tagSystem;
    }

}