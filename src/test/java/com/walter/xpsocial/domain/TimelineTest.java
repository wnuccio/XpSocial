/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walter.xpsocial.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class TimelineTest {
    Timeline timeline;
        
    @BeforeEach
    public void setUp() {
        timeline = new Timeline();
    }

    @Test
    public void testReturnNoPostForANewTimeline() {
        setUp();
        assertTrue(timeline.allPosts().isEmpty());
        fail();
    }

    @Test
    public void testReturnPostedMessageInReverseOrder() {
        setUp();
        timeline.addToHead("I love the weather today");
        timeline.addToHead("There is a beautiful sun");

        Post headPost = timeline.allPosts().get(0);
        Post secondPost = timeline.allPosts().get(1);

        assertEquals("There is a beautiful sun", headPost.message());
        assertEquals("I love the weather today", secondPost.message());
    }
}
