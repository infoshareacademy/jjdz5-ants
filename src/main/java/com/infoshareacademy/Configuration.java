package com.infoshareacademy;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private Properties cfg = new Properties();

    public Configuration() {
        loadConfig();
    }

    public String getAdminPass() {
        return cfg.getProperty("adminPass");
    }

    public String getPlacesDB() {
        return cfg.getProperty("placesDB");
    }

    public String getDefaultTR(){
        return cfg.getProperty("defaultTR");
    }

    private void loadConfig() {

        try {
            cfg.load(getClass().getClassLoader().getResourceAsStream("cfg.properties"));
        } catch (FileNotFoundException e) {
            System.out.println("NIE ZNALEZIONO PLIKU KONFIGURACYJNEGO!");
        } catch (IOException e) {
            System.out.println("BŁĄD ODCZYTU!");
        }
    }
}