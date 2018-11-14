package com.company;

public class DeanerySystem {
    public static void main(String[] args) {
        try {
            Action[] actions = new ActionsParser().parse(args);
            AbstractTimetable timetable = new Timetable();
            Lesson l1 = new Lesson(timetable, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1, true);
            Lesson l4 = new Lesson(timetable, new Term(13, 0, Day.TUE), "Angielski", "Nowak", 1, true);
            Lesson l2 = new Lesson(timetable, new Term(9, 30, Day.MON), "JTP", "Kowalski", 1, true);
            Lesson l3 = new Lesson(timetable, new Term(8, 0, Day.MON), "ABC", "DEF", 1, true);
            timetable.put(l1);
            timetable.put(l2);
            timetable.put(l3);
            timetable.put(l4);
            TeacherVisitor tv = new TeacherVisitor("Nowak", Day.TUE);
            for (Term term = new Term(8, 0); term.earlierThan(new Term(20, 0)); term = term.endTerm()) {
                term.setDay(tv.getDay());
                Lesson lesson = (Lesson)timetable.get(term);
                if (lesson != null)
                    lesson.accept(tv);
                else
                    System.out.println(lesson);
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
