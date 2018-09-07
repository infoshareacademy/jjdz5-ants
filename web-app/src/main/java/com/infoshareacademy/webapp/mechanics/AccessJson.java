package com.infoshareacademy.webapp.mechanics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped
public class AccessJson {

    private final JSONParser jsonParser = new JSONParser();
    private JSONArray jsonArray;

    //TODO - need to make simple default array, just in case! We don't want to have null here.

    private InputStreamReader JsonResourceAsStreamReader(String FILEPATH, ServletContext servletContext) {
        return new InputStreamReader(servletContext.getResourceAsStream(FILEPATH));
    }

    private JSONArray JsonArrayParse(InputStreamReader jsonFile) throws IOException, ParseException {
        return (JSONArray) jsonParser.parse(jsonFile);
    }

    public void setJsonArray(String FILEPATH, ServletContext servletContext) throws IOException, ParseException {
        jsonArray = JsonArrayParse(JsonResourceAsStreamReader(FILEPATH, servletContext));
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public JSONObject pullJsonObject(JSONArray array, Integer index) {
        return (JSONObject) array.get(index);
    }

    public JSONArray getSubJsonArray(JSONArray array, Integer index, String subValue) {
        JSONObject object = (JSONObject) array.get(index);
        return (JSONArray) object.get(subValue);
    }

    public JSONObject getSubJsonObject(JSONArray array, int index, String subValue) {
        JSONArray subArray = getSubJsonArray(array, index, subValue);
        return (JSONObject) subArray.get(0);
    }

    public Map pullJsonStringCollection(JSONObject jsonCollectionName) {
        String keyValueSplitSign = "=";
        Map keyValueMap = new TreeMap<>();
        for (Object object : jsonCollectionName.entrySet()) {
            String[] keyValueSplit = object.toString().split(keyValueSplitSign);
            String key = keyValueSplit[0];
            String value = keyValueSplit[1];
            keyValueMap.put(key, value);
        }
        return keyValueMap;
    }

    public Map<String, Integer> pullJsonIntegerCollection(JSONObject jsonCollectionName) {
        String keyValueSplitSign = "=";
        Map keyValueMap = new TreeMap<>();
        try {
            for (Object object : jsonCollectionName.entrySet()) {
                String[] keyValueSplit = object.toString().split(keyValueSplitSign);
                String key = keyValueSplit[0];
                Integer value = Integer.valueOf(keyValueSplit[1]);
                keyValueMap.put(key, value);
            }
            return keyValueMap;
        } catch (NumberFormatException e) {
            System.out.println("||ERROR: NumberFormatException in pullJsonIntegerCollection ||");
        }
        return null;
    }

    public Map<String, Double> pullJsonDoubleCollection(JSONObject jsonCollectionName) {
        String keyValueSplitSign = "=";
        Map keyValueMap = new TreeMap<>();
        try {
            for (Object object : jsonCollectionName.entrySet()) {
                String[] keyValueSplit = object.toString().split(keyValueSplitSign);
                String key = keyValueSplit[0];
                Double value = Double.valueOf(keyValueSplit[1]);
                keyValueMap.put(key, value);
            }
            return keyValueMap;
        } catch (NumberFormatException e) {
            System.out.println("||ERROR: NumberFormatException in pullJsonDoubleCollection ||");
        }
        return null;
    }

    public Object findSpecificValueInCollection(Map pulledCollection, String requestedKey) {
        for (Object entry : pulledCollection.keySet()) {
            if (requestedKey.equalsIgnoreCase(entry.toString())){
                return pulledCollection.get(entry);
            }
        }
        return null;
    }
}
