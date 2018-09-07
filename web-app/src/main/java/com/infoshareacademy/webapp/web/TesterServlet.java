package com.infoshareacademy.webapp.web;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.mechanics.AccessJson;
import com.infoshareacademy.webapp.mechanics.PlacePullFromJson;
import com.infoshareacademy.webapp.model.Place;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import org.json.simple.parser.ParseException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/tester")
public class TesterServlet extends HttpServlet {

    @Inject
    private PlacesRepository placesRepository;

    @Inject
    private AccessJson accessJson;

    @Inject
    private PlacePullFromJson placePullFromJson = new PlacePullFromJson();

    @Override
    public void init() throws ServletException {
        try {
            accessJson.setJsonArray(Configuration.PLACES_JSON_FILEPATH, getServletContext());
            placesRepository.addPlacesToRepository(accessJson.getJsonArray());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        List<Place> placesRepo = placesRepository.getPlacesRepository();
        for (Place place : placesRepo) {
            resp.getWriter().println(place);
            resp.getWriter().println("<br><br>");
        }

    }

}
