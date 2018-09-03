package com.infoshareacademy.webapp.repository;

import com.infoshareacademy.webapp.model.Place;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlacesRepository {

    private final List<Place> places;

    public PlacesRepository() {
        final List<Place> places = new ArrayList<>();
        places.add(new Place());
        this.places = places;
    }

    public List<Place> getPlaces() {
        return places;
    }

}
