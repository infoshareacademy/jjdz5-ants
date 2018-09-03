package com.infoshareacademy.webapp.model;

import com.infoshareacademy.PlaceOfInterestType;

public class PlaceMain {

    private final Integer id;
    private final Enum<PlaceOfInterestType> type;
    private final String name;
    private final String description;

    private final Integer defaultId = 0;
    private final Enum<PlaceOfInterestType> defaultType = PlaceOfInterestType.WRONG_TYPE;
    private final String defaultName = "Atrakcja turystyczna";
    private final String defaultDescription = "Jest to przykładowa atrakcja turystyczna.";

    public PlaceMain() {
        this.id = defaultId;
        this.type = defaultType;
        this.name = defaultName;
        this.description = defaultDescription;
    }

    public PlaceMain(Integer id, Enum<PlaceOfInterestType> type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public Enum<PlaceOfInterestType> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PlaceMain{" +
                "id=" + id +
                "\ntype=" + type +
                "\nname='" + name + '\'' +
                "\ndescription='" + description + '\'' +
                '}';
    }

}
