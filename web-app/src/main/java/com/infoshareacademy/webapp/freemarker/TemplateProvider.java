package com.infoshareacademy.webapp.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class TemplateProvider {

    private static final String TEMPLATE_DIRECTORY_PATH = "WEB-INF/templates";
    private static final String TEMPLATE_EXTENSION = ".ftlh";

    private static Template getTemplate(ServletContext servletContext, String templateName)
            throws IOException {
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setServletContextForTemplateLoading(servletContext, TEMPLATE_DIRECTORY_PATH);

        return cfg.getTemplate(templateName + TEMPLATE_EXTENSION);
    }

    public void print(ServletContext servletContext, String templateName, Map dataModule, HttpServletResponse resp) {
        try {
            getTemplate(servletContext, templateName).process(dataModule, resp.getWriter());
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

}