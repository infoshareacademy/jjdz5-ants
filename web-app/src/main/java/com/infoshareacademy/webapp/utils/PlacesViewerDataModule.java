package com.infoshareacademy.webapp.utils;

import com.infoshareacademy.PlaceOfInterestType;
import com.infoshareacademy.webapp.model.Place;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

@RequestScoped
public class PlacesViewerDataModule implements DataModule {

    private static final String PLACES_KEY = "places";
    private static final String PLACES_NAMES_KEY = "placesNames";
    private static final String PLACES_TYPES_KEY = "placesTypes";

    @Inject
    private PlacesViewerFiltering filtering;

    private Map<String, List> dataModuleMap;
    private Map<String, String[]> parameters;
    private List<String> placesNames;
    private static List<PlaceOfInterestType> placesTypes;
    private List<Place> places;

    public void fillDataModuleWithRequiredValues() {
        preparePlacesNamesList();
        preparePlacesTypesList();
        putIntoDataModule(PLACES_KEY, places);
        putIntoDataModule(PLACES_NAMES_KEY, placesNames);
        putIntoDataModule(PLACES_TYPES_KEY, placesTypes);
    }

    public void filterDataModule() {
        filtering.setDataModuleMap(dataModuleMap);
        filtering.setParameters(parameters);
        filtering.setOperatingRepository(places);
        filtering.fillDataModuleWithPlaceNameFilter();
        filtering.checkAndApplyFilteringByTypeIfCalled();
        filtering.checkAndApplyFilteringByMinimalRatingIfCalled();
        dataModuleMap = filtering.getDataModuleMap();
    }

    @Override
    public <V> void setDataModuleMap(Map<String, V> dataModuleMap) {
        this.dataModuleMap = (Map<String, List>) dataModuleMap;
    }

    @Override
    public <T> void setOperatingRepository(T repository) {
        this.places = (List<Place>) repository;
    }

    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    @Override
    public <T> void putIntoDataModule(String dataKey, T dataValue) {
        dataModuleMap.put(dataKey, (List) dataValue);
    }

    private void preparePlacesNamesList() {
        placesNames = new ArrayList<>();
        addAllPlacesNamesToList();
    }

    private void addAllPlacesNamesToList() {
        places.forEach(place -> placesNames.add(place.getPlaceMain().getName()));
    }

    private void preparePlacesTypesList() {
        placesTypes = new ArrayList<>(getAllPlaceTypes());
    }

    private EnumSet<PlaceOfInterestType> getAllPlaceTypes() {
        return EnumSet.allOf(PlaceOfInterestType.class);
    }

    @Override
    public <V> Map<String, V> getDataModuleMap() {
        return (Map<String, V>) dataModuleMap;
    }

    public static List<PlaceOfInterestType> getPlacesTypes() {
        return placesTypes;
    }

}
