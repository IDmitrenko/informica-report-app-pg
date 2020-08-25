package ru.avers.informica.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.InqryInd8Inf;
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

import java.util.*;
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

    public Map<Long, Map<String, Counter>> provideCounters8(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas,
                                                            Map<Long, Map<String, Counter>> counterMap)
            throws ReportExceprion, FilterException, FspeoException {
        List<InqryInd8Inf> inqryInd8Infs = reportDataProvider.getInqriesInd8();
        if (inqryInd8Infs != null && !inqryInd8Infs.isEmpty()) {
            // подсчитываем periodLength - количество дней от ЖДП до установки нужного статуса
            countPeriodLength(inqryInd8Infs);

            // Map по всем учреждениям - учреждения и его заявления
            Map<Long, List<InqryInd8Inf>> inqryByUchMap = inqryInd8Infs.stream()
                    .collect(Collectors.groupingBy(inqry -> inqry.getIdUch().longValue()));


            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                //Учреждение
                UchInf uchInf = uchInfSchema.getUchInf();
                //Счетчики учреждения
//                List<CounterConfig> inqryCounters = uchInfSchema.getSchema().getSource().getInqryCounters();
                //Заявления текущего учреждения
                List<InqryInd8Inf> inqryInd8UchInfs = inqryByUchMap.get(uchInf.getId());
                //Пройтись по каждому заявлению и посчитать счетчики
                processForInd8(reportSetting.getCurrDate(), inqryInd8UchInfs);
//TODO
                //Инициализация счетчиков значениями по умолчанию
                for (CounterConfig counterConfig : inqryCounters) {
                    Counter counter = counterMap.get(uchInf.getId())
                            .computeIfAbsent(counterConfig.getCounterDef().getId(),
                                    counterId -> new Counter(counterConfig.getCounterDef()));
                }
                if (inqryInd8UchInfs != null && inqryCounters != null) {
                    for (InqryInd8Inf inqryInd8Inf : inqryInd8UchInfs) {
                        //Для каждого счетчика проверить нужно ли его инкрементировать для текущего заявления
                        for (CounterConfig counterConfig : inqryCounters) {
                            if (counterConfig.isPassed(reportSetting.getCurrDate(),
                                    reportSetting.getCurrEducDate(), inqryInd8Inf)) {
                                Collection<TypeAgeRange> ageRanges =
                                        counterConfig.getCounterDef().getAgeRange()
                                                .getAgeRanges(reportSetting.getCurrDate(), inqryInd8Inf);
                                if (ageRanges != null && !ageRanges.isEmpty()) {
                                    // Посчитать элемент
                                    Counter counter = counterMap.get(uchInf.getId())
                                            .get(counterConfig.getCounterDef().getId());
                                    counter.count(inqryInd8Inf, ageRanges);
                                }
                            }
                        }
                    }
                }
            }
            if (counterMap.size() > 0) {
                log.info("Counters: {}", counterMap);
            }
        }
        return counterMap;
    }

    private void countPeriodLength(List<InqryInd8Inf> inqryInd8Infs) {
        for (InqryInd8Inf ind8 : inqryInd8Infs ) {
            int idUch = ind8.getIdUch();
            Date stsDt = ind8.getStsDt(),
                 wishDt = ind8.getWishDt();
            if (stsDt != null && wishDt != null) {
                DateTime start = new DateTime(wishDt),
                         stop = new DateTime(stsDt);
                Days days = Days.daysBetween(start, stop);
                int val = Days.ZERO.isGreaterThan(days) ? 0 : days.getDays();
                ind8.setPeriodLength(val);
            }
        }
        // Map заявления и его учреждения (несколько элементов одного заявления)
        Map<Integer, List<InqryInd8Inf>> inqrySeveralUch = inqryInd8Infs.stream()
                .collect(Collectors.groupingBy(inqry -> inqry.getIdInqry()));
        // вывести в лог заявления, у которых несколько элементов
        for (Map.Entry<Integer, List<InqryInd8Inf>> entry : inqrySeveralUch.entrySet()) {
            List<InqryInd8Inf> inqryList = entry.getValue();
            if (inqryList.size() > 1) {
                log.debug("Ind_8: В заявлении больше одного направления в ДОО. Inqry id={}\r\n" +
                        "Items:\r\n{}", entry.getKey(), inqryList);
            }
        }
    }

    private void processForInd8(Date currDate, List<InqryInd8Inf> inqryInd8Infs) {
        if (currDate == null || inqryInd8Infs.isEmpty()) {
            return;
        }
        int[] i_8 = {0, 0, 0, 0, 0, 0, 0, 0},
              i_8_1 = {0, 0, 0, 0, 0, 0, 0, 0},
              i_8_2 = {0, 0, 0, 0, 0, 0, 0, 0},
              i_8_3 = {0, 0, 0, 0, 0, 0, 0, 0};
        int[] i_8_cnt = {0, 0, 0, 0, 0, 0, 0, 0},
              i_8_1_cnt = {0, 0, 0, 0, 0, 0, 0, 0},
              i_8_2_cnt = {0, 0, 0, 0, 0, 0, 0, 0},
              i_8_3_cnt = {0, 0, 0, 0, 0, 0, 0, 0};
        Map<Date, Integer> calc = new HashMap<>();
        for (InqryInd8Inf ind8 : inqryInd8Infs) {
            Date birthDt = ind8.getBirthDt();
            if (birthDt == null) {
                continue;
            }

        }
    }
}
