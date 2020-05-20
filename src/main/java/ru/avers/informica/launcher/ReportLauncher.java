package ru.avers.informica.launcher;

import org.springframework.stereotype.Component;
import ru.avers.informica.common.config.CProfile;
import ru.avers.informica.utils.CHelper;

@Component
public class ReportLauncher {

    public void buildReport(String name) {
        System.out.println("Запуск отчета - " + name);
        CProfile x_cfg_profile = CHelper.getConfigProfile();
    }
}
