package com.infoshareacademy.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private final Integer id;
    private final String name;
    private final List<Integer> places;
    private final Integer placesQuantity;
    private final List<Double> distances;
    private final Double overallDistance;
    private Boolean isDefault;

    private final Integer defaultId = 0;
    private final String defaultName = "Trasa turystyczna";
    private final List<Integer> defaultPlaces = new ArrayList<>();
    private final List<Double> defaultDistances = new ArrayList<>();
    private final Double defaultOverallDistance = 0.0;

    public Route() {
        this.id = defaultId;
        this.name = defaultName;
        this.places = defaultPlaces;
        this.placesQuantity = defaultPlaces.size();
        this.distances = defaultDistances;
        this.overallDistance = defaultOverallDistance;
        this.isDefault = true;
    }

    public Route(Integer id, String name, List<Integer> places, List<Double> distances) {
        this.id = id;
        this.name = name;
        this.places = places;
        this.placesQuantity = places.size();
        this.distances = distances;
        //TODO overall distance counter
        this.overallDistance = 0.0;
        this.isDefault = false;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Boolean getDefault() {
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
