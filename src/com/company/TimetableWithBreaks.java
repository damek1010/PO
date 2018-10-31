package com.company;

import java.sql.Time;
import java.util.ArrayList;

public class TimetableWithBreaks implements ITimetable {
    private static Break[] breaks;
    private ArrayList<Lesson> lessons;

    public static Boolean skipBreaks;

    public TimetableWithBreaks(Break[] breaks) {
        TimetableWithBreaks.breaks = breaks;
        System.out.println(TimetableWithBreaks.breaks[0]);
    }

    public Break getBreakAfterLesson(Term term) {
        for (Break lessonBreak : TimetableWithBreaks.breaks) {
            if (term.endTerm().getHour() == lessonBreak.getTerm().getHour() && term.endTerm().getMinute() == lessonBreak.getTerm().getMinute())
                return lessonBreak;
        }
        return null;
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        ArrayList<Lesson> lessonsOnDay = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (this.busy(lesson.getTerm())) {
                return false;
            }
        }

        for (Break lessonBreak : breaks) {
            if (this.busy(lessonBreak.getTerm())) {
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
        for (Break lessonBreak : breaks) {
            if (lessonBreak.getTerm().getHour() == term.getHour() && lessonBreak.getTerm().getMinute() == term.getMinute()) {
                return false;
            }
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

    }

    @Override
    public Object get(Term term) {
        return null;
    }

    public String toString() {
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8, 0);
        Term lastTerm = new Term(20, 0);
        Day day = null;
        Term term = null;

        Boolean isBreak = false;
        int i = 0;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) sb.append(" ");

        for (day = firstDay; ; day = day.nextDay()) {
            StringBuilder sb_day = new StringBuilder(day.toString());
            while (sb_day.length() < 14) sb_day.append(" ");
            sb.append(sb_day);
            sb.append(" ");
            if (day.ordinal() == 6) break;
        }

        sb.append('\n');
        for (term = firstTerm; term.earlierThan(lastTerm);) {
            if (!isBreak) {
                sb.append(term);
                sb.append(" ");
                for (day = firstDay; day.compareTo(lastDay) <= 0; day = day.nextDay()) {
                    Term newTerm = new Term(term.getHour(), term.getMinute(), day);
                    StringBuilder sb_term = new StringBuilder();
                    if (this.busy(newTerm)) {
                        Lesson l = (Lesson) this.get(newTerm);
                        sb_term.append(l.getName());
                        while (sb_term.length() < 14) sb_term.append(" ");
                    } else {
                        while (sb_term.length() < 14) sb_term.append(" ");
                    }
                    sb.append(sb_term);
                    sb.append(" ");

                    if (day.ordinal() == 6) break;
                }
                if (i < TimetableWithBreaks.breaks.length)
                    term = TimetableWithBreaks.breaks[i].getTerm();
            } else {
                if (i < TimetableWithBreaks.breaks.length) {
                    StringBuilder sb_term = new StringBuilder();
                    sb_term.append(breaks[i].getTerm());
                    i++;
                    while (sb_term.length() < 110) sb_term.append("-");
                    sb.append(sb_term);
                }
                term = term.endTerm();
            }
            isBreak = !isBreak;
            sb.append("\n");
        }

        return sb.toString();
    }
}
