package ru.avers.informica.launcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CMisc;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.common.config.ReportInformica;
import ru.avers.informica.dao.ApplicationsDao;
import ru.avers.informica.entities.ApplicationsEntity;
import ru.avers.informica.infcfg.Config;
import ru.avers.informica.utils.CHelper;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
//InformicaDaemon
public class ReportLauncher {
    private Marker m_marker_email = MarkerFactory.getMarker("EMAIL_LOG");
    private final ApplicationsDao applicationDao;
    private final ServletContext context;
    private final CHelper cHelper;

    private String debug;

    public void buildReport(String launchSite, boolean checkingLaunch) {
        System.out.println("Место запуска отчета - " + launchSite);

        String absolutePath = context.getClassLoader().getResource("config").getPath();
        cHelper.setAppHomeFolder(absolutePath);

        Calendar currTime = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
        log.debug("ReportLauncher: Создание отчета информики, текущее время:"
                + dateFormat.format(currTime.getTime()));

        CProfile cProfile = cHelper.getConfigProfile();
        CMisc cMisc = cProfile.getMisc();
        ReportInformica reportInformica = cProfile.getReports().getReportInformica();
        if (checkingLaunch && !reportInformica.isAutoUploadEnabled()) {
            log.debug("ReportLauncher: Автовыгрузка отчета Информики отключена");
            return;
        }
        if (!reportInformica.isEmailLog()) {
            log.debug("ReportLauncher: Отправка лога на informika@iicavers.ru отключена");
            m_marker_email = null;
        }

        Config configInformica = cHelper.getInformicaConfig();
        try {
            List<ApplicationsEntity> allApplications = applicationDao.getAllApplications();
            log.info("Found {} applications", allApplications.size());
            //FspeoVersion x_version = FspeoFactory.transformVersion(reportInformica.getVersion());
/*
            FspeoFactory x_factory = new FspeoFactory(
                    null,
                    cMisc.getInqryEducYearBegin(),
                    CBL.getProviders());

            s_logger.debug("Fspeo Factory: {}", x_factory);

            FspeoReport x_fspeo_report = x_factory.createReport(x_version, x_settings.isMt());
*/
// InformicaDaemon 188
        } catch (Exception ex) {
            String x_str = "Ошибка при построении отчета";
            log.error(x_str, ex);
            log.error(m_marker_email, "{}. 1. Ошибка построения отчета", reportInformica.getStateName());
        }

        debug = "1";
    }
}
