package com.walter.xpsocial.parsing;

import java.util.ArrayList;
import java.util.List;

class CommandFactoryBuilder {

    private final List<String> regExpressions;

    public CommandFactoryBuilder() {
        this.regExpressions = new ArrayList<>();
    }
    
    public CommandFactoryBuilder extract(String regexpr) {
        regExpressions.add(regexpr);
        return this;
    }

    Object skip(String post_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
