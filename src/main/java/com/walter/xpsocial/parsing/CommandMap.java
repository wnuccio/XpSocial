package com.walter.xpsocial.parsing;

import com.walter.xpsocial.commands.Following;
import com.walter.xpsocial.commands.Posting;
import com.walter.xpsocial.commands.Reading;
import com.walter.xpsocial.commands.Wall;
import static com.walter.xpsocial.parsing.CommandFactory.CREATE_NULL_COMMAND;
import java.util.HashMap;
import java.util.Map;

class CommandMap {
    
    private static final String READ = "";
    private static final String POST = "->";
    private static final String FOLLOW = "follows";
    private static final String WALL = "wall";

    private final Map<String, CommandFactory> factoryMap;
    
    CommandMap() {
        factoryMap = new HashMap<>();
        factoryMap.put(READ, (username_, argument_) -> new Reading(username_));
        factoryMap.put(POST, (username_, argument_) -> new Posting(username_, argument_));
        factoryMap.put(FOLLOW, (username_, argument_) -> new Following(username_, argument_));
        factoryMap.put(WALL, (username_, argument_) -> new Wall(username_));
    }
    
    CommandFactory selectFactory(String username, String operation) {
        if (username.isEmpty()) return CREATE_NULL_COMMAND;    
        
        CommandFactory factory = factoryMap
                .getOrDefault(operation, CREATE_NULL_COMMAND);
        return factory;
    }
 
    
}
