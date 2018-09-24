package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.webapp.freemarker.TemplateProvider;
import com.infoshareacademy.webapp.model.Route;
import com.infoshareacademy.webapp.repository.RoutesRepository;
import com.infoshareacademy.webapp.utils.RoutesViewerDataModule;

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
    private RoutesViewerDataModule dataModule;

    private Map<String, List> dataModuleMap;

    @Override
    public void init() throws ServletException {
        dataModuleMap = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        routesRepository.fillPlacesRepository(getServletContext());
        List<Route> routes = routesRepository.getRoutesRepository();
        Map<String, String[]> parameters = req.getParameterMap();

        dataModule.setDataModuleMap(dataModuleMap);
        dataModule.setOperatingRepository(routes);
        dataModule.setParameters(parameters);

        dataModule.fillDataModuleWithRequiredValues();
        dataModule.filterDataModule();
        dataModuleMap = dataModule.getDataModuleMap();

        templateProvider.print(getServletContext(), TEMPLATE_NAME, dataModuleMap, resp);

    }
}
