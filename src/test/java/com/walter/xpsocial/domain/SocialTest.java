package com.walter.xpsocial.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SocialTest {

    Social social;
    
    private static Post post(String text) {
        return new Post(text, LocalDateTime.now());
    }

    @BeforeEach
    public void setUp() {
        social = new Social();
    }

    @Test
    public void testReturnsLazyInitializedUserOnFirstReference() {
        setUp();
        assertNotNull(social.user("Alice"));
    }

    @Test
    public void testReturnsNoPostBeforeAnyPosting() {
        setUp();
        assertTrue(social.allPosts("Alice").isEmpty());
    }

    @Test
    public void testReturnsPostWithUsername() {
        setUp();
        final Post post = post("I love the weather today");
        assertTrue(post.username().isEmpty());
        
        social.post("Alice", post);

        List<Post> posts = social.allPosts("Alice");

        assertEquals(1, posts.size());
        assertEquals("Alice", posts.get(0).username());
    }
    
    @Test
    public void testReturnsPostsOfUserInReversePostingOrder() {
        setUp();
        social.post("Alice", post("I love the weather today"));
        social.post("Alice", post("There is a beautiful sun"));

        List<Post> posts = social.allPosts("Alice");

        assertEquals("There is a beautiful sun", posts.get(0).message());
        assertEquals("I love the weather today", posts.get(1).message());
    }
    
    @Test
    public void testReturnsOnlyUserPostsBeforeAnyFollowing() {
        setUp();
        social.post("Alice", post("I love the weather today"));
        social.post("Charlie", post("I'm in New York today!"));

        List<Post> posts = social.allPostsIncludingFollowed("Charlie");

        assertEquals(1, posts.size());
        assertTrue(posts.containsAll(social.allPosts("Charlie")));
    }
    
    @Test
    public void testReturnsAllPostsIncludingFollowedAfterFollowing() {
        setUp();
        social.post("Alice", post("I love the weather today"));
        social.post("Charlie", post("I'm in New York today!"));
        social.follows("Charlie", "Alice");

        List<Post> posts = social.allPostsIncludingFollowed("Charlie");

        assertEquals(2, posts.size());
        assertTrue(posts.containsAll(social.allPosts("Alice")));
        assertTrue(posts.containsAll(social.allPosts("Charlie")));
    }

}
