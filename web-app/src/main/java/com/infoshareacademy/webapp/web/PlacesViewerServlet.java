package com.infoshareacademy.webapp.web;

import com.infoshareacademy.PlaceOfInterest;
import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.repository.PlacesRepository;

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

@WebServlet("/places-viewer")
public class PlacesViewerServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "places-viewer";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private PlacesRepository placesRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        Map<String, List> dataModule = new HashMap();
        dataModule.put("places", placesRepository.getPlaces());

        templateProvider.print(getServletContext(),TEMPLATE_NAME, dataModule, resp);
    }

}
