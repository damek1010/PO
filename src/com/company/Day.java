package com.company;

public enum Day {
    MON("Poniedziałek"), TUE("Wtorek"), WED("Sroda"), THU("Czwartek"), FRI("Piątek"), SAT("Sobota"), SUN("Niedziela");

    private String description;
    Day(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }

    public Day nextDay() {
        return Day.values()[(this.ordinal() + 1) % 7];
    }

    public Day prevDay() {
        int index = this.ordinal() - 1;
        if (index < 0) index = 6;
        return Day.values()[index];
    }
}
