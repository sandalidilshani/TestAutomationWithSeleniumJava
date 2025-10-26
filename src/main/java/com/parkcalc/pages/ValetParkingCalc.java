package com.parkcalc.pages;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ValetParkingCalc extends ParkCalcPage {

    public double calculate(String startDate, String startTime, String endDate, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        LocalDateTime start = LocalDateTime.parse(startDate + " " + startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, formatter);

        long totalMinutes = Duration.between(start, end).toMinutes();
        long totalHours = totalMinutes / 60;
        long remainingMinutes = totalMinutes % 60;

        if (remainingMinutes > 0) {
            totalHours += 1;
        }

        double cost = 0.0;

        if (totalHours <= 5) {
            cost = 12.0;
        } else {
            long fullDays = totalHours / 24;
            long remainingHours = totalHours % 24;

            cost += fullDays * 18.0;

            if (remainingHours > 0) {
                cost += 18.0;
            }
        }

        return Math.round(cost * 100.0) / 100.0;
    }
}
