package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class OptionsTest {

    @Test
    void testGetOption() {
        Options options = new Options();
        Option option = new Option("opt", "desc");
        options.addOption(option);
        assertEquals(option,options.getOption("opt"));
    }
    
    @Test
    void testGetOptions() {
    	Options options = new Options();
        Option o1 = new Option("opt", "desc");
        Option o2 = new Option("opt", "desc");
        
        options.addOption(o1);
        options.addOption(o2);
        
        Collection<Option> expected = new HashSet<>();
        expected.add(o1);
        expected.add(o2);
        assertEquals(expected.toString(), options.getOptions().toString());
    }

    @Test
    void testAddOptionGroup() { 
    	Options options = new Options();
    	OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("opt", "desc");
        
        optionGroup.addOption(option);
        options.addOptionGroup(optionGroup);
        
        HashSet<OptionGroup> expected = new HashSet<>();
        expected.add(optionGroup);
        assertEquals(expected, options.getOptionGroups());
    }
    
    @Test
    void testAddRequiredOptionGroup() { 
    	Options options = new Options();
    	OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("opt", "desc");
        
        
        optionGroup.addOption(option);
        optionGroup.setRequired(true);
        options.addOptionGroup(optionGroup);
        
        List<Object> expected = new ArrayList<>();
        expected.add(optionGroup);
        assertEquals(expected,options.getRequiredOptions());
    }

    @Test
    void testAddRequiredOption() {
    	Options options = new Options();
        Option option = new Option("opt", "desc");
        option.required = true;
        options.addOption(option);
        
        List<Object> expected = new ArrayList<>();
        expected.add(option.getKey());
        assertEquals(expected,options.getRequiredOptions());
    }
    
    @Test
    void testAddRequiredOptionTwice() {
    	Options options = new Options();
        Option option = new Option("opt", "desc");
        option.required = true;
        options.addOption(option);
        options.addOption(option);
        
        List<Object> expected = new ArrayList<>();
        expected.add(option.getKey());
        assertEquals(expected,options.getRequiredOptions());
    }

    @Test
    void testHasOptionValidInput() {
    	Options options = new Options();
    	Option option = new Option("c", "cancel", false, "option");
    	options.addOption(option);
    	
    	assertEquals(true, options.hasOption("c"));
    	assertEquals(true, options.hasOption("cancel"));
    }
    
    @Test
    void testHasOptionIncorrectInput() {
    	Options options = new Options();
    	assertEquals(false, options.hasOption("c"));
    }
    
    @Test
    void testAddLongOption() {
    	Options options = new Options();
    	Option option = new Option("c", "cancel", false, "option");
    	options.addOption(option);
    	
    	assertEquals(option, options.getOption("cancel"));
    	assertEquals(true, options.hasLongOption("cancel")); // ?
    }

    @Test
    void testHasShortOption() {
    	Options options = new Options();
    	Option option = new Option("c", "cancel", false, "option");
    	options.addOption(option);
    	
    	assertEquals(true, options.hasShortOption("c"));
    }
    
    @Test
    void testGetOptionGroup() { 
    	Options options = new Options();
    	OptionGroup optionGroup = new OptionGroup();
        Option option = new Option("opt", "desc");
        
        optionGroup.addOption(option);
        options.addOptionGroup(optionGroup);
        
        assertEquals(optionGroup, options.getOptionGroup(option));
    }
    
    @Test
    void testToString() {
    	Options options = new Options();
    	OptionGroup optionGroup = new OptionGroup();
    	Option o1 = new Option("o", "option");
    	Option o2 = new Option("c", "cancel", false, "option");
        optionGroup.addOption(o1);
        optionGroup.addOption(o2);
        options.addOptionGroup(optionGroup);
    	
        final String expected = "[ Options: [ short {o=[ option: o  :: option :: class java.lang.String ], c=[ option: c cancel  :: option :: class java.lang.String ]} ] [ long {cancel=[ option: c cancel  :: option :: class java.lang.String ]} ]";
    	assertEquals(expected, options.toString());
    }

    @Test
    void testGetMatchingOptionsExactMatch() {
    	Options options = new Options();
    	Option option = new Option("c", "cancel", false, "option");
        options.addOption(option);
        
        List<String> expected = new ArrayList<>();
        expected.add("cancel");
        assertEquals(expected, options.getMatchingOptions("cancel"));
    }
    
    @Test
    void testGetMatchingOptionsPartialMatch() {
    	Options options = new Options();
    	Option o1 = new Option("c", "cancel", false, "option");
    	Option o2 = new Option("d", "delete", false, "option");
        options.addOption(o1);
        options.addOption(o2);
        
        List<String> expected = new ArrayList<>();
        expected.add("cancel");
        assertEquals(expected, options.getMatchingOptions("can"));
    }

}

