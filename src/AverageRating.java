import org.json.simple.JSONArray;

import java.util.ArrayList;

public class AverageRating {

    private ReadData reader = new ReadData();
    private TextFormat txt = new TextFormat();
    private JSONArray array = new PlacesAccess().getCorrectPlacesArray();

//  CODE FROM Feature/JZAN-2

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

//  CODE FROM Feature/JZAN-5

//  PRINTING INFO.

    public void printRating(int index){
        System.out.println("Średnia ocena: " + txt.ratingValue(getAverageRating(index)) + "     Liczba ocen: " + getRatingsNumber(index));
    }

//  RATING.

    public Double getAverageRating(int index) {
        JSONArray subArray = reader.getSubJSONArray(array,index,"averageRating");
        Double ratings = 0.0;
        for (Object o : subArray){
            Double d = (Double) subArray.get(subArray.indexOf(o));
            ratings += d;
        }
        ratings /= subArray.size();
        if (ratings.isNaN()){ ratings = 0.0; }
        return ratings;
    }

    public Integer getRatingsNumber(int index) {
        return reader.getSubJSONArray(array,index,"averageRating").size();
    }

}
