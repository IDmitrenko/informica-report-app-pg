package ru.avers.informica.report.indicator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportIndicators {
    private final List<Indicator> allIndicators;

    public void calculateAllIndicators(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        allIndicators.stream().forEach(ind -> ind.calculateIndicator(uchInfSchemas));
    }
}
