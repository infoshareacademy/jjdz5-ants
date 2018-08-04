package com.infoshareacademy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PlaceOfInterest{

    private ReadData reader = new ReadData();
    private TextFormat txt = new TextFormat();
    private Location loc = new Location();
    private AverageRating rating = new AverageRating();
    private JSONArray array = new ArraysAccess().getCorrectPlacesArray();

//  PRINTING INFO.

    public void printAllPlaces(){
        for (Object object : array) {
            printFullInfo(array.indexOf(object));
        }
    }

    public void printAllOfType(Enum placeType){
        boolean isPresent = false;
        System.out.println();
        System.out.println("Wszystkie atrakcje z kategorii \"" + placeType.toString() + "\":");
        System.out.println();
        for (Object object: array){
            JSONObject jsonObject = (JSONObject) object;
            if (placeType == getType(array.indexOf(object))){
                Integer index = Math.toIntExact((Long) jsonObject.get("ID"));
                printFullInfo(index);
                isPresent = true;
            }
        }
        if (!isPresent) {
            System.out.println("Niestety, baza danych nie posiada jeszcze ŻADNEJ atrakcji w tej kategorii.");
        }
    }

    public void printByID(int index){
        try { printFullInfo(index); }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Brak atrakcji turystycznej z podanym #ID.");
        }
    }

    public void printSimpleList(){
        txt.separator();
        for (Object object : array){
            System.out.println("#ID: " + getID(array.indexOf(object)) + "   -   Nazwa: " + getName(array.indexOf(object)));
        }
        txt.separator();
    }

    public void printFullInfo(int index){
        printBasicInfo(index);
        loc.printAdress(index);
        printOH(index);
        printPrices(index);
        loc.printGPSPosition(index);
        txt.separator();
        txt.separator();
    }

    public void printBasicInfo(int index){
        txt.separator();
        System.out.println("\n---|| " + getName(index).toUpperCase() + " ||---");
        txt.separator();
        System.out.println("ID obiektu: #" + getID(index) + " / " + index);
        System.out.println("Typ obiektu: " + getType(index).toString());
        System.out.println();
        System.out.println("Opis: " + txt.capitalize(getDescription(index)));
        System.out.println();
        rating.printRating(index);
    }

    public void printOH(int index){
        txt.separator();
        System.out.println("GODZINY OTWARCIA:");
        System.out.print("Poniedziałek: " + getDayOH(index, "Monday"));
        System.out.println("    Piątek:       " + getDayOH(index, "Friday"));
        System.out.print("Wtorek:       " + getDayOH(index, "Tuesday"));
        System.out.println("    Sobota:       " + getDayOH(index, "Saturday"));
        System.out.print("Środa:        " + getDayOH(index, "Wednesday"));
        System.out.println("    Niedziela:    " + getDayOH(index, "Sunday"));
        System.out.println("Czwartek:     " + getDayOH(index, "Thursday"));
    }

    public void printPrices(int index){
        txt.separator();
        System.out.println("CENNIK: ");
        System.out.print("Dorośli: " + getPrice(index,"Adults price"));
        System.out.println("    Dzieci: " + getPrice(index,"kids price"));
        System.out.println("Emeryci: " + getPrice(index,"Seniors price"));
    }

//  MAIN FIELDS.

    public Integer getID(int index) {
        return Math.toIntExact((Long) reader.getJSONObject(array, index).get("ID"));
    }

    public Enum getType(int index) {
        String s = reader.getJSONObject(array,index).get("type").toString();
        for (PlaceOfInterestType poiType : PlaceOfInterestType.values()){
            if (s.equalsIgnoreCase(poiType.name())){
                return poiType;
            }
        }
        return PlaceOfInterestType.WRONG;
    }

    public String getName(int index) {
        return reader.getJSONObject(array,index).get("Name").toString();
    }

    public String getDescription(int index) {
        return reader.getJSONObject(array,index).get("description").toString();
    }

//  OPENING HOURS.

    public String getDayOH(int index, String day){
        JSONObject object = reader.getSubJSONObject(array, index, "Opening hours: ");
        return object.get(day).toString();
    }

//  PRICES.

    public String getPrice(int index, String ageGroup){
        JSONObject object = reader.getSubJSONObject(array,index,"Price list");
        try{
            Double price = Double.valueOf(object.get(ageGroup).toString());
            if (price == 0) {
                return "ZA DARMO";
            }
            else {
                return txt.pricePLN(price);
            }
        }
        catch (NumberFormatException e){
            return object.get(ageGroup).toString();
        }
    }

}
