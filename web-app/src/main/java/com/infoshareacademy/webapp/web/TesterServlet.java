package com.infoshareacademy.webapp.web;

import com.infoshareacademy.webapp.mechanics.PlacePullFromJson;
import com.infoshareacademy.webapp.model.PlaceAdditional;
import com.infoshareacademy.webapp.model.PlaceMain;
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

    @Inject
    private PlacePullFromJson placePullFromJson;


    @Override
    public void init() throws ServletException {
        try {
            placePullFromJson.placesJsonLoader(getServletContext());
            System.out.println("Repository successfully loaded.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);
        placePullFromJson.setPullIndex(9);
        PlaceMain placeMain;
        PlaceAdditional placeAdditional;
        placeMain = placePullFromJson.getPlaceMain();
        placeAdditional = placePullFromJson.getPlaceAdditional();

        resp.getWriter().println(placeMain);
        resp.getWriter().println(placeAdditional);
        resp.getWriter().println("<br><br>");

    }

}
