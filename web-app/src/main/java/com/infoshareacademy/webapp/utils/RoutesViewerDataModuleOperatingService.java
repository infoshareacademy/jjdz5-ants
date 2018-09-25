package com.infoshareacademy.webapp.utils;

import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.model.Route;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
public class RoutesViewerDataModuleOperatingService implements DataModuleOperatingService {

    private static final String ROUTES_KEY = "routes";
    private static final String ROUTES_NAMES_KEY = "routesNames";
    private static final String ROUTES_PLACE_QUANTITY_RANGE_KEY = "routesPlaceQuantityRange";

    @Inject
    private RoutesViewerParameterDataModuleFiltering filtering;

    private Map<String, List> dataModuleMap;
    private Map<String, String[]> parameters;
    private List<Route> routes;
    private List<Place> places;
    private static List<String> routesNames;
    private static List<Integer> routePlaceQuantityRange;

    public void fillDataModuleWithRequiredValues() {
        prepareRoutesNamesList();
        preparePlacesQuantityRange();
        putIntoDataModule(ROUTES_KEY, routes);
        putIntoDataModule(ROUTES_NAMES_KEY, routesNames);
        putIntoDataModule(ROUTES_PLACE_QUANTITY_RANGE_KEY, routePlaceQuantityRange);
        putIntoDataModule(PlacesViewerDataModuleOperatingService.PLACES_KEY, places);
    }

    public void filterDataModule() {
        filtering.setDataModuleMap(dataModuleMap);
        filtering.setParameters(parameters);
        filtering.setOperatingRepository(routes);
        filtering.fillDataModuleWithPlaceNameFilter();
        filtering.checkAndApplyFilteringByMinimalQuantityIfCalled();
        filtering.checkAndApplyFilteringByMaximumQuantityIfCalled();
        dataModuleMap = filtering.getDataModuleMap();
    }

    @Override
    public <V> void setDataModuleMap(Map<String, V> dataModuleMap) {
        this.dataModuleMap = (Map<String, List>) dataModuleMap;
    }

    @Override
    public <T> void setOperatingRepository(T repository) {
        this.routes = (List<Route>) repository;
    }

    public void setPlacesRepository(List<Place> repository) {
        this.places = repository;
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    @Override
    public <V> void putIntoDataModule(String dataKey, V dataValue) {
        dataModuleMap.put(dataKey, (List) dataValue);
    }

    @Override
    public <V> Map<String, V> getDataModuleMap() {
        return (Map<String, V>) dataModuleMap;
    }

    public static List<String> getRoutesNames() {
        return routesNames;
    }

    public static List<Integer> getRoutePlaceQuantityRange() {
        return routePlaceQuantityRange;
    }

    private void prepareRoutesNamesList() {
        routesNames = new ArrayList<>();
        addAllRoutesNamesToList();
    }

    private void addAllRoutesNamesToList() {
        routes.forEach(route -> routesNames.add(route.getName()));
    }

    private void preparePlacesQuantityRange() {
        routePlaceQuantityRange = new ArrayList<>();
        routePlaceQuantityRange.add(getMinimalPlaceQuantityValue());
        routePlaceQuantityRange.add(getMaximalPlaceQuantityValue());
    }

    private Integer getMinimalPlaceQuantityValue() {
        return routes.stream().mapToInt(Route::getPlacesQuantity).min().getAsInt();
    }

    private Integer getMaximalPlaceQuantityValue() {
        return routes.stream().mapToInt(Route::getPlacesQuantity).max().getAsInt();
    }

}
