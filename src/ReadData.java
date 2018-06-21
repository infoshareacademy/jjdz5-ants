import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ReadData {
    public static void main(String[] args) {


            //Poniższe wczytuje tablicę z pliku
            try {
                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(new FileReader("dataBasePlaces.json"));

                //Poniższe wyciąga obiekty z tablicy
                for(Object obj : array) {
                    JSONObject tempObj = (JSONObject) obj;
                    System.out.println(tempObj);
                }

            } catch (java.io.IOException | org.json.simple.parser.ParseException exc) {
                exc.printStackTrace();
            }



    }

}
