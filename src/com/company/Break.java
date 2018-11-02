package com.company;

public class Break {
    private BasicTerm term;

    public Break(BasicTerm term) {
        this.term = term;
    }

    public String toString() {
        return "Przerwa";
    }

    public BasicTerm getTerm() {
        return this.term;
    }
}
