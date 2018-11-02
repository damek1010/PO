package com.company;

public class Lesson {
    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean fullTime;
    private ITimetable timetable;

    Lesson(Term term, String name, String teacherName, int year, boolean fullTime) {
        this.setTerm(term);
        this.setName(name);
        this.setTeacherName(teacherName);
        this.setYear(year);
        this.setFullTime(fullTime);
    }

    Lesson(ITimetable timetable, Term term, String name, String teacherName, int year, boolean fullTime) {
        this.setTerm(term);
        this.setName(name);
        this.setTeacherName(teacherName);
        this.setYear(year);
        this.setFullTime(fullTime);
        this.timetable = timetable;
    }

    public String toString() {
        String fullTime = this.fullTime ? "Stacjonarne" : "Niestacjonarne";
        return String.format("%s (%s %s)\nRok %d %s\n%s", this.name, this.term.getDay(), this.term, this.year, fullTime, this.teacherName);
    }

    public boolean earlierDay() {
        Term prevDayTerm = new Term(this.term.getHour(), this.term.getMinute(), this.term.getDay().prevDay());

        if (this.fullTime && prevDayTerm.getDay() == Day.SUN) prevDayTerm.setDay(Day.FRI);
        if (!this.fullTime && prevDayTerm.getDay() == Day.THU) prevDayTerm.setDay(Day.SUN);

        if (this.timetable.canBeTransferredTo(prevDayTerm, this.fullTime)) {
            this.term = prevDayTerm;
            return true;
        }

        return false;
    }

    public boolean laterDay() {
        Term nextDayTerm = new Term(this.term.getHour(), this.term.getMinute(), this.term.getDay().nextDay());

        if (this.fullTime && nextDayTerm.getDay() == Day.SAT) nextDayTerm.setDay(Day.MON);
        if (!this.fullTime && nextDayTerm.getDay() == Day.MON) nextDayTerm.setDay(Day.FRI);

        if (this.timetable.canBeTransferredTo(nextDayTerm, this.fullTime)) {
            this.term = nextDayTerm;
            return true;
        }

        return false;
    }

    public boolean earlierTime() {
        int thisTimeInMinutes = this.term.getHour() * 60 + this.term.getMinute();
        int prevTermTimeInMinutes = thisTimeInMinutes - this.term.getDuration();

        int prevTermMinute = prevTermTimeInMinutes % 60;
        int prevTermHour = (prevTermTimeInMinutes - prevTermMinute) / 60;
        Term prevTerm = new Term(prevTermHour, prevTermMinute, this.term.getDay());

        if (this.timetable instanceof TimetableWithBreaks) {
            if (TimetableWithBreaks.skipBreaks) {
                prevTerm = ((TimetableWithBreaks) this.timetable).getBreakBeforeLesson(term).getTerm().endTerm();
            } else
                return false;
        }
        prevTerm.setDay(this.term.getDay());

        if (this.timetable.canBeTransferredTo(prevTerm, this.fullTime)) {
            this.term = prevTerm;
            return true;
        }

        return false;
    }

    public boolean laterTime() {
        int thisTimeInMinutes = this.term.getHour() * 60 + this.term.getMinute();
        int nextTermTimeInMinutes = thisTimeInMinutes + this.term.getDuration();

        int nextTermMinute = nextTermTimeInMinutes % 60;
        int nextTermHour = (nextTermTimeInMinutes - nextTermMinute) / 60;
        Term nextTerm = new Term(nextTermHour, nextTermMinute, this.term.getDay());

        if (this.timetable instanceof TimetableWithBreaks) {
            if (TimetableWithBreaks.skipBreaks) {
                nextTerm = ((TimetableWithBreaks) this.timetable).getBreakAfterLesson(term).getTerm().endTerm();
            } else
                return false;
        }
        nextTerm.setDay(term.getDay());

        if (this.timetable.canBeTransferredTo(nextTerm, this.fullTime)) {
            this.term = nextTerm;
            return true;
        }

        return false;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }
}
