package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LessonTest {

    Lesson lessonMON;
    Lesson lessonTUE;

    @Before
    public void setUp() {
        lessonTUE = new Lesson(new Term(8, 0, Day.TUE), "PO", "Jan Kowalski", 2);
        lessonMON = new Lesson(new Term(20, 0, Day.MON), "PO", "Jan Kowalski", 2);
    }

    @Test
    public void earlierDay() {
        assertTrue(lessonTUE.earlierDay());
        assertFalse(lessonMON.earlierDay());
    }

    @Test
    public void laterDay() {
    }

    @Test
    public void earlierTime() {
    }

    @Test
    public void laterTime() {
    }
}