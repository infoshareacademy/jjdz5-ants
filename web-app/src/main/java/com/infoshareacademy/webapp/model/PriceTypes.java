package com.infoshareacademy.webapp.model;

public enum PriceTypes {

    NORMAL,
    REDUCED_FOR_CHILDREN,
    REDUCED_FOR_PENSIONERS;

    @Override
    public String toString() {
        final String normal = "normalny";
        final String reducedForChildren = "ulgowy (dzieci)";
        final String reducedForPensioners = "ulgowy (emeryci/renciści)";
        switch (this) {
            case NORMAL:
                return normal;
            case REDUCED_FOR_CHILDREN:
                return reducedForChildren;
            case REDUCED_FOR_PENSIONERS:
                return reducedForPensioners;
        }
        return String.valueOf(this);
    }
}
