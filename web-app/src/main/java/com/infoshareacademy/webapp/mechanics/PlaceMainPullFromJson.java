package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.PlaceOfInterestType;
import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.PlaceConstants;
import com.infoshareacademy.webapp.model.PlaceMain;
import org.json.simple.JSONArray;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlaceMainPullFromJson{

    @Inject
    private AccessJson accessJson;

    private JSONArray placesArray;
    private Integer pullIndex;
    private Boolean idPulledCorrectly;
    private Boolean namePulledCorrectly;
    private Boolean descriptionPulledCorrectly;

    public void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    public PlaceMain preparePlaceMain() {
        PlaceMain placeMain = new PlaceMain(pullIdFromJsonArray(), pullTypeFromJsonArray(), pullNameFromJsonArray(), pullDescriptionFromJsonArray());
        if (isPlaceMainPulledCorrectly()) {
            return placeMain;
        }
        System.out.println("ERROR - ID #" + pullIndex + " place is not correct. Check JSON file: \"" + Configuration.PLACES_JSON_FILEPATH + "\" ;");
        return new PlaceMain();
    }

    private Integer pullIdFromJsonArray() {
        try {
            idPulledCorrectly = true;
            return Math.toIntExact((Long) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_ID));
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            idPulledCorrectly = false;
        }
        return null;
    }

    private Enum pullTypeFromJsonArray() {
        return checkType(accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_TYPE).toString());
    }

    private Enum checkType(String type) {
        for (PlaceOfInterestType placeOfInterestType : PlaceOfInterestType.values()){
            if (type.equalsIgnoreCase(placeOfInterestType.name())){
                return placeOfInterestType;
            }
        }
        return PlaceOfInterestType.WRONG_TYPE;
    }

    private String pullNameFromJsonArray() {
        try {
            namePulledCorrectly = true;
            return (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_NAME);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            namePulledCorrectly = false;

        }
        return null;
    }

    private String pullDescriptionFromJsonArray() {
        try {
            descriptionPulledCorrectly = true;
            return (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_DESCRIPTION);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
            descriptionPulledCorrectly = false;

        }
        return null;
    }

    private Boolean isPlaceMainPulledCorrectly() {
        Boolean placeMainPulledCorrectly = false;
        if (idPulledCorrectly && namePulledCorrectly && descriptionPulledCorrectly) {
            placeMainPulledCorrectly = true;
        }
        return placeMainPulledCorrectly;
    }

}
