package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.PlaceOfInterestType;
import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.PlaceConstants;
import com.infoshareacademy.webapp.model.PlaceMain;
import org.json.simple.JSONArray;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PlaceMainPullFromJson {

    @Inject
    private AccessJson accessJson;

    private JSONArray placesArray;
    private Integer pullIndex;

    public void setPlacesArray(JSONArray placesArray) {
        this.placesArray = placesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    PlaceMain preparePlaceMain() {
        PlaceMain placeMain= new PlaceMain(pullNameFromJsonArray(),
                pullTypeFromJsonArray(),
                pullDescriptionFromJsonArray());
        if (isAnyMajorParameterNull(placeMain)) {
            System.out.println(getError(
                    "PlaceMain major values are not correct. Check JSON file: \"" +
                    Configuration.PLACES_JSON_FILEPATH + "\"")
            );
            return new PlaceMain();
        }
        return placeMain;
    }

    private String pullNameFromJsonArray() {
        try {
            String name = (String) accessJson.pullJsonObject(placesArray, pullIndex)
                    .get(PlaceConstants.PLACE_NAME);
            if (isStringMinSizeSuitable(name, PlaceConstants.MINIMAL_VALUE_OF_CHARACTERS)){
                return name;
            }
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PlaceOfInterestType pullTypeFromJsonArray() {
        return checkType(accessJson.pullJsonObject(placesArray, pullIndex)
                .get(PlaceConstants.PLACE_TYPE).toString());
    }

    private PlaceOfInterestType checkType(String type) {
        for (PlaceOfInterestType placeOfInterestType : PlaceOfInterestType.values()){
            if (type.equalsIgnoreCase(placeOfInterestType.name())){
                return placeOfInterestType;
            }
        }
        return PlaceOfInterestType.WRONG_TYPE;
    }

    private String pullDescriptionFromJsonArray() {
        try {
            String description =  (String) accessJson.pullJsonObject(placesArray, pullIndex)
                    .get(PlaceConstants.PLACE_DESCRIPTION);
            if (isStringMinSizeSuitable(description, PlaceConstants.MINIMAL_VALUE_OF_CHARACTERS)) {
                return description;
            }
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
        return pullNameFromJsonArray();
    }

    private Boolean isStringMinSizeSuitable(String stringToAnalyze, Integer minSize) {
        return stringToAnalyze.length() >= minSize;
    }

    private Boolean isAnyMajorParameterNull(PlaceMain placeMain) {
        return placeMain.getName() == null ||
               placeMain.getDescription() == null;
    }

    private String getError(String error) {
        return "||PLACE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }

}
