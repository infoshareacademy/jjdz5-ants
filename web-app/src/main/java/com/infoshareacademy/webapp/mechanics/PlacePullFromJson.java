package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;

public class PlacePullFromJson {

    private JSONArray placesArray;
    private Integer pullIndex;

//    @Inject
//    private AccessJson accessJson;

    @Inject
    private PlaceMainPullFromJson placeMainPullFromJson;

    @Inject
    private PlaceAdditionalPullFromJson placeAdditionalPullFromJson;

//    public void placesJsonLoader(ServletContext servletContext) throws IOException, ParseException {
//        accessJson.setJsonArray(Configuration.PLACES_JSON_FILEPATH, servletContext);
//        placesArray = accessJson.getJsonArray();
//    }

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
        placeMainPullFromJson.setPlacesArray(placesArray);
        placeMainPullFromJson.setPullIndex(pullIndex);
        placeAdditionalPullFromJson.setPlacesArray(placesArray);
        placeAdditionalPullFromJson.setPullIndex(pullIndex);
        Place completePlace = new Place(placeMainPullFromJson.preparePlaceMain(),
                placeAdditionalPullFromJson.preparePlaceAdditional(),
                new PlaceLocation());
        return completePlace;
    }

    public Place getCompletePlace(){
        return preparePlace();
    }

}
