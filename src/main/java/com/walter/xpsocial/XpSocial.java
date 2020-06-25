package com.walter.xpsocial;

import com.walter.xpsocial.console.Console;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.domain.Social;
import com.walter.xpsocial.parsing.TokenizerParser;
import java.io.IOException;

public class XpSocial {

    public static void main(String[] args) throws IOException {
        new XpSocial().start();
    }

    private void start() {
        Parser parser = new TokenizerParser();
        Social social = new Social();
        Console console = new Console(parser, social);

        runConsole(console);
    }

    private void runConsole(Console console) {
        console.printWelcome();
        boolean terminate;
        do {
            terminate = console.readAndProcessInput();
        } while(! terminate);
    }
    
}
