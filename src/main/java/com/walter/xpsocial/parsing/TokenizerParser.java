package com.walter.xpsocial.parsing;

import com.walter.xpsocial.console.Command;
import com.walter.xpsocial.console.Following;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.console.Posting;
import com.walter.xpsocial.console.Reading;
import com.walter.xpsocial.console.Wall;
import java.util.HashMap;
import java.util.Map;

public class TokenizerParser implements Parser {

    interface CommandFactory {
        Command createCommand(String username, String argument);
    }
    
    private final Map<String, CommandFactory> factoryMap;
            
    public static final String POST = "->";
    public static final String READ = "";
    public static final String FOLLOW = "follows";
    public static final String WALL = "wall";
    private static final String SEPARATOR = " ";
    
    private String username;
    private String operation;
    private String argument;
    
    public TokenizerParser() {
        factoryMap = new HashMap<>();
        factoryMap.put(READ, (username_, argument_) -> new Reading(username_));
        factoryMap.put(POST, (username_, argument_) -> new Posting(username_, argument_));
        factoryMap.put(FOLLOW, (username_, argument_) -> new Following(username_, argument_));
        factoryMap.put(WALL, (username_, argument_) -> new Wall(username_));
    }

    @Override
    public Command parse(String row) {
        tokenize(row);
        Command result = createCommand();
        return result;
    }

    private String nextToken(String string) {
        String token = string.split(SEPARATOR)[0];
        return token;
    }

    private String cutToken(String string, String token) {
        if (!string.startsWith(token)) {
            throw new IllegalArgumentException();
        }
        String result = string.substring(token.length());
        if (result.startsWith(SEPARATOR)) {
            result = result.substring(SEPARATOR.length());
        }
        return result;
    }

    private void tokenize(String inputRow) {
        String row = inputRow != null ? inputRow : "";
        username = nextToken(row);
        row = cutToken(row, username);
        operation = nextToken(row);
        row = cutToken(row, operation);
        argument = row;
    }
    
    private Command createCommand() {
        CommandFactory factory = factoryMap.get(operation);
        if (factory == null) return Command.NULL_COMMAND;
        if (username.isEmpty()) return Command.NULL_COMMAND;        
        
        Command result = factory.createCommand(username, argument);
        return result;
    }

}
