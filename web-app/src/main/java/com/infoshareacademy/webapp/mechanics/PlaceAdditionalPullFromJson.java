package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.model.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class PlaceAdditionalPullFromJson {

    @Inject
    private AccessJson accessJson;

    private JSONArray placesArray;
    private Integer pullIndex;

    public void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    protected PlaceAdditional preparePlaceAdditional() {
        PlaceAdditional placeAdditional = new PlaceAdditional(pullOpeningHoursFromJsonArray(), pullPricesFromJsonArray(), pullRatingsFromJsonArray());
        return  placeAdditional;
    }

    private Map<WeekDays, String> pullOpeningHoursFromJsonArray() {
        Map<WeekDays, String> openingHours = new TreeMap<>();
        for (WeekDays weekDay : WeekDays.values()){
            openingHours.put(weekDay, pullSpecificDayHours(weekDay));
        }
        return openingHours;
    }

    private String pullSpecificDayHours(WeekDays weekDay) {
        JSONObject openingHoursCollection = accessJson.getSubJsonObject(placesArray, pullIndex, PlaceConstants.PLACE_OPENING_HOURS);
        try {
            Map pulledOpeningHours = accessJson.pullJsonStringCollection(openingHoursCollection);
            String specificDayHours = (String) accessJson.findSpecificValueInCollection(pulledOpeningHours, weekDay.name());
            if (isStringNotNull(specificDayHours)) {
                return specificDayHours;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: New error, identify and add annotation ||");
        }
        System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: Cannot identify " + weekDay.name() + ", broken entry in json ||");
        return PlaceConstants.NO_DATA;
    }

    private Map<PriceTypes, Double> pullPricesFromJsonArray() {
        Map<PriceTypes, Double> prices = new TreeMap<>();
        for (PriceTypes priceType : PriceTypes.values()) {
            prices.put(priceType, pullSpecificPriceType(priceType));
        }
        return prices;
    }

    private Double pullSpecificPriceType(PriceTypes priceType) {
        JSONObject pricesCollection = accessJson.getSubJsonObject(placesArray, pullIndex, PlaceConstants.PLACE_PRICES);
        Double noData = -1.0;
        try {
            Map pulledPrices = accessJson.pullJsonStringCollection(pricesCollection);
            Double specificPriceType = Double.parseDouble(accessJson.findSpecificValueInCollection(pulledPrices, priceType.name()).toString());
            return specificPriceType;
        } catch (NumberFormatException e) {
            System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: NumberFormatException in PRICE_TYPE: " + priceType.name() + " ||");
        } catch (NullPointerException e) {
            System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: NullPointerException in PRICE_TYPE: " + priceType.name() + " ||");
        }
        return noData;
    }

    private Map<String, Integer> pullRatingsFromJsonArray() {
        Map<String, Integer> defaultRatings;
        try {
            JSONObject ratingsCollection = accessJson.getSubJsonObject(placesArray, pullIndex, PlaceConstants.PLACE_RATINGS);
            Map<String, Integer> pulledRatings = accessJson.pullJsonIntegerCollection(ratingsCollection);
            if (isPulledCollectionNotEmpty(pulledRatings)) {
                return pulledRatings;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: IndexOutOfBoundsException in ratings ||");
        } catch (NullPointerException e) {
            System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: NullPointerException in ratings ||");
        }
        System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: ratings collection in json is empty/broken, setting default values ||");
        PlaceAdditional placeAdditional = new PlaceAdditional();
        defaultRatings = placeAdditional.getDefaultRatings();
        return defaultRatings;
    }

    private Boolean isStringNotNull(String string) {
        return string != null;
    }

    private Boolean isPulledCollectionNotEmpty(Map pulledCollection) {
        try {
            return pulledCollection.size() > 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
