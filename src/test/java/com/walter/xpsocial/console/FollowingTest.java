package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Social;
import com.walter.xpsocial.domain.User;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FollowingTest {

    Social social;

    @BeforeEach
    public void setUp() {
        social = new Social();
    }

    @Test
    public void testFollowingSavesTheFollowedAndReturnsEmptyOutput() {
        setUp();
        String output = new Following("Alice", "Charlie").execute(social);

        final List<User> followed = social.followed("Alice");
        assertEquals(1, followed.size());
        assertEquals(social.user("Charlie"), followed.get(0));
        assertTrue(output.isEmpty());
    }
}
