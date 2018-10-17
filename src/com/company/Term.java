package com.company;

public class Term {
    int hour, minute, duration;
    Day day;

    Term(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.duration = 90;
    }

    Term(int hour, int minute, Day day) {
        this.hour = hour;
        this.minute = minute;
        this.duration = 90;
        this.day = day;
    }

    public String toString() {
        String h, m;
        h = this.hour < 10 ? "0" + this.hour : "" + this.hour;
        m = this.minute < 10 ? "0" + this.minute : "" + this.minute;
        return (h + ":" + m + "[" + this.duration + "]");
    }

    public Boolean earlierThan(Term other) {
        if (this.hour < other.hour) return true;
        if (this.minute <= other.minute) return true;
        return false;
    }

    public Boolean laterThan(Term other) {
        if (this.hour > other.hour) return true;
        if (this.minute >= other.minute) return true;
        return false;
    }

    public Term endTerm(Term other) {
        Term newTerm = new Term(this.hour, this.minute);
        newTerm.duration = (other.hour * 60 + other.minute) - (newTerm.hour * 60 + newTerm.minute);
        return newTerm;
    }

    public Term endTerm() {
        int m = this.hour * 60 + this.minute + this.duration;
        return new Term((m - m % 60) / 60,m % 60);
    }

    public boolean equals(Term other) {
        return (this.hour == other.hour && this.minute == other.minute && this.duration == other.duration);
    }
}
