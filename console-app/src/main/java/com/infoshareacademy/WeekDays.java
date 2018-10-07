package com.infoshareacademy;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeekDays {
    public static final Map<String, String> weekDaysDefinition;
    static {
        Map<String, String> tempWeekDays = new LinkedHashMap<String,String>()
        {
            {
                put("Monday", "Poniedziałek");
                put("Tuesday", "Wtorek");
                put("Wednesday", "Środa");
                put("Thursday", "Czwartek");
                put("Friday", "Piątek");
                put("Saturday", "Sobota");
                put("Sunday", "Niedziela");
            }};
        weekDaysDefinition = Collections.unmodifiableMap(tempWeekDays);
    }
}
