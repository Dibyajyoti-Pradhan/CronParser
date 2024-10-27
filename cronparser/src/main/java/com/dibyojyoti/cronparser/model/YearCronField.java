package com.dibyojyoti.cronparser.model;

public class YearCronField extends CronField {
    public YearCronField(String expression) {
        super(expression, 1970, 2100); // Months range from 1970 to 2100
    }
}
