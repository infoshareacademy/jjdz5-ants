import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args){
        double[] cords = {2,3};
        Location location = new Location(cords, "x", "y", "z");
        PlaceOfInterest place = new PlaceOfInterest(1, "c", location, 5);
        List<PlaceOfInterest> places = new ArrayList<>();
        places.add(place);
        places.add(place);
        places.add(place);
        places.add(place);
        TouristPath touristPath = new TouristPath();

    }
}
