package com.dibyojyoti.cronparser.model;

public class MinuteCronField extends CronField {
    public MinuteCronField(String expression) {
        super(expression, 0, 59); // Minutes range from 0 to 59
    }
}
