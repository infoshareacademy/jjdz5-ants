package com.infoshareacademy.webapp.model;

import java.util.Map;
import java.util.TreeMap;

public class PlaceLocation {

    private final String street;
    private final String city;
    private final Integer buildingNumber;
    private final String buildingLetter;
    private final Integer apartmentNumber;
    private final Map<CoordinateTypes, Double> gpsCoordinates;

    private Boolean isDefault;

    private String defaultStreet = "Domyślna";
    private String defaultCity = "Domyślne";
    private Integer defaultBuildingNumber = 1;
    private String defaultBuildingLetter = "";
    private Integer defaultApartmentNumber = 0;
    private Map<CoordinateTypes, Double> defaultGpsCoordinates = new TreeMap<>();

    public PlaceLocation() {
        setDefaultGpsCoordinates();
        this.street = defaultStreet;
        this.city = defaultCity;
        this.buildingLetter = defaultBuildingLetter;
        this.buildingNumber = defaultBuildingNumber;
        this.apartmentNumber = defaultApartmentNumber;
        this.gpsCoordinates = defaultGpsCoordinates;
        this.isDefault = true;
    }

    public PlaceLocation(String street, String city, Integer buildingNumber, String buildingLetter,
                         Integer apartmentNumber, Map<CoordinateTypes, Double> gpsCoordinates) {
        this.street = street;
        this.city = city;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartmentNumber = apartmentNumber;
        this.gpsCoordinates = gpsCoordinates;
        this.isDefault = false;
    }

    private void setDefaultGpsCoordinates() {
        Double defaultLatitude = 54.351768;
        Double defaultLongitude = 18.643310;
        defaultGpsCoordinates.put(CoordinateTypes.LATITUDE, defaultLatitude);
        defaultGpsCoordinates.put(CoordinateTypes.LONGITUDE, defaultLongitude);
    }

    public Boolean getDefaultStatus() {
        return isDefault;
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
