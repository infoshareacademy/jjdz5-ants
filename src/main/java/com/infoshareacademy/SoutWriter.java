package com.infoshareacademy;

import java.util.Scanner;

public class SoutWriter {
    public void sout(String x) {
        System.out.println(x);
    }
    public void enumSout() {
        System.out.println("1.  Zamek\n2.  Kośćiół\n3.  Pomnik\n4.  Muzemu\n5.  Park");
    }

        public PlaceOfInterestType typeChoice (int type){
        switch (type) {
            case 1:
                return PlaceOfInterestType.ZAMEK;
            case 2:
                return PlaceOfInterestType.KOŚCIÓŁ;
            case 3:
                return PlaceOfInterestType.POMNIK;
            case 4:
                return PlaceOfInterestType.MUZEUM;
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


