package ru.avers.informica.report.builder.age;

import ru.avers.informica.report.Counter;
import ru.avers.informica.report.CounterSpecial;
import ru.avers.informica.report.xml.TagSingleOrganization;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface AgeBuilder {
    void build(String key, Map<String, Counter> countersUch,
               TagSingleOrganization organization) throws Exception;

}
