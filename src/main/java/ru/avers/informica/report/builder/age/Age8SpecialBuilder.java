package ru.avers.informica.report.builder.age;

import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.informica.AgeTransformer;
import ru.avers.informica.report.xml.TagAge8Special;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Age8SpecialBuilder implements AgeBuilder {
    private final AgeTransformer ageTransformer;

    @Override
    public void build(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws Exception {
        Counter counter = countersUch.get(key);
//TODO???        TagAge8Special tagAge8 = ageTransformer.transformToAge8Special(counter.getAge());
        //заполнить age8
//        BeanUtils.setProperty(organization, counter.getId(), tagAge8);
    }
}
