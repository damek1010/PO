package com.company;

public class Lesson {
    Term term;
    String name;
    String teacherName;
    int year;
    boolean full_time;

    Lesson(Term term, String name, String teacherName, int year) {
        this.term = term;
        this.name = name;
        this.teacherName = teacherName;
        this.year = year;
    }

    public String toString() {
        return this.name + " (" + this.term.day + " " + this.term + ")\n" +
                this.year + " " + (this.full_time ? "Stacjonarne" : "Niestacjonarne") + "\n" +
                this.teacherName;
    }

    public boolean earlierDay() {
        if (this.term.day.prevDay().ordinal() >= Day.MON.ordinal() && this.term.day.prevDay().ordinal() <= Day.THU.ordinal()) {
            this.term.day = this.term.day.prevDay();
            return true;
        }
        if (this.term.day == Day.MON && this.term.laterThan(new Term(8, 0)) && this.term.earlierThan(new Term(17, 0))) {
            this.term.day = Day.FRI;
            return true;
        }
        return false;
    }

    public boolean laterDay() {
        if (this.term.day.nextDay().ordinal() >= Day.TUE.ordinal() && this.term.day.nextDay().ordinal() <= Day.THU.ordinal()) {
            this.term.day = this.term.day.nextDay();
            return true;
        }
        if (this.term.day == Day.FRI) {
            this.term.day = Day.MON;
            return true;
        }
        if (this.term.day == Day.THU && this.term.laterThan(new Term(8, 0)) && this.term.earlierThan(new Term(17, 0))) {
            this.term.day = Day.MON;
            return true;
        }
        return false;
    }

    public boolean earlierTime() {
        int m = this.term.hour * 60 + this.term.minute - this.term.duration;
        Term newTerm = new Term((m - m % 60) / 60,m % 60, this.term.day);
        if ((newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(17,0)) && newTerm.day == Day.FRI)
                || (newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(20,0)) && newTerm.day.ordinal() >= Day.MON.ordinal() && newTerm.day.ordinal() <= Day.THU.ordinal())) {
            this.term = newTerm;
            return true;
        }

        return false;
    }

    public boolean laterTime() {
        int m = this.term.hour * 60 + this.term.minute + this.term.duration;
        Term newTerm = new Term((m - m % 60) / 60,m % 60, this.term.day);
        if ((newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(17,0)) && newTerm.day == Day.FRI)
                || (newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(20,0)) && newTerm.day.ordinal() >= Day.MON.ordinal() && newTerm.day.ordinal() <= Day.THU.ordinal())) {
            this.term = newTerm;
            return true;
        }

        return false;
    }
}
