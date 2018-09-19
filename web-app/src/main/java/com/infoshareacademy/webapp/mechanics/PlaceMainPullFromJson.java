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
    private Boolean namePulledCorrectly = false;
    private Boolean descriptionPulledCorrectly = false;

    void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    PlaceMain preparePlaceMain() {
        PlaceMain placeMain= new PlaceMain(pullNameFromJsonArray(),
                pullTypeFromJsonArray(),
                pullDescriptionFromJsonArray());
        if (isPlaceMainPulledCorrectly()) {
            return placeMain;
        }
        System.out.println(getError("PlaceMain major values are not correct. Check JSON file: \"" + Configuration.PLACES_JSON_FILEPATH + "\""));
        return new PlaceMain();
    }

    private String pullNameFromJsonArray() {
        try {
            namePulledCorrectly = true;
            return (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_NAME);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
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

    private String pullDescriptionFromJsonArray() {
        try {
            descriptionPulledCorrectly = true;
            return (String) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_DESCRIPTION);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean isPlaceMainPulledCorrectly() {
        return namePulledCorrectly && descriptionPulledCorrectly;
    }

    private String getError(String error) {
        return "||PLACE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }

}
