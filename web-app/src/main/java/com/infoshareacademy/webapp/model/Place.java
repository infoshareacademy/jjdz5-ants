package com.infoshareacademy.webapp.model;

public class Place {

    private final PlaceMain placeMain;
    private final PlaceAdditional placeAdditional;
    private final PlaceLocation placeLocation;

    public Place() {
        this.placeMain = new PlaceMain();
        this.placeAdditional = new PlaceAdditional();
        this.placeLocation = new PlaceLocation();
    }

    public Place(PlaceMain placeMain, PlaceAdditional placeAdditional, PlaceLocation location) {
        this.placeMain = placeMain;
        this.placeAdditional = placeAdditional;
        this.placeLocation = location;
    }

    @Override
    public String toString() {
        return "Place{" +
                "\nplaceMain=" + placeMain +
                ",\nplaceAdditional=" + placeAdditional +
                ",\nplaceLocation=" + placeLocation +
                '}';
    }
}
