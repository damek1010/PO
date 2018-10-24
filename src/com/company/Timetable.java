package com.company;

import java.util.ArrayList;

public class Timetable implements ITimetable{

    private ArrayList<Lesson> lessons;

    public Timetable() {
        lessons = new ArrayList<>();
        lessons.add(new Lesson(new Term(8, 0, Day.MON), "ABC", "Jan", 2));
        lessons.add(new Lesson(new Term(8, 0, Day.THU), "ABC", "Jan", 2));
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        ArrayList<Lesson> lessonsOnDay = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getTerm().getDay() == term.getDay()) {
                if (!term.laterThan(lesson.getTerm().endTerm()) && !lesson.getTerm().laterThan(term.endTerm())) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean busy(Term term) {
        return term.getDay() == term.getDay() && term.getHour() == term.getHour() && term.getMinute() == term.getMinute();
    }

    @Override
    public boolean put(Lesson lesson) {
        return false;
    }

    @Override
    public void perform(Action[] actions) {
        int id = 0;

        for (Action action : actions) {
            switch (action) {
                case DAY_LATER:
                    lessons.get(id).laterDay();
                    break;
                case DAY_EARLIER:
                    lessons.get(id).earlierDay();
                    break;
                case TIME_LATER:
                    lessons.get(id).laterTime();
                    break;
                case TIME_EARLIER:
                    lessons.get(id).earlierTime();
                    break;
            }
            id = id++ % lessons.size();
        }
    }

    @Override
    public Object get(Term term) {
        for (Lesson lesson : lessons) {
            if (lesson.getTerm().getDay() == term.getDay() && lesson.getTerm().getHour() == term.getHour() && lesson.getTerm().getMinute() == term.getMinute()) {
                return lesson;
            }
        }
        return null;
    }

    public String toString() {
        String result = "";
        ITimetable timetable = new Timetable();
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8,0,firstDay);
        Term lastTerm = new Term(20,0,lastDay);
        Day day = null;
        Term term = null;
        for(day = firstDay ; day.compareTo(lastDay) <= 0 ; day = day.nextDay()){
            result += day;
            result += " ";
            for(term = firstTerm ; term.earlierThan(lastTerm) ; term = term.endTerm()){
                result += term;
                if(timetable.busy(term)) {
                    result += timetable.get(term);
                    result += "\n";
                }
                else
                    result += " \n";
            }
            if (day.compareTo(lastDay) == 0) break;
        }
        return result;
    }
}
