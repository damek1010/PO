package com.company;

public class Term extends BasicTerm implements Comparable {
    private Day day;

    public Term(int hour, int minute) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(90);
    }

    Term(int hour, int minute, int duration) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(duration);
    }

    public Term(int hour, int minute, Day day) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(90);
        this.setDay(day);
    }

    public String toString() {
        return String.format("%02d:%02d [%d]", this.hour, this.minute, this.duration);
    }

    //returns term with duration that equals difference between "other" and this time
    //returned term begins at this time and ends at other time
    public Term endTerm(Term other) {
        int thisTimeInMinutes = this.getHour() * 60 + this.getMinute();
        int otherTimeInMinutes = other.getHour() * 60 + other.getMinute();

        Term resultTerm = new Term(this.getHour(), this.getMinute());
        resultTerm.setDuration(otherTimeInMinutes - thisTimeInMinutes);

        return resultTerm;
    }

    public boolean equals(Term other) {
        int thisTimeInMinutes = this.getHour() * 60 + this.getMinute();
        int otherTimeInMinutes = other.getHour() * 60 + other.getMinute();

        return (thisTimeInMinutes == otherTimeInMinutes && (this.day == other.day || this.day == null || other.day == null));
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;

        Term term = (Term) o;

        return day == term.day && this.equals(term);
    }

    @Override
    public int hashCode() {
        return day != null ? day.hashCode() : 0;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;

        Term term = (Term) o;
        if (this.day.ordinal() > term.getDay().ordinal()) return 1;
        if (this.day.ordinal() < term.getDay().ordinal()) return -1;
        return super.compareTo(o);
    }
}
