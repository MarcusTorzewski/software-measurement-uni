package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;

class HelpFormatterTest {

    @Test
    void testHelpFormatterWithStringWriter() {
        HelpFormatter formatter = new HelpFormatter();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        formatter.printWrapped(pw, 123, "helloWorld");
        String test = sw.toString();
        assertEquals("helloWorld\n", test);
    }

    @Test
    void test() { // make sure you give your test a descriptive name
        fail("Write your second test here.");
    }

    // Write more tests here...
}