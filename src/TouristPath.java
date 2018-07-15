import com.sun.xml.internal.ws.api.model.ExceptionType;

import java.util.List;

public class TouristPath {
    private Integer touristPathId; //as primary key
    private String touristPathName; //as primary key
    private Integer objQuantity;
    private Integer pathDistance; //in km
    private Integer estimatedTimeOfTour; //in mins
    private String[] transportRequired; //list of recommended vehicles
    private  PlaceOfInterest[] listOfAttractions;
    private String touristPathsJSON;


    public void displayTouristPath(List<TouristPath> touristPath){
        try {
            System.out.println("Trasy:");
            for(PlaceOfInterest placeOfInterest: listOfAttractions){
                System.out.format("Trasa: %s\n", placeOfInterest.getName());
            }
        } catch (Exception e){
            System.out.println("Wybrana ścieżka jest pusta");
        }
    }

    public void displayAllTouristPaths(List<TouristPath> touristPathsNames){
        try{
            for(TouristPath touristPathName: touristPathsNames){
                System.out.format("%s", touristPathName.touristPathName);
            }
        } catch (Exception e) {
            System.out.println("Nie zdefiniowano żadnych tras");
        }

    }
}
