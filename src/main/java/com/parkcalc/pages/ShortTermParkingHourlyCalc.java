package com.parkcalc.pages;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShortTermParkingHourlyCalc extends ParkCalcPage {
    public double calculate(double totalHours) {
        double totalMinutes = totalHours * 60;


        double cost = 2.00;
        totalMinutes -= 60;

        if (totalMinutes > 0) {
            double halfHours = Math.ceil(totalMinutes / 30);
            cost += halfHours * 1.00;
        }

        if (cost > 24.00) {
            cost = 24.00;
        }
        return Math.round(cost * 100.0) / 100.0;


    }
    public boolean validateSameDate(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        return start.isEqual(end);
    }
}
