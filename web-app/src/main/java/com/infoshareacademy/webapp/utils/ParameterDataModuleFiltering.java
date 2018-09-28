package com.infoshareacademy.webapp.utils;


public interface ParameterDataModuleFiltering extends DataModuleOperatingService {

    Boolean isFilteredBy(String parameter);

    String getValueFromParameter(String parameter);

}
