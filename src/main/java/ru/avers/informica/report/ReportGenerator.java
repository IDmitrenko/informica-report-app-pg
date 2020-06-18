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
import ru.avers.informica.filtersinqry.FilterChain;
import ru.avers.informica.infcfg.*;
import ru.avers.informica.report.xml.*;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.CUtil;
import ru.avers.informica.utils.DateUtil;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportGenerator {
    private final CHelper cHelper;
    private final ApplicationsDao applicationDao;
    private final InqryDao inqryDao;
    private final UchDao uchDao;
    private final FilterChain filterChain;

    public PushDataRequest generateReport(CProfile cProfile) throws FilterException {
        Config configInformica = cHelper.getInformicaConfig();
        PushDataRequest request = new PushDataRequest();
        request.setSystem(buildSystemInfo(configInformica));
        request.setSchemaVersion(schemaBuilder());
        request.setReports(reportBuilder(cProfile, configInformica));
        return request;
    }

    private TagReports reportBuilder(CProfile cProfile, Config configInformica) throws FilterException {

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

//  считать UchInf
        List<IFieldFilterParams> repForUchFilter = null;
        Set<Integer> notValidUchIds = new HashSet<Integer>();  // id не прошедших проверку учреждений
// сначала считываем все учреждения
//   TODO  разобрать далее - подумать куда это вынести (в модуль)
//        List<UchInf> validateUch = uchDao.getUchsInformica(repForUchFilter,
//                currDate, DateUtil.adjustDate(currEducDate, 1));
        List<UchInf> validateUch = uchDao.getUchInformica(repForUchFilter);
        log.info("Найдено {} uch-source", validateUch.size());

        StringBuilder uchMessage = new StringBuilder();
        String notValidUchMessage = "";
        for (UchInf uchInf : validateUch) {
            String checkRes = checkReportRequiredFields(uchInf);
            if (checkRes != null) {
                uchMessage.append(checkRes);
                notValidUchIds.add(uchInf.getId());
            }
        }
        if (!"".equals(uchMessage.toString())) {
           notValidUchMessage = "Следующие организации не включены в отчет, так как у них не заполнены " +
                   "обязательные поля:\n" + uchMessage.toString() + "\n";
        } else {
            notValidUchMessage = null;
        }

        // используем validateUch для отбора учреждений
        //TODO продолжить  (DataSourceUch - 102)

        TagReports tagReports = new TagReports();
        tagReports.setParent_Pay(parentPayBuider());

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

    static private String checkReportRequiredFields(UchInf uchInf) {
        //ОКТМО
        //code
        //type
        //status
        //structure
        boolean requiredFieldEmpty = CUtil.isStringNullOrBlank(uchInf.getMunicipObrOktmo()) ||
                CUtil.isStringNullOrBlank(uchInf.getCode()) ||
                CUtil.isStringNullOrBlank(uchInf.getOrgLegalFormCode()) ||
                CUtil.isStringNullOrBlank(uchInf.getStatusCode()) ||
                CUtil.isStringNullOrBlank(uchInf.getStructureCode());
        if (requiredFieldEmpty) {
            StringBuilder builder = new StringBuilder();
            builder.append("У организации '")
                    .append(uchInf.getName())
                    .append("' района")
                    .append(uchInf.getTerName())
                    .append("\nне заполнены поля: ");
            String delimiter = "";
            if (CUtil.isStringNullOrBlank(uchInf.getCode())) {
                builder.append("Код");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getMunicipObrOktmo())) {
                builder.append(delimiter).append("ОКТМО");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getOrgLegalFormCode())) {
                builder.append(delimiter).append("Орг.-правовая форма");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getStatusCode())) {
                builder.append(delimiter).append("Статус");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getStructureCode())) {
                builder.append(delimiter).append("Структура");
            }
            return builder.append("\n").toString();
        }
        return null;
    }
}
