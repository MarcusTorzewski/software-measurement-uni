package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class OptionTest {
	
	
	@Test
	void testConstructorOptDescriptionOnly() {
		Option option = new Option("test", "description");
		boolean opt = !(option.opt.isEmpty());
		boolean longOpt = (option.longOpt == null ? true : false);
		boolean hasArg = (option.numberOfArgs == -1 ? true : false);
		boolean desc = !(option.description.isEmpty());
		boolean result = (opt && longOpt && hasArg && desc);
		assertTrue(result);
	}
	
	@Test
	void testConstructorOptHasArgDescriptionOnly() {
		Option option = new Option("test", true, "description");
		boolean opt = !(option.opt.isEmpty());
		boolean longOpt = (option.longOpt == null ? true : false);
		boolean hasArg = (option.numberOfArgs != -1 ? true : false);
		boolean desc = !(option.description.isEmpty());
		boolean result = (opt && longOpt && hasArg && desc);
		assertTrue(result);
	}

    @Test
    void testConstructor() {
        Option option = new Option("test", "test", true, "description");
        assertTrue(option.acceptsArg());
    }
    
    @Test
    void testHasArgConstructor() {
    	Option option = new Option("test", "test", true, "description");
    	assertEquals(option.numberOfArgs, 1); 	
    }
    
    @Test
    void testHasArgConstructorFalse() {
    	Option option = new Option("test", "test", false, "description");
    	assertEquals(option.numberOfArgs, -1);
    }
    
    
    @Test
    void testgetId() {
    	Option option = new Option("test", "test", false, "description");
    	int expected = 116;
    	assertEquals(option.getId(), expected);
    }
    
    @Test
    void testgetKeyOpt() {
    	Option option = new Option("test1", "test", false, "description");
    	String expected = "test1";
    	assertEquals(option.getKey(), expected);
    }
    
    @Test
    void testgetKeyLongOpt() {
    	Option option = new Option(null, "test", false, "description");
    	String expected = "test";
    	assertEquals(option.getKey(), expected);
    }
    
    @Test
    void testSetGetType() {
    	Option option = new Option("test", "test", false, "description");
    	Class<Option> option2 = null;
    	option.setType(option2);
    	assertEquals(option.getType(), null);
    }
    
    @Test
    void testHasLongOptTrue() {
    	Option option = new Option("test", "test", false, "description");
    	boolean expected = true;
    	assertEquals(option.hasLongOpt(), expected);
    }
    
    @Test
    void testHasLongOptFalse() {
    	Option option = new Option("test", null, false, "description");
    	boolean expected = false;
    	assertEquals(option.hasLongOpt(), expected);
    }
    
    @Test
    void testHasArgTrueInput() {
    	Option option = new Option("test", "test", true, "description");
    	boolean expected = true;
    	assertEquals(option.hasArg(), expected);
    }
    @Test
    void testHasArgFalseInput() {
    	Option option = new Option("test", "test", false, "description");
    	boolean expected = false;
    	assertEquals(option.hasArg(), expected);
    }
    @Test
    void testHasArgUnlimitedValues() {
    	Option option = new Option("test", "test", false, "description");
    	option.numberOfArgs = -2;
    	boolean expected = true;
    	assertEquals(option.hasArg(), expected);
    }
    
    @Test
    void testSetGetArgName() {
    	Option option = new Option("test", "test", false, "description");
    	String expected = "testing";
    	option.setArgName(expected);
    	assertEquals(option.getArgName(), expected);
    }
    
    @Test
    void testHasArgNameFalse() {
    	Option option = new Option("test", "test", false, "description");
    	boolean expected = false;
    	assertEquals(option.hasArgName(), expected);
    }
    
    @Test
    void testHasArgNameEmptyInput() {
    	Option option = new Option("test", "test", false, "description");
    	option.setArgName("");
    	boolean expected = false;
    	assertEquals(option.hasArgName(), expected);
    }
    
    @Test
    void testHasArgNameValid() {
    	Option option = new Option("test", "test", false, "description");
    	option.setArgName("Testing");
    	boolean expected = true;
    	assertEquals(option.hasArgName(), expected);	
    }
    
    @Test
    void testHasArgsNumberOfArgs1() {
    	Option option = new Option("test", "test", true, "description");
    	boolean expected = false;
    	assertEquals(option.hasArgs(), expected);
    }
    
    
    @Test
    void testHasArgsNumberOfArgs10(){
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = 10;
    	boolean expected = true;
    	assertEquals(option.hasArgs(), expected);
    }
    
    @Test
    void testHasArgsNumberOfArgsUnlimited() {
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = -2;
    	boolean expected = true;
    	assertEquals(option.hasArgs(), expected);
    }
    
    @Test
    void testHasValueSeparatorEmptyValueSeperator() {
    	Option option = new Option("test", "test", true, "description");
    	boolean expected = false;
    	assertEquals(option.hasValueSeparator(), expected);
    }
    
    @Test
    void testHasValueSeparatorNotEmpty() {
    	Option option = new Option("test", "test", true, "description");
    	option.valuesep = 'h';
    	boolean expected = true;
    	assertEquals(option.hasValueSeparator(), expected);
    }
    
    
    @Test
    void testaddValueForProcessingRuntimeException() {
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = -1;
    	Exception exception = assertThrows(RuntimeException.class, () -> {
            option.addValueForProcessing("test");
        });

        String expected = "NO_ARGS_ALLOWED";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }
    
    @Test
    void testprocessValueNoValueSeperator() {
    	Option option = new Option("test", "test", true, "description");
    	String expected = "test";
    	option.addValueForProcessing("test");
    	assertEquals(option.getValue(), expected);
    }
    
    @Test
    void testprocessValueHasValueSeperatorSize2() {
    	Option option = new Option("test", "test", true, "description");
    	option.valuesep = (' ');
    	option.addValueForProcessing("test test");
    	assertEquals(option.getValue(),"test test"); 
    }
    
    @Test
    void testprocessValueHasValueSeperatorSize3() {
    	Option option = new Option("test", "test", true, "description");
    	option.valuesep = (' ');
    	option.numberOfArgs = 100;
    	option.addValueForProcessing("test test test test test");
    	int expected = 5;
    	assertEquals(option.getValues().length, expected);
    }
    
    @Test
    void testaddRuntimeException() {
    	Option option = new Option("test", "test", false, "description");
    	option.numberOfArgs = 0;
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		option.addValueForProcessing("test test test test test test");
        });

        String expected = "Cannot add value, list full.";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }
    
    @Test
    void testgetValueNoValues() {
    	Option option = new Option("test", "test", false, "description");
    	assertEquals(option.getValue(), null);
    }
    
    @Test
    void testgetValueIndexNoValues() {
    	Option option = new Option("test", "test", false, "description");
    	assertEquals(option.getValue(0), null);
    }
    
    @Test
    void testgetValueIndexIsNotNull() {
    	Option option = new Option("test", "test", true, "description");
    	option.valuesep = (' ');
    	option.numberOfArgs = 10;
    	String expected = "test2";
    	option.addValueForProcessing("test1 test2");
    	assertEquals(option.getValue(1), expected);
    }
    
    @Test
    void testGetValueIndexIndexError() {
    	Option option = new Option("test", "test", true, "description");
    	option.valuesep = (' ');
    	option.addValueForProcessing("test1 test2");
    	
    	assertThrows(IndexOutOfBoundsException.class, () -> {
    		option.getValue(1);
        });
    }
    
    @Test
    void testGetValueReturnsDefault() {
    	Option option = new Option("test", "test", true, "description");
    	String expected = "default";
    	assertEquals(option.getValue("default"), expected);
    }
    
    @Test
    void testGetValueReturnsNotDefault() {
    	Option option = new Option("test", "test", true, "description");
    	option.valuesep = (' ');
    	option.addValueForProcessing("test1 test2");
    	String expected = "test1 test2";
    	assertEquals(option.getValue("default"), expected);
    }
    
    @Test
    void testGetValuesReturnNull() {
    	Option option = new Option("test", "test", true, "description");
    	assertEquals(option.getValues(), null);
    }
    
    @Test
    void testGetValuesList() {
    	Option option = new Option("test", "test", true, "description");
    	option.addValueForProcessing("test1 test2");
    	List<String> expected = new ArrayList<String>();
    	expected.add("test1 test2");
    	assertEquals(option.getValuesList(), expected);
    }
    
    @Test
    void testToStringNoValues() {
    	Option option = new Option("test", "test", true, "description");
    	String expected = "[ option: test test  [ARG] :: description :: class java.lang.String ]";
    	assertEquals(option.toString(), expected);
    }
    
    @Test
    void testToStringLongOptNull() {
    	Option option = new Option("test", null, true, "description");
    	String expected = "[ option: test  [ARG] :: description :: class java.lang.String ]";
    	assertEquals(option.toString(), expected);
    }
    
    @Test
    void testToStringHasArgs() {
    	Option option = new Option("test", "test", true, "description");
        option.numberOfArgs = -2;
        String expected = "[ option: test test [ARG...] :: description :: class java.lang.String ]";
        assertEquals(option.toString(), expected);
    }
    
    @Test
    void testToStringTypeNULL() {
    	Option option = new Option("test", "test", true, "description");
        option.setType(null);
        String expected = "[ option: test test  [ARG] :: description ]";
        assertEquals(option.toString(), expected);
    }
    
    @Test
    void testToStringNoArg() {
    	Option option = new Option("test", "test", true, "description");
        option.numberOfArgs = 0;
        String expected = "[ option: test test  :: description :: class java.lang.String ]";
        assertEquals(option.toString(), expected);
    }
    
    @Test
    void testEqualsSameObject() {
    	Option option = new Option("test", "test", true, "description");
    	assertEquals(option.equals(option), true);
    }
    
    @Test
    void testEqualsSameTypeDifferentDescription() {
    	Option option = new Option("test", null, true, "description");
    	Option option2 = new Option("test", null, true, "description2");
    	assertEquals(option.equals(option2), true);
    }
    
    @Test
    void testEqualsNull() {
    	Option option = new Option("test", "test", true, "description");
    	assertEquals(option.equals(null), false);
    }
    
    @Test
    void testEqualsDifferentClass() {
    	Option option = new Option("test", "test", true, "description");
    	Options ops = new Options();
    	assertEquals(option.equals(ops), false);
    }
    
    @Test
    void testEqualsOptBothNull() {
    	Option option = new Option(null, "test", true, "description");
    	Option option2 = new Option(null, "test2", true, "description2");
    	assertEquals(option.equals(option2), false);
    }
    
    @Test
    void testEqualsOptFirstNull() {
    	Option option = new Option(null, "test", true, "description");
    	Option option2 = new Option("test2", "test2", true, "description2");
    	assertEquals(option.equals(option2), false);
    }
    
    @Test
    void testEqualsOptNotSame() {
    	Option option = new Option("test", "test", true, "description");
    	Option option2 = new Option("test2", "test2", true, "description2");
    	assertEquals(option.equals(option2), false);
    }
    
    @Test
    void testEqualsLongOptFirstNull() {
    	Option option = new Option("test", null, true, "description");
    	Option option2 = new Option("test", "test2", true, "description2");
    	assertEquals(option.equals(option2), false);
    }
    
    @Test
    void testEqualsLongOptBothNull() {
    	Option option = new Option("test", null, true, "description");
    	Option option2 = new Option("test", null, true, "description2");
    	assertEquals(option.equals(option2), true);
    }
    
    @Test
    void testEqualsLongOptNotSame() {
    	Option option = new Option("test", "test", true, "description");
    	Option option2 = new Option("test", "test2", true, "description2");
    	assertEquals(option.equals(option2), false);
    }
    
    
    @Test
    void testHashCodeSameCode() {
    	Option option = new Option("test", "test", true, "description");
    	int expected = 113807936;
    	assertEquals(option.hashCode(), expected);
    }
    
    @Test
    void testHashCodeOptAndLongOptNull() {
    	Option option = new Option(null, null, true, "description");
    	int expected = 0;
    	assertEquals(option.hashCode(), expected);
    }
    
    @Test
    void testCloneSuccess() {
    	Option option = new Option("test", "test", true, "description");
    	Option option2 = (Option) option.clone();
    	assertEquals(option2, option);
    }
    
    
    @Test
    void testclearValues() {
    	Option option = new Option("test", "test", true, "description");
    	option.addValueForProcessing("test1 test2");
    	option.clearValues();
    	assertEquals(option.getValues(), null);
    }
    
    @Test
    void testAcceptsArgNumberArgsMinus2() {
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = -2;
    	assertTrue(option.acceptsArg());
    }
    
    @Test
    void testAcceptsArgNumberArgsTenValues() {
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = 10;
    	option.addValueForProcessing("test");
    	option.addValueForProcessing("test1");
    	option.addValueForProcessing("test2");
    	option.addValueForProcessing("test3");
    	option.addValueForProcessing("test4");
    	option.addValueForProcessing("test5");
    	option.addValueForProcessing("test6");
    	option.addValueForProcessing("test7");
    	option.addValueForProcessing("test8");
    	option.addValueForProcessing("test9");
    	
    	assertFalse(option.acceptsArg());
    }
    
    @Test
    void testAcceptsArgOptionalArgTrue() {
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = 1;
    	option.addValueForProcessing("test");
    	option.numberOfArgs = 0;
    	option.optionalArg = true;
    	assertTrue(option.acceptsArg());
    }
    
    @Test
    void testrequiresArgOptionalArgTrue(){
    	Option option = new Option("test", "test", true, "description");
    	option.optionalArg = true;
    	assertFalse(option.requiresArg());
    }
    
    @Test
    void testrequiresArgOptionalArgFalse() {
    	Option option = new Option("test", "test", true, "description");
    	option.optionalArg = false;
    	assertTrue(option.requiresArg());
    }
    
    @Test
    void testrequiresArgNumberArgUnlimited() {
    	Option option = new Option("test", "test", true, "description");
    	option.numberOfArgs = -2;
    	assertTrue(option.requiresArg());
    }
    
    /*
     * Currently 96%
     * 
     * TODO
     * - Clone exception - never thrown so impossible
     * 		- https://stackoverflow.com/questions/5430944/java-clone-operation-calling-super-clone
     */
    
    
    
    
    
}
