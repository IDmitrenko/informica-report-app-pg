package ru.avers.informica.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.dao.ApplicationsDao;
import ru.avers.informica.entities.ApplicationsEntity;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.filtersinqry.FilterChain;
import ru.avers.informica.infcfg.*;
import ru.avers.informica.report.xml.*;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.DateUtil;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportGenerator {
    private final CHelper cHelper;
    private final ApplicationsDao applicationDao;
    private final FilterChain filterChain;

    public PushDataRequest generateReport(CProfile cProfile) throws FilterException {
        PushDataRequest request = new PushDataRequest();
        request.setSystem(buildSystemInfo(cProfile));
        request.setSchemaVersion(schemaBuilder(cProfile));
        request.setReports(reportBuilder(cProfile));
        return request;
    }

    private TagReports reportBuilder(CProfile cProfile) throws FilterException {
        Config configInformica = cHelper.getInformicaConfig();
        List<ApplicationsEntity> allApplications = applicationDao.getAllApplications();
        log.info("Found {} applications", allApplications.size());
        TagReports tagReports = new TagReports();
        tagReports.setParent_Pay(parentPayBuider());

        List<TagMunicipality> municipality = new ArrayList<>();
        Map<String, TagMunicipality> municipalityMap = new HashMap<>();
        Date currentDate = new Date();
        Date x_curr_educ_date = DateUtil.getCurrEducDate(currentDate, cProfile.getMisc().getInqryEducYearBegin().getMonth(), cProfile.getMisc().getInqryEducYearBegin().getDay());

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
                    Object value = new Object();//application TODO объект класса в котором есть все поля из field в теге filter (<filter field="typeCode" cmp="eq" value="01"/>)
                    if (inqryCounter.isPassed(
                            currentDate,
                            x_curr_educ_date,
                            value)) {
                        //TODO инкрементирование счетчиков
                    }
                }
            }
        }

        return null;
    }

    private TagParentPay parentPayBuider() {
        return null;

    }

    private TypeSchemaVersion schemaBuilder(CProfile cProfile) {
        return TypeSchemaVersion.tFiveDotO;
    }

    private TagSystem buildSystemInfo(CProfile cProfile) {
        TagSystem tagSystem = new TagSystem();
        tagSystem.setEmail("");
        tagSystem.setInstall_Type("");
        tagSystem.setName("");
        tagSystem.setOwn_Server("");
        tagSystem.setVendor("");
        tagSystem.setVersion("");
        return tagSystem;
    }
}
