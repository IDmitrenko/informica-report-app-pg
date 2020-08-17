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
    private final CHelper cHelper;
    private final ApplicationsDao applicationDao;
    private final InqryDao inqryDao;
    private final UchDao uchDao;
    private final MunicipalityDao municipalityDao;
    private final FilterChain filterChain;
    private final ReportSetting reportSetting;

    public PushDataRequest generateReport(CProfile cProfile)
            throws FilterException, ReportExceprion, FspeoException, InvocationTargetException, IllegalAccessException {
        Config configInformica = cHelper.getInformicaConfig();
        PushDataRequest request = new PushDataRequest();
        request.setSystem(buildSystemInfo(configInformica));
        request.setSchemaVersion(schemaBuilder());
        request.setReports(reportBuilder(cProfile, configInformica));
        return request;
    }

    private TagReports reportBuilder(CProfile cProfile, Config configInformica)
            throws FilterException, ReportExceprion, FspeoException, InvocationTargetException, IllegalAccessException {

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

        Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas = sourceUch
                .getUchInfSchemas();

// рассчитать показатель add_cont
        calculateIndicatorAddCont(allInqryEnrolments, uchInfSchemas);

// Отладочный вывод TODO убрать по завершении отладки
        int countEnrolment = 0;
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                    if (groupInf.getAddCont() != null) {
                        countEnrolment += groupInf.getAddCont();
                    }
                }
            }
        }
        log.info("Распределено в итоге {} inqry-enrolment", countEnrolment);

