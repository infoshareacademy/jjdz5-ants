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
        for (Object entry : placesArray) {
            placePullFromJson.setPullIndex(placesArray.indexOf(entry));
            Place place = placePullFromJson.getCompletePlace();
            if (isPlaceNotDefault(place)){
                places.add(place);
            }
        }
    }

    private Boolean isPlaceNotDefault(Place place){
        return !place.getDefaultStatus();
    }

    public List<Place> getPlacesRepository() {
        return places;
    }


}
