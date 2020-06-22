/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walter.xpsocial.domain;

import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class PostTest {
    LocalDateTime postingTime;
    
    public PostTest() {
    }
    
    @BeforeEach
    public void setUp() {
        postingTime = LocalDateTime.now();
        Post p = new Post("Hello", postingTime);
    }

    @Test
    public void testZeroElapsedTime() {
        setUp();
        Post p = new Post("Hello", postingTime);
        
        Duration elapsed = p.elapsedTimeFromPosting(() -> postingTime);
        
        assertEquals(0, elapsed.seconds());
    }
    
    @Test
    public void testElapsedTimeAfter30Seconds() {
        setUp();
        Post p = new Post("Hello", postingTime);
        
        Duration elapsed = p.elapsedTimeFromPosting(
                () ->  postingTime.plus(30, SECONDS));
        
        assertEquals(30, elapsed.seconds());
    }
    
    @Test
    public void testPostsAreComparableByPostingTime() {
        setUp();
        Post before = new Post("Middle", postingTime);
        Post after = new Post("Middle", postingTime.plus(10, SECONDS));
        
        assertEquals(-1, before.compareTo(after));
        assertEquals(+1, after.compareTo(before));
        assertEquals(0, before.compareTo(before));
    }
}
