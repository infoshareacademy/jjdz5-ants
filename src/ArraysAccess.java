import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ArraysAccess {

    private Configuration cfg = new Configuration();
    private String placesFP = cfg.getPlacesDB();
    private String routesFP = cfg.getDefaultTR();

    public JSONArray getCorrectPlacesArray(){
        return correctArray(new ReadData().getJSONArray(placesFP), placesFP,"atrakcji \"" + placesFP + "\"");
    }

    public JSONArray getCorrectRoutesArray(){
        return correctArray(new ReadData().getJSONArray(routesFP), routesFP,"tras \"" + routesFP + "\"");
    }

    private JSONArray correctArray(JSONArray array,String FILEPATH, String info){
        boolean isCorrect = true;
        try {
            array.sort(new IDComparator());
        }
        catch (ClassCastException e){
            System.out.println("\nNiestety lista " + info + ", nie może zostać poprawnie wczytana.");
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
