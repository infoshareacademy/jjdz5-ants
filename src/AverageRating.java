import org.json.simple.JSONArray;

public class AverageRating {

    private ReadData reader = new ReadData();
    private TextFormat txt = new TextFormat();
    private JSONArray array = new PlacesAccess().getCorrectPlacesArray();

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
