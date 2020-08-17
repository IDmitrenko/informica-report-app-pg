package ru.avers.informica.report.builder.age;

import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.informica.AgeTransformer;
import ru.avers.informica.report.xml.TagAge16;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Age16builder implements AgeBuilder {
    private final AgeTransformer ageTransformer;

    @Override
    public void build(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws Exception {
        Counter counter = countersUch.get(key);
        TagAge16 tagAge16 = ageTransformer.transformToAge16(counter.getAge());
        //заполнить age16
        BeanUtils.setProperty(organization, counter.getId(), tagAge16);
    }
}
