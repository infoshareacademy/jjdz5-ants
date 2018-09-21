package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.model.RouteConstants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoutePullFromJson {

    @Inject
    private AccessJson accessJson;

    private JSONArray routesArray;
    private Integer pullIndex;
    private Boolean isIdPulledIncorrectly = false;
    private Boolean namePulledCorrectly = false;
    private Boolean placesPulledCorrectly = false;

    public void setRoutesArray(JSONArray routesArray) {
        this.routesArray = routesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    private Integer pullIdFromJsonArray() {
        try {
            isIdPulledIncorrectly = false;
            return Math.toIntExact((Long) accessJson.pullJsonObject(routesArray, pullIndex).get(RouteConstants.ROUTE_ID));
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Cannot resolve ID, it's probably not numeric"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) ID is a null"));
        }
        return null;
    }

    private String pullNameFromJsonArray() {
        try {
            namePulledCorrectly = true;
            return (String) accessJson.pullJsonObject(routesArray, pullIndex).get(RouteConstants.ROUTE_NAME);
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> pullPlacesListFromJsonArray() {
        try {
            placesPulledCorrectly = true;
            List<Integer> placesList = new ArrayList<>();
            JSONObject jsonPlacesList = (JSONObject) accessJson.pullJsonObject(routesArray, pullIndex)
                    .get(RouteConstants.ROUTE_PLACES);
            jsonPlacesList.entrySet().forEach(placeId -> placesList.add((Integer) placeId));
            return placesList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getError(String error) {
        return "||ROUTE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }


}
