package ru.avers.informica.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dao.MunicipalityDao;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dto.informica.*;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.filtersinqry.FilterChain;
import ru.avers.informica.infcfg.*;
import ru.avers.informica.old.dao.ApplicationsDao;
import ru.avers.informica.report.builder.MunicipalityBuilder;
import ru.avers.informica.report.indicator.ReportIndicators;
import ru.avers.informica.report.provider.InqryCountersProvider;
import ru.avers.informica.report.provider.ReportDataProvider;
import ru.avers.informica.report.provider.ValidMunicipalityProvider;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;
import ru.avers.informica.report.xml.*;
import ru.avers.informica.utils.BeanUtil;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.DateUtil;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Time;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportGenerator {
    private final ApplicationsDao applicationDao;
    private final UchDao uchDao;
    private final FilterChain filterChain;
    private final ReportSetting reportSetting;
    private final DataSourceUch sourceUch;
    private final ReportDataProvider reportDataProvider;
    private final ReportIndicators reportIndicators;
    private final ValidMunicipalityProvider validMunicipalityProvider;
    private final MunicipalityBuilder municipalityBuilder;


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
        for (MunicipalityInf municipalityInf : validMunicipalityProvider.validMunicipalities(uchInfSchemas)) {
            tagReports.getMunicipality().add(municipalityBuilder.build(municipalityInf, uchInfSchemas));
        }
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
