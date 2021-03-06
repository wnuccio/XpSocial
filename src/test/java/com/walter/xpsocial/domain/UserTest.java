package com.walter.xpsocial.domain;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    User charlie;
    User alice;

    @BeforeEach
    public void setUp() {
        charlie = new User("Charlie");
        alice = new User("Alice");
    }

    @Test
    public void testNewUserReturnsNoPost() {
        setUp();
        User u = new User("Alice");

        assertTrue(u.allPosts().isEmpty());
    }
    
    @Test
    public void testReturnPostsInReverseOrder() {
        setUp();
        alice.addToTimeline(new Post("Ciao", LocalDateTime.now()));
        alice.addToTimeline(new Post("Hello", LocalDateTime.now()));

        assertEquals("Hello", alice.allPosts().get(0).message());
        assertEquals("Ciao", alice.allPosts().get(1).message());
    }
    
    @Test
    public void testNewUserReturnsNoFollowed() {
        setUp();

        assertTrue(charlie.followed().isEmpty());
    }

    @Test
    public void testReturnFollowedInReverseOrder() {
        setUp();
        User bob = new User("Bob");
        charlie.addFollowed(alice);
        charlie.addFollowed(bob);
        final List<User> followed = charlie.followed();

        assertEquals(followed.get(0), bob);
        assertEquals(followed.get(1), alice);
    }

    @Test
    public void testUserCannotFollowHimself() {
        setUp();
        charlie.addFollowed(charlie);
        
        assertTrue(charlie.followed().isEmpty());
    }
    
    @Test
    public void testFollowingIsIdempotent() {
        setUp();
        charlie.addFollowed(alice);
        charlie.addFollowed(alice);
        
        assertEquals(1, charlie.followed().size());
        assertTrue(charlie.followed().contains(alice));
    }

    @Test 
    public void testCannotFollowingTwoUsersWithSameName() {
        setUp();
        User twin1 = new User("Don");
        User twin2 = new User("Don");
        
        charlie.addFollowed(twin1);
        charlie.addFollowed(twin2);
        
        assertEquals(1, charlie.followed().size());
    }
 

    
}
