package com.walter.xpsocial.domain;

public interface Command {

    String NO_OUTPUT = "";
    Command NULL_COMMAND = (social) -> { return NO_OUTPUT; };
    
    String execute(Social social);
}
