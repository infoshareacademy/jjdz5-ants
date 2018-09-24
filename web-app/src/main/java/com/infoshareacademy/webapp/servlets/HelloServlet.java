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

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private static final String HELLO = "Hello Servlet!";
    private static final String HELLO_TEMPLATE_NAME = "hello";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfiguration.setDefaultContentType(resp);

        Map<String, String> dataModule = new HashMap<>();
        dataModule.put("text", HELLO);

        templateProvider.print(getServletContext(), HELLO_TEMPLATE_NAME, dataModule, resp);

    }
}
