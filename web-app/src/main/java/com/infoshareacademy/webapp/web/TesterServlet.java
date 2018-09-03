package com.infoshareacademy.webapp.web;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.mechanics.DataReader;
import com.infoshareacademy.webapp.mechanics.PlaceConverter;
import com.infoshareacademy.webapp.model.PlaceMain;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/tester")
public class TesterServlet extends HttpServlet {

    private JSONArray placesArray;

    @Inject
    private DataReader dataReader;

    @Inject
    PlacesRepository placesRepository;

    @Inject
    PlaceConverter placeConverter;

    @Override
    public void init() throws ServletException {
        try {
            placeConverter.placesJsonLoader(getServletContext());
            System.out.println("Repository successfully loaded.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);
        resp.getWriter().println(placesRepository.getPlaces());

        placeConverter.setPreparationIndex(3);
        PlaceMain placeMain = placeConverter.getPlaceMain();

        resp.getWriter().println(placeMain);

    }


    private void placesJsonLoader(ServletContext servletContext) throws IOException, ParseException {
            dataReader.setJsonArray(Configuration.PLACES_JSON_FILEPATH, getServletContext());
            placesArray = dataReader.getJsonArray();
    }

}
