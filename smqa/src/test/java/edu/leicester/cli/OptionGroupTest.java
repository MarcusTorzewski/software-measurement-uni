package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionGroupTest {

    @Test
    void testOptionGroupWithOneOption() {
        Option o = new Option("Option", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o);
        assertEquals("[-Option Nice option]", optionGroup.toString());
    }

    @Test
    void test2() { // make sure you give your test a descriptive name
        fail("Write your second test here.");
    }

    // Write more tests here...
}
