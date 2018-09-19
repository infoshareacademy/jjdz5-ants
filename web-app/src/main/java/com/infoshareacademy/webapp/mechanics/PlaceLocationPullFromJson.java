package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.CoordinateTypes;
import com.infoshareacademy.webapp.model.PlaceConstants;
import com.infoshareacademy.webapp.model.PlaceLocation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class PlaceLocationPullFromJson {

    @Inject
    private AccessJson accessJson;

    private JSONArray placesArray;
    private Integer pullIndex;
    private Boolean streetPulledCorrectly = false;
    private Boolean cityPulledCorrectly = false;
    private Boolean buildingNumberPulledCorrectly = false;
    private Boolean gpsCoordinatesPulledCorrectly = false;

    public void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    public PlaceLocation preparePlaceLocation() {
        PlaceLocation placeLocation = new PlaceLocation(pullStreetFromJsonArray(),
                pullCityFromJsonArray(),
                pullBuildingNumberFromJsonArray(),
                pullBuildingLetterFromJsonArray(),
                pullApartmentNumberFromJsonArray(),
                pullGpsCoordinatesFromJsonArray());
        if (isPlaceLocationPulledCorrectly()) {
            return placeLocation;
        }
        System.out.println(getError("Place Location Major Values are not correct. Check JSON file: \"" + Configuration.PLACES_JSON_FILEPATH + "\""));
        return new PlaceLocation();
    }

    private String pullStreetFromJsonArray() {
        try {
            String street = (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_STREET);
            if (isStringMinSizeSuitable(street, PlaceConstants.MINIMAL_VALUE_OF_CHARACTERS)) {
                streetPulledCorrectly = true;
                return street;
            } else {
                System.out.println(getError("Street String is too short (min. " + PlaceConstants.MINIMAL_VALUE_OF_CHARACTERS + " characters)"));
            }
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Street is not a String"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) Street is a null"));
        }
        return null;
    }

    private String pullCityFromJsonArray() {
        try {
            String city = (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_CITY);
            if (isStringMinSizeSuitable(city, PlaceConstants.MINIMAL_VALUE_OF_CHARACTERS)) {
                cityPulledCorrectly = true;
                return city;
            } else {
                System.out.println(getError("City String is too short (min. " + PlaceConstants.MINIMAL_VALUE_OF_CHARACTERS + " characters)"));
            }
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) City is not a String"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) City is a null"));
        }
        return null;
    }

    private Integer pullBuildingNumberFromJsonArray() {
        try {
            buildingNumberPulledCorrectly = true;
            return Integer.parseInt(accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_BUILDING_NUMBER).toString());
        } catch (NumberFormatException e) {
            System.out.println(getError("(NumberFormat) Building number is not numeric"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) Building number is a null"));
        }
        return null;
    }

    private String pullBuildingLetterFromJsonArray() {
        try {
            String pulledBuildingLetter = accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_BUILDING_LETTER).toString();
            if (isStringNotNumeric(pulledBuildingLetter) && isStringMaxSizeSuitable(pulledBuildingLetter, PlaceConstants.SINGLE_LETTER_SIZE)) {
                return pulledBuildingLetter;
            } else {
                System.out.println(getError("Building number is not a single letter"));
            }
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Building number is not a String"));
        } catch (NullPointerException e) {
            System.out.println(getInfo("(NullPointer) Building number is null, setting empty value"));
        }
        return PlaceConstants.EMPTY_BUILDING_LETTER;
    }

    private Integer pullApartmentNumberFromJsonArray() {
        try {
            return Integer.valueOf(accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_APARTMENT_NUMBER).toString());
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Apartment number is not numeric"));
        } catch (NullPointerException e){
            System.out.println(getInfo("(NullPointer) Apartment number is null, setting empty value"));
        }
        return PlaceConstants.EMPTY_APARTMENT_NUMBER;
    }

    private Map<CoordinateTypes, Double> pullGpsCoordinatesFromJsonArray() {
        Map<CoordinateTypes, Double> gpsCoordinates = new TreeMap<>();
        for (CoordinateTypes coordinateType : CoordinateTypes.values()) {
            gpsCoordinates.put(coordinateType, pullSpecificGpsCoordinateType(coordinateType));
        }
        return gpsCoordinates;
    }

    private Double pullSpecificGpsCoordinateType(CoordinateTypes coordinateType) {
        JSONObject gpsCoordinatesCollection = accessJson.getSubJsonObject(placesArray, pullIndex, PlaceConstants.PLACE_GPS_COORDINATES);
        try {
            gpsCoordinatesPulledCorrectly = true;
            Map pulledGpsCoordinates = accessJson.pullJsonStringCollection(gpsCoordinatesCollection);
            Double specificGpsCoordinateType = Double.parseDouble(accessJson.findSpecificValueInCollection(pulledGpsCoordinates, coordinateType.name()).toString());
            return specificGpsCoordinateType;
        } catch (NumberFormatException e) {
            System.out.println(getError("(NumberFormat) NumberFormatException in COORDINATE_TYPE: " + coordinateType.name()));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) NullPointerException in COORDINATE_TYPE: " + coordinateType.name()));
        }
        return null;
    }

    private Boolean isStringNotNumeric(String stringToAnalyze) {
        try {
            Double.parseDouble(stringToAnalyze);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private Boolean isStringMinSizeSuitable(String stringToAnalyze, Integer minSize) {
        return stringToAnalyze.length() >= minSize;
    }

    private Boolean isStringMaxSizeSuitable(String stringToAnalyze, Integer maxSize) {
        return stringToAnalyze.length() <= maxSize;
    }

    private Boolean isPlaceLocationPulledCorrectly() {
        return streetPulledCorrectly && cityPulledCorrectly &&
               buildingNumberPulledCorrectly && gpsCoordinatesPulledCorrectly;
    }

    private String getError(String error) {
        return "||PLACE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }

    private String getInfo(String info) {
        return "||PLACE INDEX: " + pullIndex + " INFO: " + info + " ||";
    }

}
