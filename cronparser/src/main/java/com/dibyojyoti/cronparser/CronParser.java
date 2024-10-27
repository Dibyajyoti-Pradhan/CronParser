package com.dibyojyoti.cronparser;

public class CronParser {
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Usage: java CronParser <cron-expression>");
            }
            CronExpression cronExpression = new CronExpression(args[0]);
            cronExpression.displaySchedule();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error processing the cron expression: " + e.getMessage());
        }
    }
}
