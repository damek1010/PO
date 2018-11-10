package com.company.test;

import com.company.Day;
import com.company.Term;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TermTest {

    Term term;

    @Before
    public void setUp() {
        term = new Term(9, 0, Day.MON);
    }

    @Test
    public void earlierThan() {
        Boolean isEarlier = term.earlierThan(new Term(10, 57));
        assertTrue(isEarlier);
        Boolean isNotEarlier = term.earlierThan(new Term(5, 23));
        assertFalse(isNotEarlier);
        isNotEarlier = term.earlierThan(term);
        assertFalse(isNotEarlier);
    }

    @Test
    public void laterThan() {
        Boolean isLater = term.laterThan(new Term(8, 36));
        assertTrue(isLater);
        Boolean isNotLater = term.laterThan(new Term(12, 17));
        assertFalse(isNotLater);
        isNotLater = term.laterThan(term);
        assertFalse(isNotLater);
    }
}