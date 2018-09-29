package com.infoshareacademy.webapp.servlets;

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

@WebServlet("/signin")
public class SingInServlet extends HttpServlet {

    private static final String HELLO_TEMPLATE_NAME = "login";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);


        templateProvider.print(getServletContext(), HELLO_TEMPLATE_NAME, new HashMap<>(), resp);

    }
}
