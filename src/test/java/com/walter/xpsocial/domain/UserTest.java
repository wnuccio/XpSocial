package com.walter.xpsocial.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testReturnsPostsInReverseOrder() {
        setUp();
        alice.post(new Post("Ciao", LocalDateTime.now()));
        alice.post(new Post("Hello", LocalDateTime.now()));

        assertEquals("Hello", alice.allPosts().get(0).message());
        assertEquals("Ciao", alice.allPosts().get(1).message());
    }
    
    @Test
    public void testNewUserReturnsNoFollowed() {
        setUp();

        assertTrue(charlie.followed().isEmpty());
    }

    @Test
    public void testUserReturnsFollowedInReverseOrder() {
        setUp();
        User bob = new User("Bob");
        charlie.follow(alice);
        charlie.follow(bob);
        final List<User> followed = charlie.followed();

        assertEquals(followed.get(0), bob);
        assertEquals(followed.get(1), alice);
    }

    @Test
    public void testUserCannotFollowHimself() {
        setUp();
        charlie.follow(charlie);
        
        assertTrue(charlie.followed().isEmpty());
    }
    
    @Test
    public void testFollowingIsIdempotent() {
        setUp();
        charlie.follow(alice);
        charlie.follow(alice);
        
        assertEquals(1, charlie.followed().size());
        assertTrue(charlie.followed().contains(alice));
    }

    @Test 
    public void testCannotFollowingTwoUsersWithSameName() {
        setUp();
        User twin1 = new User("Don");
        User twin2 = new User("Don");
        
        charlie.follow(twin1);
        charlie.follow(twin2);
        
        assertEquals(1, charlie.followed().size());
    }
 

    
}
