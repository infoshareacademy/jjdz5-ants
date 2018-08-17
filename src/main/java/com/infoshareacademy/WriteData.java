package com.infoshareacademy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WriteData {

    private String readString(){
        return new Scanner(System.in).nextLine();
    }
    private Long readLong() { return  new Scanner(System.in).nextLong(); }
    private ReadData reader = new ReadData();
    private Configuration cfg = new Configuration();
    private Menu menu = new Menu();
    private TextFormat txt = new TextFormat();
    private Double overallDistance = 0.0;
    private List<Double> distances = new ArrayList<>();

//  CODE FROM Feature/JZAN-2

    public void writePlace() {

        Scanner dataInput = new Scanner(System.in);


        JSONObject object = new JSONObject();
        SoutWriter read = new SoutWriter();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader(cfg.getPlacesDB()));
            boolean incorrectData = true;

            do {
                try {
                    for (int i = 0; i < 1; i++) {
                        read.sout("Wprowadź ID: ");
                        Scanner dataInput2 = new Scanner(System.in);
                        Long dataId = dataInput2.nextLong();
                        for (Object obj : array) {
                            Long id = (Long) ((JSONObject) obj).get("ID");
                            if (dataId == id) {
                                System.out.println("ID zarezerwowane, spróbuj ponownie");
                                i--;
                                continue;
                            }
                        }
                        object.put("ID", dataId);
                        incorrectData = false;
                    }
                } catch (InputMismatchException exc) {
                    read.sout("Wprowadziłeś niewłaściwy typ danych, spróbuj ponownie: ");
                }
            }
            while(incorrectData == true);

            List<Double> average = new ArrayList<>();
            object.put("averageRating", average);

            String dataCollector1 = read.soutString("Wprowadź nazwę: ");
            object.put("Name", dataCollector1);

            incorrectData = true;
            do {
                try {
                    read.sout("Wybierz typ z poniższej listy:");
                    read.enumSout();
                    dataInput = new Scanner(System.in);
                    int dataCollector2 = dataInput.nextInt();
                    String choice = read.typeChoice(dataCollector2);
                    object.put("type", choice);
//                    flag = false;
                } catch (InputMismatchException | NullPointerException exc) {
                    read.sout("Niewłaściwy wybór");
                }
            }
            while(incorrectData == true);

            dataCollector1 = read.soutString("Wprowadź opis obiektu: ");
            object.put("description", dataCollector1);

            //set opening hours
            read.sout("Wprowadź godziny otwarcia: ");
            JSONArray openingHours = new JSONArray();
            JSONObject day = new JSONObject();
            String nextDay;

            nextDay = read.soutString("Poniedziałek: ");
            day.put("Monday", nextDay);

            nextDay = read.soutString("Wtorek: ");
            day.put("Tuesday", nextDay);

            nextDay = read.soutString("Środa: ");
            day.put("Wednesday", nextDay);

            nextDay = read.soutString("Czwartek: ");
            day.put("Thursday", nextDay);

            nextDay = read.soutString("Piątek: ");
            day.put("Friday", nextDay);

            nextDay = read.soutString("Sobota: ");
            day.put("Saturday", nextDay);

            nextDay = read.soutString("Niedziela: ");
            day.put("Sunday", nextDay);

            openingHours.add(day);
            object.put("Opening hours: ", openingHours);

            //set prices
            JSONArray prices = new JSONArray();
            JSONObject priceCase = new JSONObject();

            String nextPrice = read.soutString("Podaj cenę biletu dla dorosłych: ");
            priceCase.put("Adults price", nextPrice);

            nextPrice = read.soutString("Podaj cenę biletu dla seniorów: ");
            priceCase.put("Seniors price", nextPrice);

            nextPrice = read.soutString("Podaj cenę biletu dla dzieci: ");
            priceCase.put("kids price", nextPrice);

            prices.add(priceCase);
            object.put("Price list", prices);

            //set location data
            dataCollector1 = read.soutString("Podaj miasto: ");
            object.put("City", dataCollector1);

            dataCollector1 = read.soutString("Podaj dzielnicę: ");
            object.put("District", dataCollector1);

            dataCollector1 = read.soutString("Podaj ulicę: ");
            object.put("Street", dataCollector1);

            incorrectData = true;
            do {
                try {
                    int dataCollector2 = read.soutInt("Podaj numer budynku: ");
                    object.put("Building number", dataCollector2);
                    incorrectData = false;
                } catch (InputMismatchException exc) {
                    System.out.println("Proszę wprowadzić liczbę całkowitą");
                }
            }
            while(incorrectData == true);

            String dataCollector2 = read.soutString("Podaj numer mieszkania: ");
            object.put("Apartment", dataCollector2);

            //set GPS coordinates
            JSONArray gps = new JSONArray();
            JSONObject longLat = new JSONObject();

            incorrectData = true;
            do {
                try {
                    dataInput = new Scanner(System.in);
                    System.out.println("Wprowadź szerokość geograficzną (xx,yyyyyy): ");
                    double dataGps = dataInput.nextDouble();
                    longLat.put("Latitude", dataGps);
                    incorrectData = false;
                }
                catch(InputMismatchException exc) {
                    System.out.println("Niewłaściwy format danych, spróbuj ponownie");
                }
            }
            while(incorrectData == true);

            incorrectData = true;
            do {
                try {
                    dataInput = new Scanner(System.in);
                    System.out.println("Wprowadź długość geograficzną (xx,yyyyyy): ");
                    double dataGps = dataInput.nextDouble();
                    longLat.put("Longitude", dataGps);
                    incorrectData = false;
                }
                catch(InputMismatchException exc) {
                    System.out.println("Niewłaściwy format danych, spróbuj ponownie");
                }
            }
            while(incorrectData == true);

            gps.add(longLat);
            object.put("GPS coordinates", gps);
            array.add(object);

            try {
                File dataBasePlaces = new File(cfg.getPlacesDB());
                dataBasePlaces.createNewFile();

                FileWriter writeObject = new FileWriter(dataBasePlaces);
                writeObject.write(array.toJSONString());
                writeObject.close();

            }
            catch(java.io.IOException exc) {
                System.out.println("EOFException");
            }
        } catch (java.io.IOException | org.json.simple.parser.ParseException exc) {
            exc.printStackTrace();
        }
    }

