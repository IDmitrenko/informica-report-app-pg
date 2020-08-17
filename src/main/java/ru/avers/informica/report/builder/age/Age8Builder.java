package ru.avers.informica.report.builder.age;

import org.springframework.stereotype.Component;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.util.Map;

@Component
public class Age8Builder implements AgeBuilder {
    @Override
    public void build(String key, Map<String, Counter> countersUch, TagSingleOrganization organization) throws Exception {

    }
}
