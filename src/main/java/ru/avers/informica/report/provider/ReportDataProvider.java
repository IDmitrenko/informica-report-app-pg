package ru.avers.informica.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dto.informica.*;
import ru.avers.informica.report.ReportSetting;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportDataProvider {
    private final InqryDao inqryDao;
    private final ReportSetting reportSetting;
    private List<InqryInf> inqries;
    private List<InqryEnrolmentInf> allInqryEnrolments;
    private List<InqryInd8Inf> inqriesInd8;
    private List<InqryInd19_3Inf> inqriesInd19_3;
    private List<InqryInd20_1Inf> inqriesInd20_1;

    public void loadData() {

        // считать InqryInf
        inqries = inqryDao.getAllInqry(reportSetting.getCurrDate(),
                reportSetting.getCurrEducDate(),
                reportSetting.getBeginCurrYear().getTime());
        log.info("Найдено {} inqry-source", inqries.size());

        // считать InqryEnrolmentInf для подсчета add_cont
        allInqryEnrolments = inqryDao.getIngryEnrolment();
        log.info("Найдено {} inqry-enrolment", allInqryEnrolments.size());

        // считать InqryInd8Inf для подсчета ind_8
        inqriesInd8 = inqryDao.getInqryInd8();
        log.info("Найдено {} inqry-ind8", inqriesInd8.size());

        // считать InqryInd19_3Inf для показателя 19_3
        inqriesInd19_3 = inqryDao.getInqryInd19_3();
        log.info("Найдено {} inqry-ind19_3", inqriesInd19_3.size());

        // считать InqryInd20_1Inf для показателя 20_1
        inqriesInd20_1 = inqryDao.getInqryInd20_1();
        log.info("Найдено {} inqry-ind20_1", inqriesInd20_1.size());
    }

    public List<InqryInf> getAllInqry() {
        return inqries;
    }

    public List<InqryEnrolmentInf> getAllInqryEnrolments() {
        return allInqryEnrolments;
    }

    public List<InqryInd8Inf> getInqriesInd8() {
        return inqriesInd8;
    }

    public List<InqryInd19_3Inf> getInqriesInd19_3() {
        return inqriesInd19_3;
    }

    public List<InqryInd20_1Inf> getInqriesInd20_1() {
        return inqriesInd20_1;
    }
}
