package com.dibyojyoti.cronparser.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class MinuteCronFieldTest {

    @Test
    public void testParseAllMinutesWithAsterisk() {
        MinuteCronField field = new MinuteCronField("*");
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46,
                47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59);
        assertEquals(expected, field.parse(), "Parsing '*' should return all minutes of an hour");
    }

    @Test
    public void testParseRange() {
        MinuteCronField field = new MinuteCronField("10-15");
        List<Integer> expected = Arrays.asList(10, 11, 12, 13, 14, 15);
        assertEquals(expected, field.parse(), "Parsing '10-15' should return a range from 10 to 15 minutes");
    }

    @Test
    public void testParseSpecificMinute() {
        MinuteCronField field = new MinuteCronField("30");
        List<Integer> expected = Arrays.asList(30);
        assertEquals(expected, field.parse(), "Parsing '30' should return a list containing only the 30th minute");
    }

    @Test
    public void testParseInterval() {
        MinuteCronField field = new MinuteCronField("*/15");
        List<Integer> expected = Arrays.asList(0, 15, 30, 45);
        assertEquals(expected, field.parse(),
                "Parsing '*/15' should return every 15th minute starting from the 0th minute");
    }

    @Test
    public void testParseComplexExpression() {
        MinuteCronField field = new MinuteCronField("0,5,20-25,55");
        List<Integer> expected = Arrays.asList(0, 5, 20, 21, 22, 23, 24, 25, 55);
        assertEquals(expected, field.parse(),
                "Parsing '0,5,20-25,55' should return specific minutes and a range of minutes");
    }
}
