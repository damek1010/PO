package com.company;

public class BasicTerm {
    protected int hour;
    protected int minute;
    protected int duration;


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

    //returns Term when this term is ending
    public Term endTerm() {
        int thisTimeInMinutes = this.getHour() * 60  + this.getMinute();
        int resultTimeInMinutes = thisTimeInMinutes + this.getDuration();

        int resultMinute = resultTimeInMinutes % 60;
        int resultHour = (resultTimeInMinutes - resultMinute) / 60;

        return new Term(resultHour, resultMinute);
    }
}