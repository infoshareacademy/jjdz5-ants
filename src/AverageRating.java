import java.util.ArrayList;

public class AverageRating {
    public Double avRating(ArrayList x) {
        Double count = 0.00;
        for(int i = 0; i < x.size(); i ++) {
            count += (Double) x.get(i);
        }
        return count/x.size();
    }
    public void addAvRating(ArrayList x, Double y) {
        x.add(y);
    }
}
