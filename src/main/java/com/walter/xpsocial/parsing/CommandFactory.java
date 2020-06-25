package com.walter.xpsocial.parsing;

import com.walter.xpsocial.domain.Command;


interface CommandFactory {
    Command createCommand(String username, String argument);
    
    CommandFactory CREATE_NULL_COMMAND = 
            (username_, argument_) -> Command.NULL_COMMAND;
}
