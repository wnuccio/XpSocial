package com.walter.xpsocial.domain;

import java.time.temporal.ChronoUnit;

public class Duration {

    private final java.time.Duration value;

    public Duration(java.time.Duration value) {
        this.value = value;
    }
    
    public static Duration of(int value, ChronoUnit unit) {
        Duration d = new Duration(java.time.Duration.of(value, unit));
        return d;
    }

    private String timeAndUnit(long time, String unit) {
        return time + " " + unit;
    }
    
    @Override
    public String toString() {
        long time = seconds();
        
        if (time < 60) {
            String unit = time > 1 ? "seconds" : "second";
            return timeAndUnit(time, unit);
        }

        time = time / 60;
        if (time < 60) {
            String unit = time > 1 ? "minutes" : "minute";
            return timeAndUnit(time, unit);
        }

        time = time / 60;
        if (time < 24) {
            String unit = time > 1 ? "hours" : "hour";
            return timeAndUnit(time, unit);
        }

        time = time / 24;
        String unit = time > 1 ? "days" : "day";
        return timeAndUnit(time, unit);
    }

    public long seconds() {
        return value.get(ChronoUnit.SECONDS);
    }

}
