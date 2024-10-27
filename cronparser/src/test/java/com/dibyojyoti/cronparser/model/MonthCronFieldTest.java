package com.dibyojyoti.cronparser.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class MonthCronFieldTest {

    @Test
    public void testParseAllMonthsWithAsterisk() {
        MonthCronField field = new MonthCronField("*");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        assertEquals(expected, field.parse(), "Parsing '*' should return all months of the year");
    }

    @Test
    public void testParseRange() {
        MonthCronField field = new MonthCronField("3-5");
        List<Integer> expected = Arrays.asList(3, 4, 5);
        assertEquals(expected, field.parse(), "Parsing '3-5' should return a range from March to May");
    }

    @Test
    public void testParseSpecificMonth() {
        MonthCronField field = new MonthCronField("10");
        List<Integer> expected = Arrays.asList(10);
        assertEquals(expected, field.parse(), "Parsing '10' should return a list containing only October");
    }

    @Test
    public void testParseInterval() {
        MonthCronField field = new MonthCronField("*/4");
        List<Integer> expected = Arrays.asList(4, 8, 12);
        assertEquals(expected, field.parse(), "Parsing '*/4' should return every fourth month starting from January");
    }

    @Test
    public void testParseComplexExpression() {
        MonthCronField field = new MonthCronField("1,6-8,12");
        List<Integer> expected = Arrays.asList(1, 6, 7, 8, 12);
        assertEquals(expected, field.parse(), "Parsing '1,6-8,12' should return specific months and a range of months");
    }
}
