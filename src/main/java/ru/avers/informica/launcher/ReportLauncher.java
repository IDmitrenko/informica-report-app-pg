package ru.avers.informica.launcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.report.ReportGenerator;
import ru.avers.informica.report.xml.PushDataRequest;
import ru.avers.informica.utils.CHelper;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
@RequiredArgsConstructor
@Slf4j
//InformicaDaemon
public class ReportLauncher {
    private Marker m_marker_email = MarkerFactory.getMarker("EMAIL_LOG");
    private final ServletContext context;
    private final CHelper cHelper;
    private final ReportGenerator reportGenerator;

    private String debug;

    @PostConstruct
    public void init() {
        String absolutePath = context.getClassLoader().getResource("config").getPath();
        cHelper.setAppHomeFolder(absolutePath);
    }

    public void buildReport(String launchSite, boolean checkingLaunch) {
        logStart(launchSite);

        CProfile cProfile = cHelper.getConfigProfile();
        if (checkingLaunch && !cProfile.getReports().getReportInformica().isAutoUploadEnabled()) {
            log.debug("ReportLauncher: Автовыгрузка отчета Информики отключена");
            return;
        }
        if (!cProfile.getReports().getReportInformica().isEmailLog()) {
            log.debug("ReportLauncher: Отправка лога на informika@iicavers.ru отключена");
            m_marker_email = null;
        }
        try {
            PushDataRequest report = reportGenerator.generateReport( cProfile);
            //FspeoReport fspeoReport = fspeoFactory.createReport(fspeoVersion, reportInformica.isMt());
// InformicaDaemon 188
        } catch (Exception ex) {
            String x_str = "Ошибка при построении отчета";
            log.error(x_str, ex);
            log.error(m_marker_email, "{}. 1. Ошибка построения отчета", cProfile.getReports().getReportInformica().getStateName());
        }

        debug = "1";
    }

    private void logStart(String launchSite) {
        System.out.println("Место запуска отчета - " + launchSite);
        Calendar currTime = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
        log.debug("ReportLauncher: Создание отчета информики, текущее время:"
                + dateFormat.format(currTime.getTime()));
    }
}
