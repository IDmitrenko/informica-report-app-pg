package ru.avers.informica.launcher;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CMisc;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.common.config.ReportInformica;
import ru.avers.informica.config.InformicaConfig;
import ru.avers.informica.utils.CHelper;

import javax.servlet.ServletContext;

@Component
@RequiredArgsConstructor
public class ReportLauncher {

    private final ServletContext context;
    private final CHelper cHelper;

    private String debug;

    public void buildReport(String name) {
        System.out.println("Запуск отчета - " + name);

        String absolutePath = context.getClassLoader().getResource("config").getPath();
        cHelper.setAppHomeFolder(absolutePath);

        CProfile x_cfg_profile = cHelper.getConfigProfile();
        CMisc x_cfg_misc = x_cfg_profile.getMisc();
        ReportInformica c_cfg_report_informica = x_cfg_profile.getReports().getReportInformica();

        debug = "1";
    }
}
