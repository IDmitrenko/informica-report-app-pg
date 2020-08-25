package ru.avers.informica.report.builder.age;

import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.AgeItemSpecial;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.CounterSpecial;
import ru.avers.informica.report.informica.AgeTransformer;
import ru.avers.informica.report.xml.TagAge8Special;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Age8SpecialBuilder {
    private final AgeTransformer ageTransformer;

    public void build(String key, Map<String, CounterSpecial> countersUch,
                      TagSingleOrganization organization) throws Exception {
        CounterSpecial counterSpecial = countersUch.get(key);
        TagAge8Special tagAge8 = ageTransformer.transformToAge8Special(counterSpecial.getAgeItemSpecials());
        //заполнить age8
        BeanUtils.setProperty(organization, counterSpecial.getId(), tagAge8);
    }
}
