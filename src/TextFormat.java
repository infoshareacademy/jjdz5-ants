import java.text.DecimalFormat;

public class TextFormat {

    public String pricePLN(Double d){
        return new DecimalFormat("0.00 PLN").format(d);
    }

    public String gpsValue(Double d){
        return new DecimalFormat("0.0000").format(d);
    }

    public String ratingValue(Double d){ return new DecimalFormat("0.00").format(d); }

    public String capitalize(String s){
            String trimmedS = s.trim();
            try {
                return trimmedS.substring(0, 1).toUpperCase() + trimmedS.substring(1);
            }
            catch (StringIndexOutOfBoundsException e) { return ""; }

    }

    public void separator(){
        System.out.println("------------------------------------------------------");
    }

}
