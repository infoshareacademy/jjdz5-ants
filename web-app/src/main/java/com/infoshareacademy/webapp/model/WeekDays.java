package com.infoshareacademy.webapp.model;

public enum WeekDays {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    private final String monday = "Poniedziałek";
    private final String tuesday = "Wtorek";
    private final String wednesday = "Środa";
    private final String thursday = "Czwartek";
    private final String friday = "Piątek";
    private final String saturday = "Sobota";
    private final String sunday = "Niedziela";

    @Override
    public String toString() {
        switch (this) {
            case MONDAY:
                return monday;
            case TUESDAY:
                return tuesday;
            case WEDNESDAY:
                return wednesday;
            case THURSDAY:
                return thursday;
            case FRIDAY:
                return friday;
            case SATURDAY:
                return saturday;
            case SUNDAY:
                return sunday;
        }
        return String.valueOf(this);
    }
}
