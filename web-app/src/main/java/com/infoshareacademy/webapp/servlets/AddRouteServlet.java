package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import com.infoshareacademy.webapp.utils.PlacesViewerDataModuleOperatingService;

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

@WebServlet ("/add-route")
public class AddRouteServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "add-route";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private PlacesRepository placesRepository;

    @Inject
    private PlacesViewerDataModuleOperatingService dataModule;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);
        Map<String, List> dataModuleMap = new HashMap<>();
        placesRepository.fillPlacesRepository(getServletContext());
        List<Place> places = placesRepository.getPlacesRepository();
        dataModule.setDataModuleMap(dataModuleMap);
        dataModule.setOperatingRepository(places);
        dataModule.setParameters(req.getParameterMap());
        dataModule.fillDataModuleWithRequiredValues();
        dataModuleMap = dataModule.getDataModuleMap();

        templateProvider.print(getServletContext(), TEMPLATE_NAME, dataModuleMap, resp);
    }
}
