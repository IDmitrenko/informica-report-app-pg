package ru.avers.informica.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.DateUtil;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class ReportSetting {
    private final ServletContext context;
    private Date currDate;              // текущая дата
    private Date currEducDate;          // дата начала текущего учебного года
    private Calendar beginCurrYear;     // дата начала текущего года
    private Integer currEducYear;       // текущий учебный год
    private CProfile cProfile;          // config.xml отчета
    private boolean isFirstOccurrence;      // признак первого вхождения

    @PostConstruct
    public void init() {
        String absolutePath = context.getClassLoader().getResource("config").getPath();
        CHelper.setAppHomeFolder(absolutePath);
        cProfile = CHelper.getConfigProfile();
        currDate = DateUtil.getCurrentDate(false);
        currEducDate = DateUtil.getCurrEducDate(currDate,
                cProfile.getMisc().getInqryEducYearBegin().getMonth(),
                cProfile.getMisc().getInqryEducYearBegin().getDay());
        beginCurrYear = GregorianCalendar.getInstance(); // Начало текущего календарного года
        DateUtil.clearCalendarTimePart(beginCurrYear);
        beginCurrYear.set(Calendar.DAY_OF_MONTH, 1);
        beginCurrYear.set(Calendar.MONTH, Calendar.JANUARY);
        currEducYear = DateUtil.getYearPart(currEducDate);
        isFirstOccurrence = true;
    }

}
