import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ReadData {

    public JSONArray getJSONArray(String FILEPATH) {
        try {
            JSONParser jsonParser = new JSONParser();
            return (JSONArray) jsonParser.parse(new FileReader(FILEPATH));
        }
        catch (java.io.IOException | org.json.simple.parser.ParseException exc) {
            System.out.println();
            System.out.println("BŁĄD ODCZYTU PLIKU: \"" + FILEPATH + "\"!");
            System.out.println();
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
