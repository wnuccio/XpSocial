package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.ClockMock;
import com.walter.xpsocial.domain.Post;
import com.walter.xpsocial.domain.Social;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReadingTest {

    Social social;

    @BeforeEach
    public void setUp() {
        social = new Social();
    }

    @Test
    public void testReadingReturnsNoMessageBeforeAnyPosting() {
        setUp();
        String output = new Reading("Newbie").execute(social);

        assertTrue(output.isEmpty());
    }
    
    @Test
    public void testReadingReturnsMessagesWithElapsedTime() {
        setUp();
        LocalDateTime now = LocalDateTime.now(); 
        
        social.post("Sodium", new Post("Hello!!!", now));
        social.post("Sodium", new Post("Is anybody here???", now.plus(1, SECONDS)));
        social.post("Sodium", new Post("I'm a sodium particle.", now.plus(6, SECONDS)));
        social.post("Sodium", new Post("All gone...", now.plus(8, SECONDS)));

        ClockMock readingTimes = new ClockMock().doReturn(now.plus(9, SECONDS));        
        String output = new Reading("Sodium").clock(readingTimes).execute(social);

        assertEquals(
                "All gone... (1 second ago)\n" +
                "I'm a sodium particle. (3 seconds ago)\n" +
                "Is anybody here??? (8 seconds ago)\n" +
                "Hello!!! (9 seconds ago)", output);
    }
}
