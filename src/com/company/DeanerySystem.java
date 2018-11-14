package com.company;

public class DeanerySystem {
    public static void main(String[] args) {
        try {
            Action[] actions = new ActionsParser().parse(args);
            AbstractTimetable timetable = new Timetable();
            Lesson l1 = new Lesson(timetable, new Term(8, 0, Day.TUE), "Angielski", "Nowak", 1, true);
            Lesson l2 = new Lesson(timetable, new Term(9, 30, Day.MON), "JTP", "Kowalski", 1, true);
            timetable.put(l1);
            timetable.put(l2);
            System.out.println(timetable);
            timetable.perform(actions);
            System.out.println(timetable);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
