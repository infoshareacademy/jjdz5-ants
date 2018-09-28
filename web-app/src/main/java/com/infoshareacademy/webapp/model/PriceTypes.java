package com.infoshareacademy.webapp.model;

public enum PriceTypes {

    NORMAL,
    REDUCED_FOR_CHILDREN,
    REDUCED_FOR_PENSIONERS;

    @Override
    public String toString() {
        final String normal = "Normalny";
        final String reducedForChildren = "Ulgowy (dzieci)";
        final String reducedForPensioners = "Ulgowy (seniorzy)";
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
