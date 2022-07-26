package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
	
    @Test
    void testStripLeadingHyphenWithHyphensAtStartString() {
    	Utils u =  new Utils();
        final String s = "--hello";
        final String expected = "hello";
        final String actual = u.stripLeadingHyphens(s);
        assertEquals(expected, actual);
    }
    
    @Test
    void testStripLeadingHyphenWithOnlyOneHyphenAtStartString() {
        final String s = "-hello";
        final String expected = "hello";
        final String actual = Utils.stripLeadingHyphens(s);
        assertEquals(expected, actual);
    }
    
    @Test
    void testStripLeadingHyphenWithOnlyString() {
        final String s = "hello";
        final String expected = "hello";
        final String actual = Utils.stripLeadingHyphens(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingHyphensWithNullInput() {
        final String s = null;
        final String actual = Utils.stripLeadingHyphens(s);
        assertNull(actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithEmptyString() {
        final String s = "";
        final String expected = "";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithOneCharacter() {
        final String s = "a";
        final String expected = "a";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithQuotesStartEndEmptyString() {
        final String s = "\"\"";
        final String expected = "";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithQuoteStartString() {
        final String s = "\"hello";
        final String expected = "\"hello";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithQuoteEndString() {
        final String s = "hello\"";
        final String expected = "hello\"";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithQuotesStartEndString() {
        final String s = "\"hello\"";
        final String expected = "hello";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }

    @Test
    void testStripLeadingAndTrailingQuotesWithQuotesStartMiddleEndString() {
        final String s = "\"hello \" there\"";
        final String expected = "\"hello \" there\"";
        final String actual = Utils.stripLeadingAndTrailingQuotes(s);
        assertEquals(expected, actual);
    }


    @Test
    void testValidateOptionWithNullOption() {
        final String s = null;
        
        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }

    @Test
    void testValidateOptionWithInvalidSingleCharacterOption() {
        final String s = "/";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Utils.validateOption(s);
        });

        final String expected = "Illegal option name '/'";
        final String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    void testValidateOptionWithValidSingleCharacterOption() {
        final String s = "a";
        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }
    
    @Test
    void testValidateOptionWithValidSingleCharacterQuestionmarkOption() {
        final String s = "?";
        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }

    @Test
    void testValidateOptionWithValidSingleCharacterAtSymbolOption() {
        final String s = "@";
        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }

    @Test
    void testValidateOptionWithInvalidStringOption() {
        final String s = "hello/";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Utils.validateOption(s);
        });

        final String expected = "The option '" + s + "' contains an illegal " + "character : '" + "/" + "'";
        final String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    void testValidateOptionWithValidStringOption() {
        final String s = "hello";
        
        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }
}
