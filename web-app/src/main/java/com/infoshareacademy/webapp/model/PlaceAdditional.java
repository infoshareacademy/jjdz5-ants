package com.infoshareacademy.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class PlaceAdditional {

    private static final String ALWAYS_OPEN = "Otwarte przez cały czas.";
    private static final String CLOSED = "Zamknięte";

    private final Map<WeekDays, String> openingHours;
    private final Map<PriceType, Double> prices;

    private Map<WeekDays, String> defaultOpeningHours = new HashMap<>();
    private Map<PriceType, Double> defaultPrices = new HashMap<>();

    public PlaceAdditional() {
        setDefaultOpeningHours();
        setDefaultPrices();
        this.openingHours = defaultOpeningHours;
        this.prices = defaultPrices;
    }

    public PlaceAdditional(Map<WeekDays, String> openingHours, Map<PriceType, Double> prices) {
        this.openingHours = openingHours;
        this.prices = prices;
    }

    private void setDefaultOpeningHours() {
        defaultOpeningHours.put(WeekDays.MONDAY, ALWAYS_OPEN);
        defaultOpeningHours.put(WeekDays.TUESDAY, ALWAYS_OPEN);
        defaultOpeningHours.put(WeekDays.WEDNESDAY, ALWAYS_OPEN);
        defaultOpeningHours.put(WeekDays.THURSDAY, ALWAYS_OPEN);
        defaultOpeningHours.put(WeekDays.FRIDAY, ALWAYS_OPEN);
        defaultOpeningHours.put(WeekDays.SATURDAY, ALWAYS_OPEN);
        defaultOpeningHours.put(WeekDays.SUNDAY, ALWAYS_OPEN);
    }

    private void setDefaultPrices() {
        Double forFree = 0.0;
        defaultPrices.put(PriceType.NORMAL, forFree);
        defaultPrices.put(PriceType.REDUCED, forFree);
    }

    @Override
    public String toString() {
        return "PlaceAdditional{" +
                "\nopeningHours=" + openingHours +
                "\n, prices=" + prices +
                '}';
    }

}
