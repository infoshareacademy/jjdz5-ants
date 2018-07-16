import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ReadData {
    public void dataRead() {


            //read array from file
            try {
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(new FileReader("dataBasePlaces.json"));

                //take out objects from array
                for(Object obj : array) {
                    JSONObject tempObj = (JSONObject) obj;
                    System.out.println(tempObj);
                }

            } catch (java.io.IOException | org.json.simple.parser.ParseException exc) {
                exc.printStackTrace();
            }



    }

}
