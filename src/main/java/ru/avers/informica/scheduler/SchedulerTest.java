package ru.avers.informica.scheduler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.avers.informica.launcher.ReportLauncher;


@Component
@RequiredArgsConstructor
public class SchedulerTest {
    private static final Logger log = LoggerFactory.getLogger(SchedulerTest.class);

    private final ReportLauncher launcher;

    @Scheduled(cron = "${scheduler.start.time}")
    public void reportCurrentTime() {
        launcher.buildReport("Launcher");
    }
}
