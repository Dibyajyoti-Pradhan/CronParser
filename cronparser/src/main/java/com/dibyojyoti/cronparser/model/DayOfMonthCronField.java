package com.dibyojyoti.cronparser.model;

public class DayOfMonthCronField extends CronField {
    public DayOfMonthCronField(String expression) {
        super(expression, 1, 31); // Days of the month range from 1 to 31
    }
}