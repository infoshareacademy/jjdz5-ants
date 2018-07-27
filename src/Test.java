import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
//        new WriteData().writeRoute();

        new TouristRoute().printAllRoutes();
        new TouristRoute().printSimpleList();
        new TouristRoute().printByID(0,true);
        new TouristRoute().printByID(0,false);
        new TouristRoute().printByID(1,true);
        new TouristRoute().printByID(1,false);
    }

}
