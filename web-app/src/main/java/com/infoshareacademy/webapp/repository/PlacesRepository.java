package com.infoshareacademy.webapp.repository;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.mechanics.AccessJson;
import com.infoshareacademy.webapp.mechanics.PlacePullFromJson;
import com.infoshareacademy.webapp.model.Place;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class PlacesRepository {

    @Inject
    private AccessJson accessJson;

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

    public void fillPlacesRepository(ServletContext servletContext) {
        try {
            accessJson.setJsonArray(Configuration.PLACES_JSON_FILEPATH, servletContext);
            addPlacesToRepository(accessJson.getJsonArray());
            System.out.println("||PLACES REPOSITORY loaded successfully");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
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
