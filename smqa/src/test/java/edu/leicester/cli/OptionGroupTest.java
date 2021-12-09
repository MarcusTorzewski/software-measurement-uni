package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class OptionGroupTest {
    @Test
    void testAddOptionAndToStringWithNullOptionName() {
        Option o = new Option(null, "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o);
        assertEquals("[--null Nice option]", optionGroup.toString());
    }

    @Test
    void testAddOptionAndToStringWithNullOptionDescription() {
        Option o = new Option("Option", null);
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o);
        assertEquals("[-Option]", optionGroup.toString());
    }

    @Test
    void testAddOptionAndToStringWithOneOption() {
        Option o = new Option("Option", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o);
        assertEquals("[-Option Nice option]", optionGroup.toString());
    }

    @Test
    void testAddOptionAndToStringWithMultipleOptions() {
        Option o1 = new Option("Option_1", "Nice option");
        Option o2 = new Option("Option_2", "Good option");
        Option o3 = new Option("Option_3", "Great option");
        Option o4 = new Option("Option_4", "Cool option");
        Option o5 = new Option("Option_5", "Bad option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o1);
        optionGroup.addOption(o2);
        optionGroup.addOption(o3);
        optionGroup.addOption(o4);
        optionGroup.addOption(o5);
        assertEquals("[-Option_1 Nice option, -Option_2 Good option, -Option_3 Great option, -Option_4 Cool option, -Option_5 Bad option]", optionGroup.toString());
    }

    @Test
    void testGetNamesWithOneOption() {
        Option o = new Option("Option", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o);
        assertEquals("[-Option Nice option]", optionGroup.toString());

        Set<String> expected = new HashSet<>();
        expected.add("Option");
        final Collection<String> actual = optionGroup.getNames();
        assertEquals(expected, actual);
    }

    @Test
    void testGetNamesWithMulipleOptions() {
        Option o1 = new Option("Option_1", "Nice option");
        Option o2 = new Option("Option_2", "Good option");
        Option o3 = new Option("Option_3", "Great option");
        Option o4 = new Option("Option_4", "Cool option");
        Option o5 = new Option("Option_5", "Bad option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o1);
        optionGroup.addOption(o2);
        optionGroup.addOption(o3);
        optionGroup.addOption(o4);
        optionGroup.addOption(o5);
        assertEquals("[-Option_1 Nice option, -Option_2 Good option, -Option_3 Great option, -Option_4 Cool option, -Option_5 Bad option]", optionGroup.toString());

        Set<String> expected = new HashSet<>();
        expected.add("Option_1");
        expected.add("Option_2");
        expected.add("Option_3");
        expected.add("Option_4");
        expected.add("Option_5");
        final Collection<String> actual = optionGroup.getNames();
        assertEquals(expected, actual);
    }

    @Test
    void testGetOptionsWithOneOption() {
        Option o = new Option("Option", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o);
        assertEquals("[-Option Nice option]", optionGroup.toString());

        final Iterator<Option> iter = optionGroup.getOptions().iterator();

        while (iter.hasNext())
        {
            final Option actual = iter.next();
            
            assertEquals(o, actual);
        }
    }

    @Test
    void testGetOptionsWithMulipleOptions() {
        Option o1 = new Option("Option_1", "Nice option");
        Option o2 = new Option("Option_2", "Good option");
        Option o3 = new Option("Option_3", "Great option");
        Option o4 = new Option("Option_4", "Cool option");
        Option o5 = new Option("Option_5", "Bad option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(o1);
        optionGroup.addOption(o2);
        optionGroup.addOption(o3);
        optionGroup.addOption(o4);
        optionGroup.addOption(o5);
        assertEquals("[-Option_1 Nice option, -Option_2 Good option, -Option_3 Great option, -Option_4 Cool option, -Option_5 Bad option]", optionGroup.toString());

        // Set<Option> expected = new HashSet<>(Arrays.asList(o1,o2,o3,o4,o5));
        // final Collection<Option> actual = optionGroup.getOptions();
        // assertEquals(expected, actual);

        final Iterator<Option> iter = optionGroup.getOptions().iterator();

        // while (iter.hasNext())
        for (int i = 1; i <= 5; i++)
        {
            final Option actual = iter.next();

            switch(i) {
                case 1:
                    assertEquals(o1, actual);
                    break;
                case 2:
                    assertEquals(o2, actual);
                    break;
                case 3:
                    assertEquals(o3, actual);
                    break;
                case 4:
                    assertEquals(o4, actual);
                    break;
                case 5:
                    assertEquals(o5, actual);
                    break;
            }
        }
    }

    @Test
    void testSetSelectedAndGetSelectedWithNullOption() {
        Option o = null;
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setSelected(o);
        final String actual = optionGroup.getSelected();
        assertNull(actual);
    }

    @Test
    void testSetSelectedAndGetSelectedWithValidOption() {
        Option o = new Option("Option", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setSelected(o);
        final String expected = "Option";
        final String actual = optionGroup.getSelected();
        assertEquals(expected, actual);
    }

    @Test
    void testSetSelectedAndGetSelectedWithSameValidOptionSetTwice() {
        Option o = new Option("Option", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setSelected(o);
        String actual = optionGroup.getSelected();
        assertNotNull(actual);

        optionGroup.setSelected(o);
        final String expected = "Option";
        actual = optionGroup.getSelected();
        assertEquals(expected, actual);
    }

    @Test
    void testSetSelectedAndGetSelectedWithSetSelectedTwiceWithDifferentOptions() {
        Option o1 = new Option("Option_1", "Nice option");
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setSelected(o1);
        String actual = optionGroup.getSelected();
        assertNotNull(actual);

        Option o2 = new Option("Option_2", "Good option");
        assertThrows(IllegalStateException.class, () -> {
            optionGroup.setSelected(o2);;
        });
    }

    @Test
    void testSetRequiredAndIsRequiredWithRequiredTrue() {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(true);
        final boolean expected = true;
        final boolean actual = optionGroup.isRequired();
        assertEquals(expected, actual);
    }

    @Test
    void testSetRequiredAndIsRequiredWithRequiredFalse() {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setRequired(false);
        final boolean expected = false;
        final boolean actual = optionGroup.isRequired();
        assertEquals(expected, actual);
    }
}
