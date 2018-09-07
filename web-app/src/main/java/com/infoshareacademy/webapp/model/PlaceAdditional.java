package com.infoshareacademy.webapp.model;

import java.util.*;

public class PlaceAdditional {

    private final Map<WeekDays, String> openingHours;
    private final Map<PriceTypes, Double> prices;
    private final Map<String, Integer> ratings;

    private Boolean isDefault;

    private Map<WeekDays, String> defaultOpeningHours = new TreeMap<>();
    private Map<PriceTypes, Double> defaultPrices = new TreeMap<>();
    private static Map<String, Integer> defaultRatings = new HashMap<>();


    public PlaceAdditional() {
        setDefaultOpeningHours();
        setDefaultPrices();
        setDefaultRatings();
        this.openingHours = defaultOpeningHours;
        this.prices = defaultPrices;
        this.ratings = defaultRatings;
        this.isDefault = true;
    }

    public PlaceAdditional(Map<WeekDays, String> openingHours, Map<PriceTypes, Double> prices, Map<String, Integer> ratings) {
        this.openingHours = openingHours;
        this.prices = prices;
        this.ratings = ratings;
        this.isDefault = false;
    }

    private void setDefaultOpeningHours() {
        defaultOpeningHours.put(WeekDays.MONDAY, PlaceConstants.NO_DATA);
        defaultOpeningHours.put(WeekDays.TUESDAY, PlaceConstants.NO_DATA);
        defaultOpeningHours.put(WeekDays.WEDNESDAY, PlaceConstants.NO_DATA);
        defaultOpeningHours.put(WeekDays.THURSDAY, PlaceConstants.NO_DATA);
        defaultOpeningHours.put(WeekDays.FRIDAY, PlaceConstants.NO_DATA);
        defaultOpeningHours.put(WeekDays.SATURDAY, PlaceConstants.NO_DATA);
        defaultOpeningHours.put(WeekDays.SUNDAY, PlaceConstants.NO_DATA);
    }

    private void setDefaultPrices() {
        Double forFree = 0.0;
        defaultPrices.put(PriceTypes.NORMAL, forFree);
        defaultPrices.put(PriceTypes.REDUCED_FOR_CHILDREN, forFree);
        defaultPrices.put(PriceTypes.REDUCED_FOR_PENSIONERS, forFree);
    }

    private static void setDefaultRatings() {
        Integer defaultRating = 5;
        String defaultUser = "Użytkownik";
        defaultRatings.put(defaultUser, defaultRating);
    }

    public static Map<String, Integer> getDefaultRatings() {
        setDefaultRatings();
        return defaultRatings;
    }

    public Boolean getDefaultStatus() {
        return isDefault;
    }

    @Override
    public String toString() {
        return "PlaceAdditional{" +
                "openingHours=" + openingHours +
                ", prices=" + prices +
                ", ratings=" + ratings +
                '}';
    }
}
