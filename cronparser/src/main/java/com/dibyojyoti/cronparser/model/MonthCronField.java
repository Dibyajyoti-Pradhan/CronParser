package com.dibyojyoti.cronparser.model;

public class MonthCronField extends CronField {
    public MonthCronField(String expression) {
        super(expression, 1, 12); // Months range from 1 (January) to 12 (December)
    }
}
