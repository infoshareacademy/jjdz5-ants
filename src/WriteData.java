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

    public void write() {

    Scanner dataInput = new Scanner(System.in);

    JSONObject object = new JSONObject();
    SoutWriter read = new SoutWriter();


        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader("dataBasePlaces.json"));

            //just for tests only:
            for (Object obj : array) {
                JSONObject tempObj = (JSONObject) obj;
                System.out.println(tempObj);
            }

            boolean flag = true;

            do {
                try {
                    for (int i = 0; i < 1; i++) {
                        read.sout("Input ID: ");
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
                        flag = false;
                    }
                } catch (InputMismatchException exc) {
                    read.sout("Wprowadziłeś niewłaściwy typ danych, spróbuj ponownie: ");
                    flag = true;
                }
            }
            while(flag == true);


            List<Double> average = new ArrayList<>();
            object.put("averageRating", average);



            String dataCollector1 = read.soutString("Wprowadź nazwę: ");
            object.put("Name", dataCollector1);

            flag = true;
            do {
                try {
                    read.sout("Wybierz typ z poniższej listy:");
                    read.enumSout();
                    dataInput = new Scanner(System.in);
                    int dataCollector2 = dataInput.nextInt();
                    PlaceOfInterestType choice = read.typeChoice(dataCollector2);
                    object.put("type", choice.name());
                    flag = false;
                } catch (InputMismatchException | NullPointerException exc) {
                    read.sout("Niewłaściwy wybór");
                }
            }
            while(flag == true);



            dataCollector1 = read.soutString("Input description: ");
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

        dataCollector1 = read.soutString("Podaj miato: ");
        object.put("City", dataCollector1);

        dataCollector1 = read.soutString("Podaj dzielnicę: ");
        object.put("District", dataCollector1);

        dataCollector1 = read.soutString("Podaj ulicę: ");
        object.put("Street", dataCollector1);

        flag = false;
        do {
            try {
                int dataCollector2 = read.soutInt("Podaj numer budynku: ");
                object.put("Building number", dataCollector2);
                flag = true;
            } catch (InputMismatchException exc) {
                System.out.println("Proszę wprowadzić liczbę całkowitą");
            }
        }
        while(flag == false);


        String dataCollector2 = read.soutString("Podaj numer mieszkania: ");
        object.put("Apartment", dataCollector2);




        //set GPS coordinates
        JSONArray gps = new JSONArray();
        JSONObject longLat = new JSONObject();

        do {
            try {
                dataInput = new Scanner(System.in);
                System.out.println("Wprowadź szerokość geograficzną (xx,yyyyyy): ");
                double dataGps = dataInput.nextDouble();
                longLat.put("Latitude", dataGps);
                flag = true;
            }
            catch(InputMismatchException exc) {
                System.out.println("Niewłaściwy format danych, spróbuj ponownie");
            }
        }
        while(flag == false);

            do {
                try {
                    dataInput = new Scanner(System.in);
                    System.out.println("Wprowadź długość geograficzną (xx,yyyyyy): ");
                double dataGps = dataInput.nextDouble();
                longLat.put("Longitude", dataGps);
                flag = false;
            }
            catch(InputMismatchException exc) {
                System.out.println("Niewłaściwy format danych, spróbuj ponownie");
            }
        }
        while(flag == true);

        gps.add(longLat);
        object.put("GPS coordinates", gps);
        array.add(object);

        try {
            File dataBasePlaces = new File("dataBasePlaces.json");
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

}
