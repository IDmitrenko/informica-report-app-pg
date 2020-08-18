package ru.avers.informica.report.indicator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.BuildingInf;
import ru.avers.informica.dto.informica.GroupInf;
import ru.avers.informica.dto.informica.InqryEnrolmentInf;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.report.provider.ReportDataProvider;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;
import ru.avers.informica.utils.DateUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1)
public class AddContIndicator implements Indicator {
    private final ReportSetting reportSetting;
    private final ReportDataProvider reportDataProvider;

    @Override
    public void calculateIndicator(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        int countNotDistributed = 0;
        int countEnrolment = 0;
        int freeSpace = 0;
        boolean isDistributed;
        List<InqryEnrolmentInf> enrolmentsNotDistributed = new ArrayList<>();
        inqryEnrolment:
        for (InqryEnrolmentInf ie : reportDataProvider.getAllInqryEnrolments()) {
            isDistributed = false;
            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                if (ie.getIdUch() == uchInfSchema.getUchInf().getId().intValue()) {
                    for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                        for (GroupInf groupInf : buildingInf.getGroupInfs()) {
// попадает ли ребенок в возрастной интервал группы
                            if (childInAgeGroup(ie, groupInf)) {
// Совпадает ли направленность группы
                                if (groupInf.getIdHealthCsp().contains(ie.getIdHealthCsp())) {
// Проверяем если есть в заявлении режим посещения группы
                                    if (((ie.getIdGrpTimesCsp().size() > 0) &&
                                            ie.getIdGrpTimesCsp().contains(groupInf.getIdWorkTimeCsp())) ||
                                            ie.getIdGrpTimesCsp().size() == 0) {
// Определяем свободные места в подходящей группе
/*
                                        int freeSpace = Integer.parseInt(groupInf.getCapacity()) -
                                                Integer.parseInt(groupInf.getEnrolled());
                                        if (groupInf.getAddCont() != null) {
                                            freeSpace -= Integer.parseInt(groupInf.getAddCont());
                                        }
*/
                                        if (groupInf.getAddCont() == null) {
                                            freeSpace = groupInf.getCapacity() -
                                                    groupInf.getEnrolled();
                                        } else {
                                            freeSpace = groupInf.getCapacity() -
                                                    groupInf.getEnrolled() - groupInf.getAddCont();
                                        }
                                        if (freeSpace > 0) {
// Ребенок по этому заявлению идет в эту группу
                                            int addCont = 1;
/*
                                            if (groupInf.getAddCont() != null) {
                                                addCont += Integer.parseInt(groupInf.getAddCont());
                                            }
                                            groupInf.setAddCont(Integer.toString(addCont));
*/
                                            if (groupInf.getAddCont() != null) {
                                                addCont += groupInf.getAddCont();
                                            }
                                            groupInf.setAddCont(addCont);
                                            isDistributed = true;
                                            countEnrolment++;
                                            continue inqryEnrolment;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!isDistributed) {
// распределяем в первую попавшуюся по характеристикам группу
                        for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                            for (GroupInf groupInf : buildingInf.getGroupInfs()) {
// попадает ли ребенок в возрастной интервал группы
                                if (childInAgeGroup(ie, groupInf)) {
// Совпадает ли направленность группы
                                    if (groupInf.getIdHealthCsp().contains(ie.getIdHealthCsp())) {
// Проверяем если есть в заявлении режим посещения группы
                                        if (((ie.getIdGrpTimesCsp().size() > 0) &&
                                                ie.getIdGrpTimesCsp().contains(groupInf.getIdWorkTimeCsp())) ||
                                                ie.getIdGrpTimesCsp().size() == 0) {
// Ребенок по этому заявлению идет в эту группу
                                            int addCont = 1;
/*
                                            if (groupInf.getAddCont() != null) {
                                                addCont += Integer.parseInt(groupInf.getAddCont());
                                            }
                                            groupInf.setAddCont(Integer.toString(addCont));
*/
                                            if (groupInf.getAddCont() != null) {
                                                addCont += groupInf.getAddCont();
                                            }
                                            groupInf.setAddCont(addCont);
                                            isDistributed = true;
                                            countEnrolment++;
                                            continue inqryEnrolment;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!isDistributed) {
// не нашли группы подходящей по характеристикам
                        countNotDistributed++;
                        enrolmentsNotDistributed.add(ie);
                    }
                }
            }

        }
        log.info("Распределено в подходящие группы {} inqry-enrolment", countEnrolment);
/*
 Распределяем путевки для которых не нашлось групп, подходящих по характеристикам
 Когда нет группы, подходящей по характеристикам,
 оставшиеся путёвки необходимо распределять равномерно по всем группам.
 Например, если осталось 9 нераспределённых путёвок,
 для которых ни одна группа не подходит по характеристикам,
 а групп в организации всего 3,
 то необходимо распределить по 3 путёвки в каждую группу, соответственно.
 */
        if (countNotDistributed > 0) {
            log.info("Не распределено {} inqry-enrolment", countNotDistributed);
            int countDistributedNot = 0;
// не распределенные путевки по учреждениям
            Map<Integer, List<InqryEnrolmentInf>> inqryEnrolmentByUchMap = enrolmentsNotDistributed.stream()
                    .collect(Collectors.groupingBy(enrolment -> enrolment.getIdUch()));
/*
            int inqryUchNotDistributed = 0;
            for (InqryEnrolmentInf iei : enrolmentsNotDistributed) {
                if (iei.getIdUch() == 1161) {
                    inqryUchNotDistributed++;
                }
            }
*/
            for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
                List<InqryEnrolmentInf> inqrysUchEnrolment = inqryEnrolmentByUchMap
                        .get(uchInfSchema.getUchInf().getId().intValue());
                if (inqrysUchEnrolment != null) {
// у нас есть учреждение и все его не распределенные заявления
// количество не распределенных заявлений
                    int countInqrysUchNotDistributed = inqrysUchEnrolment.size();
// считаем количество групп учреждения
                    int countGroups = 0;
                    int currAddCont = 0;
                    for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                        countGroups += buildingInf.getGroupInfs().size();
                    }
// определяем начальное количество заявлений для распределения в группу
                    int countInqrysGroupNotDistributed = 0;
                    countInqrysGroupNotDistributed = (int) Math
                            .ceil((double) countInqrysUchNotDistributed / countGroups);

                    if (countInqrysGroupNotDistributed > 0) {
                        outer:
                        for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                            for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                                if (groupInf.getAddCont() != null) {
                                    currAddCont = groupInf.getAddCont();
                                } else {
                                    currAddCont = 0;
                                }
                                if (countInqrysUchNotDistributed > countInqrysGroupNotDistributed) {
                                    groupInf.setAddCont(currAddCont + countInqrysGroupNotDistributed);
                                    countInqrysUchNotDistributed -= countInqrysGroupNotDistributed;
                                    countDistributedNot += countInqrysGroupNotDistributed;
                                } else {
                                    groupInf.setAddCont(currAddCont + countInqrysUchNotDistributed);
                                    countDistributedNot += countInqrysUchNotDistributed;
                                    break outer;
                                }
                            }
                        }
                    }
                }
            }
            log.info("Распределено равномерно по группам не подходящих {} inqry-enrolment", countDistributedNot);
        }
        logCountEnrolment(uchInfSchemas);
    }

    private void logCountEnrolment(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        // Отладочный вывод
        int countEnrolment = 0;
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                    if (groupInf.getAddCont() != null) {
                        countEnrolment += groupInf.getAddCont();
                    }
                }
            }
        }
        log.info("Распределено в итоге {} inqry-enrolment", countEnrolment);
    }

    private boolean childInAgeGroup(InqryEnrolmentInf ie, GroupInf groupInf) {
        int diffYears = DateUtil.getYearPart(reportSetting.getCurrDate()) - DateUtil.getYearPart(ie.getBDt());
        int monthDiff = DateUtil.getMonthPart(reportSetting.getCurrDate()) - DateUtil.getMonthPart(ie.getBDt());
        int totalMonths = diffYears * 12 + monthDiff;

        int fromMonth = groupInf.getAgeFromYears().intValue() * 12 + groupInf.getAgeFromMonths().intValue();
        int toMonth = groupInf.getAgeToYears().intValue() * 12 + groupInf.getAgeToMonths().intValue();
        if (totalMonths >= fromMonth && totalMonths <= toMonth) {
            return true;
        } else {
            return false;
        }
    }
}
