package com.walter.xpsocial.commands;

import java.time.temporal.ChronoUnit;

public class Duration {

    private final java.time.Duration value;

    public Duration(java.time.Duration value) {
        this.value = value;
    }
    
    public static String elapsed(java.time.Duration d) {
        return new Duration(d).elapsedTimeString();
    }
    
    public String elapsedTimeString() {
        final String time_unit = timeAndUnit();
        return String.format("(%s ago)", time_unit);
    }

    String timeAndUnit() {
        long seconds = seconds();
        
        if (seconds < 60) {
            return timeAndUnit(seconds, "second");
        }
        long minutes = seconds / 60;
        if (minutes < 60) {
            return timeAndUnit(minutes, "minute");
        }
        long hours = minutes / 60;
        if (hours < 24) {
            return timeAndUnit(hours, "hour");
        }
        long days = hours / 24;
        return timeAndUnit(days, "day");
    }

    private long seconds() {
        return value.get(ChronoUnit.SECONDS);
    }

    private String timeAndUnit(long time, String unit) {
        String pluralizedUnit = isPlural(time) ? unit + "s" : unit;
        return time + " " + pluralizedUnit;
    }
    
    private boolean isPlural(long value) {
        return value > 1 || value == 0;
    }
}
