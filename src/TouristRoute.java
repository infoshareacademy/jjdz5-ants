import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TouristRoute {

    private ReadData reader = new ReadData();
    private JSONArray array = new PlacesAccess().getCorrectRoutesArray();
    private PlaceOfInterest place = new PlaceOfInterest();
    private TextFormat txt = new TextFormat();

//  PRINTING INFO.

    public void printAllRoutes() {
        txt.separator();
        for (Object object : array) {
            printBasicInfo(array.indexOf(object));
            printBasicPOIInfo(array.indexOf(object));
            txt.separator();
        }
        txt.separator();
    }

    public void printByID(int index, boolean isBasic){
        try {
            if (isBasic) {
                printBasicInfo(index);
                printBasicPOIInfo(index);
                txt.separator();
            }
            else {
                printFullInfo(index);
            }
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Brak atrakcji turystycznej z podanym #ID.");
        }
    }

    public void printFullInfo(int index){
        printBasicInfo(index);
        System.out.println("Lista atrakcji (wg kolejności zwiedzania):\n");
        int dist = 0;
        for (int i : getPlacesList(index)) {
            place.printFullInfo(i);
            try {
                System.out.println("(Dystans do następnej atrakcji: " + txt.distanceKM(getDistancesList(index).get(dist)) + ")");
                dist++;
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("(Ostatnia atrakcja trasy)");
            }
        }
        txt.separator();
    }

    public void printBasicInfo(int index) {
        txt.separator();
        System.out.println("Nazwa trasy: \"" + getName(index) + "\"\n");
        System.out.println("#ID: " + getID(index) + " / " + index + "\n");
        System.out.println("Ilość atrakcji: " + getPlacesQuantity(index));
        System.out.println("Ogólny dystans: " + txt.distanceKM(getOverallDistance(index)) + "\n");
    }

    public void printSimpleList() {
        txt.separator();
        for (Object object : array){
            System.out.println("#ID: " + getID(array.indexOf(object)) + "   -   Nazwa: " + getName(array.indexOf(object)));
        }
        txt.separator();
    }

    public void printBasicPOIInfo(int index) {
        System.out.println("Lista atrakcji (wg kolejności zwiedzania):\n");
        int dist = 0;
        for (int i : getPlacesList(index)) {
            System.out.print(txt.capitalize(place.getName(i)));
            try {
                System.out.println(" (Dystans do następnej atrakcji: " + txt.distanceKM(getDistancesList(index).get(dist)) + ")");
                dist++;
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(" (Ostatnia atrakcja trasy)");
            }
        }
    }

//  GETTERS

    public Integer getID(int index) {
        return Math.toIntExact((Long) reader.getJSONObject(array, index).get("ID"));
    }

    public String getName(int index) {
        return reader.getJSONObject(array,index).get("routeName").toString();
    }

    public String getPlacesQuantity(int index) {
        return reader.getJSONObject(array,index).get("placesQuantity").toString();
    }

    public Double getOverallDistance(int index) {
        return (Double) reader.getJSONObject(array,index).get("overallDistance");
    }

    public List<Integer> getPlacesList(int index) {
        JSONArray subArray = reader.getSubJSONArray(array,index,"placesList");
        List<Integer> placesList = new ArrayList<>();
        for (Object object : subArray){
            placesList.add(Math.toIntExact((Long) subArray.get(subArray.indexOf(object))));
        }
        return placesList;
    }

    public List<Double> getDistancesList(int index) {
            JSONArray subArray = reader.getSubJSONArray(array,index,"distancesList");
            List<Double> distancesList = new ArrayList<>();
            for (Object object : subArray){
                distancesList.add((Double) subArray.get(subArray.indexOf(object)));
            }
            return distancesList;
        }

}
