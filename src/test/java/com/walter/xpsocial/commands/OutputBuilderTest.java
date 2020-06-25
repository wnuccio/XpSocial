package com.walter.xpsocial.commands;

import com.walter.xpsocial.domain.Post;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OutputBuilderTest {

    private static Post post(String text) {
        return new Post(text, LocalDateTime.now()); 
    }

    @Test
    public void testConcatMessagesWithSeparator() {
        List<Post> posts = Arrays.asList(
            post("Uno"), post("Due"), post("Tre")
        );
        
        String result = OutputBuilder.forEach(posts)
                .append(Post::message)
                .append("-")
                .endLine()
                .doBuild();
        
        assertEquals("Uno-\nDue-\nTre-", result);
    }
}
