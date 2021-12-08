package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    void testStripLeadingHyphensWithOnlyHyphens() {
        final String s = "----";
        final String expected = "--";
        final String actual = Utils.stripLeadingHyphens(s);
        assertEquals(expected, actual);
    }

    @Test
    void test2() { // make sure you give your test a descriptive name
        fail("Write your second test here.");
    }

    // Write more tests here...
}
