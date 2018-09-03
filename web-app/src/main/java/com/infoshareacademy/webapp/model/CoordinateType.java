package com.infoshareacademy.webapp.model;

public enum CoordinateType {

    LATITUDE,
    LONGITUDE;

    private final String latitude = "szerokość";
    private final String longitude = "długość";

    @Override
    public String toString() {
        switch (this) {
            case LATITUDE:
                return latitude;
            case LONGITUDE:
                return longitude;
        }
        return String.valueOf(this);
    }
}
