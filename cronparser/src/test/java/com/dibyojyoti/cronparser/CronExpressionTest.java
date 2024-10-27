package com.dibyojyoti.cronparser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class CronExpressionTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor)); // Redirect System.out to capture output
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut); // Reset System.out to its original stream
    }

    @Test
    void testCronExpressionOutput() {
        String cron = "*/15 0 1,15 * 1-5 /usr/bin/find";
        CronExpression expression = new CronExpression(cron);
        expression.displaySchedule();
        String expectedOutput = "minute         [0, 15, 30, 45]\n" +
                "hour           [0]\n" +
                "day of month   [1, 15]\n" +
                "month          [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]\n" +
                "day of week    [1, 2, 3, 4, 5]\n" +
                "command        /usr/bin/find";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void testInvalidCronExpression() {
        String cron = "*/15 0 1,15 *"; // Incorrect format
        assertThrows(IllegalArgumentException.class, () -> new CronExpression(cron));
    }
}
