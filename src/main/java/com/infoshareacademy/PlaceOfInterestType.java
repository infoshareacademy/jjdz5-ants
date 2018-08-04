package com.infoshareacademy;

public enum PlaceOfInterestType {

    CASTLE,
    CHURCH,
    MUSEUM,
    PARK,
    MONUMENT,
    WRONG_TYPE;

    @Override
    public String toString(){
        switch (this){
            case CASTLE:
                return "Zamek";
            case CHURCH:
                return "Kościół";
            case MUSEUM:
                return "Muzeum";
            case PARK:
                return "Park";
            case MONUMENT:
                return "Pomnik";
            case WRONG_TYPE:
                return "Inny";
        }
        return null;
    }

}
