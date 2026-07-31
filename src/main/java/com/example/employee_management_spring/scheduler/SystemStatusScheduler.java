package com.example.employee_management_spring.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemStatusScheduler {
    private static final Logger log = LoggerFactory.getLogger(SystemStatusScheduler.class);

    @Scheduled(fixedRate = 30_000)
    public void logSystemRunning() {
        log.info("System running");
    }
}
