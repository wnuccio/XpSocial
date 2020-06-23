package com.walter.xpsocial.console;

import com.walter.xpsocial.domain.Social;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {
    
    private Console console;
    
    @BeforeEach
    public void setUp() {
        console = new Console(new Parser(), new Social());
    }

    @Test
    public void testEmptyOutputOnPosting() {
        setUp();
        String output = console.input("Alice -> I love the weather today");

        assertTrue(output.isEmpty());
    }

    @Test
    public void testEmptyOutputOnReadingBeforeAnyPosting() {
        setUp();
        String output = console.input("Alice");

        assertTrue(output.isEmpty());
    }

    @Test
    public void testReadingAfterOnePosting() {
        setUp();
        console.input("Alice -> I love the weather today");
        String output = console.input("Alice");

        assertTrue(output.startsWith("I love the weather today"));
    }
    
    @Test
    public void testReadingAfterMorePosting() {
        setUp();
        console.input("Alice -> Hi all!");
        console.input("Alice -> I'm Alice");
        console.input("Alice -> What a nice group!");
        String output = console.input("Alice");
        String[] rows = output.split("\n");
        
        assertEquals(3, rows.length);
        assertTrue(rows[0].startsWith("What a nice group"));
        assertTrue(rows[1].startsWith("I'm Alice"));
        assertTrue(rows[2].startsWith("Hi all!"));
    }
}
