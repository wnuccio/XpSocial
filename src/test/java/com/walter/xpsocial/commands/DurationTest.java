package com.walter.xpsocial.commands;

import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DurationTest {
    
     public static Duration duration(int value, ChronoUnit unit) {
        Duration d = new Duration(java.time.Duration.of(value, unit));
        return d;
    }
    
    @Test
    public void testDurationsToStringWithPluralValues() {
        assertEquals("59 seconds", duration(59, SECONDS).timeAndUnit());
        assertEquals("59 minutes", duration(59, MINUTES).timeAndUnit());
        assertEquals("23 hours", duration(23, HOURS).timeAndUnit());
        assertEquals("2 days", duration(2, DAYS).timeAndUnit());
    }
    
    @Test
    public void testDurationsToStringWithSingularValues() {
        assertEquals("1 second", duration(1, SECONDS).timeAndUnit());
        assertEquals("1 minute", duration(60, SECONDS).timeAndUnit());
        assertEquals("1 hour", duration(60, MINUTES).timeAndUnit());
        assertEquals("1 day", duration(24, HOURS).timeAndUnit());
    }
}
