package com.dibyojyoti.cronparser.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class HourCronFieldTest {

    @Test
    public void testParseAllHoursWithAsterisk() {
        HourCronField field = new HourCronField("*");
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23);
        assertEquals(expected, field.parse(), "Parsing '*' should return all hours of the day");
    }

    @Test
    public void testParseRange() {
        HourCronField field = new HourCronField("8-11");
        List<Integer> expected = Arrays.asList(8, 9, 10, 11);
        assertEquals(expected, field.parse(), "Parsing '8-11' should return a range from 8 AM to 11 AM");
    }

    @Test
    public void testParseSpecificHour() {
        HourCronField field = new HourCronField("15");
        List<Integer> expected = Arrays.asList(15);
        assertEquals(expected, field.parse(), "Parsing '15' should return a list with only 3 PM");
    }

    @Test
    public void testParseInterval() {
        HourCronField field = new HourCronField("*/6");
        List<Integer> expected = Arrays.asList(0, 6, 12, 18);
        assertEquals(expected, field.parse(), "Parsing '*/6' should return every 6 hours starting from midnight");
    }

    @Test
    public void testParseComplexExpression() {
        HourCronField field = new HourCronField("0,5,12-14,23");
        List<Integer> expected = Arrays.asList(0, 5, 12, 13, 14, 23);
        assertEquals(expected, field.parse(),
                "Parsing '0,5,12-14,23' should return a mix of specific hours and a range of hours");
    }
}
