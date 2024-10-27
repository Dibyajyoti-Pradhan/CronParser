package com.dibyojyoti.cronparser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class CronParserTest {
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testMainMethodWithValidInput() {
        String[] args = { "*/15 0 1,15 * 1-5 /usr/bin/find" };
        CronParser.main(args);

        String expectedOutput = "minute         [0, 15, 30, 45]\n" +
                "hour           [0]\n" +
                "day of month   [1, 15]\n" +
                "month          [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]\n" +
                "day of week    [1, 2, 3, 4, 5]\n" +
                "command        /usr/bin/find";
        assertEquals(expectedOutput, outContent.toString().trim());
        assertTrue(errContent.toString().isEmpty());
    }

    @Test
    void testMainMethodWithInvalidArguments() {
        String[] args = {}; // No arguments provided
        CronParser.main(args);

        String expectedError = "Error processing the cron expression: Usage: java CronParser <cron-expression>";
        assertTrue(errContent.toString().trim().contains(expectedError));
        assertTrue(outContent.toString().isEmpty());
    }

    @Test
    void testMainMethodWithMalformedCronExpression() {
        String[] args = { "invalid-cron-expression" };
        CronParser.main(args);

        String expectedError = "Error processing the cron expression:";
        assertTrue(errContent.toString().trim().contains(expectedError));
        assertTrue(outContent.toString().isEmpty());
    }

    @Test
    void testMainMethodWithCommandHavingArgumentsCronExpression() {
        String[] args = { "*/15 0 1,15 * 1-5 /usr/bin/find arg1 arg2" };
        CronParser.main(args);

        String expectedOutput = "minute         [0, 15, 30, 45]\n" +
                "hour           [0]\n" +
                "day of month   [1, 15]\n" +
                "month          [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]\n" +
                "day of week    [1, 2, 3, 4, 5]\n" +
                "command        /usr/bin/find arg1 arg2";

        assertEquals(expectedOutput, outContent.toString().trim());
        assertTrue(errContent.toString().isEmpty());
    }

    @Test
    void testMainMethodWithValidInputWithYearAndWithCommandArgs() {
        String[] args = { "*/15 0 1,15 * 1-5 2020-2022 /usr/bin/find arg1 arg2" };
        CronParser.main(args);

        String expectedOutput = "minute         [0, 15, 30, 45]\n" +
                "hour           [0]\n" +
                "day of month   [1, 15]\n" +
                "month          [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]\n" +
                "day of week    [1, 2, 3, 4, 5]\n" +
                "year           [2020, 2021, 2022]\n" +
                "command        /usr/bin/find arg1 arg2";
        assertEquals(expectedOutput, outContent.toString().trim());
        assertTrue(errContent.toString().isEmpty());
    }

    @Test
    void testMainMethodWithValidInputWithYearAndWithoutCommandArgs() {
        String[] args = { "*/15 0 1,15 * 1-5 2020-2022 /usr/bin/find" };
        CronParser.main(args);

        String expectedOutput = "minute         [0, 15, 30, 45]\n" +
                "hour           [0]\n" +
                "day of month   [1, 15]\n" +
                "month          [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]\n" +
                "day of week    [1, 2, 3, 4, 5]\n" +
                "year           [2020, 2021, 2022]\n" +
                "command        /usr/bin/find";
        assertEquals(expectedOutput, outContent.toString().trim());
        assertTrue(errContent.toString().isEmpty());
    }
}
