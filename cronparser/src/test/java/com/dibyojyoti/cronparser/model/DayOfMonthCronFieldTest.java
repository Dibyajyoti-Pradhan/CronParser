package com.dibyojyoti.cronparser.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DayOfMonthCronFieldTest {

    @Test
    public void testParseAllValuesWithAsterisk() {
        DayOfMonthCronField field = new DayOfMonthCronField("*");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        assertEquals(expected, field.parse(), "Parsing '*' should return all days of the month");
    }

    @Test
    public void testParseRange() {
        DayOfMonthCronField field = new DayOfMonthCronField("5-10");
        List<Integer> expected = Arrays.asList(5, 6, 7, 8, 9, 10);
        assertEquals(expected, field.parse(), "Parsing '5-10' should return a range from 5 to 10");
    }

    @Test
    public void testParseSpecificValue() {
        DayOfMonthCronField field = new DayOfMonthCronField("15");
        List<Integer> expected = Arrays.asList(15);
        assertEquals(expected, field.parse(), "Parsing '15' should return a list with only 15");
    }

    @Test
    public void testParseInterval() {
        DayOfMonthCronField field = new DayOfMonthCronField("*/5");
        List<Integer> expected = Arrays.asList(5, 10, 15, 20, 25, 30);
        assertEquals(expected, field.parse(), "Parsing '*/5' should return every 5th day starting from day 1");
    }

    @Test
    public void testParseComplexExpression() {
        DayOfMonthCronField field = new DayOfMonthCronField("1,5-7,20");
        List<Integer> expected = Arrays.asList(1, 5, 6, 7, 20);
        assertEquals(expected, field.parse(), "Parsing '1,5-7,20' should return specific and range of days");
    }
}
