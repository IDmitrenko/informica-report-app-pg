package ru.avers.informica.report.builder.age;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.xml.TagAge1;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
public class Age1Builder implements AgeBuilder {
    @Override
    public void build(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws Exception {
        Counter counter = countersUch.get(key);
        TagAge1 age1 = new TagAge1();
        if (counter.getAge() == null) {
            age1.setAll(0);
        } else {
            age1.setAll(counter.getAge().get(0).getValue());
        }
        //counter.getId() это ind_1_1 например
        //counter.getId() это имя поля в классе TagSingleOrganization
        BeanUtils.setProperty(organization, counter.getId(), age1);
    }
}
