package com.walter.xpsocial;

import com.walter.xpsocial.console.Console;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.domain.Social;
import com.walter.xpsocial.parsing.ExpressionParser;
import java.io.IOException;

public class XpSocial {
    public static void main(String[] args) throws IOException {
//        Parser parser = new TokenizerParser();
        Parser parser = new ExpressionParser();
        Social social = new Social();
        
        Console console = new Console(parser, social);
        console.run();
    }
}
