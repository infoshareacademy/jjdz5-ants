package com.infoshareacademy.webapp.model;

import java.util.HashMap;
import java.util.Map;

public class PlaceLocation {

    private final String street;
    private final String city;
    private final String buildingLetter;
    private final Integer buildingNumber;
    private final Integer apartmentNumber;
    private final Map<CoordinateType, Double> gpsCoordinates;

    private String defaultStreet = "Domyślna";
    private String defaultCity = "Domyślne";
    private String defaultBuildingLetter = "";
    private Integer defaultBuildingNumber = 1;
    private Integer defaultApartmentNumber = 0;
    private Map<CoordinateType, Double> defaultGpsCoordinates = new HashMap<>();

    public PlaceLocation() {
        setDefaultGpsCoordinates();
        this.street = defaultStreet;
        this.city = defaultCity;
        this.buildingLetter = defaultBuildingLetter;
        this.buildingNumber = defaultBuildingNumber;
        this.apartmentNumber = defaultApartmentNumber;
        this.gpsCoordinates = defaultGpsCoordinates;
    }

    public PlaceLocation(String street, String city, String buildingLetter, Integer buildingNumber,
                         Integer apartmentNumber, Map<CoordinateType, Double> gpsCoordinates) {
        this.street = street;
        this.city = city;
        this.buildingLetter = buildingLetter;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.gpsCoordinates = gpsCoordinates;
    }

    private void setDefaultGpsCoordinates() {
        Double defaultLatitude = 54.351768;
        Double defaultLongitude = 18.643310;
        defaultGpsCoordinates.put(CoordinateType.LATITUDE, defaultLatitude);
        defaultGpsCoordinates.put(CoordinateType.LONGITUDE, defaultLongitude);
    }

    @Override
    public String toString() {
        return "PlaceLocation{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", buildingLetter='" + buildingLetter + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", apartmentNumber=" + apartmentNumber +
                ", gpsCoordinates=" + gpsCoordinates +
                '}';
    }
}
