package com.walter.xpsocial.console;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    interface CommandFactory {
        Command createCommand(String username, String argument);
    }
    
    private final Map<String, CommandFactory> factoryMap;
            
    public static final String POST = "->";
    public static final String READ = "";
    public static final String FOLLOW = "follows";
    public static final String WALL = "wall";
    
    private String username;
    private String operation;
    private String argument;
    
    private static final String SEPARATOR = " ";

    
    public Parser() {
        factoryMap = new HashMap<>();
        factoryMap.put(READ, (username, argument) -> new Reading(username));
        factoryMap.put(POST, (username, argument) -> new Posting(username, argument));
        factoryMap.put(FOLLOW, (username, argument) -> new Following(username, argument));
        factoryMap.put(WALL, (username, argument) -> new Wall(username));
    }

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
