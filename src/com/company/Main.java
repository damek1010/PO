package com.company;

public class Main {

    public static void main(String[] args) {
        Timetable tt = new Timetable();
        tt.put(new Lesson(new Term(8, 0, Day.MON), "ABC", "Jan", 2));
        tt.put(new Lesson(new Term(8, 0, Day.THU), "ABC", "Jan", 2));
        System.out.println(tt);
    }
}
