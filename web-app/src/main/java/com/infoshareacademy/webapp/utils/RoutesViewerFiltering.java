package com.infoshareacademy.webapp.utils;

import com.infoshareacademy.webapp.model.Route;
import com.infoshareacademy.webapp.repository.RoutesRepository;
import com.infoshareacademy.webapp.servlets.ServletParameters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
public class RoutesViewerFiltering implements Filtering {

    private static final String ROUTE_ID_BY_NAME_SELECTION_KEY = "routeIdByNameSelection";
    private static final String ROUTE_NAME_PARAMETER = "rName";
    private static final Integer DEFAULT_NULL_NUMERIC_VALUE = 0;
    private static final Integer MINIMAL_PLACES_QUANTITY_VALUE = 1;
    private static final Integer MAXIMUM_PLACES_QUANTITY_VALUE = 5;

    @Inject
    private RoutesRepository routesRepository;

    private Map<String, List> dataModuleMap;
    private Map<String, String[]> parameters;
    private List<Route> routes;
    private List<Integer> routeIdByNameSelection;
    private List<String> routeNameParameter;

    @Override
    public <V> void setDataModuleMap(Map<String, V> dataModuleMap) {
        this.dataModuleMap = (Map<String, List>) dataModuleMap;
    }

    @Override
    public <T> void setOperatingRepository(T repository) {
        this.routes = (List<Route>) repository;
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Boolean isFilteredBy(String parameter) {
        return ServletParameters.isParameterPresent(parameters, parameter);
    }

    @Override
    public String getValueFromParameter(String parameter) {
        return ServletParameters.getOnlyFirstValueOfParameter(parameters.get(parameter));
    }

    public void fillDataModuleWithPlaceNameFilter() {
        prepareRouteNameSelectionLists();
        if (isFilteredBy(ROUTE_NAME_PARAMETER)) {
            addSelectedPlaceToLists(getValueFromParameter(ROUTE_NAME_PARAMETER));
        } else {
            addDefaultSelectionToList();
        }
        putIntoDataModule(ROUTE_ID_BY_NAME_SELECTION_KEY, routeIdByNameSelection);
        putIntoDataModule(ROUTE_NAME_PARAMETER, routeNameParameter);
    }

    private void prepareRouteNameSelectionLists() {
        routeIdByNameSelection = new ArrayList<>();
        routeNameParameter = new ArrayList<>();
    }

    private void addSelectedPlaceToLists(String routeName) {
        routeIdByNameSelection.add(routesRepository.getRouteIdByName(routeName));
        routeNameParameter.add(routeName);
    }

    private void addDefaultSelectionToList() {
        routeIdByNameSelection.add(DEFAULT_NULL_NUMERIC_VALUE);
    }

    @Override
    public <V> void putIntoDataModule(String dataKey, V dataValue) {
        dataModuleMap.put(dataKey, (List) dataValue);
    }

    @Override
    public <V> Map<String, V> getDataModuleMap() {
        return (Map<String, V>) dataModuleMap;
    }
}
