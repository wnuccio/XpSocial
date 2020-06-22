package com.walter.xpsocial.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    
    Parser p;
    
    @BeforeEach
    public void setUp() {
        p = new Parser();
    }
    
    @Test
    public void testReturnsNullCommandFromNullInput() {
        setUp();
        Command c = p.parse(null);
        
        assertTrue(c == Command.NULL_COMMAND);
    }
    
    @Test
    public void testReturnsNullCommandFromEmptyInput() {
        setUp();
        Command c = p.parse("");
        
        assertTrue(c == Command.NULL_COMMAND);
    }
    
    @Test
    public void testReturnsReadingCommandFromUsername() {
        setUp();
        Command c = p.parse("Bob");
        
        assertTrue(c instanceof Reading);
    }

    @Test
    public void testReturnsPostingCommandFromUsernamePostMessage() {
        setUp();
        Command c = p.parse("Bob -> Ciao");
        
        assertTrue(c instanceof Posting);
    }
    
    @Test
    public void testReturnsFollowingCommandFromUsernameFollowUsername() {
        setUp();
        Command c = p.parse("Bob follows Alice");
        
        assertTrue(c instanceof Following);
    }

    @Test
    public void testReturnsWallCommandFromUsernameWall() {
        setUp();
        Command c = p.parse("Bob wall");
        
        assertTrue(c instanceof Wall);
    }
    
    @Test
    public void testReturnsNullCommandFromWrongInput() {
        setUp();
        Command c = p.parse("Bob xxx");
        
        assertTrue(c == Command.NULL_COMMAND);
    }
}
