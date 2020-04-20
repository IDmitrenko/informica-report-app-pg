package ru.avers.informica.launcher;

import org.springframework.stereotype.Component;

@Component
public class ReportLauncher {

    public void buildReport(String name) {
        System.out.println("Запуск отчета - " + name);
    }
}
