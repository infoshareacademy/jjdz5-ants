package com.infoshareacademy.webapp.mechanics;

import org.json.simple.JSONArray;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RoutePullFromJson {

    @Inject
    private AccessJson accessJson;

    private JSONArray routesArray;
    private Integer pullIndex;

    public void setRoutesArray(JSONArray routesArray) {
        this.routesArray = routesArray;
    }

    public void setPullIndex(Integer pullIndex) {
        this.pullIndex = pullIndex;
    }
}
