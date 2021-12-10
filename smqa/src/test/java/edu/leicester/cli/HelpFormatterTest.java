package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Comparator;

class HelpFormatterTest {

    @Test
    void testSetOptionComparatorAndGetOptionComparatorWithNullInput() {
        HelpFormatter formatter = new HelpFormatter();
        Comparator<Option> optionComparator = null;
        formatter.setOptionComparator(optionComparator);

        Comparator<Option> expected = null;
        final Comparator<Option> actual = formatter.getOptionComparator();
        assertEquals(expected, actual);
    }

    // @Test
    // void testSetOptionComparatorAndGetOptionComparator() {
    //     HelpFormatter formatter = new HelpFormatter();
    //     Comparator<Option> optionComparator = null;
    //     formatter.setOptionComparator(optionComparator);

    //     Comparator<Option> expected = null;
    //     final Comparator<Option> actual = formatter.getOptionComparator();
    //     assertEquals(expected, actual);
    // }

    // @Test
    // void testHelpFormatterWithStringWriter() {
    //     HelpFormatter formatter = new HelpFormatter();
    //     StringWriter sw = new StringWriter();
    //     PrintWriter pw = new PrintWriter(sw);
    //     formatter.printWrapped(pw, 123, "hello");
    //     String test = sw.toString();
    //     assertEquals("hello\n", test);
    // }
    
    @Test
    void testRenderWrappedTextWithWidthEqualToStringLengthAndPlainString() {
    	HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
    	final int width = 5;
    	final int nextLineTabStop = 0;
    	final String text = "hello";
        final StringBuffer expected = new StringBuffer("hello");
    	final StringBuffer actual = formatter.renderWrappedText(sb, width, nextLineTabStop, text);
    	assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }
    
   @Test
   void testRenderWrappedTextWithNewLineWithinWidthAndNextLineTabStop0() {
        HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
        final int width = 6;
        final int nextLineTabStop = 0;
        final String text = "hello\n";
        final StringBuffer expected = new StringBuffer();
        expected.append("hello");
        expected.append(System.getProperty("line.separator"));
        final StringBuffer actual = formatter.renderWrappedText(sb, width, nextLineTabStop, text);
        assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
   }
   
   @Test
   void testRenderWrappedTextWithNewLineWithinWidthAndNextLineTabStopGreaterThanWidth() {
	   HelpFormatter formatter = new HelpFormatter();
	   final StringBuffer sb = new StringBuffer();
	   final int width = 6;
	   final int nextLineTabStop = 10;
	   final String text = "hello\n";
	   final StringBuffer expected = new StringBuffer();
	   expected.append("hello");
	   expected.append(System.getProperty("line.separator"));
	   expected.append(" ");
	   final StringBuffer actual = formatter.renderWrappedText(sb, width, nextLineTabStop, text);
	   assertEquals(expected.length(), actual.length());
	   assertEquals(expected.toString(), actual.toString());
   }


   @Test
   void testRenderWrappedTextWithWidthLessThanStringLengthAndWhitespaceWithinWidthAndNextLineTabStopLessThanWidthAndTextAfterWhitespace() {
        HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
        final int width = 8;
        final int nextLineTabStop = 4;
        final String text = "hello there";
        final StringBuffer expected = new StringBuffer();
        expected.append("hello");
        expected.append(System.getProperty("line.separator"));
        expected.append("    ther");
        expected.append(System.getProperty("line.separator"));
        expected.append("    e");
        final StringBuffer actual = formatter.renderWrappedText(sb, width, nextLineTabStop, text);
        assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
   }
   
   @Test
   void testRenderWrappedTextWithWidthLessThanStringLengthAndWhitespaceWithinWidthAndNextLineTabStopLessThanWidthAndTextAfterWhitespaceFollowedByWhitespaceAndText() {
        HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
        final int width = 5;
        final int nextLineTabStop = 2;
        final String text = "Good Morning Hi";
        final StringBuffer expected = new StringBuffer();
        expected.append("Good");
        expected.append(System.getProperty("line.separator"));
        expected.append("  Mor");
        expected.append(System.getProperty("line.separator"));
        expected.append("  nin");
        expected.append(System.getProperty("line.separator"));
        expected.append("  g");
        expected.append(System.getProperty("line.separator"));
        expected.append("  Hi");
        final StringBuffer actual = formatter.renderWrappedText(sb, width, nextLineTabStop, text);
         assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
   }
   
