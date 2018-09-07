package com.infoshareacademy.webapp.model;

import com.infoshareacademy.PlaceOfInterestType;

public class PlaceMain {

    private final String name;
    private final Enum<PlaceOfInterestType> type;
    private final String description;

    private Boolean isDefault;

    private final String defaultName = "Atrakcja turystyczna";
    private final Enum<PlaceOfInterestType> defaultType = PlaceOfInterestType.WRONG_TYPE;
    private final String defaultDescription = "Jest to przykładowa atrakcja turystyczna.";

    public PlaceMain() {
        this.name = defaultName;
        this.type = defaultType;
        this.description = defaultDescription;
        this.isDefault = true;
    }

    public PlaceMain(String name, Enum<PlaceOfInterestType> type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.isDefault = false;
    }

    public String getName() {
        return name;
    }

    public Enum<PlaceOfInterestType> getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDefaultStatus() {
        return isDefault;
    }

    @Override
    public String toString() {
        return "PlaceMain{" +
                "\nname='" + name + '\'' +
                "\ntype=" + type +
                "\ndescription='" + description + '\'' +
                '}';
    }

}
