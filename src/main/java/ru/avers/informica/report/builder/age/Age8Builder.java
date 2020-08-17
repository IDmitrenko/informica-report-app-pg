package ru.avers.informica.report.builder.age;

import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.informica.AgeTransformer;
import ru.avers.informica.report.xml.TagAge16;
import ru.avers.informica.report.xml.TagAge8;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Age8Builder implements AgeBuilder {
    private final AgeTransformer ageTransformer;

    @Override
    public void build(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws Exception {
        Counter counter = countersUch.get(key);
        TagAge8 tagAge8 = ageTransformer.transformToAge8(counter.getAge());
        //заполнить age8
        BeanUtils.setProperty(organization, counter.getId(), tagAge8);
    }
}
