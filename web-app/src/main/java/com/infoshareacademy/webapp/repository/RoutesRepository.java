package com.infoshareacademy.webapp.repository;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.mechanics.AccessJson;
import com.infoshareacademy.webapp.mechanics.RoutePullFromJson;
import com.infoshareacademy.webapp.model.Route;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class RoutesRepository {

    @Inject
    private AccessJson accessJson;

    @Inject
    private RoutePullFromJson routePullFromJson;

    private List<Route> routes = new ArrayList<>();

    public void fillPlacesRepository(ServletContext servletContext) {
        try {
            accessJson.setJsonArray(Configuration.ROUTES_JSON_FILEPATH, servletContext);
            addRoutesToRepository(accessJson.getJsonArray());
            System.out.println("||ROUTES REPOSITORY loaded successfully");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void addRoutesToRepository(JSONArray routesArray) {
        routePullFromJson.setRoutesArray(routesArray);
        routesArray.forEach(entry -> {
            routePullFromJson.setPullIndex(routesArray.indexOf(entry));
            Route route = routePullFromJson.prepareRoute();
            if (isIdAlreadyInRepository(route)) {
                System.out.println("||ERROR: #ID" + route.getId() +
                        " is already in repository. Route will not be loaded.||");
            } else if (isRouteNotDefault(route)) {
                routes.add(route);
            }
        });
    }

    private Boolean isIdAlreadyInRepository(Route route) {
        return routes.stream().anyMatch(entry -> entry.getId().equals(route.getId()));
    }

    private Boolean isRouteNotDefault(Route route){
        return !route.getDefaultStatus();
    }

    public List<Route> getRoutesRepository() {
        return routes;
    }

    public Integer getRouteIdByName(String name) {
        Route selectedRoute = routes.stream().filter(
                route -> name.equalsIgnoreCase(route.getName())
        ).findAny().orElse(new Route());
        return selectedRoute.getId();
    }

}
