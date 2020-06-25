package com.walter.xpsocial.commands;

import com.walter.xpsocial.domain.Command;
import com.walter.xpsocial.domain.Clock;
import com.walter.xpsocial.domain.Post;
import com.walter.xpsocial.domain.Social;
import java.util.List;

public class Wall implements Command {
    
    private final String username;
    private Clock clock;
    
    public String username() {
        return username;
    }
    
    public Wall(String username) {
        this.username = username;
        this.clock = Clock.REAL;
    }

    Wall clock(Clock clock) {
        this.clock = clock;
        return this;
    }
    
    @Override
    public String execute(Social social) {
        final List<Post> posts = social.allPostsIncludingFollowed(username);
        
        String output = OutputBuilder.forEach(posts)
                .append(Post::username)
                .append(" - ")
                .append(Post::message)
                .append(" ")
                .append(post -> Duration.elapsed(post.timeFromPosting(clock)))
                .endLine()
                .doBuild(); 
        
        return output;
    }
}
