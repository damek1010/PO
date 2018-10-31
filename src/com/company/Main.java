package com.company;

public class Main {

    public static void main(String[] args) {
        Term term1 = new Term(9, 30);
        Term term2 = new Term(11, 5);
        term1.setDuration(5);
        term2.setDuration(5);
        Break[] breaks = {
            new Break(term1),
            new Break(term2)
        };
        TimetableWithBreaks tt = new TimetableWithBreaks(breaks);
        tt.put(new Lesson(new Term(8, 0, Day.MON), "ABC", "Jan", 2));
        tt.put(new Lesson(new Term(8, 0, Day.THU), "ABC", "Jan", 2));
        System.out.println(tt);
    }
}
