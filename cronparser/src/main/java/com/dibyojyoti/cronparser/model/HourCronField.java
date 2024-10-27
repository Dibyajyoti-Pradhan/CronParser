package com.dibyojyoti.cronparser.model;

public class HourCronField extends CronField {
    public HourCronField(String expression) {
        super(expression, 0, 23); // Hours range from 0 to 23
    }
}