//  CODE "JUST IN CASE"

    public void writeRoute() {

        JSONArray jsonArray = new ReadData().getJSONArray(cfg.getDefaultTR());
        JSONObject jsonObject = new JSONObject();

        // NEW #ID TYPING

        boolean correctInput = false;
        while (!correctInput) {
            try {
                System.out.print("\nWprowadź #ID nowej " + IDType.ROUTE + " (liczba całkowita dodatnia): ");
                long typedID = readLong();
                if (typedID >= 0){
                    boolean repeatedID = true;
                    for (Object object : jsonArray) {
                        long savedID = (Long) reader.getJSONObject(jsonArray, jsonArray.indexOf(object)).get("ID");
                        if (typedID == savedID) {
                            System.out.println("\nPodane #ID zostało już wcześniej przypisane. Spróbuj ponownie.");
                            repeatedID = true;
                            break;
                        }
                        else {
                            repeatedID = false;
                        }
                    }
                    if (!repeatedID) {
                        jsonObject.put("ID",typedID);
                        correctInput = true;
                    }
                }
                else {
                    System.out.println("\nProszę wprowadzić liczbę całkowitą DODATNIĄ.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\nWprowadzono nieprawidłowy format!");
            }
        }

        // ADDING PLACES TO ROUTE

        boolean addingDone = false;
        List<Long> places = new ArrayList<>();
        while (!addingDone) {
            PlaceOfInterest place = new PlaceOfInterest();
            new Menu().routeMenuAddingPoiOptions();
            int selection = menu.getSelection();
            switch (selection) {
                case 1:
                    place.printSimpleList();
                    int addingID = menu.idTyping(IDType.PLACEOFINTEREST);
                    try {
                        boolean repeatedID = false;
                        for (Long waitingID : places) {
                            if (addingID == waitingID) {
                                System.out.println("\nPodana atrakcja została już wcześniej dodana do listy.");
                                repeatedID = true;
                                break;
                            }
                        }
                        if (!repeatedID) {
                            place.printBasicInfo(addingID);
                            menu.areYouSure("\nCzy chcesz dodać daną atrakcję do listy?");
                            if (menu.getYesNoResult()) {
                                places.add((long) addingID);
                                System.out.println("\n" + txt.capitalize(place.getName(addingID)) + " dodano do listy!");
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }
                    catch (IndexOutOfBoundsException e) {
                        System.out.println("Brak atrakcji turystycznej z podanym #ID.");
                        break;
                    }
                case 2:
                    placesWaitingListStatus(places,false);
                    if (places.size() < 1) {
                        break;
                    }
                    else {
                        int deleteID = menu.idTyping(IDType.PLACEOFINTEREST);
                        int deleteIndex = -1;
                        boolean isPresent = false;
                        for (Long waitingID : places) {
                            if (deleteID == waitingID) {
                                deleteIndex = places.indexOf(waitingID);
                                isPresent = true;
                            }
                        }
                        if (isPresent) {
                            menu.areYouSure("\nCzy jesteś pewien, że chcesz usunąć \"" + txt.capitalize(place.getName(deleteID)) + "\" z listy?");
                            if (menu.getYesNoResult()) {
                                places.remove(deleteIndex);
                                System.out.println("\nUsunięto " + txt.capitalize(place.getName(deleteID)) + " z listy oczekującej.");
                                break;
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("\nLista nie posiada miejsca o takim numerze #ID.");
                            break;
                        }
                    }
                case 3:
                    placesWaitingListStatus(places,false);
                    break;
                case 0:
                    if (places.size() < 2){
                        System.out.println("\nNowa trasa turystyczna nie może zawierać mniej niż DWIE atrakcje!");
                        break;
                    }
                    else {
                        overallDistance = 0.0;
                        distances.removeAll(distances);
                        placesWaitingListStatus(places,true);
                        Long placesQuantity = (long) places.size();
                        System.out.println("Ilość atrakcji: " + placesQuantity);
                        System.out.println("Dystans całej trasy: " + txt.distanceKM(overallDistance));
                        System.out.println("Szacowany czas przejścia trasy: ???");
                        menu.areYouSure("Czy chcesz dodać aktualną listę do nowej trasy turystycznej?");
                        if (menu.getYesNoResult()) {
                            jsonObject.put("placesList", places);
                            jsonObject.put("placesQuantity", placesQuantity);
                            jsonObject.put("overallDistance", overallDistance);
                            jsonObject.put("distancesList", distances);
                            addingDone = true;
                            break;
                        }
                        else {
                            break;
                        }
                    }

            }
        }

        // ADDING NAME

        System.out.print("Podaj nazwę dla nowej trasy turystycznej: ");
        jsonObject.put("routeName", readString());

        jsonArray.add(jsonObject);
        WriteJSONArray(jsonArray,cfg.getDefaultTR());

        System.out.println("Trasa została dodana do pliku \"" + cfg.getDefaultTR() + "\".");
    }

    private void placesWaitingListStatus(List<Long> list, boolean isFinal){
        if (list.size() > 0) {
            PlaceOfInterest infoPlace = new PlaceOfInterest();
            Location infoLocation = new Location();
            List<Double> previousPosition = new ArrayList<>();
            Distance dist = new Distance();
            Double distance;
            txt.separator();
            System.out.println("Aktualny stan listy oczekującej:\n");
            for (Long waitingID : list) {
                Double currentLat = infoLocation.getCoordinate(Math.toIntExact(waitingID),"Latitude");
                Double currentLong = infoLocation.getCoordinate(Math.toIntExact(waitingID),"Longitude");
                try {
                    distance = dist.distanceCounter(previousPosition.get(0), previousPosition.get(1), currentLat, currentLong);
                }
                catch (IndexOutOfBoundsException e) {
                    distance = 0.0;
                }
                if (distance > 0.0) {
                    System.out.println(" --- Dystans: " + txt.distanceKM(distance) + " --- ");
                    if (isFinal) {
                        distances.add(distance);
                    }
                }
                System.out.println("#ID: " + infoPlace.getID(Math.toIntExact(waitingID)) + "   -   Nazwa: " + infoPlace.getName(Math.toIntExact(waitingID)));
                overallDistance += distance;
                previousPosition.removeAll(previousPosition);
                previousPosition.add(currentLat);
                previousPosition.add(currentLong);

            }
            txt.separator();
        }
        else {
            System.out.println("\nLista narazie jest PUSTA.");
        }
    }

//  CODE FROM Feature/JZAN-5

    public void WriteJSONArray(JSONArray array, String FILEPATH) {
        try {
            FileWriter updateArray = new FileWriter(new File(FILEPATH));
            updateArray.write(array.toJSONString());
            updateArray.close();
        }
        catch (java.io.IOException e) {
            System.out.println("\nBŁĄD AKTUALIZACJI PLIKU: \"" + FILEPATH + "\"!\n");
        }
    }

}
