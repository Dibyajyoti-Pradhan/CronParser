package com.dibyojyoti.cronparser.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CronField {
    protected String expression;
    private int minRange;
    private int maxRange;

    public CronField(String expression, int minRange, int maxRange) {
        this.expression = expression;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public List<Integer> parse() {
        List<Integer> values = new ArrayList<>();
        if (expression.contains(",")) {
            String[] parts = expression.split(",");
            for (String part : parts) {
                values.addAll(parsePart(part));
            }
        } else {
            values.addAll(parsePart(expression));
        }
        return values;
    }

    private List<Integer> parsePart(String part) {
        List<Integer> result = new ArrayList<>();
        if (part.equals("*")) {
            for (int i = minRange; i <= maxRange; i++) {
                result.add(i);
            }
        } else if (part.contains("-")) {
            String[] range = part.split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            for (int i = start; i <= end; i++) {
                result.add(i);
            }
        } else if (part.contains("/")) {
            String[] intervalParts = part.split("/");
            int interval = Integer.parseInt(intervalParts[1]);
            int start = minRange == 0 ? minRange : interval;
            for (int i = start; i <= maxRange; i += interval) {
                result.add(i);
            }
        } else {
            result.add(Integer.parseInt(part));
        }
        return result;
    }
}
