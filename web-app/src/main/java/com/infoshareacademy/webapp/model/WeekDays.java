package com.infoshareacademy.webapp.model;

public enum WeekDays {

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    private final String monday = "poniedziałek";
    private final String tuesday = "wtorek";
    private final String wednesday = "środa";
    private final String thursday = "czwartek";
    private final String friday = "piątek";
    private final String saturday = "sobota";
    private final String sunday = "niedziela";

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
