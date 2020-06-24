package ru.avers.informica.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.dao.ApplicationsDao;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dto.informica.InqryInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.entities.ApplicationsEntity;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.filtersinqry.FilterChain;
import ru.avers.informica.filtersinqry.IFilter;
import ru.avers.informica.infcfg.*;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.xml.*;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.CUtil;
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
    private final FilterChain filterChain;

    public PushDataRequest generateReport(CProfile cProfile) throws FilterException, ReportExceprion {
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

//        List<ApplicationsEntity> allApplications = applicationDao.getAllApplications();
//        log.info("Found {} applications", allApplications.size());
//  считать InqryInf
        List<InqryInf> allInqry = inqryDao.getAllInqry(currDate,
                currEducDate,
                beginCurrYear.getTime());
        log.info("Найдено {} inqry-source", allInqry.size());

        final ReportConfig reportConfig = configInformica.getReport(Config.s_informica_report);
        List<SchemaConfig> schemaConfigs = reportConfig.getSchemas();
        // Собрать базовую информацию по учреждениям и их схемы показателей
        DataSourceUch sourceUch = new DataSourceUch(uchDao, schemaConfigs, currDate, currEducDate);


        TagReports tagReports = new TagReports();
        tagReports.setParent_Pay(parentPayBuider());

        Map<Integer, List<InqryInf>> inqryByUchMap = allInqry.stream().collect(Collectors.groupingBy(inqry -> inqry.getIdUch()));
        for (DataSourceUch.UchInfSchema uchInfSchema : sourceUch.getUchInfSchemas().getFirst()) {
            //Учреждение
            UchInf uchInf = uchInfSchema.getUchInf();
            //Счетчики учреждения
            List<CounterConfig> inqryCounters = uchInfSchema.getSchema().getSource().getInqryCounters();
            //Заявления текущего учреждения
            List<InqryInf> inqryInfs = inqryByUchMap.get(uchInf.getId());
            //Пройтись по каждому заявлению и посчитать счетчики
            for (InqryInf inqryInf : inqryInfs) {
                //Для каждого счетчика проверить нужно ли его инкрементировать для текущего заявления
                for (CounterConfig counter : inqryCounters) {
                    if (counter.isPassed(currDate, currEducDate, inqryInf)) {
                        Collection<TypeAgeRange> ageRanges =
                                counter.getCounterDef().getAgeRange().getAgeRanges(currDate, inqryInf);
                        if (ageRanges != null && !ageRanges.isEmpty()) {
                            // Посчитать элемент
                            x_counter.count(p_countable, ageRanges);
                        }
                    }
                }
            }

        }

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
