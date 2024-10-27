package com.dibyojyoti.cronparser;

import java.util.Arrays;
import java.util.List;

import com.dibyojyoti.cronparser.model.CommandField;
import com.dibyojyoti.cronparser.model.CronField;
import com.dibyojyoti.cronparser.model.DayOfMonthCronField;
import com.dibyojyoti.cronparser.model.DayOfWeekCronField;
import com.dibyojyoti.cronparser.model.HourCronField;
import com.dibyojyoti.cronparser.model.MinuteCronField;
import com.dibyojyoti.cronparser.model.MonthCronField;
import com.dibyojyoti.cronparser.model.YearCronField;

public class CronExpression {
    private List<CronField> cronFields;
    private CommandField commandField;
    private CronField yearCronField = null;

    public CronExpression(String cronExpression) {
        String[] parts = cronExpression.split(" ", 6);
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid cron expression. Must consist of 6 parts.");
        }

        char lastPartStartChar = parts[5].charAt(0);
        if ('0' < lastPartStartChar && lastPartStartChar <= '9' || lastPartStartChar == '*') {
            String year = parts[5].split(" ", 2)[0];
            parts[5] = parts[5].split(" ", 2)[1];
            yearCronField = new YearCronField(year);

            cronFields = Arrays.asList(
                    new MinuteCronField(parts[0]),
                    new HourCronField(parts[1]),
                    new DayOfMonthCronField(parts[2]),
                    new MonthCronField(parts[3]),
                    new DayOfWeekCronField(parts[4]),
                    yearCronField);
        } else {
            cronFields = Arrays.asList(
                    new MinuteCronField(parts[0]),
                    new HourCronField(parts[1]),
                    new DayOfMonthCronField(parts[2]),
                    new MonthCronField(parts[3]),
                    new DayOfWeekCronField(parts[4]));
        }
        this.commandField = new CommandField(parts[5]);
    }

    public void displaySchedule() {
        System.out.printf("%-14s %s\n", "minute", cronFields.get(0).parse());
        System.out.printf("%-14s %s\n", "hour", cronFields.get(1).parse());
        System.out.printf("%-14s %s\n", "day of month", cronFields.get(2).parse());
        System.out.printf("%-14s %s\n", "month", cronFields.get(3).parse());
        System.out.printf("%-14s %s\n", "day of week", cronFields.get(4).parse());
        if (yearCronField != null) {
            System.out.printf("%-14s %s\n", "year", cronFields.get(5).parse());
        }
        System.out.printf("%-14s %s\n", "command", commandField.getCommand());
    }
}