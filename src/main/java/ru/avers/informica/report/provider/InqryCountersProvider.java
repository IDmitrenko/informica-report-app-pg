package ru.avers.informica.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.infcfg.CounterConfig;
import ru.avers.informica.infcfg.TypeAgeRange;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class InqryCountersProvider {
    private final ReportDataProvider reportDataProvider;
    private final ReportSetting reportSetting;

    public Map<Long, Map<String, Counter>> provideCounters(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas)
            throws ReportExceprion, FilterException, FspeoException {
        // Map по всем учреждениям - учреждения и его заявления
        Map<Long, List<InqryInf>> inqryByUchMap = reportDataProvider.getAllInqry().stream()
                .collect(Collectors.groupingBy(inqry -> inqry.getIdUch().longValue()));
        Map<Long, Map<String, Counter>> counterMap = new HashMap<>();
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            //Учреждение
            UchInf uchInf = uchInfSchema.getUchInf();
            counterMap.put(uchInf.getId(), new HashMap<>());
            //Счетчики учреждения
            List<CounterConfig> inqryCounters = uchInfSchema.getSchema().getSource().getInqryCounters();
            //Заявления текущего учреждения
            List<InqryInf> inqryInfs = inqryByUchMap.get(uchInf.getId());
            //Пройтись по каждому заявлению и посчитать счетчики

            //Инициализация счетчиков значениями по умолчанию
            for (CounterConfig counterConfig : inqryCounters) {
                Counter counter = counterMap.get(uchInf.getId())
                        .computeIfAbsent(counterConfig.getCounterDef().getId(),
                                counterId -> new Counter(counterConfig.getCounterDef()));
            }
            if (inqryInfs != null && inqryCounters != null) {
                for (InqryInf inqryInf : inqryInfs) {
                    //Для каждого счетчика проверить нужно ли его инкрементировать для текущего заявления
                    for (CounterConfig counterConfig : inqryCounters) {
                        if (counterConfig.isPassed(reportSetting.getCurrDate(),
                                reportSetting.getCurrEducDate(), inqryInf)) {
                            Collection<TypeAgeRange> ageRanges =
                                    counterConfig.getCounterDef().getAgeRange()
                                            .getAgeRanges(reportSetting.getCurrDate(), inqryInf);
                            if (ageRanges != null && !ageRanges.isEmpty()) {
                                // Посчитать элемент
                                Counter counter = counterMap.get(uchInf.getId())
                                        .get(counterConfig.getCounterDef().getId());
                                counter.count(inqryInf, ageRanges);
                            }
                        }
                    }
                }
            }
        }
        if (counterMap.size() > 0) {
            log.info("Counters: {}", counterMap);
        }
        return counterMap;
    }
}
