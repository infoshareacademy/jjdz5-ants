import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Rating {
    public enum grades {
        very_poor, poor, neutral, good, very_good
    }
    private Map<String, Integer> Grades = new HashMap<String, Integer>();
    private double avarageUserRating;
    //private Array<Rating> usersRatings;
    private double avarageCityRating;
}
