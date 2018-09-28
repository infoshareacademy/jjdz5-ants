package com.infoshareacademy.webapp.utils;

import java.util.Map;

public interface DataModuleOperatingService {

    <V> void setDataModuleMap(Map<String, V> dataModuleMap);

    <T> void setOperatingRepository(T repository);

    void setParameters(Map<String, String[]> parameters);

    <V> void putIntoDataModule(String dataKey, V dataValue);

    <V> Map<String, V> getDataModuleMap();

}
