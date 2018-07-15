import java.util.Scanner;

public class SoutWriter {

    public void sout(String x) {
        System.out.println(x);
    }


    public void enumSout() {
        System.out.println("1.  CASTLE\n2.  CHURCH\n3.  MONUMENT\n4.  MUSEUM\n5.  PARK");
    }





        public PlaceOfInterestType typeChoice ( int type){
        switch (type) {
            case 1:
                return PlaceOfInterestType.CASTLE;
            case 2:
                return PlaceOfInterestType.CHURCH;
            case 3:
                return PlaceOfInterestType.MONUMENT;
            case 4:
                return PlaceOfInterestType.MUSEUM;
            case 5:
                return PlaceOfInterestType.PARK;
            default:

        }

        return null;
    }




        public String soutString(String x) {
            Scanner scan = new Scanner(System.in);
            System.out.println(x);
            return scan.nextLine();
        }

    public int soutInt(String x) {
        Scanner scan = new Scanner(System.in);
        System.out.println(x);
        return scan.nextInt();
    }

    }


