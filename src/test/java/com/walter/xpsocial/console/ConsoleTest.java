package com.walter.xpsocial.console;

import com.walter.xpsocial.parsing.ExpressionParser;
import com.walter.xpsocial.domain.Social;
import org.junit.jupiter.api.BeforeEach;

public class ConsoleTest {
    
    private Console console;
    
    private static class Mock implements Parser, Command {

        String expectedInput;
        boolean commandInvocked;
        
        @Override
        public Command parse(String row) {
            return expectedInput.equals(row) ? this : null;
        }

        @Override
        public String execute(Social social) {
            commandInvocked = true;
            return "";
        }
    }
    
    @BeforeEach
    public void setUp() {
        final Parser parser = new ExpressionParser();
//        final Parser parser = new RegularExpressionParser();
//        final Parser parser = new TokenizerParser();
        console = new Console(parser, new Social());
    }

//    @Test
//    public void testConsoleParsesCommandAndExecutesItOnTheSocial() {
//        Mock mock = new Mock();
//        Social social = new Social();
//        mock.expectedInput = "inputString";
//        mock.commandInvocked = false;
//        
//        Console c = new Console(mock, social);
//        String output = c.input("inputString");
//        
//        assertEquals("inputString", mock.expectedInput);
//        assertEquals("onlyForTest", output);
//    }
//    
//    @Test
//    public void testEmptyOutputOnPosting() {
//        setUp();
//        String output = console.input("Alice -> I love the weather today");
//
//        assertTrue(output.isEmpty());
//    }
//
//    @Test
//    public void testEmptyOutputOnReadingBeforeAnyPosting() {
//        setUp();
//        String output = console.input("Alice");
//
//        assertTrue(output.isEmpty());
//    }
//
//    @Test
//    public void testReadingAfterOnePosting() {
//        setUp();
//        console.input("Alice -> I love the weather today");
//        String output = console.input("Alice");
//
//        assertTrue(output.startsWith("I love the weather today"));
//    }
//    
//    @Test
//    public void testReadingAfterMorePosting() {
//        setUp();
//        console.input("Alice -> Hi all!");
//        console.input("Alice -> I'm Alice");
//        console.input("Alice -> What a nice group!");
//        String output = console.input("Alice");
//        String[] rows = output.split("\n");
//        
//        assertEquals(3, rows.length);
//        assertTrue(rows[0].startsWith("What a nice group!"));
//        assertTrue(rows[1].startsWith("I'm Alice"));
//        assertTrue(rows[2].startsWith("Hi all!"));
//    }
//
//    @Test
//    public void testEmptyOutputOnWall() {
//        setUp();
//        String output = console.input("Alice wall");
//
//        assertTrue(output.isEmpty());
//    }
//
//    @Test
//    public void testEmptyOutputOnWallBeforeAnyFollowing() {
//        setUp();
//        String output = console.input("Alice wall");
//
//        assertTrue(output.isEmpty());
//    }

    // errato: dipende dal tempo
//    @Test
//    public void testWallAfterOneFollowing() {
//        setUp();
//        console.input("Alice -> I love the weather today");
//        console.input("Bob -> Me too");
//        console.input("Alice follows Bob");
//        String output = console.input("Alice wall");
//
//        assertTrue(output.startsWith("Alice - I love the weather today"));
//    }
}
