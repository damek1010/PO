package com.company;

public class StudentVisitor implements TimetableElementVisitor {
    int year;
    boolean fullTime;
    Day day;

    StudentVisitor(int year, boolean fullTime, Day day) {
        this.year = year;
        this.fullTime = fullTime;
        this.day = day;
    }

    @Override
    public void visit(Lesson lesson) {

    }

    @Override
    public void visit(Break b) {

    }
}
