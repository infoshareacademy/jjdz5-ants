package com.infoshareacademy.webapp.servlets;

import javax.servlet.http.HttpServletResponse;

class ServletConfiguration {

    private static final String HEADER_TYPE_CONTENT = "Content-Type";
    private static final String HEADER_HTML_UTF = "text/html; charset=utf-8";

    static void setDefaultContentType(HttpServletResponse resp) {
        resp.setHeader(HEADER_TYPE_CONTENT, HEADER_HTML_UTF);
    }

}
