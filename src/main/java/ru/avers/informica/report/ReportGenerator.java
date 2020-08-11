package ru.avers.informica.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
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
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;
import ru.avers.informica.report.xml.PushDataRequest;
import ru.avers.informica.report.xml.TagParentPay;
import ru.avers.informica.report.xml.TagReports;
import ru.avers.informica.report.xml.TagSystem;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.DateUtil;

import java.sql.Time;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportGenerator {
    private final CHelper cHelper;
    private final ApplicationsDao applicationDao;
    private final InqryDao inqryDao;
    private final UchDao uchDao;
    private final MunicipalityDao municipalityDao;
    private final FilterChain filterChain;
    private final ReportSetting reportSetting;

    public PushDataRequest generateReport(CProfile cProfile)
            throws FilterException, ReportExceprion, FspeoException {
        Config configInformica = cHelper.getInformicaConfig();
        PushDataRequest request = new PushDataRequest();
        request.setSystem(buildSystemInfo(configInformica));
        request.setSchemaVersion(schemaBuilder());
        request.setReports(reportBuilder(cProfile, configInformica));
        return request;
    }

    private TagReports reportBuilder(CProfile cProfile, Config configInformica)
            throws FilterException, ReportExceprion, FspeoException {

//  считать InqryInf
        List<InqryInf> allInqry = inqryDao.getAllInqry(reportSetting.getCurrDate(),
                reportSetting.getCurrEducDate(),
                reportSetting.getBeginCurrYear().getTime());
        log.info("Найдено {} inqry-source", allInqry.size());

//  считать InqryEnrolmentInf для подсчета add_cont
        List<InqryEnrolmentInf> allInqryEnrolments = inqryDao.getIngryEnrolment();
        log.info("Найдено {} inqry-enrolment", allInqryEnrolments.size());

        final ReportConfig reportConfig = configInformica.getReport(Config.S_INFORMICA_REPORT);
        List<SchemaConfig> schemaConfigs = reportConfig.getSchemas();
        // Собрать базовую информацию по учреждениям и их схемы показателей
        DataSourceUch sourceUch = new DataSourceUch(uchDao, schemaConfigs,
                reportSetting.getCurrEducDate(), reportSetting);

        TagReports tagReports = new TagReports();
        tagReports.setParent_Pay(parentPayBuider());

        Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas = sourceUch
                .getUchInfSchemas();

// рассчитать показатель add_cont
        calculateIndicatorAddCont(allInqryEnrolments, uchInfSchemas);

// Отладочный вывод
        int countEnrolment = 0;
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                    if (groupInf.getAddCont() != null) {
                        countEnrolment += Integer.parseInt(groupInf.getAddCont());
                    }
                }
            }
        }
        log.info("Распределено {} inqry-enrolment", countEnrolment);

        List<MunicipalityInf> allMunicipalityInfs = municipalityDao
                .getMunicipalitys(reportSetting.getCurrDate(),
                        reportSetting.getCurrEducDate());
        // отобрать муниципалитеты для которых есть учреждения
        List<Integer> noValidMunicipalitys = new ArrayList<>();
        municip:
        for (
                MunicipalityInf municipalityInf : allMunicipalityInfs) {
            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                if (municipalityInf.getIdTer().equals(uchInfSchema.getUchInf().getIdTer())) {
                    continue municip;
                }
            }
            noValidMunicipalitys.add(municipalityInf.getIdTer());
        }

        List<MunicipalityInf> municipalityInfs = new ArrayList<>();
        for (
                MunicipalityInf municipalityInf : allMunicipalityInfs) {
            if ((noValidMunicipalitys != null &&
                    !noValidMunicipalitys.contains(municipalityInf.getIdTer())) ||
                    noValidMunicipalitys == null) {
                municipalityInfs.add(municipalityInf);
            }
        }

        allMunicipalityInfs = null;
        noValidMunicipalitys = null;

        Map<Long, List<InqryInf>> inqryByUchMap = allInqry.stream()
                .collect(Collectors.groupingBy(inqry -> inqry.getIdUch().longValue()));
        Map<String, Counter> counterMap = new HashMap<>();
        for (
                DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            //Учреждение
            UchInf uchInf = uchInfSchema.getUchInf();
            //Счетчики учреждения
            List<CounterConfig> inqryCounters = uchInfSchema.getSchema().getSource().getInqryCounters();
            //Заявления текущего учреждения
            List<InqryInf> inqryInfs = inqryByUchMap.get(uchInf.getId());
            //Пройтись по каждому заявлению и посчитать счетчики

            if (inqryInfs != null && inqryCounters != null) {
                for (InqryInf inqryInf : inqryInfs) {
                    //Для каждого счетчика проверить нужно ли его инкрементировать для текущего заявления
                    for (CounterConfig counterConfig : inqryCounters) {
                        if (counterConfig.isPassed(reportSetting.getCurrDate(),
                                reportSetting.getCurrEducDate(), inqryInf)) {
                            Collection<TypeAgeRange> ageRanges =
                                    counterConfig.getCounterDef().getAgeRange()
                                            .getAgeRanges(reportSetting.getCurrDate(), inqryInf);
                            if (ageRanges != null && !ageRanges.isEmpty()) {
                                // Посчитать элемент
                                Counter counter = counterMap.computeIfAbsent(counterConfig.getCounterDef().getId(),
                                        counterId -> new Counter(counterConfig.getCounterDef()));
                                counter.count(inqryInf, ageRanges);
                            }
                        }
                    }
                }
            }
        }
        if (counterMap.size() > 0) {
            log.info("Counters: {}", counterMap);
        }
