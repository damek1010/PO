package com.company;

import java.util.ArrayList;

public class Timetable implements ITimetable {

    private ArrayList<Lesson> lessons;

    public Timetable() {
        lessons = new ArrayList<>();
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        if (this.busy(term)) return false;
        switch (term.getDay()) {
            case MON:
            case TUE:
            case WED:
            case THU: {
                if (term.laterThan(new Term(8, 0)) && term.endTerm().earlierThan(new Term(20, 0)) && full_time)
                    return true;
                return false;
            }
            case FRI: {
                if (term.laterThan(new Term(8, 0)) && term.endTerm().earlierThan(new Term(17, 0)) && full_time)
                    return true;
                if (term.laterThan(new Term(17, 0)) && term.endTerm().earlierThan(new Term(20, 0)) && !full_time)
                    return true;
                return false;
            }
            case SAT:
            case SUN: {
                if (term.laterThan(new Term(8, 0)) && term.endTerm().earlierThan(new Term(20, 0)) && !full_time)
                    return true;
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean busy(Term term) {
        return this.get(term) != null;
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
            if (lesson.getTerm().equals(term)) {
                return lesson;
            }
        }
        return null;
    }

    public String toString() {
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8, 0);
        Term lastTerm = new Term(20, 0);
        Day day = null;
        Term term = null;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10s|", ""));

        for (day = firstDay; ; day = day.nextDay()) {
            StringBuilder sb_day = new StringBuilder(String.format("%-14s|", day.toString()));
            sb.append(sb_day);
            if (day.ordinal() == 6) break;
        }

        sb.append('\n');
        for (term = firstTerm; term.earlierThan(lastTerm); term = term.endTerm()) {
            sb.append(String.format("%10s|", term));
            for (day = firstDay; day.compareTo(lastDay) <= 0; day = day.nextDay()) {
                term.setDay(day);
                StringBuilder sb_term = new StringBuilder();
                if (this.busy(term)) {
                    Lesson l = (Lesson) this.get(term);
                    sb_term.append(String.format("%-14s|", l.getName()));
                } else {
                    sb_term.append(String.format("%-14s|", ""));
                }
                sb.append(sb_term);

                if (day.ordinal() == 6) break;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
