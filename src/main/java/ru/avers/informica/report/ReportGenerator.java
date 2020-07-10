package ru.avers.informica.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dao.MunicipalityDao;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dto.informica.InqryInf;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.dto.informica.UchInf;
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

import java.util.*;
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

    public PushDataRequest generateReport(CProfile cProfile)
            throws FilterException, ReportExceprion, FspeoException {
        Config configInformica = cHelper.getInformicaConfig();
        PushDataRequest request = new PushDataRequest();
        request.setSystem(buildSystemInfo(configInformica));
        request.setSchemaVersion(schemaBuilder());
        request.setReports(reportBuilder(cProfile, configInformica));
        return request;
    }

    private TagReports reportBuilder(CProfile cProfile, Config configInformica) throws FilterException, ReportExceprion, FspeoException {

        final Date currDate = DateUtil.getCurrentDate(false);
        final Date currEducDate = DateUtil.getCurrEducDate(currDate,
                cProfile.getMisc().getInqryEducYearBegin().getMonth(),
                cProfile.getMisc().getInqryEducYearBegin().getDay());
        final Calendar beginCurrYear = GregorianCalendar.getInstance(); // Начало текущего календарного года
        DateUtil.clearCalendarTimePart(beginCurrYear);
        beginCurrYear.set(Calendar.DAY_OF_MONTH, 1);
        beginCurrYear.set(Calendar.MONTH, Calendar.JANUARY);

//  считать InqryInf
        List<InqryInf> allInqry = inqryDao.getAllInqry(currDate,
                currEducDate,
                beginCurrYear.getTime());
        log.info("Найдено {} inqry-source", allInqry.size());

        final ReportConfig reportConfig = configInformica.getReport(Config.S_INFORMICA_REPORT);
        List<SchemaConfig> schemaConfigs = reportConfig.getSchemas();
        // Собрать базовую информацию по учреждениям и их схемы показателей
        DataSourceUch sourceUch = new DataSourceUch(uchDao, schemaConfigs, currEducDate);

        TagReports tagReports = new TagReports();
        tagReports.setParent_Pay(parentPayBuider());

        Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas = sourceUch.getUchInfSchemas();

        List<MunicipalityInf> allMunicipalityInfs = municipalityDao.getMunicipalitys(currDate, currEducDate);
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

        Map<Integer, List<InqryInf>> inqryByUchMap = allInqry.stream()
                .collect(Collectors.groupingBy(inqry -> inqry.getIdUch()));
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
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
                    for (CounterConfig counter : inqryCounters) {
                        if (counter.isPassed(currDate, currEducDate, inqryInf)) {
                            Collection<TypeAgeRange> ageRanges =
                                    counter.getCounterDef().getAgeRange().getAgeRanges(currDate, inqryInf);
                            if (ageRanges != null && !ageRanges.isEmpty()) {
                                // Посчитать элемент
//TODO                            x_counter.count(p_countable, ageRanges);
                            }
                        }
                    }
                }
            }
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
