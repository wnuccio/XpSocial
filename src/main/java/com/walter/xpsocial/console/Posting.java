package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Clock;
import com.walter.xpsocial.domain.Post;
import com.walter.xpsocial.domain.Social;

public class Posting implements Command {
    
    private final String username;
    private final String message;
    private Clock clock;
    
    public String username() {
        return username;
    }

    public String message() {
        return message;
    }
    
    Posting clock(Clock clock) {
        this.clock = clock;
        return this;
    }
    
    public Posting(String username, String message) {
        this.username = username;
        this.message = message;
        this.clock = Clock.REAL;
    }

    @Override
    public String execute(Social social) {
        Post post = new Post(message, clock.currentTime());
        social.post(username, post);
        return NO_OUTPUT;
    }
}
