package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.PlaceOfInterestType;
import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.mechanics.AccessJson;
import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import org.json.simple.parser.ParseException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet("/places-viewer")
public class PlacesViewerServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "places-viewer";
    private static final String PLACES_KEY = "places";
    private static final String PLACES_NAMES_KEY = "placesNames";
    private static final String PLACES_TYPES_KEY = "placesTypes";
    private static final String PLACE_ID_BY_NAME_SELECTION_KEY = "placeIdByNameSelection";
    private static final String PLACE_NAME_PARAMETER = "pName";
    private static final String PLACE_TYPE_PARAMETER = "pType";
    private static final String PLACE_MINIMAL_RATE_PARAMETER = "pMinRate";
    private static final Integer DEFAULT_NULL_NUMERIC_VALUE = 0;
    private static final Integer MINIMAL_RATING_VALUE = 1;
    private static final Integer MAXIMUM_RATING_VALUE = 5;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AccessJson accessJson;

    @Inject
    private PlacesRepository placesRepository;

    private Map<String, List> dataModule;
    private Map<String, String[]> parameters;
    private List<String> placesNames;
    private List<PlaceOfInterestType> placesTypes;
    private List<Place> places;
    private List<Integer> placeIdByNameSelection;
    private List<String> placeNameParameter;

    @Override
    public void init() throws ServletException {
        dataModule = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);
        parameters = req.getParameterMap();

        fillPlacesRepository();
        places = placesRepository.getPlacesRepository();

        putPlacesNamesToDataModule();
        putPlacesTypesToDataModule();
        fillDataModuleWithPlaceNameFilter();
        checkFilteringByType();
        checkFilteringByMinimalRating();
        putPlacesRepositoryToDataModule();

        templateProvider.print(getServletContext(),TEMPLATE_NAME, dataModule, resp);
    }

    private void putPlacesRepositoryToDataModule() {
        dataModule.put(PLACES_KEY, places);
    }

    private void fillPlacesRepository() {
        try {
            accessJson.setJsonArray(Configuration.PLACES_JSON_FILEPATH, getServletContext());
            placesRepository.addPlacesToRepository(accessJson.getJsonArray());
            System.out.println("||PLACES REPOSITORY loaded successfully");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void putPlacesNamesToDataModule() {
        preparePlacesNamesList();
        dataModule.put(PLACES_NAMES_KEY, placesNames);
    }

    private void preparePlacesNamesList() {
        placesNames = new ArrayList<>();
        addAllPlacesNamesToList();
    }

    private void addAllPlacesNamesToList() {
        places.forEach(place -> placesNames.add(place.getPlaceMain().getName()));
    }

    private void putPlacesTypesToDataModule() {
        preparePlacesTypesList();
        dataModule.put(PLACES_TYPES_KEY, placesTypes);
    }

    private void preparePlacesTypesList() {
        placesTypes = new ArrayList<>(getAllPlaceTypes());
    }

    private EnumSet<PlaceOfInterestType> getAllPlaceTypes() {
        return EnumSet.allOf(PlaceOfInterestType.class);
    }

    private void fillDataModuleWithPlaceNameFilter() {
        preparePlaceNameSelectionLists();
        if (ServletParameters.isParameterPresent(parameters, PLACE_NAME_PARAMETER)) {
            addSelectedPlaceToLists(getPlaceNameFromParameter());
        } else {
            addDefaultSelectionToList();
        }
        putPlaceNameFilterToDataModule();
    }

    private void preparePlaceNameSelectionLists() {
        placeIdByNameSelection = new ArrayList<>();
        placeNameParameter = new ArrayList<>();
    }

    private void addSelectedPlaceToLists(String placeName) {
        placeIdByNameSelection.add(placesRepository.getPlaceIdByName(placeName));
        placeNameParameter.add(placeName);
    }

    private String getPlaceNameFromParameter() {
        return ServletParameters.getOnlyFirstValueOfParameter(parameters.get(PLACE_NAME_PARAMETER));
    }

    private void addDefaultSelectionToList() {
        placeIdByNameSelection.add(DEFAULT_NULL_NUMERIC_VALUE);
    }

    private void putPlaceNameFilterToDataModule() {
        dataModule.put(PLACE_ID_BY_NAME_SELECTION_KEY, placeIdByNameSelection);
        dataModule.put(PLACE_NAME_PARAMETER, placeNameParameter);
    }

    private void checkFilteringByType() {
        if (isFilteredByType()) {
            String type = getPlaceTypeFromParameter();
            if (isTypeCorrect(type)) {
                places.removeIf(place -> !type.equalsIgnoreCase(place.getPlaceMain().getType()));
                putPlaceTypeParameterToDataModule(type);
            }
        }
    }

    private Boolean isFilteredByType() {
        return ServletParameters.isParameterPresent(parameters, PLACE_TYPE_PARAMETER);
    }

    private String getPlaceTypeFromParameter() {
        return ServletParameters.getOnlyFirstValueOfParameter(parameters.get(PLACE_TYPE_PARAMETER));
    }

    private Boolean isTypeCorrect(String type) {
        return placesTypes.stream().anyMatch(placeType -> type.equalsIgnoreCase(placeType.toString()));
    }

    private void putPlaceTypeParameterToDataModule(String type) {
        List<String> placeTypeParameter = new ArrayList<>();
        placeTypeParameter.add(type);
        dataModule.put(PLACE_TYPE_PARAMETER, placeTypeParameter);
    }

    private void checkFilteringByMinimalRating() {
        if (isFilteredByMinimalRating()) {
            Integer rate = getMinimalRatingFromParameter();
            if (isMinimalRatingInRange(rate)) {
                BigDecimal rateValue = new BigDecimal(rate);
                places.removeIf(place -> place.getPlaceAdditional().getAverageRating()
                        .compareTo(rateValue) < 0);
            }
        }
    }

    private Boolean isFilteredByMinimalRating() {
        return ServletParameters.isParameterPresent(parameters, PLACE_MINIMAL_RATE_PARAMETER);
    }

    private Integer getMinimalRatingFromParameter() {
        Integer rate = DEFAULT_NULL_NUMERIC_VALUE;
        try {
            rate = Integer.valueOf(ServletParameters.getOnlyFirstValueOfParameter(
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
