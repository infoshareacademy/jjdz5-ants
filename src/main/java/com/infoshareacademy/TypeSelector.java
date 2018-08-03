package com.infoshareacademy;
//

public class TypeSelector {

    public String getTypes() {
        String types = "";
        int index = 1;
        for (PlaceOfInterestType poiType : PlaceOfInterestType.values()){
            types += "[" + index + "] - " + poiType.toString() + "\n";
            index++;
        }
        return types;
    }

    public PlaceOfInterestType typeChoice (int type) {
        switch (type) {
            case 1:
                return PlaceOfInterestType.ZAMEK;
            case 2:
                return PlaceOfInterestType.KOŚCIÓŁ;
            case 3:
                return PlaceOfInterestType.MUZEUM;
            case 4:
                return PlaceOfInterestType.PARK;
            case 5:
                return PlaceOfInterestType.POMNIK;
            case 6:
                return PlaceOfInterestType.BŁĘDNY_TYP;
        }
        return null;
    }

}
