package com.company;

public class Term {
    private int hour;
    private int minute;
    private int duration;
    private Day day;

    Term(int hour, int minute) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(90);
    }

    Term(int hour, int minute, Day day) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(90);
        this.setDay(day);
    }

    public String toString() {
        String h, m;
        h = this.getHour() < 10 ? "0" + this.getHour() : "" + this.getHour();
        m = this.getMinute() < 10 ? "0" + this.getMinute() : "" + this.getMinute();
        return (h + ":" + m + "[" + this.getDuration() + "]");
    }

    public Boolean earlierThan(Term other) {
//        if (this.day != other.day) return this.day.ordinal() < other.day.ordinal();
        int m1 = this.hour * 60 + this.minute;
        int m2 = other.hour * 60 + other.minute;
        return m1 < m2;
    }

    public Boolean laterThan(Term other) {
//        if (this.day != other.day) return this.day.ordinal() > other.day.ordinal();
        int m1 = this.hour * 60 + this.minute;
        int m2 = other.hour * 60 + other.minute;
        return m1 > m2;
    }

    public Term endTerm(Term other) {
        Term newTerm = new Term(this.getHour(), this.getMinute());
        newTerm.setDuration((other.getHour() * 60 + other.getMinute()) - (newTerm.getHour() * 60 + newTerm.getMinute()));
        return newTerm;
    }

    public Term endTerm() {
        int m = this.getHour() * 60 + this.getMinute() + this.getDuration();
        return new Term((m - m % 60) / 60,m % 60);
    }

    public boolean equals(Term other) {
        return (this.getHour() == other.getHour() && this.getMinute() == other.getMinute() && this.getDuration() == other.getDuration());
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
