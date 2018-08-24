package com.infoshareacademy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Location{

    private ReadData reader = new ReadData();
    private TextFormat txt = new TextFormat();
    private JSONArray array = new ArraysAccess().getCorrectPlacesArray();

    public static final String APARTMENT = "Apartment";
    private static final Integer EMPTY = 0;

//  PRINTING INFO

    public void printAdress(int index){
        String apartment = "";
        Integer apartmentNumber = getApartmentNumber(index);
        if (apartmentNumber != EMPTY) {
            apartment = ("/" + apartmentNumber);
        }
        txt.separator();
        System.out.println("Adres:");
        System.out.println("Ul. " + txt.capitalize(getStreet(index)) + " " + getBuildingNumber(index) + apartment);
        System.out.println(txt.capitalize(getCity(index)) + " " + txt.capitalize(getDistrict(index)));
    }

    public void printGPSPosition(int index){
        txt.separator();
        System.out.println("WSPÓŁRZĘDNE GPS:");
        System.out.print("Szerokość: " + txt.gpsValue(getCoordinate(index, "Latitude")));
        System.out.println("  Długość: " + txt.gpsValue(getCoordinate(index, "Longitude")));
    }

//  ADRESS.

    public String getCity(int index) {
        return reader.getJSONObject(array,index).get("City").toString();
    }

    public String getDistrict(int index) {
        return reader.getJSONObject(array,index).get("District").toString();
    }

    public String getStreet(int index) {
        return reader.getJSONObject(array,index).get("Street").toString();
    }

    public String getBuildingNumber(int index) {
        return reader.getJSONObject(array, index).get("Building number").toString();
    }

    public Integer getApartmentNumber(int index) {
        try {
            return Integer.valueOf(reader.getJSONObject(array,index).get(APARTMENT).toString());
        }
        catch (NumberFormatException e) {
            return EMPTY;
        }
    }

//  GPS COORDINATES.

    public Double getCoordinate(int index, String coordinate){
        JSONObject object = reader.getSubJSONObject(array,index,"GPS coordinates");
        try { return Double.valueOf(object.get(coordinate).toString()); }
        catch (NumberFormatException e) { return 0.0; }
    }

}
