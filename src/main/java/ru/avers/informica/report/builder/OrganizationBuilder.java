package ru.avers.informica.report.builder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.MunicipalityInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.CounterSpecial;
import ru.avers.informica.report.IndicatorType;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.report.builder.age.Age16builder;
import ru.avers.informica.report.builder.age.Age1Builder;
import ru.avers.informica.report.builder.age.Age8Builder;
import ru.avers.informica.report.builder.age.Age8SpecialBuilder;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.xml.TagOrganizations;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrganizationBuilder {
    private final BuildingsBuilder buildingsBuilder;
    private final Age16builder age16builder;
    private final Age8Builder age8Builder;
    private final Age8SpecialBuilder age8SpecialBuilder;
    private final Age1Builder age1Builder;
    private final ReportSetting reportSetting;

    public TagOrganizations build(MunicipalityInf municipalityInf,
                                  Collection<DataSourceUch.UchInfSchema> uchInfSchemas,
                                  Map<Long, Map<String, Counter>> counterMap,
                                  Map<Long, Map<String, CounterSpecial>> counterMapSpecial) throws Exception {
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
/*
        // список id учреждений текущего муниципалитета
        Set<Long> idUchTer = uchInfSchemasTer.stream()
                .map(u -> u.getUchInf().getId())
                .collect(Collectors.toSet());
        // список коунтеров учреждений текущего муниципалитета
        Map<Long, Map<String, Counter>> counterMapTer = counterMap.entrySet().stream()
                .filter(map -> uchInfSchemasTer.stream()
                        .map(u -> u.getUchInf().getId())
                        .collect(Collectors.toSet()).contains(map.getKey()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
*/
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemasTer) {
            Map<String, Counter> countersUch = counterMap.get(uchInfSchema.getUchInf().getId());
            Map<String, CounterSpecial> countersUchSpecial = counterMapSpecial.get(uchInfSchema.getUchInf().getId());
            TagSingleOrganization organization = organizationBuilder(uchInfSchema.getUchInf(),
                    countersUch, countersUchSpecial);
            organizations.getOrganization().add(organization);
        }
        return organizations;
    }

    private TagSingleOrganization organizationBuilder(UchInf uchInf,
                                                      Map<String, Counter> countersUch,
                                                      Map<String, CounterSpecial> countersUchSpecial) throws Exception {
        TagSingleOrganization organization = new TagSingleOrganization();

        organization.setCode(uchInf.getCode());
        organization.setName(uchInf.getName());
        organization.setType(uchInf.getOrgLegalFormCode().replaceAll("^0",""));
        organization.setStatus(uchInf.getStatusСode().replaceAll("^0",""));
        organization.setStructure(uchInf.getStructureCode().replaceAll("^0",""));
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
                    age8Builder.build(entry.getKey(), countersUch, organization);
                    break;
                case AGE8SPECIAL:
                    age8SpecialBuilder.build(entry.getKey(), countersUchSpecial, organization);
            }
        }
        return organization;
    }
}
