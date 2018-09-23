package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.model.Route;
import com.infoshareacademy.webapp.model.RouteConstants;
import org.json.simple.JSONArray;

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

    public void setRoutesArray(JSONArray routesArray) {
        this.routesArray = routesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }

    public Route prepareRoute() {
        Route route = new Route(
                pullIdFromJsonArray(),
                pullNameFromJsonArray(),
                pullPlacesListFromJsonArray(),
                pullDistancesListFromJsonArray()
        );
        if (isAnyParameterNull(route)) {
            System.out.println(getError(
                    "Route values are not correct. Check JSON file: \"" +
                            Configuration.ROUTES_JSON_FILEPATH + "\""));
            return new Route();
        }
        return route;
    }

    private Integer pullIdFromJsonArray() {
        try {
            return Math.toIntExact((Long) accessJson.pullJsonObject(routesArray, pullIndex)
                    .get(RouteConstants.ROUTE_ID));
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Cannot resolve ID, it's probably not numeric"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) ID is a null"));
        } catch (NumberFormatException e) {
            System.out.println(getError("(NumberFormat) Cannot resolve ID, it's probably not numeric"));
        }
        return null;
    }

    private String pullNameFromJsonArray() {
        try {
            return (String) accessJson.pullJsonObject(routesArray, pullIndex)
                    .get(RouteConstants.ROUTE_NAME);
        } catch (ClassCastException e) {
            System.out.println(getError("(ClassCast) Name is not a String"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) Name is a null"));
        }
        return null;
    }

    public List<Integer> pullPlacesListFromJsonArray() {
        try {
            JSONArray jsonPlacesList = accessJson.getSubJsonArray(routesArray, pullIndex,
                    RouteConstants.ROUTE_PLACES);
            List<Integer> placesList = new ArrayList<>();
            jsonPlacesList.forEach(placeId -> placesList.add(Math.toIntExact((Long) placeId)));
            return placesList;
        } catch (ClassCastException e) {
            System.out.println(getError(
                    "(ClassCast) Cannot resolve places list, ID's are probably not numeric"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) Places list is a null"));
        } catch (NumberFormatException e) {
            System.out.println(getError(
                    "(NumberFormat) Cannot resolve places list, ID's are probably not numeric"));
        }
        return null;
    }

    public List<Double> pullDistancesListFromJsonArray() {
        try {
            JSONArray jsonDistancesList = accessJson.getSubJsonArray(routesArray, pullIndex,
                    RouteConstants.ROUTE_DISTANCES);
            List<Double> distancesList = new ArrayList<>();
            jsonDistancesList.forEach(distance -> distancesList.add(Double.valueOf(distance.toString())));
            return distancesList;
        } catch (ClassCastException e) {
            System.out.println(getError(
                    "(ClassCast) Cannot resolve distances list, entries are probably not numeric"));
        } catch (NullPointerException e) {
            System.out.println(getError("(NullPointer) Distances list is a null"));
        } catch (NumberFormatException e) {
            System.out.println(getError(
                    "(NumberFormat) Cannot resolve distances list, entries are probably not numeric"));
        }
        return null;
    }

    private Boolean isAnyParameterNull(Route route) {
        return route.getId() == null ||
               route.getName() == null ||
               route.getPlaces() == null ||
               route.getDistances() == null;
    }

    private String getError(String error) {
        return "||ROUTE INDEX: " + pullIndex + " ERROR: " + error + " ||";
    }


}
