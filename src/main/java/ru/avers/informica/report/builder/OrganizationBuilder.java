package ru.avers.informica.report.builder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.IndicatorType;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.report.builder.age.Age16builder;
import ru.avers.informica.report.builder.age.Age1Builder;
import ru.avers.informica.report.builder.age.Age8Builder;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.xml.TagAge1;
import ru.avers.informica.report.xml.TagAge16;
import ru.avers.informica.report.xml.TagOrganizations;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrganizationBuilder {
    private final BuildingsBuilder buildingsBuilder;
    private final Age16builder age16builder;
    private final Age8Builder age8Builder;
    private final Age1Builder age1Builder;
    private final ReportSetting reportSetting;

    public TagOrganizations build(MunicipalityInf municipalityInf,
                                  Collection<DataSourceUch.UchInfSchema> uchInfSchemas,
                                  Map<Long, Map<String, Counter>> counterMap) throws Exception {
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
                                                      Map<String, Counter> countersUch) throws Exception {
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
        organization.setBuildings(buildingsBuilder.build(uchInf));
        for (Map.Entry<String, IndicatorType> entry : reportSetting.getCounterNameToAgeType().entrySet()) {
            switch (entry.getValue()) {
                case AGE1:
                    age1Builder.build(entry.getKey(), countersUch, organization);
                    break;
                case AGE16:
                    age16builder.build(entry.getKey(), countersUch, organization);
                    break;
                case AGE8:
                case AGE8SPECIAL:
                    age8Builder.build(entry.getKey(), countersUch, organization);
                    break;
            }
        }
        return organization;
    }
}
