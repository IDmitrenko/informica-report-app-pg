package ru.avers.informica.report.builder.age;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.xml.TagAge16;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
public class Age16builder implements AgeBuilder {
    @Override
    public void build(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws Exception {
        Counter counter = countersUch.get(key);
        TagAge16 age16 = new TagAge16();
        //заполнить age16
        BeanUtils.setProperty(organization, counter.getId(), age16);
    }
}
