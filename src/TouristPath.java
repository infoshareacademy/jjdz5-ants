import com.sun.xml.internal.ws.api.model.ExceptionType;

public class TouristPath {
    private Integer touristPathId; //as primary key
    private String touristPathName; //as primary key
    private Integer objQuantity;
    private Integer pathDistance; //in km
    private Integer estimatedTimeOfTour; //in mins
    private String[] transportRequired; //list of recommended vehicles
    private  PlaceOfInterest[] listOfAttractions;
    private String touristPathsJSON;

    //create new path
    private String createNewTouristPath (String touristPathName){
        //find last id
        Integer lastTouristPathId = 0;
        Integer nextTouristPathId = ++lastTouristPathId;
        //add path to json file (id+name)

    }

    private boolean checkIfPathNameExists(String touristPathName){
        //check if exists in touristPathsJSON
        return false;
    }

    private get

    public void addPlaceToPath(PlaceOfInterest placeOfInterest, String touristPathName){
        //check if path exists in json file touristPaths.json
        //if no, create new path

        if (checkIfPathNameExists()== false ) {
            createNewTouristPath(touristPatName);
        };
        //getTouristPath
        TouristPath touristPath = getTouristPath(touristPathName);
        touristPath.addPlace(placeOfInterest);
    }

    private PlaceOfInterest[] getListOfAttractions(String touristPatName) {
        //read touristPath from touristPathsJSON
        //translate to list of attractions
        return listOfAttractions;
    }

    public void displayTouristPath(String touristPathName){
        try {
            PlaceOfInterest[] listOfAttractions = getListOfAttractions(touristPathName);
        } catch (Exception e){
            System.out.println("Nie istnieje taka ścieżka");
        }
        System.out.format("Trasa: %s\n", touristPathName);
        //placeOfInterests
    }

    public void displayAllTouristPaths(){
        try{
            //read ouristPathsJSON line by line and add to array touristPathsNames[]


        } catch (Exception e) {
            System.out.println("Nie zdefiniowano żadnych tras");
        }
        for(String pathName: touristPathNames){
            System.out.println(pathName);
        }
    }
}
