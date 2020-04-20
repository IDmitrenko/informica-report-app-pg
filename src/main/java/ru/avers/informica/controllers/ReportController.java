package ru.avers.informica.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avers.informica.launcher.ReportLauncher;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportLauncher launcher;

    @GetMapping("/start")
    public String getStart() {
        launcher.buildReport("RestController");
        return "Запустили построение отчета!";
    }
}
