package com.walter.xpsocial.parsing;

import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.domain.Command;

public class TokenizerParser implements Parser {

    private static final String EMPTY_TOKEN = "";
    private static final String SPACES = "\\s+";
    
    private final CommandMap commandMap = new CommandMap();
    
    private String username;
    private String operation;
    private String argument;
    
    public TokenizerParser() {
    }

    @Override
    public Command parse(String inputRow) {
        String row = preProcessRow(inputRow);
        tokenize(row);
        CommandFactory factory = commandMap.selectFactory(username, operation);
        Command result = factory.createCommand(username, argument);
        return result;
    }
    
    private void tokenize(String row) {
        String[] tokens = row.split(SPACES);
        username = token(tokens, 0);
        operation = token(tokens, 1);
        String thirdToken = token(tokens, 2);
        argument = untilEndOfString(thirdToken, row);
        
        assert username != null;
        assert operation != null;
        assert argument != null;
    }

    private static String untilEndOfString(String thirdToken, String row) {
        if (thirdToken.isEmpty()) return EMPTY_TOKEN;
        final String result = row.substring(row.indexOf(thirdToken));
        return result;
    }

    private String token(String[] tokens, int index) {
        if (index < 0 || index > tokens.length-1) return EMPTY_TOKEN;
        final String result = tokens[index];
        return result;
    }
    
    private String preProcessRow(String inputRow) {
        String result = inputRow != null ? inputRow : "";
        return result.trim();
    }
}