// рассчитать показатель free_space
        calculateIndicatorFreeSpace(uchInfSchemas);

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

        allMunicipalityInfs = null;
        noValidMunicipalitys = null;

        Map<Long, List<InqryInf>> inqryByUchMap = allInqry.stream()
                .collect(Collectors.groupingBy(inqry -> inqry.getIdUch().longValue()));
        Map<Long, Map<String, Counter>> counterMap = new HashMap<>();
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            //Учреждение
            UchInf uchInf = uchInfSchema.getUchInf();
            counterMap.put(uchInf.getId(), new HashMap<>());
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
                                Counter counter = counterMap.get(uchInf.getId())
                                        .computeIfAbsent(counterConfig.getCounterDef().getId(),
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

// Формирование выходного XML-файла
        TagReports tagReports = new TagReports();
        for (MunicipalityInf municipalityInf : municipalityInfs) {
            TagMunicipality municipality = new TagMunicipality();

            municipalityBuilder(municipalityInf, municipality,
                    uchInfSchemas.getFirst(), counterMap);

            tagReports.getMunicipality().add(municipality);
        }
        tagReports.setParent_Pay(parentPayBuider());


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

    private void municipalityBuilder(MunicipalityInf municipalityInf,
                                     TagMunicipality municipality,
                                     Collection<DataSourceUch.UchInfSchema> uchInfSchemas,
                                     Map<Long, Map<String, Counter>> counterMap) throws InvocationTargetException, IllegalAccessException {
        municipality.setRegulation(municipalityInf.getRegulation());
        municipality.setOktmo(municipalityInf.getOktmo());
        municipality.setNum_Early_Assistance(municipalityInf.getNumEarlyAssistance());
        municipality.setNum_Advisory_Centr(municipalityInf.getNumAdvisoryCentr());
        municipality.setMax_Doo(municipalityInf.getMaxDoo());
        municipality.setFix_Area(municipalityInf.getFixArea());
        municipality.setTime_Mouo(municipalityInf.getTimeMouo());
        municipality.setPhones_Mouo(municipalityInf.getPhonesMouo());
        municipality.setEmail_Mouo(municipalityInf.getEmailMouo());
        municipality.setSite_Mouo(municipalityInf.getSiteMouo());
        municipality.setAddress_Mouo(municipalityInf.getAddressMouo());
        municipality.setName_Mouo(municipalityInf.getNameMouo());
        municipality.setRpgu_Link(municipalityInf.getRpguLink());
        municipality.setEpgu_Link(municipalityInf.getEpguLink());
        municipality.setCommon(commonBuilder(municipalityInf));
        municipality.setOrganizations(organizatiosBuilder(municipalityInf,
                uchInfSchemas, counterMap));
    }

    private TagCommon commonBuilder(MunicipalityInf municipalityInf) {
        TagCommon common = new TagCommon();

        common.setNo_Doo_Act(noDooActBuilder(municipalityInf));
        common.setNo_Doo_Def(noDooDefBuilder(municipalityInf));
        common.setMedic(medicBuilder(municipalityInf));
        common.setFamily(familyBuilder(municipalityInf));

        return common;
    }

    private TagCommonAged noDooActBuilder(MunicipalityInf municipalityInf) {
        TagCommonAged commonAged = new TagCommonAged();

        commonAged.setAge_0_3(municipalityInf.getNoDooAct_0_3());
        commonAged.setAge_3_7(municipalityInf.getNoDooAct_3_7());

        return commonAged;
    }

    private TagCommonAged noDooDefBuilder(MunicipalityInf municipalityInf) {
        TagCommonAged commonAged = new TagCommonAged();

        commonAged.setAge_0_3(municipalityInf.getNoDooDef_0_3());
        commonAged.setAge_3_7(municipalityInf.getNoDooDef_3_7());

        return commonAged;
    }

    private TagCommonAged medicBuilder(MunicipalityInf municipalityInf) {
        TagCommonAged commonAged = new TagCommonAged();

        commonAged.setAge_0_3(municipalityInf.getMedic_0_3());
        commonAged.setAge_3_7(municipalityInf.getMedic_3_7());

        return commonAged;
    }

    private TagCommonAged familyBuilder(MunicipalityInf municipalityInf) {
        TagCommonAged commonAged = new TagCommonAged();

        commonAged.setAge_0_3(municipalityInf.getFamily_0_3());
        commonAged.setAge_3_7(municipalityInf.getFamily_3_7());

        return commonAged;
    }

    private void calculateIndicatorFreeSpace(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        int freeSpace = 0;
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                    freeSpace = groupInf.getCapacity() - groupInf.getEnrolled() -
                            groupInf.getTransferSpace() - groupInf.getAddCont();
                    groupInf.setFreeSpace(freeSpace);
                }
            }
        }
    }

    private void calculateIndicatorAddCont(List<InqryEnrolmentInf> allInqryEnrolments,
                                           Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        int countNotDistributed = 0;
        int countEnrolment = 0;
        int freeSpace = 0;
        boolean isDistributed;
        List<InqryEnrolmentInf> enrolmentsNotDistributed = new ArrayList<>();
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
/*
                                        int freeSpace = Integer.parseInt(groupInf.getCapacity()) -
                                                Integer.parseInt(groupInf.getEnrolled());
                                        if (groupInf.getAddCont() != null) {
                                            freeSpace -= Integer.parseInt(groupInf.getAddCont());
                                        }
*/
                                        if (groupInf.getAddCont() == null) {
                                            freeSpace = groupInf.getCapacity() -
                                                    groupInf.getEnrolled();
                                        } else {
                                            freeSpace = groupInf.getCapacity() -
                                                    groupInf.getEnrolled() - groupInf.getAddCont();
                                        }
                                        if (freeSpace > 0) {
// Ребенок по этому заявлению идет в эту группу
                                            int addCont = 1;
/*
                                            if (groupInf.getAddCont() != null) {
                                                addCont += Integer.parseInt(groupInf.getAddCont());
                                            }
                                            groupInf.setAddCont(Integer.toString(addCont));
*/
                                            if (groupInf.getAddCont() != null) {
                                                addCont += groupInf.getAddCont();
                                            }
                                            groupInf.setAddCont(addCont);
                                            isDistributed = true;
                                            countEnrolment++;
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
/*
                                            if (groupInf.getAddCont() != null) {
                                                addCont += Integer.parseInt(groupInf.getAddCont());
                                            }
                                            groupInf.setAddCont(Integer.toString(addCont));
*/
                                            if (groupInf.getAddCont() != null) {
                                                addCont += groupInf.getAddCont();
                                            }
                                            groupInf.setAddCont(addCont);
                                            isDistributed = true;
                                            countEnrolment++;
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
                        enrolmentsNotDistributed.add(ie);
                    }
                }
            }

        }
        log.info("Распределено в подходящие группы {} inqry-enrolment", countEnrolment);
