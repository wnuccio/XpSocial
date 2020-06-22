/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Post;
import com.walter.xpsocial.domain.Social;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class PostingTest {

    Social social;

    @BeforeEach
    public void setUp() {
        social = new Social();
    }

    @Test
    public void testPostingSavesThePostAndReturnsEmptyOutput() {
        setUp();
        String output = new Posting("Alice", "I love the weather today").execute(social);

        final Post post = social.allPosts("Alice").get(0);
        assertEquals("I love the weather today", post.message());
        assertTrue(output.isEmpty());
    }
}
