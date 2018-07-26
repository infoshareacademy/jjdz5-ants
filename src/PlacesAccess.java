import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PlacesAccess {

    private String FILEPATH = new Configuration().getPlacesDB();

    public JSONArray getCorrectPlacesArray(){
        return correctPlacesArray(new ReadData().getJSONArray(FILEPATH));
    }

    private JSONArray correctPlacesArray(JSONArray array){
        boolean isCorrect = true;
        try {
            array.sort(new IDComparator());
        }
        catch (ClassCastException e){
            System.out.println("\nNiestety lista atrakcji \"" + FILEPATH + "\", nie może zostać poprawnie wczytana.");
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
            System.out.println("\nProszę naprawić bazę danych przed ponowną próbą.\n");
            return null;
        }
    }

}
