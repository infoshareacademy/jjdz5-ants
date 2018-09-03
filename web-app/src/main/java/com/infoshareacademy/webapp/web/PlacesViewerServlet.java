package com.infoshareacademy.webapp.web;

import com.infoshareacademy.webapp.freemarker.TemplateProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/places-viewer")
public class PlacesViewerServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "places-viewer";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        Map dataModule = new HashMap();

        templateProvider.print(getServletContext(),TEMPLATE_NAME, dataModule, resp);
    }
}
