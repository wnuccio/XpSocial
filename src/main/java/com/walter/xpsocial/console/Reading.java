package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Clock;
import com.walter.xpsocial.domain.Post;
import com.walter.xpsocial.domain.Social;
import java.util.List;

public class Reading implements Command {
    
    private final String username;
    private Clock clock;

    public String username() {
        return username;
    }
    
    Reading clock(Clock clock) {
        this.clock = clock;
        return this;
    }
    
    public Reading(String username) {
        this.username = username;
        this.clock = Clock.REAL;
    }

    @Override
    public String execute(Social social) {
        final List<Post> posts = social.allPosts(username);
        
        String output = OutputBuilder.forEach(posts)
                .append(Post::message)
                .append(" ")
                .append(post -> post.elapsedTimeAsString(clock))
                .endLine()
                .doBuild();
        
        return output;
    }
}
