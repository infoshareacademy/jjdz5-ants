package com.infoshareacademy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalTime;
import java.lang.Math;
import java.util.*;

public class WriteData {

    private String readString(){
        return new Scanner(System.in).nextLine();
    }

    private Long readLong() {
        return new Scanner(System.in).nextLong();
    }

    private Double readDouble() {
        return new Scanner(System.in).nextDouble();
    }

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

            long dataID = array.size();
            object.put("ID", dataID);
            System.out.println("Dodawana atrakcja otrzyma numer #ID: " + dataID);

            List<Double> average = new ArrayList<>();
            object.put("averageRating", average);

            String dataCollector1 = read.soutString("\nWprowadź nazwę: ");
            object.put("Name", dataCollector1);

            boolean incorrectData = true;
            do {
                try {
                    read.sout("Wybierz typ z poniższej listy:");
                    read.enumSout();
                    dataInput = new Scanner(System.in);
                    int dataCollector2 = dataInput.nextInt();
                    String choice = read.typeChoice(dataCollector2);
                    object.put("type", choice);
                    incorrectData = false;
                } catch (InputMismatchException | NullPointerException exc) {
                    read.sout("Niewłaściwy wybór");
                }
            }
            while(incorrectData == true);

            dataCollector1 = read.soutString("Wprowadź opis obiektu: ");
            object.put("description", dataCollector1);

            //set opening hours
            read.sout("Wprowadź godziny otwarcia (format HH:MM-HH:MM lub 'zamknięte' lub zostaw puste pole jeśli obiekt jest otwarty całą dobę): ");
            JSONArray openingHours = new JSONArray();
            JSONObject day = new JSONObject();
            String nextDay;


            try {
                for (Map.Entry<String,String> weekDay: WeekDays.weekDaysDefinition.entrySet()){
                    do{
                        nextDay = read.soutString(weekDay.getValue()+":");
                        if (nextDay.toLowerCase().equals("zamknięte")){
                            day.put(weekDay.getKey(), nextDay);
                            incorrectData = false;
                        }
                        else if(nextDay.isEmpty()){
                            day.put(weekDay.getKey(), "00.00-23.59");
                            incorrectData = false;
                        }
                        else if(hoursValidator(nextDay))
                        {
                            day.put(weekDay.getKey(), nextDay);
                            incorrectData = false;
                        }
                        else{
                            System.out.println("Zły format danych. Spróbuj ponownie (HH:MM-HH:MM lub zamknięte lub ''):" +
                                    "");
                            incorrectData = true;
                        }
                    } while(incorrectData);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            openingHours.add(day);
            object.put("Opening hours: ", openingHours);

            //set prices
            JSONArray prices = new JSONArray();
            JSONObject priceCase = new JSONObject();

            System.out.println("\nPodaj cenę biletu dla dorosłych: ");
            Double nextPrice = priceReadAndVerify();
            priceCase.put("Adults price", nextPrice);

            System.out.println("\nPodaj cenę biletu dla seniorów: ");
            nextPrice = priceReadAndVerify();
            priceCase.put("Seniors price", nextPrice);

            System.out.println("\nPodaj cenę biletu dla dzieci: ");
            nextPrice = priceReadAndVerify();
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

            System.out.println("Podaj numer mieszkania (jeżeli nie występuje, proszę wpisać \"0\"): ");
            Long apartmentNumber = apartmentReadAndVerify();
            object.put(Location.APARTMENT, apartmentNumber);

            //set GPS coordinates
            JSONArray gps = new JSONArray();
            JSONObject longLat = new JSONObject();

            incorrectData = true;
            do {
                try {
                    dataInput = new Scanner(System.in);
                    System.out.println("Wprowadź szerokość geograficzną (xx.yyyyyy): ");
                    double dataGps = dataInput.nextDouble();
                    if (verifyLatitude(dataGps)) {
                        longLat.put("Latitude", dataGps);
                        incorrectData = false;
                    } else {
                        System.out.println("Niewłaściwy zakres spróbuj ponownie");
                        incorrectData = true;
                    }
                }
                catch(InputMismatchException exc) {
                    System.out.println("Niewłaściwy format danych, spróbuj ponownie");
                    incorrectData = true;
                }
            }while(incorrectData);

            do {
                try {
                    dataInput = new Scanner(System.in);
                    System.out.println("Wprowadź długość geograficzną (xx.yyyyyy): ");
                    double dataGPS = dataInput.nextDouble();
                    if (verifyLongitude(dataGPS)) {
                        longLat.put("Longitude", dataGPS);
                        incorrectData = false;
                    } else {
                        System.out.println("Niewłaściwy zakres spróbuj ponownie");
                        incorrectData = true;
                    }
                }
                catch(InputMismatchException exc) {
                    System.out.println("Niewłaściwy format danych, spróbuj ponownie");
                    incorrectData = true;
                }
            }while(incorrectData);

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

        long dataID = jsonArray.size();
        jsonObject.put("ID", dataID);
        System.out.println("\nDodawana trasa otrzyma numer #ID: " + dataID + "\n");

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
    public Boolean verifyLatitude(double coords) {
            return (Math.abs(coords) <= 90);
    }
    public Boolean verifyLongitude(double coords) {
	    return (Math.abs(coords) <=180);
    }

// OTHER CODE

    private Double priceReadAndVerify() {
        boolean incorrectInput = true;
        Double price = 0d;
        while (incorrectInput) {
            try {
                price = readDouble();
                if (price >= 0) {
                    incorrectInput = false;
                    return price;
                } else {
                    System.out.println("\nProszę podać liczbę większą lub równą 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nWprowadzono nieprawidłowy format!");
            }
        }
        return price;
    }
    public Boolean hoursValidator(String openingHours){
        String hoursPattern = "(([0-1]{0,1}[0-9])||(2[0-3]))\\:([0-5][0-9])";
        String fullPattern = "^(" + hoursPattern + "\\-" + hoursPattern + ")$";
        return (openingHours.matches(fullPattern) && openCloseTimesValidator(openingHours));
    }
    public Boolean openCloseTimesValidator(String openingHours){
        String[] times = openingHours.split("-");
        LocalTime openingTime = LocalTime.parse(times[0]);
        LocalTime closingTime = LocalTime.parse(times[1]);
        return openingTime.isBefore(closingTime);

    }

    private Long apartmentReadAndVerify() {
        boolean incorrectInput = true;
        Long apartmentNumber = 0L;
        while (incorrectInput) {
            try {
                apartmentNumber = readLong();
                if (apartmentNumber >= 0) {
                    incorrectInput = false;
                    return apartmentNumber;
                } else {
                    System.out.println("\nProszę podać liczbę większą lub równą 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nWprowadzono nieprawidłowy format!");
            }
        }
        return apartmentNumber;
    }

}
