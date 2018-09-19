package com.infoshareacademy.webapp.servlets;

import com.infoshareacademy.webapp.model.Place;

import java.util.List;
import java.util.Map;

public class ServletParameters {

    private static final Integer FIRST_INDEX = 0;
    private static final Integer EMPTY_SIZE = 0;

    public static Boolean atLeastOneParameter(Map<String, String[]> parameters){
        return parameters.size() > EMPTY_SIZE;
    }

    public static Boolean isParameterPresent(Map<String, String[]> parameters, String parameter) {
        return parameters.keySet().contains(parameter);
    }

    public static Boolean isSelectedPlaceNotNull(List<Place> selectedPlace) {
        return selectedPlace.size() > EMPTY_SIZE;
    }

    public static String getOnlyFirstValueOfParameter(String[] values) {
        return values[FIRST_INDEX];
    }

}
