package com.company;

public interface VisitableTimetableElement {
    void accept(TimetableElementVisitor visitor);
}
