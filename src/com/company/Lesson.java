package com.company;

public class Lesson {
    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean full_time;
    private ITimetable timetable;

    Lesson(Term term, String name, String teacherName, int year) {
        this.setTerm(term);
        this.setName(name);
        this.setTeacherName(teacherName);
        this.setYear(year);
    }

    Lesson(ITimetable timetable, Term term, String name, String teacherName, int year) {
        this.setTerm(term);
        this.setName(name);
        this.setTeacherName(teacherName);
        this.setYear(year);
        this.timetable = timetable;
    }

    public String toString() {
        return this.getName() + " (" + this.getTerm().getDay() + " " + this.getTerm() + ")\n" +
                this.getYear() + " " + (this.isFull_time() ? "Stacjonarne" : "Niestacjonarne") + "\n" +
                this.getTeacherName();
    }

    public boolean earlierDay() {
        if (this.getTerm().getDay().prevDay().ordinal() >= Day.MON.ordinal() && this.getTerm().getDay().prevDay().ordinal() <= Day.THU.ordinal()) {
            this.getTerm().setDay(this.getTerm().getDay().prevDay());
            return timetable.canBeTransferredTo(new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().prevDay()), this.isFull_time());
        }
        if (this.getTerm().getDay() == Day.MON && this.getTerm().laterThan(new Term(8, 0)) && this.getTerm().earlierThan(new Term(17, 0))) {
            this.getTerm().setDay(Day.FRI);
            return timetable.canBeTransferredTo(new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().prevDay()), this.isFull_time());
        }
        return false;
    }

    public boolean laterDay() {
        if (this.getTerm().getDay().nextDay().ordinal() >= Day.TUE.ordinal() && this.getTerm().getDay().nextDay().ordinal() <= Day.THU.ordinal()) {
            this.getTerm().setDay(this.getTerm().getDay().nextDay());
            return timetable.canBeTransferredTo(new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().nextDay()), this.isFull_time());
        }
        if (this.getTerm().getDay() == Day.FRI) {
            this.getTerm().setDay(Day.MON);
            return timetable.canBeTransferredTo(new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().nextDay()), this.isFull_time());
        }
        if (this.getTerm().getDay() == Day.THU && this.getTerm().laterThan(new Term(8, 0)) && this.getTerm().earlierThan(new Term(17, 0))) {
            this.getTerm().setDay(Day.MON);
            return timetable.canBeTransferredTo(new Term(this.getTerm().getHour(), this.getTerm().getMinute(), this.getTerm().getDay().nextDay()), this.isFull_time());
        }
        return false;
    }

    public boolean earlierTime() {
        int m = this.getTerm().getHour() * 60 + this.getTerm().getMinute() - this.getTerm().getDuration();
        Term newTerm = new Term((m - m % 60) / 60,m % 60, this.getTerm().getDay());
        if ((newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(17,0)) && newTerm.getDay() == Day.FRI)
                || (newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(20,0)) && newTerm.getDay().ordinal() >= Day.MON.ordinal() && newTerm.getDay().ordinal() <= Day.THU.ordinal())) {

            if (timetable.canBeTransferredTo(newTerm, this.isFull_time())) {
                this.setTerm(newTerm);
                return true;
            }
        }

        return false;
    }

    public boolean laterTime() {
        int m = this.getTerm().getHour() * 60 + this.getTerm().getMinute() + this.getTerm().getDuration();
        Term newTerm = new Term((m - m % 60) / 60,m % 60, this.getTerm().getDay());
        if ((newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(17,0)) && newTerm.getDay() == Day.FRI)
                || (newTerm.laterThan(new Term(8,0)) && newTerm.earlierThan(new Term(20,0)) && newTerm.getDay().ordinal() >= Day.MON.ordinal() && newTerm.getDay().ordinal() <= Day.THU.ordinal())) {
            if (timetable.canBeTransferredTo(newTerm, this.isFull_time())) {
                this.setTerm(newTerm);
                return true;
            }
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

    public boolean isFull_time() {
        return full_time;
    }

    public void setFull_time(boolean full_time) {
        this.full_time = full_time;
    }
}
