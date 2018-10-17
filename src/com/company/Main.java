package com.company;

public class Main {

    public static void main(String[] args) {
        Lesson lesson = new Lesson(new Term(8,0, Day.MON), "Programowanie obiektowe", "Jan Kowalski", 2);
        ActionsParser ap = new ActionsParser();
        Action[] actions = ap.parse(args);
        System.out.println(lesson);
        for (Action action : actions) {
            switch (action) {
                case DAY_LATER:
                    lesson.laterDay();
                    break;
                case DAY_EARLIER:
                    lesson.earlierDay();
                    break;
                case TIME_LATER:
                    lesson.laterTime();
                    break;
                case TIME_EARLIER:
                    lesson.earlierTime();
                    break;
            }
            System.out.println(lesson);
        }
    }
}
