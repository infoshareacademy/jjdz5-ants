package com.infoshareacademy.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private final Integer id;
    private final String name;
    private final String description;
    private final List<Integer> places;
    private final Integer placesQuantity;
    private final List<Double> distances;
    private final Double overallDistance;
    private Boolean isDefault;

    private final Integer defaultId = 0;
    private final String defaultName = "Trasa turystyczna";
    private final String defaultDescription = "Opis trasy turystycznej";
    private final List<Integer> defaultPlaces = new ArrayList<>();
    private final List<Double> defaultDistances = new ArrayList<>();
    private final Double defaultOverallDistance = 0.0;

    public Route() {
        this.id = defaultId;
        this.name = defaultName;
        this.description = defaultDescription;
        this.places = defaultPlaces;
        this.placesQuantity = defaultPlaces.size();
        this.distances = defaultDistances;
        this.overallDistance = defaultOverallDistance;
        this.isDefault = true;
    }

    public Route(Integer id, String name, String description, List<Integer> places,
                 List<Double> distances) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.places = places;
        this.placesQuantity = placesQuantityCounter(places);
        this.distances = distances;
        this.overallDistance = overallDistanceCounter(distances);
        this.isDefault = false;
    }

    private Integer placesQuantityCounter(List<Integer> places) {
        try {
            return places.size();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Double overallDistanceCounter(List<Double> distances) {
        try {
            return distances.stream()
                    .mapToDouble(distance -> distance.doubleValue())
                    .sum();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getPlaces() {
        return places;
    }

    public Integer getPlacesQuantity() {
        return placesQuantity;
    }

    public List<Double> getDistances() {
        return distances;
    }

    public Double getOverallDistance() {
        return overallDistance;
    }

    public Boolean getDefaultStatus() {
        return isDefault;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", places=" + places +
                ", placesQuantity=" + placesQuantity +
                ", distances=" + distances +
                ", overallDistance=" + overallDistance +
                ", isDefault=" + isDefault +
                '}';
    }
}
