import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteData {

    public void write() {

    Scanner dataInput = new Scanner(System.in);

    JSONObject object = new JSONObject();
    //JSONArray array = new JSONArray();

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader("dataBasePlaces.json"));


            for(Object obj : array) {
                JSONObject tempObj = (JSONObject) obj;
                Long id = (Long)((JSONObject) obj).get("ID");
                System.out.println(tempObj);
            }

            for(int i = 0; i < 1;i++ ) {
                System.out.println("Input ID: ");
                 Long dataId = dataInput.nextLong();
                for (Object obj : array) {
                    Long id = (Long) ((JSONObject) obj).get("ID");
                    if(dataId == id) {
                        System.out.println("ID reserved, try again");
                        i--;
                        continue;
                    }
                }


                object.put("ID", dataId);
            }

            List<Double> average = new ArrayList<>();
            object.put("averageRating", average);


            dataInput = new Scanner(System.in);
            System.out.println("Input name: ");
            String dataCollector1 = dataInput.nextLine();
            object.put("Name", dataCollector1);

            System.out.println("Input type: ");
            String dataCollector2 = dataInput.nextLine();
            object.put("type", dataCollector2);

            System.out.println("Input description: ");
            String dataCollector3 = dataInput.nextLine();
            object.put("description", dataCollector3);

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
        String dataCollector4 = dataInput.nextLine();
        object.put("City", dataCollector4);

        System.out.println("Input district: ");
        String dataCollector5 = dataInput.nextLine();
        object.put("District", dataCollector5);

        System.out.println("Input street: ");
        String dataCollector6 = dataInput.nextLine();
        object.put("Street", dataCollector6);

        System.out.println("Input building number: ");
        String dataCollector7 = dataInput.nextLine();
        object.put("Building number", dataCollector7);

        System.out.println("Input apartment: ");
        String dataCollector8 = dataInput.nextLine();
        object.put("Apartment", dataCollector8);


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
