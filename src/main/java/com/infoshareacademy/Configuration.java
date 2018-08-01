package com.infoshareacademy;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private Properties cfg = new Properties();

    public Configuration() {
        loadConfig();
    }

    public String getPlacesDB(){
        return cfg.getProperty("placesDB");
    }

    public String getDefaultTR(){ return cfg.getProperty("defaultTR"); }

    public String getUserTR(){
        return cfg.getProperty("userTR");
    }

    public String getTripOTD(){
        return cfg.getProperty("tripOTD");
    }

    public String getTestFile(){
        return cfg.getProperty("testFile");
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

    public void allLineReader(String FILEPATH){

        String line;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILEPATH))) {
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}