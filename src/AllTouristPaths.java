import java.util.ArrayList;
import java.util.List;

public class AllTouristPaths {

    private List<TouristPath> allTouristPaths= new ArrayList<TouristPath>();

    public void addTouristPath (TouristPath newTouristPath){
        this.allTouristPaths.add(newTouristPath);
    }

    public void displayAllTouristPathsByName(){
        try{
            System.out.println("Dostępne trasy:");
            for(TouristPath touristPathName: this.allTouristPaths){
                System.out.format("\t%s\n", touristPathName.getTouristPathName());
            }
        } catch (Exception e) {
            System.out.println("Nie zdefiniowano żadnych tras");
        }
    }

}
