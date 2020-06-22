/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walter.xpsocial.domain;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class DurationTest {
    
    @Test
    public void testDurationsToStringWithPluralValues() {
        assertEquals("59 seconds", Duration.of(59, SECONDS).toString());
        assertEquals("59 minutes", Duration.of(59, MINUTES).toString());
        assertEquals("23 hours", Duration.of(23, HOURS).toString());
        assertEquals("2 days", Duration.of(2, DAYS).toString());
    }
    
    @Test
    public void testDurationsToStringWithSingularValues() {
        assertEquals("1 minute", Duration.of(60, SECONDS).toString());
        assertEquals("1 hour", Duration.of(60, MINUTES).toString());
        assertEquals("1 day", Duration.of(24, HOURS).toString());
    }
}
