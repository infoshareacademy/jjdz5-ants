package com.infoshareacademy;

public enum IDType {

    PLACEOFINTEREST,
    ROUTE;

    @Override
    public String toString(){
        switch (this){
            case PLACEOFINTEREST:
                return "atrakcji turystycznej";
            case ROUTE:
                return "trasy turystycznej";
        }
        return null;
    }

}
