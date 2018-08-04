package com.infoshareacademy;

import java.util.Scanner;

public class SoutWriter {
    public void sout(String x) {
        System.out.println(x);
    }
    public void enumSout() {
        System.out.println("1.  Zamek\n2.  Kośćiół\n3.  Pomnik\n4.  Muzemu\n5.  Park");
    }

        public String typeChoice (int type){
        switch (type) {
            case 1:
                return PlaceOfInterestType.CASTLE.toString();
            case 2:
                return PlaceOfInterestType.CHURCH.toString();
            case 3:
                return PlaceOfInterestType.MONUMENT.toString();
            case 4:
                return PlaceOfInterestType.MUSEUM.toString();
            case 5:
                return PlaceOfInterestType.PARK.toString();
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


