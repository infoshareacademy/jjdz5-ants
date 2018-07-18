import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.Menu;

public class PlacesAccess {

    private String FILEPATH = new Configuration().getPlacesDB();
    private JSONArray correctArray = correctPlacesArray(new ReadData().getJSONArray(FILEPATH));

    private JSONArray correctPlacesArray(JSONArray array){
        boolean isCorrect = true;
        try {
            array.sort(new IDComparator());
        }
        catch (ClassCastException e){
            System.out.println();
            System.out.println("Niestety lista atrakcji \"" + FILEPATH + "\", nie może zostać poprawnie wczytana.");
            System.out.println("Powód:");
            isCorrect = false;
        }
        for (Object object : array) {
            JSONObject jsonObject = (JSONObject) object;
            Long index = (long) array.indexOf(object);
            try {
                Long id = (Long) jsonObject.get("ID");
                if (!index.equals(id)) {
                    jsonObject.put("ID", index);
                }
            }
            catch(ClassCastException e){
                System.out.println("- Niepoprawny zapis #ID (\"" + jsonObject.get("ID") + "\") w index'ie: #" + array.indexOf(jsonObject) + ".");
            }
        }
        if (isCorrect) {
            new WriteData().WriteJSONArray(array, FILEPATH);
            return array;
        }
        else{
            System.out.println();
            System.out.println("Proszę naprawić bazę danych przed ponowną próbą.");
            System.out.println();
            return null;
        }
    }

    public JSONArray getCorrectPlacesArray(){
        return correctArray;
    }

}
