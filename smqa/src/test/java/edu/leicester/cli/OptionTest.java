package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionTest {

    @Test
    void testConstructor() {
        Option option = new Option("test", "test", true, "description");
        assertTrue(option.acceptsArg());
    }

    @Test
    void test2() { // make sure you give your test a descriptive name
        fail("Write your second test here.");
    }

    // Write more tests here...
}
