import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class WriteData {

    public void Write(String obname) {

    Scanner dataInput = new Scanner(System.in);

    JSONObject object = new JSONObject();
    JSONArray array = new JSONArray();
    object.put("name", obname);


        System.out.println("Input ID: ");
        String dataCollector = dataInput.nextLine();
        object.put("ID", dataCollector);

        System.out.println("Input type: ");
        dataCollector = dataInput.nextLine();
        object.put("type", dataCollector);

        System.out.println("Input description: ");
        dataCollector = dataInput.nextLine();
        object.put("description", dataCollector);

        //set opening hours
        System.out.println("Input opening hours: ");
        JSONArray openingHours = new JSONArray();
        JSONObject day = new JSONObject();
        String nextDay;

        System.out.println("Monday: ");
        nextDay = dataInput.nextLine();
        day.put("Monday", nextDay);

        System.out.println("Tuesday: ");
        nextDay = dataInput.nextLine();
        day.put("Tuesday", nextDay);

        System.out.println("Wednesday: ");
        nextDay = dataInput.nextLine();
        day.put("Wednesday", nextDay);

        System.out.println("Thursday: ");
        nextDay = dataInput.nextLine();
        day.put("Thursday", nextDay);

        System.out.println("Friday: ");
        nextDay = dataInput.nextLine();
        day.put("Friday", nextDay);

        System.out.println("Saturday: ");
        nextDay = dataInput.nextLine();
        day.put("Saturday", nextDay);

        System.out.println("Sunday: ");
        nextDay = dataInput.nextLine();
        day.put("Sunday", nextDay);


        openingHours.add(day);
        object.put("Opening hours: ", openingHours);


        //set prices
        JSONArray prices = new JSONArray();
        JSONObject priceCase = new JSONObject();

        System.out.println("Input adults price: ");
        String nextPrice = dataInput.nextLine();
        priceCase.put("Adults price", nextPrice);

        System.out.println("Input seniors price: ");
        nextPrice = dataInput.nextLine();
        priceCase.put("Seniors price", nextPrice);

        System.out.println("Input kids price: ");
        nextPrice = dataInput.nextLine();
        priceCase.put("kids price", nextPrice);

        prices.add(priceCase);
        object.put("Price list", prices);


        //set location data
        System.out.println("Input city: ");
        dataCollector = dataInput.nextLine();
        object.put("City", dataCollector);

        System.out.println("Input district: ");
        dataCollector = dataInput.nextLine();
        object.put("District", dataCollector);

        System.out.println("Input street: ");
        dataCollector = dataInput.nextLine();
        object.put("Street", dataCollector);

        System.out.println("Input building number: ");
        dataCollector = dataInput.nextLine();
        object.put("Building number", dataCollector);

        System.out.println("Input apartment: ");
        dataCollector = dataInput.nextLine();
        object.put("Apartment", dataCollector);


        //set GPS coordinates
        JSONArray gps = new JSONArray();
        JSONObject longLat = new JSONObject();

        System.out.println("Input latitude: ");
        double dataGps = dataInput.nextDouble();
        longLat.put("Latitude", dataGps);

        System.out.println("Input longitude: ");
        dataGps = dataInput.nextDouble();
        longLat.put("Longitude", dataGps);

        gps.add(longLat);
        object.put("GPS coordinates", gps);

        try {
            File dataBasePlaces = new File("dataBasePlaces.json");
            dataBasePlaces.createNewFile();

            FileWriter writeObject = new FileWriter(dataBasePlaces);
            writeObject.write(object.toJSONString());
            writeObject.close();


        }
        catch(java.io.IOException exc) {
            System.out.println("EOFException");
        }
        

    }

}
