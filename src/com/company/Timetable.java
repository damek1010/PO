package com.company;

import java.util.ArrayList;

public class Timetable implements ITimetable{

    private ArrayList<Lesson> lessons;

    public Timetable() {
        lessons = new ArrayList<>();
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        ArrayList<Lesson> lessonsOnDay = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (this.busy(lesson.getTerm())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean busy(Term term) {
        for (Lesson lesson : lessons) {
            if (lesson.getTerm().getDay() == term.getDay() && lesson.getTerm().getHour() == term.getHour() && lesson.getTerm().getMinute() == term.getMinute()) return true;
        }
        return false;
    }

    @Override
    public boolean put(Lesson lesson) {
        if (!this.busy(lesson.getTerm())) {
            lessons.add(lesson);
            return true;
        }
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
            id++;
            id = id % lessons.size();
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
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8,0);
        Term lastTerm = new Term(20,0);
        Day day = null;
        Term term = null;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) sb.append(" ");

        for (day = firstDay; ; day = day.nextDay()) {
            StringBuilder sb_day = new StringBuilder(day.toString());
            while(sb_day.length() < 14) sb_day.append(" ");
            sb.append(sb_day);
            sb.append(" ");
            if (day.ordinal() == 6) break;
        }

        sb.append('\n');
        for (term = firstTerm; term.earlierThan(lastTerm); term = term.endTerm()) {
            sb.append(term);
            sb.append(" ");
            for (day = firstDay; day.compareTo(lastDay) <= 0; day = day.nextDay()) {
                Term newTerm = new Term(term.getHour(), term.getMinute(), day);
                StringBuilder sb_term = new StringBuilder();
                if (this.busy(newTerm)) {
                    Lesson l = (Lesson)this.get(newTerm);
                    sb_term.append(l.getName());
                    while(sb_term.length() < 14) sb_term.append(" ");
                } else {
                    while(sb_term.length() < 14) sb_term.append(" ");
                }
                sb.append(sb_term);
                sb.append(" ");

                if (day.ordinal() == 6) break;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
