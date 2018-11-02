package com.company;

import java.sql.Time;
import java.util.ArrayList;

public class TimetableWithBreaks extends AbstractTimetable {
    private static Break[] breaks;

    public static Boolean skipBreaks;

    public TimetableWithBreaks(Break[] breaks) {
        TimetableWithBreaks.breaks = breaks;
        TimetableWithBreaks.skipBreaks = true;
        lessons = new ArrayList<>();
    }

    public Break getBreakAfterLesson(Term term) {
        for (Break lessonBreak : TimetableWithBreaks.breaks) {
            if (term.endTerm().equals(lessonBreak.getTerm()))
                return lessonBreak;
        }
        return null;
    }

    public Break getBreakBeforeLesson(Term term) {
        int index = 0;
        for (Break lessonBreak : TimetableWithBreaks.breaks) {
            if (term.equals(lessonBreak.getTerm().endTerm()))
                return breaks[index - 1];

            index++;
        }

        return null;
    }

    @Override
    public boolean canBeTransferredTo(Term term, boolean full_time) {
        if (this.busy(term)) return false;

        for (Break lessonBreak : TimetableWithBreaks.breaks) {
            if (term.equals(lessonBreak.getTerm()))
                return TimetableWithBreaks.skipBreaks;
        }

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

    public String toString() {
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8, 0);
        Term lastTerm = new Term(20, 0);
        Day day = null;
        Term term = null;
        boolean isBreak = false;
        int breakNumber = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%10s|", ""));

        for (day = firstDay; ; day = day.nextDay()) {
            StringBuilder sb_day = new StringBuilder(String.format("%-14s|", day.toString()));
            sb.append(sb_day);
            if (day.ordinal() == 6) break;
        }

        sb.append('\n');
        for (term = firstTerm; term.earlierThan(lastTerm); ) {
            sb.append(String.format("%-10s|", term));
            for (day = firstDay; day.compareTo(lastDay) <= 0; day = day.nextDay()) {
                term.setDay(day);
                StringBuilder sb_term = new StringBuilder();
                if (this.busy(term)) {
                    Lesson l = (Lesson) this.get(term);
                    sb_term.append(String.format("%-14s|", l.getName()));
                } else if (isBreak) {
                    sb_term.append(String.format("%-14s|", " ").replace(" ", "-"));
                } else {
                    sb_term.append(String.format("%-14s|", ""));
                }
                sb.append(sb_term);

                if (day.ordinal() == 6) break;
            }
            sb.append("\n");
            isBreak = !isBreak;
            if (isBreak && breakNumber < breaks.length) {
                term = (Term) breaks[breakNumber].getTerm();
                breakNumber++;
            } else
                term = term.endTerm();
        }

        return sb.toString();
    }
}
