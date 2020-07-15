package ru.avers.informica.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class ReportSetting {

    private final ServletContext context;
    private final CHelper cHelper;

    private final Date currDate;
    private final Date currEducDate;
    private final Calendar beginCurrYear;
    private final Integer currEducYear;

    public ReportSetting(CHelper cHelper, ServletContext context) {
        this.cHelper = cHelper;
        this.context = context;
        String absolutePath = context.getClassLoader().getResource("config").getPath();
        cHelper.setAppHomeFolder(absolutePath);
        currDate = DateUtil.getCurrentDate(false);
        currEducDate = DateUtil.getCurrEducDate(currDate,
                cHelper.getConfigProfile().getMisc().getInqryEducYearBegin().getMonth(),
                cHelper.getConfigProfile().getMisc().getInqryEducYearBegin().getDay());
        beginCurrYear = GregorianCalendar.getInstance(); // Начало текущего календарного года
        DateUtil.clearCalendarTimePart(beginCurrYear);
        beginCurrYear.set(Calendar.DAY_OF_MONTH, 1);
        beginCurrYear.set(Calendar.MONTH, Calendar.JANUARY);
        currEducYear = DateUtil.getYearPart(currEducDate);
    }

}
