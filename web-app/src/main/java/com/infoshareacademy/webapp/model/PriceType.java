package com.infoshareacademy.webapp.model;

public enum PriceType {

    NORMAL,
    REDUCED;

    private final String normal = "normalny";
    private final String reduced = "ulgowy";

    @Override
    public String toString() {
        switch (this) {
            case NORMAL:
                return normal;
            case REDUCED:
                return reduced;
        }
        return String.valueOf(this);
    }
}
