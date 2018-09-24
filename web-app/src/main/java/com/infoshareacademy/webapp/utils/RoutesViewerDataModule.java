package com.infoshareacademy.webapp.utils;

import com.infoshareacademy.webapp.model.Route;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
public class RoutesViewerDataModule implements DataModule {

    private static final String ROUTES_KEY = "routes";
    private static final String ROUTES_NAMES_KEY = "routesNames";

    @Inject
    private RoutesViewerFiltering filtering;

    private Map<String, List> dataModuleMap;
    private Map<String, String[]> parameters;
    private List<String> routesNames;
    private List<Route> routes;

    public void fillDataModuleWithRequiredValues() {
        prepareRoutesNamesList();
        putIntoDataModule(ROUTES_KEY, routes);
        putIntoDataModule(ROUTES_NAMES_KEY, routesNames);
    }

    public void filterDataModule() {
        filtering.setDataModuleMap(dataModuleMap);
        filtering.setParameters(parameters);
        filtering.setOperatingRepository(routes);
        filtering.fillDataModuleWithPlaceNameFilter();
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

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    private void prepareRoutesNamesList() {
        routesNames = new ArrayList<>();
        addAllRoutesNamesToList();
    }

    private void addAllRoutesNamesToList() {
        routes.forEach(route -> routesNames.add(route.getName()));
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
