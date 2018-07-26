import com.sun.xml.internal.ws.api.model.ExceptionType;

import java.util.ArrayList;
import java.util.List;

public class TouristPath {
    private Integer touristPathId; //as primary key
    private String touristPathName; //as primary key
    private Integer objQuantity;
    private Integer pathDistance; //in km
    private Integer estimatedTimeOfTour; //in mins
    private String[] transportRequired; //list of recommended vehicles
    private List<PlaceOfInterest> listOfAttractions = new ArrayList<PlaceOfInterest>();

    public TouristPath(Integer touristPathId, String touristPathName) {
        this.touristPathId = touristPathId;
        this.touristPathName = touristPathName;
    }

    public String getTouristPathName() {
        return touristPathName;
    }

    public void addPlaceToTouristPath(PlaceOfInterest newPlace){
        try {
            this.listOfAttractions.add(newPlace);
        } catch (Exception e){
            System.out.println("Nie można dodać atrakcji");
        }
    }

    public void displayTouristPath(TouristPath touristPath){
        try {
            System.out.println("Trasa:");
            for(PlaceOfInterest placeOfInterest: listOfAttractions){
                System.out.format("\t%s\n", placeOfInterest.getName());
            }
        } catch (Exception e){
            System.out.println("Wybrana ścieżka jest pusta");
        }
    }

}
