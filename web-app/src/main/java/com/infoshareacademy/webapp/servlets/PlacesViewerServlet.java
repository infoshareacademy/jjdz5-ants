package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.PlaceOfInterestType;
import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.mechanics.AccessJson;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import org.json.simple.parser.ParseException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/places-viewer")
public class PlacesViewerServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "places-viewer";
    private static final String PLACES_KEY = "places";
    private static final String PLACES_TYPES_KEY = "placesTypes";
    private static final String PLACE_NAME_PARAMETER = "pName";
    private static final String PLACE_SELECTION_KEY = "placeSelectionId";
    private static final String PLACE_NAME_PARAMETER_KEY = "placeNameParameter";
    private static final Integer DEFAULT_PLACE_NAME_VALUE = 0;

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AccessJson accessJson;

    @Inject
    private PlacesRepository placesRepository;

    private Map<String, List> dataModule;
    private Map<String, String[]> parameters;
    private List<Integer> placeSelectionId;
    private List<String> placeNameParameter;
    private List<PlaceOfInterestType> placesTypes;

    @Override
    public void init() throws ServletException {
        try {
            accessJson.setJsonArray(Configuration.PLACES_JSON_FILEPATH, getServletContext());
            placesRepository.addPlacesToRepository(accessJson.getJsonArray());
            System.out.println("||PLACES REPOSITORY loaded successfully");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        dataModule = new HashMap();
        dataModule.put(PLACES_KEY, placesRepository.getPlacesRepository());
        fillDataModuleWithPlacesTypes();

        parameters = req.getParameterMap();
        fillDataModuleWithPlaceNameFilter();

        templateProvider.print(getServletContext(),TEMPLATE_NAME, dataModule, resp);
    }

    private void fillDataModuleWithPlacesTypes() {
        preparePlacesTypesList();
        putPlacesTypesToDataModule();
    }

    private void preparePlacesTypesList() {
        placesTypes = new ArrayList<>(getAllPlaceTypes());
    }

    private EnumSet<PlaceOfInterestType> getAllPlaceTypes() {
        return EnumSet.allOf(PlaceOfInterestType.class);
    }

    private void putPlacesTypesToDataModule() {
        dataModule.put(PLACES_TYPES_KEY, placesTypes);
    }

    private void fillDataModuleWithPlaceNameFilter() {
        preparePlaceNameLists();
        if (ServletParameters.isParameterPresent(parameters, PLACE_NAME_PARAMETER)) {
            addSelectedPlaceToLists(getPlaceNameFromParameter());
        } else {
            addDefaultSelectionToList();
        }
        putPlaceNameFilterToDataModule();
    }

    private void preparePlaceNameLists() {
        placeSelectionId = new ArrayList<>();
        placeNameParameter = new ArrayList<>();
    }

    private void addSelectedPlaceToLists(String placeName) {
        placeSelectionId.add(placesRepository.getPlaceIdByName(placeName));
        placeNameParameter.add(placeName);
    }

    private String getPlaceNameFromParameter() {
        return ServletParameters.getOnlyFirstValueOfParameter(parameters.get(PLACE_NAME_PARAMETER));
    }

    private void addDefaultSelectionToList() {
        placeSelectionId.add(DEFAULT_PLACE_NAME_VALUE);
    }

    private void putPlaceNameFilterToDataModule() {
        dataModule.put(PLACE_SELECTION_KEY, placeSelectionId);
        dataModule.put(PLACE_NAME_PARAMETER_KEY, placeNameParameter);
    }

}
