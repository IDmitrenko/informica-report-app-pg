package ru.avers.informica.report.indicator;

import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;

import java.util.Collection;

public interface Indicator {
    void calculateIndicator(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas);
}
