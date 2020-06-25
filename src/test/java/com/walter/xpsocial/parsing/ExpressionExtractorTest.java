package com.walter.xpsocial.parsing;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpressionExtractorTest {

    ExpressionExtractor extractor;

    public ExpressionExtractorTest() {
    }

    @BeforeEach
    public void setUp() {
        extractor = new ExpressionExtractor();
    }

    @Test
    public void testMatchUsername() {
        setUp();
        String[] matches = extractor
                .from("Alice")
                .extractToken("\\w+")
                .end();

        assertFalse(extractor.hasError());
        assertEquals(1, matches.length);
        assertEquals("Alice", matches[0]);
    }

    @Test
    public void testMatchUsernamePostMessage() {
        setUp();
        String[] matches = extractor
                .from("Alice -> Hi all, it's a beautiful day!")
                .extractToken("\\w+")
                .extractToken("->")
                .extractToken(".*")
                .end();

        assertFalse(extractor.hasError());
        assertEquals(3, matches.length);
        assertEquals("Alice", matches[0]);
        assertEquals("Hi all, it's a beautiful day!", matches[2]);
    }

    @Test
    public void testMatchUsernameFollowUsername() {
        setUp();
        String[] matches = extractor
                .from("Alice follows Bob")
                .extractToken("\\w+")
                .extractToken("follows")
                .extractToken("\\w+")
                .end();

        assertFalse(extractor.hasError());
        assertEquals(3, matches.length);
        assertEquals("Alice", matches[0]);
        assertEquals("Bob", matches[2]);
    }

    @Test
    public void testMatchUsernameWall() {
        setUp();
        String[] matches = extractor
                .from("Alice wall")
                .extractToken("\\w+")
                .extractToken("wall")
                .end();

        assertFalse(extractor.hasError());
        assertEquals(2, matches.length);
        assertEquals("Alice", matches[0]);
    }

    @Test
    public void testErrorAlwaysReturnEmptyResult() {
        setUp();
        String[] matches = extractor
                .from("Alice -> Hi all")
                .extractToken("Alice")
                .extractToken("errore")
                .extractToken("Hi all")
                .end();

        assertTrue(extractor.hasError());
        assertEquals(0, matches.length);
    }

    @Test
    public void testErrorOnStringNotTotallyParsed() {
        setUp();
        String[] matches = extractor
                .from("Alice -> Hi all")
                .extractToken("\\w+")
                .extractToken("->")
                .end();

        assertTrue(extractor.hasError());
        assertEquals(0, matches.length);
    }
    
    @Test
    public void testMultipleExtraction() {
        String string = "one two three";
        String[] multiple = new ExpressionExtractor()
                .from(string)
                .extract("one", "two", "three");
        
        String[] single = new ExpressionExtractor()
                .from(string)
                .extractToken("one")
                .extractToken("two")
                .extractToken("three")
                .end();
        
        assertTrue(Arrays.equals(multiple, single));
    }

    @Test
    public void testIgnoreExtraSpaces() {
        String string = "one    two    three";
        String[] matches = new ExpressionExtractor()
                .from(string)
                .extract("one", "two", "three");
        
        assertEquals(3, matches.length);
        assertEquals("one", matches[0]);
        assertEquals("two", matches[1]);
        assertEquals("three", matches[2]);
    }

}
