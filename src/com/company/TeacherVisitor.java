package com.company;

public class TeacherVisitor implements TimetableElementVisitor {
    public String getName() {
        return name;
    }

    public Day getDay() {
        return day;
    }

    String name;
    Day day;

    TeacherVisitor(String name, Day day) {
        this.name = name;
        this.day = day;
    }

    @Override
    public void visit(Lesson lesson) {
        System.out.println(lesson.getTerm());
        System.out.println(lesson.getTeacherName());
        if (lesson.getTerm().getDay() == this.day && lesson.getTeacherName().equals(this.name))
            System.out.printf(lesson.getTerm() + " " + lesson.getName());
    }

    @Override
    public void visit(Break b) {

    }
}
