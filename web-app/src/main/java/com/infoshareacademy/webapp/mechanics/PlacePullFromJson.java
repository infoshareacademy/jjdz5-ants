package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.*;


@ApplicationScoped
public class PlacePullFromJson {

    private JSONArray placesArray;
    private Place completePlace;
    private Integer pullIndex;
    private Boolean idPulledCorrectly;
    private Boolean namePulledCorrectly;
    private Boolean descriptionPulledCorrectly;
//    private Boolean placeAdditionalPulledCorrectly = false;
//    private Boolean placeLocationPulledCorrectly = false;

    @Inject
    private AccessJson accessJson;

    public void placesJsonLoader(ServletContext servletContext) throws IOException, ParseException {
        accessJson.setJsonArray(Configuration.PLACES_JSON_FILEPATH, servletContext);
        placesArray = accessJson.getJsonArray();
    }

    public JSONArray getPlacesArray(){
        return placesArray;
    }

    public void setPullIndex(Integer index) {
        this.pullIndex = index;
    }

    public Integer getPullIndex() {
        return pullIndex;
    }

    private Place preparePlace(){
        try {
            return new Place();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new Place();
    }

    public PlaceMain getPlaceMain() {
        return new PlaceMainPullFromJson(placesArray, pullIndex).preparePlaceMain();
    }

    public Place getCompletePlace(){
        return completePlace;
    }


    private PlaceAdditional preparePlaceAdditional() {
        PlaceAdditional placeAdditional = new PlaceAdditional(pullOpeningHoursFromJsonArray(), pullPricesFromJsonArray(), pullRatingsFromJsonArray());
        return  placeAdditional;
    }

    public PlaceAdditional getPlaceAdditional() {
        return preparePlaceAdditional();
    }


//PlaceAdditional values.

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
            return specificDayHours;
        } catch (Exception e) {
            e.printStackTrace();
            return PlaceConstants.NO_DATA;
            }
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
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException in PRICE_TYPE: " + priceType.name() +" - INDEX: " + pullIndex);
            return noData;
        }
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
            System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: IndexOutOfBoundsException");
        }
        System.out.println("||PLACE INDEX: " + pullIndex + " ERROR: ratings collection in json is empty, setting default values");
        PlaceAdditional placeAdditional = new PlaceAdditional();
        defaultRatings = placeAdditional.getDefaultRatings();
        return defaultRatings;
    }

    private Boolean isPulledCollectionNotEmpty(Map pulledCollection) {
        try {
            return pulledCollection.size() > 0;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
