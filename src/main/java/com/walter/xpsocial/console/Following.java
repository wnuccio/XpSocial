package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Social;

public class Following implements Command {
    
    private final String username;
    private final String followed;
    
    public Following(String username, String followed) {
        this.username = username;
        this.followed = followed;
    }

    @Override
    public String execute(Social social) {
        social.follows(username, followed);
        return NO_OUTPUT;
    }
}
