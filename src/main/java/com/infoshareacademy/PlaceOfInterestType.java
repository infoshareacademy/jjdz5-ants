package com.infoshareacademy;

public enum PlaceOfInterestType {

    ZAMEK,
    KOŚCIÓŁ,
    MUZEUM,
    PARK,
    POMNIK,
    BŁĘDNY_TYP;

    @Override
    public String toString(){
        switch (this){
            case ZAMEK:
                return "Zamek";
            case KOŚCIÓŁ:
                return "Kościół";
            case MUZEUM:
                return "Muzeum";
            case PARK:
                return "Park";
            case POMNIK:
                return "Pomnik";
            case BŁĘDNY_TYP:
                return "Inny";
        }
        return null;
    }

}
