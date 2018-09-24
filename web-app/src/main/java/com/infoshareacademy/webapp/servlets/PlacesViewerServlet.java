package com.infoshareacademy.webapp.servlets;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/places-viewer")
public class PlacesViewerServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "places-viewer";
    private static final String PLACES_KEY = "places";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private AccessJson accessJson;

    @Inject
    private PlacesRepository placesRepository;

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

        Map<String, List> dataModule = new HashMap();
        dataModule.put(PLACES_KEY, placesRepository.getPlacesRepository());

        templateProvider.print(getServletContext(),TEMPLATE_NAME, dataModule, resp);
    }

}
