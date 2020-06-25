package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Social;

public interface Command {

    String NO_OUTPUT = "";
    Command NULL_COMMAND = (social) -> { return NO_OUTPUT; };
    
    String execute(Social social);
}
