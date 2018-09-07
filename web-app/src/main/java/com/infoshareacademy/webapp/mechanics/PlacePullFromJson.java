package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.model.*;
import org.json.simple.JSONArray;

import javax.inject.Inject;

public class PlacePullFromJson {

    private JSONArray placesArray;
    private Integer pullIndex;
    private Boolean isIdPulledIncorrectly;

    @Inject
    AccessJson accessJson;

    @Inject
    private PlaceMainPullFromJson placeMainPullFromJson;

    @Inject
    private PlaceAdditionalPullFromJson placeAdditionalPullFromJson;

    @Inject
    private PlaceLocationPullFromJson placeLocationPullFromJson;

    public void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    public JSONArray getPlacesArray() {
        return placesArray;
    }

    public void setPullIndex(Integer index) {
        this.pullIndex = index;
    }

    public Integer getPullIndex() {
        return pullIndex;
    }

    private Place preparePlace(){
        setPlaceMainPullFromJsonValues();
        setPlaceAdditionalPullFromJsonValues();
        setPlaceLocationPullFromJsonValues();

        PlaceMain placeMain = placeMainPullFromJson.preparePlaceMain();
        PlaceAdditional placeAdditional = placeAdditionalPullFromJson.preparePlaceAdditional();
        PlaceLocation placeLocation = placeLocationPullFromJson.preparePlaceLocation();
        Place completePlace = new Place(pullIdFromJsonArray(), placeMain, placeAdditional, placeLocation);

        if (isIdPulledIncorrectly || arePlacesDefault(placeMain, placeAdditional, placeLocation)) {
            completePlace = new Place();
            return completePlace;
        }
        System.out.println(getInfo("Place successfully pulled!"));
        return completePlace;
    }

    private void setPlaceMainPullFromJsonValues() {
        placeMainPullFromJson.setPlacesArray(placesArray);
        placeMainPullFromJson.setPullIndex(pullIndex);
    }
    private void setPlaceAdditionalPullFromJsonValues() {
        placeAdditionalPullFromJson.setPlacesArray(placesArray);
        placeAdditionalPullFromJson.setPullIndex(pullIndex);
    }
    private void setPlaceLocationPullFromJsonValues() {
        placeLocationPullFromJson.setPlacesArray(placesArray);
        placeLocationPullFromJson.setPullIndex(pullIndex);
    }

    public Place getCompletePlace(){
        return preparePlace();
    }

    private Integer pullIdFromJsonArray() {
        try {
            isIdPulledIncorrectly = false;
            return Math.toIntExact((Long) accessJson.pullJsonObject(placesArray, pullIndex).get(PlaceConstants.PLACE_ID));
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Cannot resolve ID, it's probably not numeric"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) ID is a null"));
        }
        isIdPulledIncorrectly = true;
        return null;
    }

    private Boolean arePlacesDefault(PlaceMain placeMain, PlaceAdditional placeAdditional, PlaceLocation placeLocation) {
        return placeMain.getDefaultStatus() || placeAdditional.getDefaultStatus() || placeLocation.getDefaultStatus();
    }

    private String getError(String error) {
        return "||PLACE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }

    private String getInfo(String info) {
        return "||PLACE INDEX: " + pullIndex + " INFO: " + info + " ||";
    }

}
