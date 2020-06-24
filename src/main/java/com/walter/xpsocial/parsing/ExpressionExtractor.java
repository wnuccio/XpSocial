package com.walter.xpsocial.parsing;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class ExpressionExtractor {

    private final Pattern spaces;
    private final ArrayList<String> tokenList;
    private String string;
    private boolean error;
    private int index;
    
    
    private void init() {
        this.string = "";
        this.error = false;
        this.index = 0;
        this.tokenList.clear();
    }
    
    public ExpressionExtractor() {
        this.spaces = Pattern.compile(" ");
        this.tokenList = new ArrayList<>();
        init();
    }
    
    public ExpressionExtractor from(String string) {
        init();
        this.string = string != null ? string : "";
        return this;
    }
    
    public String[] extract(String... regexpr) {
        Stream.of(regexpr)
                .forEach(this::extractToken);
        
        return this.end();
    }
    
    public boolean hasError() {
        return error;
    }
    
    ExpressionExtractor extractToken(String regexpr) {
        if (error) return this;
        
        findNextMatchingToken(regexpr);
        skipSpaces();
        return this;
    }

    private void skipSpaces() {
        if (error) return;
        
        Matcher matcher = spaces.matcher(string);
        if (matcher.find(index)) {
            String skip = matcher.group();
            index += skip.length();
        }
    }
    
    private String findNextMatchingToken(String regexpr) {
        Matcher matcher = Pattern.compile(regexpr).matcher(string);
        String token = "";
        if (matcher.find(index)) {
            token = matcher.group();
            tokenList.add(token);
        } else {
            error = true;
        }
        index = string.indexOf(token, index) + token.length();
        return token;
    }

    String[] end() {
        if (index != string.length()) {
            error = true;
        }
        
        if (error) return new String[0];
        
        final String[] tokens = tokenList.toArray(new String[tokenList.size()]);
        return tokens;
    }
}
