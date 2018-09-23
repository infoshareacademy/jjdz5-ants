package com.infoshareacademy.webapp.utils;


public interface Filtering extends DataModule{

    Boolean isFilteredBy(String parameter);

    String getValueFromParameter(String parameter);

}
