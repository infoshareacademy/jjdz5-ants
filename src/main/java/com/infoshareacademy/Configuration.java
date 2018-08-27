package com.infoshareacademy;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private Properties cfg = new Properties();

    private static final String CFG_FILEPATH = "cfg.properties";
    private static final String ADMIN_PASS = "adminPass";
    private static final String PLACES_DB = "placesDB";
    private static final String DEFAULT_TR = "defaultTR";

    public Configuration() {
        loadConfig();
    }

    public String getAdminPass() {
        return cfg.getProperty(ADMIN_PASS);
    }

    public String getPlacesDB() {
        return cfg.getProperty(PLACES_DB);
    }

    public String getDefaultTR(){
        return cfg.getProperty(DEFAULT_TR);
    }

    private void loadConfig() {

        try {
            cfg.load(getClass().getClassLoader().getResourceAsStream(CFG_FILEPATH));
        } catch (FileNotFoundException e) {
            System.out.println("NIE ZNALEZIONO PLIKU KONFIGURACYJNEGO!");
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU!");
        }
    }
}