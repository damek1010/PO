package com.company;

public enum Day {
    MON("Poniedziałek"), TUE("Wtorek"), WED("Środa"), THU("Czwartek"), FRI("Piątek"), SAT("Sobota"), SUN("Niedziela");

    private String description;
    Day(String description) {
        this.setDescription(description);
    }

    public String toString() {
        return this.getDescription();
    }

    public Day nextDay() {
        return Day.values()[(this.ordinal() + 1) % 7];
    }

    public Day prevDay() {
        int index = this.ordinal() > 0 ? this.ordinal() - 1 : Day.values().length - 1;
        return Day.values()[index];
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
