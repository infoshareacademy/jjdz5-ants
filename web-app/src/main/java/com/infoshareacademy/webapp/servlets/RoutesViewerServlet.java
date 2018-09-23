package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.webapp.model.Route;
import com.infoshareacademy.webapp.repository.RoutesRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/routes-viewer")
public class RoutesViewerServlet extends HttpServlet {

    @Inject
    private RoutesRepository routesRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        routesRepository.fillPlacesRepository(getServletContext());
        List<Route> routes = routesRepository.getRoutesRepository();

        routes.forEach(route -> {
            try {
                resp.getWriter().println(route);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
