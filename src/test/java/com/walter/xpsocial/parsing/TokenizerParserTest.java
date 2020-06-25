package com.walter.xpsocial.parsing;

import com.walter.xpsocial.commands.Posting;
import com.walter.xpsocial.commands.Following;
import com.walter.xpsocial.commands.Following;
import com.walter.xpsocial.commands.Posting;
import com.walter.xpsocial.commands.Reading;
import com.walter.xpsocial.commands.Reading;
import com.walter.xpsocial.domain.Command;
import com.walter.xpsocial.commands.Wall;
import com.walter.xpsocial.commands.Wall;
import com.walter.xpsocial.parsing.TokenizerParser;
import com.walter.xpsocial.console.Parser;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TokenizerParserTest {
    
    Parser p;
    
    @BeforeEach
    public void setUp() {
        p = new TokenizerParser();
    }
    
    @Test
    public void testReturnsNullCommandFromNullInput() {
        setUp();
        Command c = p.parse(null);
        
        assertEquals(Command.NULL_COMMAND, c);
    }
    
    @Test
    public void testReturnsNullCommandFromEmptyInput() {
        setUp();
        Command c = p.parse("");
        
        assertEquals(Command.NULL_COMMAND, c);
    }
    
    @Test
    public void testReturnsReadingCommandFromUsername() {
        setUp();
        Command c = p.parse("Bob");
        
        assertTrue(c instanceof Reading);
        assertEquals("Bob", ((Reading)c).username());
    }

    @Test
    public void testReturnsPostingCommandFromUsernamePostMessage() {
        setUp();
        Command c = p.parse("Bob -> Ciao");
        
        assertTrue(c instanceof Posting);
        assertEquals("Bob", ((Posting)c).username());
        assertEquals("Ciao", ((Posting)c).message());
    }
    
    @Test
    public void testReturnsFollowingCommandFromUsernameFollowUsername() {
        setUp();
        Command c = p.parse("Bob follows Alice");
        
        assertTrue(c instanceof Following);
        assertEquals("Bob", ((Following)c).username());
        assertEquals("Alice", ((Following)c).followed());
    }

    @Test
    public void testReturnsWallCommandFromUsernameWall() {
        setUp();
        Command c = p.parse("Bob wall");
        
        assertTrue(c instanceof Wall);
        assertEquals("Bob", ((Wall)c).username());
    }
    
    @Test
    public void testReturnsNullCommandFromWrongInput() {
        setUp();
        Command c = p.parse("Bob xxx");
        
        assertEquals(Command.NULL_COMMAND, c);
    }
    
    @Test
    public void testIgnoreInitialSpaces() {
        setUp();
        Command c = p.parse("  Bob   -> Ciao");
        
        assertTrue(c instanceof Posting);
        assertEquals("Bob", ((Posting)c).username());
        assertEquals("Ciao", ((Posting)c).message());
    }
    
    @Test
    public void testIgnoreExtraSpaces() {
        setUp();
        Command c = p.parse("Bob   ->    Ciao  Alice   ");
        
        assertTrue(c instanceof Posting);
        assertEquals("Bob", ((Posting)c).username());
        assertEquals("Ciao  Alice", ((Posting)c).message());
    }
}
