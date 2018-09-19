package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.model.*;
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

    void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    PlaceAdditional preparePlaceAdditional() {
        return new PlaceAdditional(
                pullOpeningHoursFromJsonArray(),
                pullPricesFromJsonArray(),
                pullRatingsFromJsonArray()
        );
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
            System.out.println(getError("New error, identify and add annotation (PullAdditionalPullFromJson : 53 line)"));
        }
        System.out.println(getError("Cannot identify WEEKDAY: \"" + weekDay.name() + "\", possible broken entry"));
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
            System.out.println(getError("(NumberFormat) in PRICE TYPE: \"" + priceType.name() + "\""));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) in PRICE TYPE: \"" + priceType.name() + "\""));
        }
        return noData;
    }

    private Map<String, Integer> pullRatingsFromJsonArray() {
        try {
            JSONObject ratingsCollection = accessJson.getSubJsonObject(placesArray, pullIndex, PlaceConstants.PLACE_RATINGS);
            Map<String, Integer> pulledRatings = accessJson.pullJsonIntegerCollection(ratingsCollection);
            if (isPulledCollectionNotEmpty(pulledRatings)) {
                return pulledRatings;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(getError("(IndexOutOfBounds) Ratings collection is probably empty"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) Ratings collection is probably empty"));
        }
        System.out.println(getInfo("Ratings collection is probably empty/broken, setting default values"));
        Map<String, Integer> defaultRatings;
        defaultRatings = PlaceAdditional.getDefaultRatings();
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

    private String getError(String error) {
        return "||PLACE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }

    private String getInfo(String info) {
        return "||PLACE INDEX: " + pullIndex + " INFO: " + info + " ||";
    }

}