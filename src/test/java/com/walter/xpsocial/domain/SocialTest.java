package com.walter.xpsocial.domain;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testReturnLazyInitializedUserOnFirstReference() {
        setUp();
        assertNotNull(social.user("Alice"));
    }

    @Test
    public void testFindNoUserOnFirstReference() {
        setUp();
        assertTrue(social.findUser("Alice").isEmpty());
    }

    @Test
    public void testReturnNoPostBeforeAnyPosting() {
        setUp();
        assertTrue(social.allPosts("Alice").isEmpty());
    }

    @Test
    public void testReturnPostWithUsername() {
        setUp();
        final Post post = post("I love the weather today");
        assertTrue(post.username().isEmpty());
        
        social.post("Alice", post);

        List<Post> posts = social.allPosts("Alice");

        assertEquals(1, posts.size());
        assertEquals("Alice", posts.get(0).username());
    }
    
    @Test
    public void testReturnPostsOfUserInReversePostingOrder() {
        setUp();
        social.post("Alice", post("I love the weather today"));
        social.post("Alice", post("There is a beautiful sun"));

        List<Post> posts = social.allPosts("Alice");

        assertEquals("There is a beautiful sun", posts.get(0).message());
        assertEquals("I love the weather today", posts.get(1).message());
    }
    
    @Test
    public void testReturnOnlyPostsOfUserBeforeAnyFollowing() {
        setUp();
        social.post("Alice", post("I love the weather today"));
        social.post("Charlie", post("I'm in New York today!"));

        List<Post> posts = social.allPostsIncludingFollowed("Charlie");

        assertEquals(1, posts.size());
        assertTrue(posts.containsAll(social.allPosts("Charlie")));
    }
    
    @Test
    public void testReturnAllPostsIncludingFollowedAfterFollowing() {
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
