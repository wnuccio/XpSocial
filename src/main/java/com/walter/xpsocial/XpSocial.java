package com.walter.xpsocial;

import com.walter.xpsocial.console.Console;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.domain.Social;
import java.io.IOException;

public class XpSocial {
    public static void main(String[] args) throws IOException {
        Console console = new Console(new Parser(), new Social());
        console.run();
    }
}
