package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Command;
import com.walter.xpsocial.domain.Social;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ConsoleTest {

    private static class CommandMock implements Command {

        Social expectedSocial;
        private boolean invocationOk = false;

        @Override
        public String execute(Social social) {
            invocationOk = expectedSocial == social;
            return "";
        }
    };
    
    @Test
    public void testCreateCommandAndInvokeItOnSocial() {

        Social social = new Social();
        CommandMock commandMock = new CommandMock();
        commandMock.expectedSocial = social;
        Parser parser = (row) -> commandMock;

        Console c = new Console(parser, social);

        c.processInputRow("");

        assertTrue(commandMock.invocationOk);

    }
}
