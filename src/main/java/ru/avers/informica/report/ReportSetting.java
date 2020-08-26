package ru.avers.informica.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.dto.dictcode.InqryStatusCode;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.DateUtil;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private boolean isFirstOccurrence;  // признак первого вхождения
    private String statusCodeInd8;      // код статуса заявления для ind_8
    private int shiftYear;              // сдвиг для года от текущей даты для работы с ind_8
    private final String emptyValueAge8Special = "-";  // Значение по умолчанию для Age8Special

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
        statusCodeInd8 = InqryStatusCode.SENT_TO_DOO_12;
        shiftYear = -2;
    }

    //TODO Когда будут добавляться enrolled  счетчики надо учесть что ключи будут одинаковые!!!
    Map<String, IndicatorType> counterNameToAgeType = Stream.of(new Object[][]{
            {"counter-1", IndicatorType.AGE1},
            {"counter-1.1", IndicatorType.AGE16},
            {"counter-2", IndicatorType.AGE16},
            {"counter-3", IndicatorType.AGE16},
            {"counter-5", IndicatorType.AGE16},
            {"counter-6", IndicatorType.AGE16},
            {"counter-7", IndicatorType.AGE16},
            {"counter-7.1", IndicatorType.AGE16},
            {"counter-7.2", IndicatorType.AGE16},
            {"counter-7.3", IndicatorType.AGE16},
            {"counter-7.4", IndicatorType.AGE16},
            {"counter-7.5", IndicatorType.AGE16},
            {"counter-7.6", IndicatorType.AGE16},
            {"counter-7.7", IndicatorType.AGE16},
            {"counter-8", IndicatorType.AGE8SPECIAL},
            {"counter-8.1", IndicatorType.AGE8SPECIAL},
            {"counter-8.2", IndicatorType.AGE8SPECIAL},
            {"counter-8.3", IndicatorType.AGE8SPECIAL},
            {"counter-10", IndicatorType.AGE1}
            //TODO добавить остальные индикаторы
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (IndicatorType) data[1]));

    Map<String, String> counterName = Stream.of(new Object[][] {
            {"counter-8", "ind_8"},
            {"counter-8.1", "ind_8_1"},
            {"counter-8.2", "ind_8_2"},
            {"counter-8.3", "ind_8_3"}
            }).collect(Collectors.toMap(name -> (String) name[0], name -> (String) name[1]));
}
