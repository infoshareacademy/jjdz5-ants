package com.infoshareacademy.webapp.mechanics;

import com.infoshareacademy.ReadData;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStreamReader;

@ApplicationScoped
public class DataReader extends ReadData {

    private final JSONParser jsonParser = new JSONParser();
    private JSONArray jsonArray;

    //TODO - need to make simple default array, just in case! We don't want to have null here.

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(String FILEPATH, ServletContext servletContext) throws IOException, ParseException {
        jsonArray = JsonArrayParse(JsonResourceAsStreamReader(FILEPATH, servletContext));
    }

    private InputStreamReader JsonResourceAsStreamReader(String FILEPATH, ServletContext servletContext) {
        return new InputStreamReader(servletContext.getResourceAsStream(FILEPATH));
    }

    private JSONArray JsonArrayParse(InputStreamReader jsonFile) throws IOException, ParseException {
        return (JSONArray) jsonParser.parse(jsonFile);
    }



}
