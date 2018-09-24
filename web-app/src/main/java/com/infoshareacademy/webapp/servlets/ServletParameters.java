package com.infoshareacademy.webapp.servlets;

import java.util.Map;

public class ServletParameters {

    private static final Integer FIRST_INDEX = 0;

    public static Boolean isParameterPresent(Map<String, String[]> parameters, String parameter) {
        return parameters.keySet().contains(parameter);
    }

    public static String getOnlyFirstValueOfParameter(String[] values) {
        return values[FIRST_INDEX];
    }

}
