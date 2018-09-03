package com.infoshareacademy.webapp.web;

import javax.servlet.http.HttpServletResponse;

public class ServletConfiguration {

    private static final String HEADER_TYPE_CONTENT = "Content-Type";
    private static final String HEADER_HTML_UTF = "text/html; charset=utf-8";

    protected static void setDefaultContentType(HttpServletResponse resp) {
        resp.setHeader(HEADER_TYPE_CONTENT, HEADER_HTML_UTF);
    }

}
