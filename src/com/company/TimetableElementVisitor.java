package com.company;

public interface TimetableElementVisitor {
    void visit(Lesson lesson);
    void visit(Break b);
}
