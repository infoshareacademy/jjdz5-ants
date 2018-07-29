package com.infoshareacademy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ReadData {

//  CODE FROM Feature/JZAN-2

    public void dataRead() {
        //read array from file
        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader(new Configuration().getPlacesDB()));
            //take out objects from array
            for(Object obj : array) {
                JSONObject tempObj = (JSONObject) obj;
                System.out.println(tempObj);
            }
        } catch (java.io.IOException | org.json.simple.parser.ParseException exc) {
            exc.printStackTrace();
        }
    }

////  CODE FROM Feature/JZAN-5

    public JSONArray getJSONArray(String FILEPATH) {
        try {
            JSONParser jsonParser = new JSONParser();
            return (JSONArray) jsonParser.parse(new FileReader(FILEPATH));
        }
        catch (java.io.IOException | org.json.simple.parser.ParseException exc) {
            System.out.println("\nBŁĄD ODCZYTU PLIKU: \"" + FILEPATH + "\"!\n");
        }
        return null;
    }

    public JSONArray getSubJSONArray(JSONArray array, int index, String subValue){
        JSONObject object = (JSONObject) array.get(index);
        return (JSONArray) object.get(subValue);
    }

    public JSONObject getJSONObject(JSONArray array, int index){
        return (JSONObject) array.get(index);
    }

    public JSONObject getSubJSONObject(JSONArray array, int index, String subValue){
        JSONArray subArray = getSubJSONArray(array, index, subValue);
        return (JSONObject) subArray.get(0);
    }

}
