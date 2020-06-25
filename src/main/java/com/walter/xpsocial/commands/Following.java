package com.walter.xpsocial.commands;

import com.walter.xpsocial.domain.Command;
import com.walter.xpsocial.domain.Social;

public class Following implements Command {
    
    private final String username;
    private final String followed;
    
    public String username() {
        return username;
    }

    public String followed() {
        return followed;
    }
    
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
