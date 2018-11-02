package com.company;

public class Term extends BasicTerm{
    private Day day;

    Term(int hour, int minute) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(90);
    }

    Term(int hour, int minute, int duration) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(duration);
    }

    Term(int hour, int minute, Day day) {
        this.setHour(hour);
        this.setMinute(minute);
        this.setDuration(90);
        this.setDay(day);
    }

    public String toString() {
        return String.format("%02d:%02d [%d]", this.hour, this.minute, this.duration);
    }

    public Boolean earlierThan(Term other) {
        int thisTimeInMinutes = this.getHour() * 60 + this.getMinute();
        int otherTimeInMinutes = other.getHour() * 60 + other.getMinute();
        return thisTimeInMinutes <= otherTimeInMinutes;
    }

    public Boolean laterThan(Term other) {
        int thisTimeInMinutes = this.getHour() * 60 + this.getMinute();
        int otherTimeInMinutes = other.getHour() * 60 + other.getMinute();
        return thisTimeInMinutes >= otherTimeInMinutes;
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
}
