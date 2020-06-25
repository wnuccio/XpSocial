package com.walter.xpsocial.commands;

import com.walter.xpsocial.commands.Wall;
import com.walter.xpsocial.domain.ClockMock;
import com.walter.xpsocial.domain.Post;
import com.walter.xpsocial.domain.Social;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WallTest {

    Social social;

    @BeforeEach
    public void setUp() {
        social = new Social();
    }

    @Test
    public void testWallReturnsMessagesOfUserWithNoFollowed() {
        setUp();
        LocalDateTime now = LocalDateTime.now(); 
        
        social.post("Alice", new Post("I love the weather today", now));
        social.post("Alice", new Post("There is a beautiful sun", now.plus(1, SECONDS)));
        social.post("Alice", new Post("Bye bye...", now.plus(2, SECONDS)));

        ClockMock wallTimes = new ClockMock().doReturn(now.plus(3, SECONDS));        
        String output = new Wall("Alice").clock(wallTimes).execute(social);
        
        assertEquals(
                "Alice - Bye bye... (1 second ago)\n" +
                "Alice - There is a beautiful sun (2 seconds ago)\n" +
                "Alice - I love the weather today (3 seconds ago)"
                , output);
    }

    @Test
    public void testWallReturnsMessagesOfUserAndHerFollowedOrderedByTime() {
        setUp();
        LocalDateTime now = LocalDateTime.now();
        
        social.post("Ali", new Post("Hi all!", now));
        social.post("Bob", new Post("Hi Alice", now.plus(1, SECONDS)));
        social.post("Cha", new Post("Help me!", now.plus(2, SECONDS)));
        social.post("---", new Post("......", now.plus(3, SECONDS)));
        social.post("---", new Post("......", now.plus(4, SECONDS)));
        social.post("Bob", new Post("Good game", now.plus(5, SECONDS)));
        social.post("---", new Post("......", now.plus(6, SECONDS)));
        social.post("Bob", new Post("Damn!", now.plus(7, SECONDS)));
        
        social.follows("Cha", "Ali");
        social.follows("Cha", "Bob");

        ClockMock clock = new ClockMock().doReturn(now.plus(8, SECONDS));        
        String output = new Wall("Cha").clock(clock).execute(social);

        assertEquals(
                "Bob - Damn! (1 second ago)\n" +
                "Bob - Good game (3 seconds ago)\n" +
                "Cha - Help me! (6 seconds ago)\n" +
                "Bob - Hi Alice (7 seconds ago)\n" +
                "Ali - Hi all! (8 seconds ago)"
                , output);
    }
}
