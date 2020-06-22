package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Post;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
