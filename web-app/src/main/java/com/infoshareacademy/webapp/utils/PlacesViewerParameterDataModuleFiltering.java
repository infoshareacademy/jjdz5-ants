package com.infoshareacademy.webapp.utils;

import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.repository.PlacesRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
public class PlacesViewerParameterDataModuleFiltering implements ParameterDataModuleFiltering {

    private static final String PLACE_ID_BY_NAME_SELECTION_KEY = "placeIdByNameSelection";
    private static final String PLACE_NAME_PARAMETER = "pName";
    private static final String PLACE_TYPE_PARAMETER = "pType";
    private static final String PLACE_MINIMAL_RATE_PARAMETER = "pMinRate";
    private static final Integer DEFAULT_NULL_NUMERIC_VALUE = 0;
    private static final Integer MINIMAL_RATING_VALUE = 1;
    private static final Integer MAXIMUM_RATING_VALUE = 5;

    @Inject
    private PlacesRepository placesRepository;

    private Map<String, List> dataModuleMap;
    private Map<String, String[]> parameters;
    private List<Place> places;
    private List<Integer> placeIdByNameSelection;
    private List<String> placeNameParameter;

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
    public Boolean isFilteredBy(String parameter) {
        return ParametersOperatingService.isParameterPresent(parameters, parameter);
    }

    @Override
    public String getValueFromParameter(String parameter) {
        return ParametersOperatingService.getOnlyFirstValueOfParameter(parameters.get(parameter));
    }

    public void fillDataModuleWithPlaceNameFilter() {
        preparePlaceNameSelectionLists();
        if (isFilteredBy(PLACE_NAME_PARAMETER)) {
            addSelectedPlaceToLists(getPlaceNameFromParameter());
        } else {
            addDefaultSelectionToList();
        }
        putIntoDataModule(PLACE_ID_BY_NAME_SELECTION_KEY, placeIdByNameSelection);
        putIntoDataModule(PLACE_NAME_PARAMETER, placeNameParameter);
    }

    public void checkAndApplyFilteringByTypeIfCalled() {
        if (isFilteredBy(PLACE_TYPE_PARAMETER)) {
            String type = getValueFromParameter(PLACE_TYPE_PARAMETER);
            if (isTypeCorrect(type)) {
                places.removeIf(place -> !type.equalsIgnoreCase(place.getPlaceMain().getType()));
                putPlaceTypeParameterToDataModule(type);
            }
        }
    }

    public void checkAndApplyFilteringByMinimalRatingIfCalled() {
        if (isFilteredBy(PLACE_MINIMAL_RATE_PARAMETER)) {
            Integer rate = getMinimalRatingFromParameter();
            if (isMinimalRatingInRange(rate)) {
                BigDecimal rateValue = new BigDecimal(rate);
                places.removeIf(place -> place.getPlaceAdditional().getAverageRating()
                        .compareTo(rateValue) < 0);
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

    private void preparePlaceNameSelectionLists() {
        placeIdByNameSelection = new ArrayList<>();
        placeNameParameter = new ArrayList<>();
    }

    private String getPlaceNameFromParameter() {
        return ParametersOperatingService.getOnlyFirstValueOfParameter(
                parameters.get(PLACE_NAME_PARAMETER));
    }

    private void addSelectedPlaceToLists(String placeName) {
        placeIdByNameSelection.add(placesRepository.getPlaceIdByName(placeName));
        placeNameParameter.add(placeName);
    }

    private void addDefaultSelectionToList() {
        placeIdByNameSelection.add(DEFAULT_NULL_NUMERIC_VALUE);
    }

    private Boolean isTypeCorrect(String type) {
        return PlacesViewerDataModuleOperatingService.getPlacesTypes().stream().anyMatch(
                placeType -> type.equalsIgnoreCase(placeType.toString()));
    }

    private void putPlaceTypeParameterToDataModule(String type) {
        List<String> placeTypeParameter = new ArrayList<>();
        placeTypeParameter.add(type);
        putIntoDataModule(PLACE_TYPE_PARAMETER, placeTypeParameter);
    }

    private Integer getMinimalRatingFromParameter() {
        Integer rate = DEFAULT_NULL_NUMERIC_VALUE;
        try {
            rate = Integer.valueOf(ParametersOperatingService.getOnlyFirstValueOfParameter(
                    parameters.get(PLACE_MINIMAL_RATE_PARAMETER)));
        } catch (NumberFormatException e) {
            System.out.println("||Unable to read \"pMinValue\" GET parameter");
        }
        return rate;
    }

    private Boolean isMinimalRatingInRange(Integer rate) {
        return rate >= MINIMAL_RATING_VALUE && rate <= MAXIMUM_RATING_VALUE;
    }
}
