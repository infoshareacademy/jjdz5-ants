package com.infoshareacademy.webapp.repository;

import com.infoshareacademy.webapp.mechanics.PlacePullFromJson;
import com.infoshareacademy.webapp.model.Place;
import org.json.simple.JSONArray;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class PlacesRepository {

    @Inject
    private PlacePullFromJson placePullFromJson;

    private List<Place> places = new ArrayList<>();

    public void addPlacesToRepository(JSONArray placesArray) {
        placePullFromJson.setPlacesArray(placesArray);
        for (Object entry : placesArray) {
            placePullFromJson.setPullIndex(placesArray.indexOf(entry));
            Place place = placePullFromJson.getCompletePlace();
            if (isIdAlreadyInRepository(place)) {
                System.out.println("||ERROR: #ID" + place.getId() +
                        " is already in repository. Place will not be loaded.||");
            } else {
                if (isPlaceNotDefault(place)) {
                    places.add(place);
                }
            }
        }
    }

    private Boolean isIdAlreadyInRepository(Place place) {
        return places.stream().anyMatch(entry -> entry.getId().equals(place.getId()));
    }

    private Boolean isPlaceNotDefault(Place place){
        return !place.getDefaultStatus();
    }

    public List<Place> getPlacesRepository() {
        return places;
    }

    public Integer getPlaceIdByName(String name) {
        Place selectedPlace = places.stream().filter(
                place -> name.equalsIgnoreCase(place.getPlaceMain().getName())
        ).findAny().orElse(new Place());
        return selectedPlace.getId();
    }


}
