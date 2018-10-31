package com.company;

public class Break {
    private Term term;

    public Break(Term term) {
        this.term = term;
    }

    public String toString() {
        return "Przerwa";
    }

    public Term getTerm() {
        return this.term;
    }
}
