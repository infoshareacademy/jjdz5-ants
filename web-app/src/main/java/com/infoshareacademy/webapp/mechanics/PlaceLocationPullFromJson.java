package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.model.PlaceConstants;
import com.infoshareacademy.webapp.model.PlaceLocation;
import org.json.simple.JSONArray;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlaceLocationPullFromJson {

    @Inject
    AccessJson accessJson;

    private JSONArray placesArray;
    private Integer pullIndex;
    private Boolean streetPulledCorrectly;

    public void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    public PlaceLocation preparePlaceLocation() {
        PlaceLocation placeLocation = new PlaceLocation();
        return placeLocation;
    }

    private String pullStreetFromJsonArray() {
        try {
            streetPulledCorrectly = true;
            return (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_STREET);
        } catch (ClassCastException e) {
            //TODO set bugs const...
            System.out.println("Class");
        }
        return null;
    }

}
