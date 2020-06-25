package com.walter.xpsocial.console;

import java.util.HashMap;
import java.util.Map;

public class TokenizerParser implements Parser {

    private static interface CommandFactory {
        Command createCommand(String username, String argument);
    }
    
    private static final CommandFactory defaultFactory = 
            (username_, argument_) -> Command.NULL_COMMAND;
    private static final String READ = "";
    private static final String POST = "->";
    private static final String FOLLOW = "follows";
    private static final String WALL = "wall";
    private static final String SPACES = "\\s+";
    
    private final Map<String, CommandFactory> factoryMap;
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
        CommandFactory factory = getFactory();
        Command result = factory.createCommand(username, argument);
        return result;
    }
    
    private String token(String[] tokens, int index) {
        if (index < 0 || index > tokens.length-1) return "";
        return tokens[index];
    }
    
    private void tokenize(String inputRow) {
        username = "";
        operation = "";
        argument = "";

        String row = preprocessRow(inputRow);
        
        String[] tokens = row.split(SPACES);
        username = token(tokens, 0);
        operation = token(tokens, 1);
        String thirdToken = token(tokens, 2);
        argument = row.substring(row.indexOf(thirdToken));
        
        assert username != null;
        assert operation != null;
        assert argument != null;
    }

    private String preprocessRow(String inputRow) {
        String result = inputRow != null ? inputRow : "";
        return result.trim();
    }
    
    private CommandFactory getFactory() {
        if (username.isEmpty()) return defaultFactory;        
        CommandFactory factory = factoryMap
                .getOrDefault(operation, defaultFactory);
        return factory;
    }

}
