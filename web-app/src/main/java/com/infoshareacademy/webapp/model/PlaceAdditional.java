package com.infoshareacademy.webapp.model;

import java.math.BigDecimal;
import java.util.*;

public class PlaceAdditional {

    private final Map<WeekDays, String> openingHours;
    private final Map<PriceTypes, Double> prices;
    private final Map<String, Integer> ratings;
    private final Integer ratingsAmount;
    private final BigDecimal averageRating;

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
        this.ratingsAmount = defaultRatings.size();
        this.averageRating = countAverageRating(defaultRatings);
        this.isDefault = true;
    }

    public PlaceAdditional(Map<WeekDays, String> openingHours, Map<PriceTypes, Double> prices,
                           Map<String, Integer> ratings) {
        this.openingHours = openingHours;
        this.prices = prices;
        this.ratings = ratings;
        this.ratingsAmount = ratings.size();
        this.averageRating = countAverageRating(ratings);
        this.isDefault = false;
    }

    private void setDefaultOpeningHours() {
        defaultOpeningHours.put(WeekDays.MONDAY, PlaceConstants.NO_DATA_TEXT);
        defaultOpeningHours.put(WeekDays.TUESDAY, PlaceConstants.NO_DATA_TEXT);
        defaultOpeningHours.put(WeekDays.WEDNESDAY, PlaceConstants.NO_DATA_TEXT);
        defaultOpeningHours.put(WeekDays.THURSDAY, PlaceConstants.NO_DATA_TEXT);
        defaultOpeningHours.put(WeekDays.FRIDAY, PlaceConstants.NO_DATA_TEXT);
        defaultOpeningHours.put(WeekDays.SATURDAY, PlaceConstants.NO_DATA_TEXT);
        defaultOpeningHours.put(WeekDays.SUNDAY, PlaceConstants.NO_DATA_TEXT);
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

    public Map<WeekDays, String> getOpeningHours() {
        return openingHours;
    }

    public Map<PriceTypes, Double> getPrices() {
        return prices;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public Integer getRatingsAmount() {
        return ratingsAmount;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public Boolean getDefaultStatus() {
        return isDefault;
    }

    private BigDecimal countAverageRating(Map<String, Integer> ratings) {
        BigDecimal sumOfRatings = new BigDecimal(countSumOfRatings(ratings));
        BigDecimal ratingsAmount = new BigDecimal(ratings.size());
        BigDecimal averageRating = sumOfRatings.divide(ratingsAmount, 2, BigDecimal.ROUND_HALF_UP);
        return averageRating;
    }

    private Integer countSumOfRatings(Map<String, Integer> ratings){
        return ratings.values().stream().mapToInt(rating -> rating.intValue()).sum();
    }

    @Override
    public String toString() {
        return "PlaceAdditional{" +
                "openingHours=" + openingHours +
                ", prices=" + prices +
                ", ratings=" + ratings +
                ", ratingsAmount=" + ratingsAmount +
                ", averageRating=" + averageRating +
                ", isDefault=" + isDefault +
                '}';
    }
}
