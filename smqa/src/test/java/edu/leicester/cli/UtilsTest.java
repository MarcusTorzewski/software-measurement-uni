package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

//    @Test
//    void testStripLeadingHyphensWithOnlyHyphens() {
//        final String s = "----";
//        final String expected = "--";
//        final String actual = Utils.stripLeadingHyphens(s);
//        assertEquals(expected, actual);
//    }

//    @Test
//    void testStripLeadingHyphenWithOnlyOneHyphen() {
//        final String s = "-";
//        final String expected = "";
//        final String actual = Utils.stripLeadingHyphens(s);
//        assertEquals(expected, actual);
//    }

    @Test
    void testStripLeadingHyphenWithHyphensAtStartString() {
        final String s = "--hello";
        final String expected = "hello";
        final String actual = Utils.stripLeadingHyphens(s);
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
        // Utils.validateOption(s);
        
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

        String expected = "Illegal option name '/'";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    void testValidateOptionWithValidSingleCharacterOption() {
        final String s = "a";
        // Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        //     Utils.validateOption(s);
        // });
        
        // String expected = "Illegal option name '?'";
        // String actual = exception.getMessage();
        // assertTrue(actual.contains(expected));

        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }
    
    @Test
    void testValidateOptionWithValidSingleCharacterQuestionmarkOption() {
        final String s = "?";
        // Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        //     Utils.validateOption(s);
        // });
        
        // String expected = "Illegal option name '?'";
        // String actual = exception.getMessage();
        // assertTrue(actual.contains(expected));

        assertDoesNotThrow(() -> {
            Utils.validateOption(s);
        });
    }

    @Test
    void testValidateOptionWithValidSingleCharacterAtSymbolOption() {
        final String s = "@";
        // Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        //     Utils.validateOption(s);
        // });

        // String expected = "Illegal option name '@'";
        // String actual = exception.getMessage();
        // assertTrue(actual.contains(expected));

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

        String expected = "The option '" + s + "' contains an illegal " + "character : '" + "/" + "'";
        String actual = exception.getMessage();
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
