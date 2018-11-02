package com.company;

public class Main {

    public static void main(String[] args) {
        Term terms[] = {
                new Term(9, 30, 5),
                new Term(11, 5, 10),
                new Term(12, 45, 5),
                new Term(14, 20, 20),
                new Term(16, 10, 5),
                new Term(17,45, 5),
                new Term(19, 20,5),
                new Term(20, 55, 5),
        };

        Break[] breaks = new Break[terms.length];
        int i = 0;
        for (Term term : terms) {
            breaks[i] = new Break(term);
            i++;
        }

        TimetableWithBreaks tt = new TimetableWithBreaks(breaks);

        Lesson l1 = new Lesson(tt, new Term(14, 40, Day.SUN), "PO1", "Nowak", 2, false);
        Lesson l2 = new Lesson(tt, new Term(17, 50, Day.WED), "PO2", "Nowak", 2, false);
        tt.put(l1);
        tt.put(l2);
        System.out.println(tt);
    }
}
