package com.company.test;

import com.company.Day;
import org.junit.Before;

import static org.junit.Assert.*;

public class DayTest {
    Day thu, sun, mon;

    @Before
    public void setUp() {
        thu = Day.THU;
        sun = Day.SUN;
        mon = Day.MON;
    }

    @org.junit.Test
    public void nextDay() {
        Day fri = thu.nextDay();
        assertEquals(fri, Day.FRI);
        Day mon = sun.nextDay();
        assertEquals(mon, Day.MON);
    }

    @org.junit.Test
    public void prevDay() {
        Day wed = thu.prevDay();
        assertEquals(wed,Day.WED);
        Day sun = mon.prevDay();
        assertEquals(sun, Day.SUN);
    }
}