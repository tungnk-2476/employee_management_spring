package com.example.employee_management_spring.service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
    private final Clock applicationClock;
    private final AtomicInteger employeeSequence = new AtomicInteger(1);

    public UtilityService(Clock applicationClock) {
        this.applicationClock = applicationClock;
    }

    public String formatName(String preName) {
        if (preName == null || preName.isBlank())
            return "";
        return Arrays.stream(preName.trim().replaceAll("\\s+", " ").split(" ")).map(this::capitalize)
                .collect(Collectors.joining(" "));
    }

    public String generateEmployeeCode() {
        String date = LocalDate.now(applicationClock)
                .format(DateTimeFormatter.BASIC_ISO_DATE);

        int sequence = employeeSequence.getAndIncrement();

        return "EMP-" + date + "-" + String.format("%04d", sequence);
    }

    private String capitalize(String word) {
        if (word.isEmpty()) {
            return word;
        }

        return word.substring(0, 1).toUpperCase()
                + word.substring(1).toLowerCase();
    }
}
