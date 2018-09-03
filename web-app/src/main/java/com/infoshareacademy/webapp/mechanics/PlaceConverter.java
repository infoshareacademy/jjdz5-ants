package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.PlaceOfInterest;
import com.infoshareacademy.PlaceOfInterestType;
import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.model.PlaceMain;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;

@ApplicationScoped
public class PlaceConverter {

    private JSONArray placesArray;
    private Place completePlace;
    private Integer preparationIndex;

    @Inject
    DataReader dataReader;

    public void placesJsonLoader(ServletContext servletContext) throws IOException, ParseException {
        dataReader.setJsonArray(Configuration.PLACES_JSON_FILEPATH, servletContext);
        placesArray = dataReader.getJsonArray();
    }

    private Place preparePlace(){
        try {
            return new Place();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return new Place();
    }

    private PlaceMain preparePlaceMain() {
        return new PlaceMain(prepareId(), prepareType(), prepareName(), prepareDescription());
    }

    public void setPreparationIndex(Integer index) {
        this.preparationIndex = index;
    }

    public PlaceMain getPlaceMain() {
        return preparePlaceMain();
    }



    //TODO hardcoded variables to change, here and in json file, need to clean this up...


    private Integer prepareId() {
        return Math.toIntExact((Long) dataReader.getJSONObject(placesArray, preparationIndex).get("ID"));
    }

    private Enum prepareType() {
        return checkType(dataReader.getJSONObject(placesArray, preparationIndex).get("type").toString());
    }

    private Enum checkType(String type) {
        for (PlaceOfInterestType placeOfInterestType : PlaceOfInterestType.values()){
            if (type.equalsIgnoreCase(placeOfInterestType.name())){
                return placeOfInterestType;
            }
        }
        return PlaceOfInterestType.WRONG_TYPE;
    }

    private String prepareName() {
        return (String) dataReader.getJSONObject(placesArray, preparationIndex).get("Name");
    }

    private String prepareDescription() {
        return (String) dataReader.getJSONObject(placesArray, preparationIndex).get("description");
    }

    public Place getCompletePlace(){
        return completePlace;
    }

}
