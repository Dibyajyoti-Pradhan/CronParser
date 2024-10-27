package com.dibyojyoti.cronparser.model;

public class DayOfWeekCronField extends CronField {
    public DayOfWeekCronField(String expression) {
        super(expression, 1, 7); // Days of the week range from 1 (Monday) to 7 (Sunday)
    }
}
