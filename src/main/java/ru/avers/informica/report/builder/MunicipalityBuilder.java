package ru.avers.informica.report.builder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.provider.InqryCountersProvider;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;
import ru.avers.informica.report.xml.TagCommon;
import ru.avers.informica.report.xml.TagCommonAged;
import ru.avers.informica.report.xml.TagMunicipality;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MunicipalityBuilder {
    private final InqryCountersProvider counterProvider;
    private final OrganizationBuilder organizationBuilder;

    public TagMunicipality build(MunicipalityInf municipalityInf, Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) throws Exception {
        TagMunicipality municipality = new TagMunicipality();
        Map<Long, Map<String, Counter>> counterMap = counterProvider.provideCounters(uchInfSchemas);
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
        municipality.setOrganizations(organizationBuilder.build(municipalityInf,
                uchInfSchemas.getFirst(), counterMap));
        return municipality;
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
}
