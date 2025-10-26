package com.parkcalc.pages;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LongTermGarageWeeklyCalc extends ParkCalcPage{
    public double calculate(String startDate, String startTime, String endDate, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

        LocalDateTime start = LocalDateTime.parse(startDate + " " + startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate + " " + endTime, formatter);

        long totalHours = Duration.between(start, end).toHours();
        long totalDays = totalHours / 24;
        long remainingHours = totalHours % 24;

        double cost = 0.0;

        // Apply weekly rate
        long weeks = totalDays / 7;
        if (weeks > 0) {
            cost += weeks * 72.0;
            totalDays -= weeks * 7;
        }

        cost += totalDays * 12.0;

        if (remainingHours > 0) {
            double hourlyCost = remainingHours * 2.0;
            cost += Math.min(hourlyCost, 12.0);
        }

        return cost;
    }
}