/* Пример построения отчета CReportDataAdapter
    private IReport createReport(IPushDataRequest p_request, Pair<UchInf, SchemaConfig> p_uch_inf_schema)
                    throws CBaseInqryDbBLException {
        UchInf x_uch_inf = p_uch_inf_schema.getFirst();
        SchemaConfig x_schema = p_uch_inf_schema.getSecond();
        IReport x_report = p_request.createReport();
        // Установить информацию об муниципалитете и организации
        x_report.fillData(x_uch_inf);
        // init counters
        addCounters(x_report, x_schema.getSource().getInqryCounters());
        addCounters(x_report, x_schema.getSource().getEnrolledCounters());
        addCounters(x_report, x_schema.getSource().getCapacityCounters());
        return x_report;
    }

    private void addCounters(IReport p_report, List<CounterConfig> p_counters_config) {
        for (CounterConfig x_counter_config : p_counters_config) {
            p_report.initCounter(x_counter_config, IDataAdapter.DataMode.Detail.equals(m_mode));
        }
    }
*/

/* продолжить...
        List<TagMunicipality> municipality = new ArrayList<>();
        Map<String, TagMunicipality> municipalityMap = new HashMap<>();

        for (ApplicationsEntity application : allApplications) {
            TagMunicipality tagMunicipality = municipalityMap.get(application.getOktmo());
            if (tagMunicipality == null) {
                tagMunicipality = new TagMunicipality();
                tagMunicipality.setOktmo(application.getOktmo());
                //TODO заполнить атрибуты
                municipalityMap.put(application.getOktmo(), tagMunicipality);
            }
            TagSingleOrganization tagSingleOrganization = tagMunicipality.getOrganizations()
                    .getOrganization()
                    .stream()
                    .filter(organization -> organization.getCode().equals(application.getOrganizationCode()))
                    .findFirst()
                    .orElse(null);
            if (tagSingleOrganization == null) {
                TagSingleOrganization newTagSingleOrganization = new TagSingleOrganization();
                //TODO заполнить атрибуты
                tagSingleOrganization.setCode("");
                tagMunicipality.getOrganizations().getOrganization().add(newTagSingleOrganization);
                //TODO заполнить buildings
            }

            for (SchemaConfig schema : configInformica.getSchemas()) {
                for (CounterConfig inqryCounter : schema.getSource().getInqryCounters()) {
//application TODO объект класса в котором есть все поля из field в теге filter
// (<filter field="typeCode" cmp="eq" value="01"/>)
                    Object value = new Object();
                    if (inqryCounter.isPassed(
                            currDate,
                            currEducDate,
                            value)) {
                        //TODO инкрементирование счетчиков
                    }
                }
            }
        }
*/
        return null;
    }

    private void calculateIndicatorAddCont(List<InqryEnrolmentInf> allInqryEnrolments,
                                           Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        int countNotDistributed = 0;
        boolean isDistributed;
        inqryEnrolment:
        for (InqryEnrolmentInf ie : allInqryEnrolments) {
            isDistributed = false;
            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                if (ie.getIdUch() == uchInfSchema.getUchInf().getId().intValue()) {
                    for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                        for (GroupInf groupInf : buildingInf.getGroupInfs()) {
// попадает ли ребенок в возрастной интервал группы
                            if (childInAgeGroup(ie, groupInf)) {
// Совпадает ли направленность группы
                                if (groupInf.getIdHealthCsp().contains(ie.getIdHealthCsp())) {
// Проверяем если есть в заявлении режим посещения группы
                                    if (((ie.getIdGrpTimesCsp().size() > 0) &&
                                            ie.getIdGrpTimesCsp().contains(groupInf.getIdWorkTimeCsp())) ||
                                            ie.getIdGrpTimesCsp().size() == 0) {
// Определяем свободные места в подходящей группе
                                        int freeSpace = Integer.parseInt(groupInf.getCapacity()) -
                                                Integer.parseInt(groupInf.getEnrolled());
                                        if (groupInf.getAddCont() != null) {
                                            freeSpace += Integer.parseInt(groupInf.getAddCont());
                                        }
                                        if (freeSpace > 0) {
// Ребенок по этому заявлению идет в эту группу
                                            int addCont = 1;
                                            if (groupInf.getAddCont() != null) {
                                                addCont += Integer.parseInt(groupInf.getAddCont());
                                            }
                                            groupInf.setAddCont(Integer.toString(addCont));
                                            isDistributed = true;
                                            continue inqryEnrolment;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!isDistributed) {
// распределяем в первую попавшуюся по характеристикам группу
                        for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                            for (GroupInf groupInf : buildingInf.getGroupInfs()) {
// попадает ли ребенок в возрастной интервал группы
                                if (childInAgeGroup(ie, groupInf)) {
// Совпадает ли направленность группы
                                    if (groupInf.getIdHealthCsp().contains(ie.getIdHealthCsp())) {
// Проверяем если есть в заявлении режим посещения группы
                                        if (((ie.getIdGrpTimesCsp().size() > 0) &&
                                                ie.getIdGrpTimesCsp().contains(groupInf.getIdWorkTimeCsp())) ||
                                                ie.getIdGrpTimesCsp().size() == 0) {
// Ребенок по этому заявлению идет в эту группу
                                            int addCont = 1;
                                            if (groupInf.getAddCont() != null) {
                                                addCont += Integer.parseInt(groupInf.getAddCont());
                                            }
                                            groupInf.setAddCont(Integer.toString(addCont));
                                            isDistributed = true;
                                            continue inqryEnrolment;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!isDistributed) {
// не нашли группы подходящей по характеристикам
                        countNotDistributed++;
/*
 TODO
 */
                    }
                }
            }

        }
        log.info("Не распределено {} inqry-enrolment", countNotDistributed);
    }

    private boolean childInAgeGroup(InqryEnrolmentInf ie, GroupInf groupInf) {
        int diffYears = DateUtil.getYearPart(reportSetting.getCurrDate()) - DateUtil.getYearPart(ie.getBDt());
        int monthDiff = DateUtil.getMonthPart(reportSetting.getCurrDate()) - DateUtil.getMonthPart(ie.getBDt());
        int totalMonths = diffYears * 12 + monthDiff;

        int fromMonth = groupInf.getAgeFromYears().intValue() * 12 + groupInf.getAgeFromMonths().intValue();
        int toMonth = groupInf.getAgeToYears().intValue() * 12 + groupInf.getAgeToMonths().intValue();
        if (totalMonths >= fromMonth && totalMonths <= toMonth) {
            return true;
        } else {
            return false;
        }
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
