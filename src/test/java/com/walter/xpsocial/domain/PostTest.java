package com.walter.xpsocial.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostTest {
    LocalDateTime postingTime;
    
    public PostTest() {
    }
    
    @BeforeEach
    public void setUp() {
        postingTime = LocalDateTime.now();
    }

    @Test
    public void testZeroElapsedTime() {
        setUp();
        Post p = new Post("Hello", postingTime);
        
        Duration elapsed = p.timeFromPosting(() -> postingTime);
        
        assertEquals(Duration.of(0, SECONDS), elapsed);
    }
    
    @Test
    public void testElapsedTimeAfter30Seconds() {
        setUp();
        Post p = new Post("Hello", postingTime);
        
        Duration elapsed = p.timeFromPosting(() -> postingTime.plus(30, SECONDS));
        
        assertEquals(Duration.of(30, SECONDS), elapsed);
    }
    
    @Test
    public void testPostsAreComparableByPostingTime() {
        setUp();
        Post before = new Post("Middle", postingTime);
        Post after = new Post("Middle", postingTime.plus(10, SECONDS));
        
        assertEquals(+1, before.compareTo(after));
        assertEquals(-1, after.compareTo(before));
        assertEquals(0, before.compareTo(before));
    }
}