/*
 Распределяем путевки для которых не нашлось групп, подходящих по характеристикам
 Когда нет группы, подходящей по характеристикам,
 оставшиеся путёвки необходимо распределять равномерно по всем группам.
 Например, если осталось 9 нераспределённых путёвок,
 для которых ни одна группа не подходит по характеристикам,
 а групп в организации всего 3,
 то необходимо распределить по 3 путёвки в каждую группу, соответственно.
 */
        if (countNotDistributed > 0) {
            log.info("Не распределено {} inqry-enrolment", countNotDistributed);
            int countDistributedNot = 0;
// не распределенные путевки по учреждениям
            Map<Integer, List<InqryEnrolmentInf>> inqryEnrolmentByUchMap = enrolmentsNotDistributed.stream()
                    .collect(Collectors.groupingBy(enrolment -> enrolment.getIdUch()));
/*
            int inqryUchNotDistributed = 0;
            for (InqryEnrolmentInf iei : enrolmentsNotDistributed) {
                if (iei.getIdUch() == 1161) {
                    inqryUchNotDistributed++;
                }
            }
*/
            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                List<InqryEnrolmentInf> inqrysUchEnrolment = inqryEnrolmentByUchMap
                        .get(uchInfSchema.getUchInf().getId().intValue());
                if (inqrysUchEnrolment != null) {
// у нас есть учреждение и все его не распределенные заявления
// количество не распределенных заявлений
                    int countInqrysUchNotDistributed = inqrysUchEnrolment.size();
// считаем количество групп учреждения
                    int countGroups = 0;
                    int currAddCont = 0;
                    for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                        countGroups += buildingInf.getGroupInfs().size();
                    }
// определяем начальное количество заявлений для распределения в группу
                    int countInqrysGroupNotDistributed = 0;
                    countInqrysGroupNotDistributed = (int) Math
                            .ceil((double) countInqrysUchNotDistributed / countGroups);

                    if (countInqrysGroupNotDistributed > 0) {
                        outer:
                        for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                            for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                                if (groupInf.getAddCont() != null) {
                                    currAddCont = groupInf.getAddCont();
                                } else {
                                    currAddCont = 0;
                                }
                                if (countInqrysUchNotDistributed > countInqrysGroupNotDistributed) {
                                    groupInf.setAddCont(currAddCont + countInqrysGroupNotDistributed);
                                    countInqrysUchNotDistributed -= countInqrysGroupNotDistributed;
                                    countDistributedNot += countInqrysGroupNotDistributed;
                                } else {
                                    groupInf.setAddCont(currAddCont + countInqrysUchNotDistributed);
                                    countDistributedNot += countInqrysUchNotDistributed;
                                    break outer;
                                }
                            }
                        }
                    }
                }
            }
            log.info("Распределено равномерно по группам не подходящих {} inqry-enrolment", countDistributedNot);
        }
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

    private TagOrganizations organizatiosBuilder(MunicipalityInf municipalityInf,
                                                 Collection<DataSourceUch.UchInfSchema> uchInfSchemas,
                                                 Map<Long, Map<String, Counter>> counterMap) throws InvocationTargetException, IllegalAccessException {
        TagOrganizations organizations = new TagOrganizations();

/* то же самое stream
        Collection<DataSourceUch.UchInfSchema> uchInfSchemasTer = new ArrayList<DataSourceUch.UchInfSchema>();
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas) {
            if (uchInfSchema.getUchInf().getIdTer() == municipalityInf.getIdTer()) {
                uchInfSchemasTer.add(uchInfSchema);
            }
        }
*/
        List<DataSourceUch.UchInfSchema> uchInfSchemasTer = uchInfSchemas
                .stream().filter(s -> s.getUchInf().getIdTer().equals(municipalityInf.getIdTer()))
                .collect(Collectors.toList());

        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemasTer) {

            Map<String, Counter> countersUch = counterMap.get(uchInfSchema.getUchInf().getId());

            TagSingleOrganization organization = organizationBuilder(uchInfSchema.getUchInf(),
                    countersUch);

            organizations.getOrganization().add(organization);
        }

        return organizations;
    }

    private TagSingleOrganization organizationBuilder(UchInf uchInf,
                                                      Map<String, Counter> countersUch) throws InvocationTargetException, IllegalAccessException {
        TagSingleOrganization organization = new TagSingleOrganization();

        organization.setCode(uchInf.getCode());
        organization.setName(uchInf.getName());
        organization.setType(uchInf.getOrgLegalFormCode());
        organization.setStatus(uchInf.getStatusСode());
        organization.setStructure(uchInf.getStructureCode());
        organization.setLicense(uchInf.getLicense());
        organization.setType_Area(uchInf.getTypeArea());
        organization.setDirector(uchInf.getChief());
        organization.setWorktime(uchInf.getWorkTime());
        organization.setMeal_Serving_Type(uchInf.getMealServingType());
        organization.setFias_Org_Guid(uchInf.getFiasOrgGuid());
        organization.setOrg_Address(uchInf.getAddrKladr());
        organization.setWebsite(uchInf.getWebsite());
        organization.setEmail(uchInf.getEmail());
        organization.setPhone(uchInf.getPhone());
        organization.setAdditional_Education(uchInf.getAddEducation());
        organization.setFeature(uchInf.getFeatures());
        organization.setNum_Filial(uchInf.getNumFilial());
        organization.setNum_Building(uchInf.getNumBuilding());
        organization.setNum_Group(uchInf.getNumGroup());
        organization.setPartner_Doo(uchInf.getPartnerDoo());
        organization.setPassport(uchInf.getPassport());
        organization.setLekoteka(uchInf.getLekoteka());
        organization.setCentre_Game(uchInf.getCentreGame());
        organization.setCommet_Status(uchInf.getCommetStatus());

        organization.setBuildings(buildingsBuilder(uchInf));

        //TODO Когда будут добавляться enrolled  счетчики надо учесть что ключи будут одинаковые!!!
        Map<String, IndicatorType> map = Stream.of(new Object[][]{
                {"counter-1", IndicatorType.AGE1},
                {"counter-1.1", IndicatorType.AGE16},
                {"counter-2", IndicatorType.AGE16},
                {"counter-3", IndicatorType.AGE16},
                {"counter-10", IndicatorType.AGE1}
                //TODO добавить остальные индикаторы
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (IndicatorType) data[1]));

        for (Map.Entry<String, IndicatorType> entry : map.entrySet()) {
            switch (entry.getValue()) {
                case AGE1:
                    age1Builder(entry.getKey(), countersUch, organization);
                    break;
                case AGE16:
                    age16Builder(entry.getKey(), countersUch, organization);
                    break;
                case AGE8:
                case AGE8SPECIAL:

            }
        }

/* Заготовка моего варианта в лоб
        if (countersUch != null) {
            organization.setInd_1(ind_1Builder(countersUch));
            organization.setInd_1_1(ind_1_1Builder(countersUch));


        }
*/

        return organization;
    }

    private void age16Builder(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws InvocationTargetException, IllegalAccessException {
        Counter counter = countersUch.get(key);
        TagAge16 age16 = new TagAge16();
        //заполнить age16
        BeanUtils.setProperty(organization, counter.getId(), age16);
    }

    private void age1Builder(String key, Map<String, Counter> countersUch,
                             TagSingleOrganization organization) throws InvocationTargetException, IllegalAccessException {
        Counter counter = countersUch.get(key);
        TagAge1 age1 = new TagAge1();
        if(counter.getAge() == null){
            age1.setAll(0);
        }else {
            age1.setAll(counter.getAge().get(0).getValue());
        }
        //counter.getId() это ind_1_1 например
        //counter.getId() это имя поля в классе TagSingleOrganization
        BeanUtils.setProperty(organization, counter.getId(), age1);
    }

    private TagBuildings buildingsBuilder(UchInf uchInf) {

        TagBuildings buildings = new TagBuildings();

        for (BuildingInf building : uchInf.getBuildingInfs()) {
            TagSingleBuilding sb = new TagSingleBuilding();

            sb.setId(building.getIdCode());
            sb.setName(building.getName());
            sb.setFias_House_Guid(building.getFiasHouseGuid());
            sb.setPlain_Address(building.getPlainAddress());
            sb.setBuilding_Type_Area(building.getBuildingTypeArea());
            sb.setType_Building(building.getTypeBuilding());
            sb.setFilial(building.getFilial());
            sb.setDepreciation(building.getDepreciation());
            sb.setPool(building.getPool());
            sb.setEar_Equipment(building.getEarEquipment());
            sb.setEar_Path(building.getEarPath());
            sb.setEar_Communication(building.getEarCommunication());
            sb.setEar_Washroom(building.getEarWashroom());
            sb.setEar_Room(building.getEarRoom());
            sb.setEar_Way(building.getEarWay());
            sb.setEar_Entrance(building.getEarEntrance());
            sb.setEar_Territory(building.getEarTerritory());
            sb.setVision_Equipment(building.getVisionEquipment());
            sb.setVision_Path(building.getVisionPath());
            sb.setVision_Communication(building.getVisionCommunication());
            sb.setVision_Washroom(building.getVisionWashroom());
            sb.setVision_Room(building.getVisionRoom());
            sb.setVision_Way(building.getVisionWay());
            sb.setVision_Entrance(building.getVisionEntrance());
            sb.setVision_Territory(building.getVisionTerritory());
            sb.setOda_Equipment(building.getOdaEquipment());
            sb.setOda_Path(building.getOdaPath());
            sb.setOda_Communication(building.getOdaCommunication());
            sb.setOda_Washroom(building.getOdaWashroom());
            sb.setOda_Room(building.getOdaRoom());
            sb.setOda_Way(building.getOdaWay());
            sb.setOda_Entrance(building.getOdaEntrance());
            sb.setOda_Territory(building.getOdaTerritory());
            sb.setMeeting_Room(building.getMeetingRoom());
            sb.setSport_Gym(building.getSportGym());
            sb.setCabinet_Med(building.getCabinetMed());
            sb.setCabinet_Logopedist(building.getCabinetLogopedist());
            sb.setCabinet_Defectologist(building.getCabinetDefectologist());
            sb.setCabinet_Psychologist(building.getCabinetPsychologist());
            sb.setStatus_building(building.getStatusBuilding());

            for (GroupInf group : building.getGroupInfs()) {

                TagSingleGroup sg = groupBuilder(group);

                sb.getGroup().add(sg);
            }

            buildings.getBuilding().add(sb);
        }

        return buildings;
    }

    private TagSingleGroup groupBuilder(GroupInf group) {
        TagSingleGroup sg = new TagSingleGroup();

        sg.setId(group.getIdCode());
        sg.setName(group.getName());
        sg.setAge_From(group.getAgeFrom().toString());
        sg.setAge_To(group.getAgeTO().toString());
        sg.setOrientation(group.getOrientation());
        sg.setWorktime_Group(group.getWorktimeGroup());
        sg.setActivity(group.getActivity());
        sg.setCapacity(group.getCapacity());
        sg.setEnrolled(group.getEnrolled());
        sg.setSubgroup(group.getSubgroup());
        sg.setOvz_Deti(group.getOvzDeti());
        sg.setFree_Space(group.getFreeSpace());
        sg.setAdd_Cont(group.getAddCont());
        sg.setTransfer_Space(group.getTransferSpace());
        sg.setPartner_Group(group.getPartnerGroup());
        sg.setPartner(group.getPartner());
        sg.setDays(group.getDays());
        sg.setEducator(group.getEducator());
        sg.setInvalid(group.getInvalid());
        sg.setSize(group.getSize());
        sg.setProgram(group.getProgram());
        sg.setReduction_Other(group.getReductionOther());
        sg.setReduction_School(group.getReductionSchool());
        sg.setAdd_Cont_Ovz(group.getAddContOvz());
        sg.setAdd_Cont_Gkp(group.getAddContGkp());
        sg.setEnrolled_Gkp(group.getEnrolledGkp());
        sg.setCapacity_Gkp(group.getCapacityGkp());
        sg.setProgram_Ovz(group.getProgramOvz());
        if (group.getOvzType() != null) {
            sg.setOvz_Type(group.getOvzType());
        }
        if (group.getOvzTypeDop() != null) {
            sg.setOvz_Type_Dop(group.getOvzTypeDop());
        }
        if (group.getOvzTypeNew() != null) {
            sg.setOvz_Type_New(group.getOvzTypeNew());
        }
        if (group.getWellness() != null) {
            sg.setWellness(group.getWellness());
        }

        return sg;
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
