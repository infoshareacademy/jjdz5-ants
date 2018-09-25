package com.infoshareacademy.webapp.utils;

import com.infoshareacademy.webapp.model.Route;
import com.infoshareacademy.webapp.repository.RoutesRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
public class RoutesViewerParameterDataModuleFiltering implements ParameterDataModuleFiltering {

    private static final String ROUTE_ID_BY_NAME_SELECTION_KEY = "routeIdByNameSelection";
    public static final String ROUTE_NAME_PARAMETER = "rName";
    public static final String ROUTE_MINIMAL_PLACE_QUANTITY_PARAMETER = "rMinQty";
    public static final String ROUTE_MAXIMUM_PLACE_QUANTITY_PARAMETER = "rMaxQty";
    private static final Integer DEFAULT_NULL_NUMERIC_VALUE = 0;

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
        return ParametersOperatingService.isParameterPresent(parameters, parameter);
    }

    @Override
    public String getValueFromParameter(String parameter) {
        return ParametersOperatingService.getOnlyFirstValueOfParameter(parameters.get(parameter));
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

    public void checkAndApplyFilteringByMinimalQuantityIfCalled() {
        if (isFilteredBy(ROUTE_MINIMAL_PLACE_QUANTITY_PARAMETER)) {
            Integer minimalQuantity = getMinimalPlacesQuantityFromParameter();
            if (isPlaceQuantityInRange(minimalQuantity)) {
                routes.removeIf(route -> route.getPlacesQuantity()
                        .compareTo(minimalQuantity) < 0);
            }
        }
    }

    public void checkAndApplyFilteringByMaximumQuantityIfCalled() {
        if (isFilteredBy(ROUTE_MAXIMUM_PLACE_QUANTITY_PARAMETER)) {
            Integer maximumQuantity = getMaximumPlacesQuantityFromParameter();
            if (isPlaceQuantityInRange(maximumQuantity)) {
                routes.removeIf(route -> route.getPlacesQuantity()
                        .compareTo(maximumQuantity) > 0);
            }
        }
    }

    @Override
    public <V> void putIntoDataModule(String dataKey, V dataValue) {
        dataModuleMap.put(dataKey, (List) dataValue);
    }

    @Override
    public <V> Map<String, V> getDataModuleMap() {
        return (Map<String, V>) dataModuleMap;
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

    private Integer getMinimalPlacesQuantityFromParameter() {
        Integer minimalQuantity = DEFAULT_NULL_NUMERIC_VALUE;
        try {
            minimalQuantity = Integer.valueOf(ParametersOperatingService.getOnlyFirstValueOfParameter(
                    parameters.get(ROUTE_MINIMAL_PLACE_QUANTITY_PARAMETER)));
        } catch (NumberFormatException e) {
            System.out.println("||Unable to read \"rMinQty\" GET parameter");
        }
        return minimalQuantity;
    }

    private Integer getMaximumPlacesQuantityFromParameter() {
        Integer maximumQuantity = DEFAULT_NULL_NUMERIC_VALUE;
        try {
            maximumQuantity = Integer.valueOf(ParametersOperatingService.getOnlyFirstValueOfParameter(
                    parameters.get(ROUTE_MAXIMUM_PLACE_QUANTITY_PARAMETER)));
        } catch (NumberFormatException e) {
            System.out.println("||Unable to read \"rMaxQty\" GET parameter");
        }
        return maximumQuantity;
    }

    private Boolean isPlaceQuantityInRange(Integer quantity) {
        List<Integer> range = RoutesViewerDataModuleOperatingService.getRoutePlaceQuantityRange();
        return quantity >= range.stream().mapToInt(Integer::intValue).min().getAsInt() &&
               quantity <= range.stream().mapToInt(Integer::intValue).max().getAsInt();
    }
}
