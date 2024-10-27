package com.dibyojyoti.cronparser.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DayOfWeekCronFieldTest {

    @Test
    public void testParseAllDaysWithAsterisk() {
        DayOfWeekCronField field = new DayOfWeekCronField("*");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertEquals(expected, field.parse(), "Parsing '*' should return all days of the week");
    }

    @Test
    public void testParseRange() {
        DayOfWeekCronField field = new DayOfWeekCronField("2-5");
        List<Integer> expected = Arrays.asList(2, 3, 4, 5);
        assertEquals(expected, field.parse(), "Parsing '2-5' should return a range from Tuesday to Friday");
    }

    @Test
    public void testParseSpecificDay() {
        DayOfWeekCronField field = new DayOfWeekCronField("3");
        List<Integer> expected = Arrays.asList(3);
        assertEquals(expected, field.parse(), "Parsing '3' should return a list with only Wednesday");
    }

    @Test
    public void testParseInterval() {
        DayOfWeekCronField field = new DayOfWeekCronField("*/2");
        List<Integer> expected = Arrays.asList(2, 4, 6);
        assertEquals(expected, field.parse(), "Parsing '*/2' should return every second day starting from Monday");
    }

    @Test
    public void testParseComplexExpression() {
        DayOfWeekCronField field = new DayOfWeekCronField("1,3,5-7");
        List<Integer> expected = Arrays.asList(1, 3, 5, 6, 7);
        assertEquals(expected, field.parse(), "Parsing '1,3,5-7' should return a mix of specific and range of days");
    }
}
