package com.infoshareacademy.webapp.web;

import com.infoshareacademy.webapp.Configuration;
import com.infoshareacademy.webapp.mechanics.DataReader;
import com.infoshareacademy.webapp.repository.PlacesRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import javax.inject.Inject;
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

    @Override
    public void init() throws ServletException {
        try {
            dataLoader();
            System.out.println("Repository successfully loaded.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);
        resp.getWriter().println(placesRepository.getPlaces());
    }


    private void dataLoader() throws IOException, ParseException {
            dataReader.setJsonArray(Configuration.PLACES_JSON_FILEPATH, getServletContext());
            placesArray = dataReader.getJsonArray();
    }

}
