import java.util.ArrayList;

public class AverageRating {
    public Double avRating(ArrayList rating) {
        Double count = 0.00;
        for(int i = 0; i < rating.size(); i ++) {
            count += (Double) rating.get(i);
        }
        return count/rating.size();
    }
    public void addAvRating(ArrayList x, Double newRating) {
        x.add(newRating);
    }
}
