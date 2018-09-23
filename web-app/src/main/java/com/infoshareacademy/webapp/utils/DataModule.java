package com.infoshareacademy.webapp.utils;

import java.util.Map;

public interface DataModule {

    <V> void setDataModuleMap(Map<String, V> dataModuleMap);

    void setParameters(Map<String, String[]> parameters);

    <V> void putIntoDataModule(String dataKey, V dataValue);

    <V> Map<String, V> getDataModuleMap();

}
