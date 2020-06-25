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

    @Override
    public String toString() {
        long time = seconds();
        
        if (time < 60) {
            return timeAndUnit(time, "second");
        }
        time = time / 60;
        if (time < 60) {
            return timeAndUnit(time, "minute");
        }
        time = time / 60;
        if (time < 24) {
            return timeAndUnit(time, "hour");
        }
        time = time / 24;
        return timeAndUnit(time, "day");
    }

    public long seconds() {
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
