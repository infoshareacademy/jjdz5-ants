package com.infoshareacademy.webapp.model;

public class Place {

    private final Integer id;
    private final PlaceMain placeMain;
    private final PlaceAdditional placeAdditional;
    private final PlaceLocation placeLocation;

    private Boolean isDefault;

    private final Integer defaultId = 0;

    public Place() {
        this.id = defaultId;
        this.placeMain = new PlaceMain();
        this.placeAdditional = new PlaceAdditional();
        this.placeLocation = new PlaceLocation();
        this.isDefault = true;
    }

    public Place(Integer id, PlaceMain placeMain, PlaceAdditional placeAdditional, PlaceLocation location) {
        this.id = id;
        this.placeMain = placeMain;
        this.placeAdditional = placeAdditional;
        this.placeLocation = location;
        this.isDefault = false;
    }

    public Integer getId() {
        return id;
    }

    public PlaceMain getPlaceMain() {
        return placeMain;
    }

    public PlaceAdditional getPlaceAdditional() {
        return placeAdditional;
    }

    public PlaceLocation getPlaceLocation() {
        return placeLocation;
    }

    public Boolean getDefaultStatus() {
        return isDefault;
    }

    @Override
    public String toString() {
        return "Place{" +
                "\nid=" + id +
                "\nplaceMain=" + placeMain +
                ",\nplaceAdditional=" + placeAdditional +
                ",\nplaceLocation=" + placeLocation +
                '}';
    }
}
