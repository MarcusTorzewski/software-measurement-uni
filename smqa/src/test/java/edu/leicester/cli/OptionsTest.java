package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsTest {

    @Test
    void testGetOption() {
        Options options = new Options();
        Option option = new Option("opt", "desc");
        options.addOption(option);
        assertEquals(option,options.getOption("opt"));
    }

    @Test
    void test2() { // make sure you give your test a descriptive name
        fail("Write your second test here.");
    }

    // Write more tests here...
}
