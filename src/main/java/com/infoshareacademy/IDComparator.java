package com.infoshareacademy;

import org.json.simple.JSONObject;

import java.util.Comparator;

public class IDComparator implements Comparator<JSONObject> {

    @Override
    public int compare(JSONObject firstObject, JSONObject secondObject) {

        if (((Long) firstObject.get("ID")) > ((Long) secondObject.get("ID"))) {
            return 1;
        }

        if (((Long) firstObject.get("ID")) < ((Long) secondObject.get("ID"))) {
            return -1;
        }

        return 0;

    }

}
