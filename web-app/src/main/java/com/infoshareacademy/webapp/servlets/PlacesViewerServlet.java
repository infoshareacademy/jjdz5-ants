package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import com.infoshareacademy.webapp.utils.PlacesViewerDataModule;

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

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private PlacesRepository placesRepository;

    @Inject
    private PlacesViewerDataModule dataModule;

    private Map<String, List> dataModuleMap;

    @Override
    public void init() throws ServletException {
        dataModuleMap = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        placesRepository.fillPlacesRepository(getServletContext());
        List<Place> places = placesRepository.getPlacesRepository();
        Map<String, String[]> parameters = req.getParameterMap();

        dataModule.setDataModuleMap(dataModuleMap);
        dataModule.setPlaces(places);
        dataModule.setParameters(parameters);

        dataModule.fillDataModuleWithRequiredValues();
        dataModule.filterDataModule();
        dataModuleMap = dataModule.getDataModuleMap();

        templateProvider.print(getServletContext(),TEMPLATE_NAME, dataModuleMap, resp);
    }

}
