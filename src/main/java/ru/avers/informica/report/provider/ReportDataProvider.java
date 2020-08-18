package ru.avers.informica.report.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.InqryDao;
import ru.avers.informica.dto.informica.InqryEnrolmentInf;
import ru.avers.informica.dto.informica.InqryInf;
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

    public void loadData() {

        //  считать InqryInf
        inqries = inqryDao.getAllInqry(reportSetting.getCurrDate(),
                reportSetting.getCurrEducDate(),
                reportSetting.getBeginCurrYear().getTime());
        log.info("Найдено {} inqry-source", inqries.size());

        //  считать InqryEnrolmentInf для подсчета add_cont
        allInqryEnrolments = inqryDao.getIngryEnrolment();
        log.info("Найдено {} inqry-enrolment", allInqryEnrolments.size());
    }

    public List<InqryInf> getAllInqry() {
        return inqries;
    }

    public List<InqryEnrolmentInf> getAllInqryEnrolments() {
        return allInqryEnrolments;
    }


}
