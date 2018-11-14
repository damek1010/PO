package com.company;

public class BasicTerm implements Comparable{
    protected int hour;
    protected int minute;
    protected int duration;


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour > 23) throw new IllegalArgumentException("Hour cannot be larger than 23");
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if (minute > 59) throw new IllegalArgumentException("Minute cannot be larger than 59");
        this.minute = minute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    //returns Term when this term is ending
    public Term endTerm() {
        int thisTimeInMinutes = this.getHour() * 60 + this.getMinute();
        int resultTimeInMinutes = thisTimeInMinutes + this.getDuration();

        int resultMinute = resultTimeInMinutes % 60;
        int resultHour = (resultTimeInMinutes - resultMinute) / 60;

        return new Term(resultHour, resultMinute);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;

        Term term = (Term) o;
        if (this.laterThan(term)) return 1;
        if (this.earlierThan(term)) return -1;
        return 0;
    }
}
