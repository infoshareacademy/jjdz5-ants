package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.model.Route;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import com.infoshareacademy.webapp.repository.RoutesRepository;
import com.infoshareacademy.webapp.utils.RoutesViewerDataModuleOperatingService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/routes-viewer")
public class RoutesViewerServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "routes-viewer";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private RoutesRepository routesRepository;

    @Inject
    private PlacesRepository placesRepository;

    @Inject
    private RoutesViewerDataModuleOperatingService dataModule;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        Map<String, List> dataModuleMap = new HashMap<>();

        routesRepository.fillPlacesRepository(getServletContext());
        placesRepository.fillPlacesRepository(getServletContext());
        List<Route> routes = routesRepository.getRoutesRepository();
        List<Place> places = placesRepository.getPlacesRepository();
        Map<String, String[]> parameters = req.getParameterMap();

        dataModule.setDataModuleMap(dataModuleMap);
        dataModule.setOperatingRepository(routes);
        dataModule.setPlacesRepository(places);
        dataModule.setParameters(parameters);

        dataModule.fillDataModuleWithRequiredValues();
        dataModule.filterDataModule();
        dataModuleMap = dataModule.getDataModuleMap();

        templateProvider.print(getServletContext(), TEMPLATE_NAME, dataModuleMap, resp);

    }

}
