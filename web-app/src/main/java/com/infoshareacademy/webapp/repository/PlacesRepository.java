package com.infoshareacademy.webapp.repository;

import com.infoshareacademy.webapp.mechanics.PlacePullFromJson;
import com.infoshareacademy.webapp.model.Place;
import org.json.simple.JSONArray;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlacesRepository {

    @Inject
    private PlacePullFromJson placePullFromJson;

    private List<Place> places = new ArrayList<>();

    public void addPlacesToRepository(JSONArray placesArray) {
        placePullFromJson.setPlacesArray(placesArray);
        for (Object place : placesArray) {
            placePullFromJson.setPullIndex(placesArray.indexOf(place));
            places.add(placePullFromJson.getCompletePlace());
        }
    }

    public List<Place> getPlacesRepository() {
        return places;
    }


}
