package ru.avers.informica.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.CAge;
import ru.avers.informica.dto.informica.InqryInd8Inf;
import ru.avers.informica.dto.informica.InqryInf;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.dto.inqry.AgeDto;
import ru.avers.informica.exception.FilterException;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;
import ru.avers.informica.infcfg.CounterConfig;
import ru.avers.informica.infcfg.TypeAgeRange;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.CounterSpecial;
import ru.avers.informica.report.IndicatorType;
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

    public Map<Long, Map<String, CounterSpecial>> provideCounters8(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas)
            throws ReportExceprion, FilterException, FspeoException {

        // список коунтеров по показателю типа AGE8SPECIAL
        Map<String, IndicatorType> counterName8 = reportSetting.getCounterNameToAgeType()
                .entrySet().stream().filter(map -> IndicatorType.AGE8SPECIAL.equals(map.getValue()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        Map<Long, Map<String, CounterSpecial>> counterMapSpecial = new HashMap<>();
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
                counterMapSpecial.put(uchInf.getId(), new HashMap<>());
                //Счетчики учреждения counterName8

                //Заявления текущего учреждения
                List<InqryInd8Inf> inqryInd8UchInfs = inqryByUchMap.get(uchInf.getId());
                //Инициализация счетчиков значениями по умолчанию
                // нужно проинициализировать показатели типа AGE8SPECIAL (8, 8.1, 8.2, 8.3) в counterMap
                for (Map.Entry<String, IndicatorType> entryType : counterName8.entrySet()) {
//TODO
// в Map<Long, Map<String, CounterSpecial>> counterMapSpecial  нужно проинициализировать Map<String, CounterSpecial>

                }
                //Пройтись по каждому заявлению и посчитать счетчики
                processForInd8(reportSetting.getCurrDate(), inqryInd8UchInfs);

/*
                for (Map.Entry<Long, Map<String, Counter>> entry : counterMap.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
                    Map<String, Counter> entryCounter = entry.getValue();
                    for (Map.Entry<String, IndicatorType> entryType : counterName8.entrySet()) {
                        // ищем id для коунтера
                        String idCounter = null;
                        for (Map.Entry<String, String> entryId : reportSetting.getCounterName().entrySet()) {
                            if (entryId.getKey().equals(entryType.getKey())) {
                                idCounter = entryId.getValue();
                                break;
                            }
                        }
                        if (idCounter != null) {
                            Counter counter = new Counter();
                            counter.setId(idCounter);
                            AgeItemSpecial
                            counter.getAge().add()
                            entryCounter.put(entryType.getKey(), counter);
                        }
                    }
                }
*/

/*
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
*/
            }
            if (counterMapSpecial.size() > 0) {
                log.info("Counters Special: {}", counterMapSpecial);
            }
        }
        return counterMapSpecial;
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
            Integer indValue = calcIndexInProcessForInd_8(currDate, birthDt, calc);
            if (indValue == null) {
                continue;
            }
            int periodLength = ind8.getPeriodLength();
            i_8[indValue] += periodLength;
            ++i_8_cnt[indValue];

            if (ind8.isLgot()) {
                i_8_2[indValue] += periodLength;
                ++i_8_2_cnt[indValue];
            } else {
                i_8_1[indValue] += periodLength;
                ++i_8_1_cnt[indValue];
            }

            if (ind8.isOvz()) {
                i_8_3[indValue] += periodLength;
                ++i_8_3_cnt[indValue];
            }
        }
        // данные подготовлены
        log.debug("Данные по казателям 8 : \r\n8:\r\n{}\r\n{}\r\n8.1:\r\n{}\r\n{}\r\n" +
                        "8.2:\r\n{}\r\n{}\r\n8.3:\r\n{}\r\n{}",
                i_8, i_8_cnt, i_8_1, i_8_1_cnt, i_8_2, i_8_2_cnt, i_8_3, i_8_3_cnt);
        // нужно преобразовать и записать в TagSingleOrganization


    }

    private static Integer calcIndexInProcessForInd_8(Date currDate,
                                                      Date birthDt,
                                                      Map<Date, Integer> calc) {
        Integer rv = calc.get(birthDt);
        if (rv == null) {
            AgeDto ageDto = AgeDto.calculateAge(currDate, birthDt);
            CAge chldAge = new CAge((int)ageDto.getYears(), (int)ageDto.getMonths(), (int)ageDto.getDays());
            if (TypeAgeRange.t_m2_to_y1.getAgeInterval().containsLeft(chldAge)) {
                rv = 0;
            } else if (TypeAgeRange.t_y1_to_y2.getAgeInterval().containsLeft(chldAge)) {
                rv = 1;
            } else if (TypeAgeRange.t_y2_to_y3.getAgeInterval().containsLeft(chldAge)) {
                rv = 2;
            } else if (TypeAgeRange.t_y3_to_y4.getAgeInterval().containsLeft(chldAge)) {
                rv = 3;
            } else if (TypeAgeRange.t_y4_to_y5.getAgeInterval().containsLeft(chldAge)) {
                rv = 4;
            } else if (TypeAgeRange.t_y5_to_y6.getAgeInterval().containsLeft(chldAge)) {
                rv = 5;
            } else if (TypeAgeRange.t_y6_to_y7.getAgeInterval().containsLeft(chldAge)) {
                rv = 6;
            } else if (TypeAgeRange.t_y7_plus.getAgeInterval().containsLeft(chldAge)) {
                rv = 7;
            }
            if (rv == null) {
                log.debug("Проблема определения категории по возрасту ребенка : currDate={}, " +
                        "birthDt={}, age={}", currDate, birthDt, chldAge);
            } else {
                calc.put(birthDt, rv);
            }
        }
        return rv;
    }

}