   @Test
   void testRenderWrappedTextWithWidthLessThanStringLengthAndWhitespaceWithinWidthAndNextLineTabStopLessThanWidthAndTextAfterWhitespaceWithTabWithWidthAndPaddingPlusTestLessThanWidth() {
        HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
        final int width = 8;
        final int nextLineTabStop = 2;
        final String text = "Morning a\tb";
        final StringBuffer expected = new StringBuffer();
        expected.append("Morning");
        expected.append(System.getProperty("line.separator"));
        expected.append("  a");
        expected.append(System.getProperty("line.separator"));
        expected.append("  b");
        final StringBuffer actual = formatter.renderWrappedText(sb, width, nextLineTabStop, text);
          assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
   }

    @Test
    void testFindWrapPosWithNewLineWithinWidth() {
    	HelpFormatter formatter = new HelpFormatter();
    	final String text = "hello\n";
    	final int width = 7;
    	final int startPos = 0;
    	final int expected = 6;
    	final int actual = formatter.findWrapPos(text, width, startPos);
    	assertEquals(expected, actual);
    }

     @Test
     void testFindWrapPosWithWithNewLineOutsideWidth() {
     	HelpFormatter formatter = new HelpFormatter();
     	final String text = "hello\n";
     	final int width = 4;
     	final int startPos = 0;
     	final int expected = 4;
     	final int actual = formatter.findWrapPos(text, width, startPos);
     	assertEquals(expected, actual);
     }

    @Test
    void testFindWrapPosWithTabWithinWidth() {
        HelpFormatter formatter = new HelpFormatter();
        final String text = "hello\t";
        final int width = 7;
        final int startPos = 0;
        final int expected = 6;
        final int actual = formatter.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    @Test
    void testFindWrapPosWithTabOutsideWidth() {
        HelpFormatter formatter = new HelpFormatter();
        final String text = "hello\t";
        final int width = 4;
        final int startPos = 0;
        final int expected = 4;
        final int actual = formatter.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }

    @Test
    void testFindWrapPosWithWidthEqualToStringLengthAndPlainString() {
        HelpFormatter formatter = new HelpFormatter();
        final String text = "Hello";
        final int width = 5;
        final int startPos = 0;
        final int expected = -1;
        final int actual = formatter.findWrapPos(text, width, startPos);
        assertEquals(expected, actual);
    }
    
     @Test
     void testFindWrapPosWithWidthLessThanStringLengthAndWhitespaceWithinWidth() {
         HelpFormatter formatter = new HelpFormatter();
         final String text = "hello there";
         final int width = 10;
         final int startPos = 0;
         final int expected = 5;
         final int actual = formatter.findWrapPos(text, width, startPos);
         assertEquals(expected, actual);
     }
    
     @Test
     void testFindWrapPosWithWidthLessThanStringLengthAndCarriageReturnWithinWidth() {
         HelpFormatter formatter = new HelpFormatter();
         final String text = "hello\rthere";
         final int width = 10;
         final int startPos = 0;
         final int expected = 5;
         final int actual = formatter.findWrapPos(text, width, startPos);
         assertEquals(expected, actual);
     }

    @Test
    void testFindWrapPosWithWithNewLineOutsideWidthAndWidthLessThanStringLengthAndCarriageReturnWithinWidth() {
    	HelpFormatter formatter = new HelpFormatter();
    	final String text = "hello\nthere";
    	final int width = 4;
    	final int startPos = 5;
    	final int expected = 9;
    	final int actual = formatter.findWrapPos(text, width, startPos);
    	assertEquals(expected, actual);
    }

    @Test
    void testCreatePadding() {
        HelpFormatter formatter = new HelpFormatter();
        final int padding = 4;
        final String expected = "    ";
        final String actual = formatter.createPadding(padding);
        assertEquals(expected, actual);
    }

    @Test
    void testRtrimWithEmptyString() {
        HelpFormatter formatter = new HelpFormatter();
        final String s = "";
        final String expected = "";
        final String actual = formatter.rtrim(s);
        assertEquals(expected, actual);
    }

    @Test
    void testRtrimWithNullInput() {
        HelpFormatter formatter = new HelpFormatter();
        final String s = null;
        final String expected = null;
        final String actual = formatter.rtrim(s);
        assertEquals(expected, actual);
    }

    @Test
    void testRtrimWithStringWithWhitespaceAtEnd() {
        HelpFormatter formatter = new HelpFormatter();
        final String s = "hello   ";
        final String expected = "hello";
        final String actual = formatter.rtrim(s);
        assertEquals(expected, actual);
    }
    
    @Test
    void testRtrimWithStringWithOnlyWhitespace() {
        HelpFormatter formatter = new HelpFormatter();
        final String s = "   ";
        final String expected = "";
        final String actual = formatter.rtrim(s);
        assertEquals(expected, actual);
    }
}