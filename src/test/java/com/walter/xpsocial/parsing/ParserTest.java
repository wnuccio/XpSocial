package com.walter.xpsocial.parsing;

import com.walter.xpsocial.console.Command;
import com.walter.xpsocial.console.Following;
import com.walter.xpsocial.console.Parser;
import com.walter.xpsocial.console.Posting;
import com.walter.xpsocial.console.Reading;
import com.walter.xpsocial.console.Wall;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {
    
    Parser p;
    
    @BeforeEach
    public void setUp() {
//        p = new TokenizerParser();
        p = new ExpressionParser();
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
    
    public void testRegisterNewCommand() {
        ExpressionParser p = new ExpressionParser();
        p.register(row -> {
            String[] tokens = new ExpressionExtractor()
                    .from(row)
                    .extract("sayhello", "\\w+");
            return s -> "Hello " + tokens[1] + "!";
        });
        
        Command command = p.parse("sayhello XPepper");
        
        assertEquals("Hello XPepper!", command.execute(null));
    }
    
    public void testRegisterNewCommand2() {
        ExpressionParser p = new ExpressionParser();
        
        Function<String[], Command> greetings = tokens -> {
            return social -> "Hello " + tokens[1] + "!";
        };
        
        p.registerCommand(
                greetings,
                "hello", "\\w+");
        
        Command command = p.parse("sayhello XPepper");
        
        assertEquals("Hello XPepper!", command.execute(null));
    }
}
