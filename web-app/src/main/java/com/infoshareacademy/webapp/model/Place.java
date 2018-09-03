package com.infoshareacademy.webapp.model;

public class Place {

    private final PlaceMain placeMain;
    private final PlaceAdditional placeAdditional;
    private final PlaceLocation location;

    public Place() {
        this.placeMain = new PlaceMain();
        this.placeAdditional = new PlaceAdditional();
        this.location = new PlaceLocation();
    }

    public Place(PlaceMain placeMain, PlaceAdditional placeAdditional, PlaceLocation location) {
        this.placeMain = placeMain;
        this.placeAdditional = placeAdditional;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Place{" +
                "\nplaceMain=" + placeMain +
                ",\nplaceAdditional=" + placeAdditional +
                ",\nlocation=" + location +
                '}';
    }
}
