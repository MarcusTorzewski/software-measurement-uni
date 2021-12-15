package edu.leicester.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;

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
    
    @Test
    void testPrintUsageWithOptions() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o1 = new Option("Option_1", "Nice option");
    		Option o2 = new Option("Option_2", "Good option");
    		Options options = new Options();
    		options.addOption(o1);
    		options.addOption(o2);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion_1");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ]");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion_2");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndOptionComparatorNull() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
            Comparator<Option> optionComparator = null;
            formatter.setOptionComparator(optionComparator);

            Comparator<Option> expected = null;
            final Comparator<Option> actual = formatter.getOptionComparator();
            assertEquals(expected, actual);
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o1 = new Option("Option_1", "Nice option");
    		Option o2 = new Option("Option_2", "Good option");
    		Options options = new Options();
    		options.addOption(o1);
    		options.addOption(o2);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expectedSb = new StringBuffer();
    		expectedSb.append("usage:");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" test");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" [-Opt");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" ion_1");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" ]");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" [-Opt");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" ion_2");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" ]");
    		expectedSb.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actualSb = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actualSb.append(data);
    			actualSb.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expectedSb.length(), actualSb.length());
    		assertEquals(expectedSb.toString(), actualSb.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    @Test
    void testPrintUsageWithOptionsAndEachOptionRequired() {
        try {
            HelpFormatter formatter = new HelpFormatter();
            File file = new File("test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            final int width = 6;
            final String text = "test";
            Option o1 = new Option("Option_1", "Nice option");
            Option o2 = new Option("Option_2", "Good option");
            o1.required = true;
            o2.required = true;
            Options options = new Options();
            options.addOption(o1);
            options.addOption(o2);
            formatter.printUsage(pw, width, text, options);
            pw.close();

            final StringBuffer expected = new StringBuffer();
            expected.append("usage:");
            expected.append(System.getProperty("line.separator"));
            expected.append(" test");
            expected.append(System.getProperty("line.separator"));
            expected.append(" -Opti");
            expected.append(System.getProperty("line.separator"));
            expected.append(" on_1");
            expected.append(System.getProperty("line.separator"));
            expected.append(" -Opti");
            expected.append(System.getProperty("line.separator"));
            expected.append(" on_2");
            expected.append(System.getProperty("line.separator"));

            final StringBuffer actual = new StringBuffer();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                actual.append(data);
                actual.append(System.getProperty("line.separator"));
            }
            myReader.close();
            file.delete();
            
            assertEquals(expected.length(), actual.length());
            assertEquals(expected.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testPrintUsageWithOptionsAndOptionNamesNullAndHasArgFalseAndHasLongOpt() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option(null, "A New Option", false, "Nice option");
    		Options options = new Options();
    		options.addOption(o);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [--A");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" New");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" Optio");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" n]");
    		expected.append(System.getProperty("line.separator"));

    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndHasArgTrueAndHasLongOptAndArgNameNull() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option("Option", "A New Option", true, "Nice option");
    		Options options = new Options();
    		options.addOption(o);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion");
    		expected.append(System.getProperty("line.separator"));
            expected.append(" <arg>");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndHasArgTrueAndHasLongOptAndArgNameEmptyString() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option("Option", "A New Option", true, "Nice option");
            o.setArgName("");
    		Options options = new Options();
    		options.addOption(o);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndHasArgTrueAndHasLongOptAndArgName() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option("Option", "A New Option", true, "Nice option");
            o.setArgName("Time");
    		Options options = new Options();
    		options.addOption(o);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion");
    		expected.append(System.getProperty("line.separator"));
            expected.append(" <Time");
    		expected.append(System.getProperty("line.separator"));
            expected.append(" >]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndHasArgTrueAndHasLongOptAndArgNameNullAndOptionNameNull() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option(null, "A New Option", true, "Nice option");
    		Options options = new Options();
    		options.addOption(o);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [--A");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" New");
    		expected.append(System.getProperty("line.separator"));
            expected.append(" Optio");
    		expected.append(System.getProperty("line.separator"));
            expected.append(" n");
    		expected.append(System.getProperty("line.separator"));
            expected.append(" <arg>");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndGroupOption() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option("Option", "Nice option");
            OptionGroup optionGroup = new OptionGroup();
            optionGroup.addOption(o);
    		Options options = new Options();
    		options.addOption(o);
            options.addOptionGroup(optionGroup);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndGroupOptionWithMultipleOptions() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o1 = new Option("Option_1", "Nice option");
    		Option o2 = new Option("Option_2", "Good option");
            OptionGroup optionGroup = new OptionGroup();
            optionGroup.addOption(o1);
            optionGroup.addOption(o2);
    		Options options = new Options();
    		options.addOption(o1);
    		options.addOption(o2);
            options.addOptionGroup(optionGroup);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" [-Opt");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" ion_1");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" |");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" -Opti");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" on_2]");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		// assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndGroupOptionAndOptionGroupRequired() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option("Option", "Nice option");
            OptionGroup optionGroup = new OptionGroup();
            optionGroup.addOption(o);
            optionGroup.setRequired(true);
    		Options options = new Options();
    		options.addOption(o);
            options.addOptionGroup(optionGroup);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expected = new StringBuffer();
    		expected.append("usage:");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" test");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" -Opti");
    		expected.append(System.getProperty("line.separator"));
    		expected.append(" on");
    		expected.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actual = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actual.append(data);
    			actual.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expected.length(), actual.length());
    		assertEquals(expected.toString(), actual.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    void testPrintUsageWithOptionsAndGroupOptionAndOptionComparatorNull() {
    	try {
    		HelpFormatter formatter = new HelpFormatter();
            Comparator<Option> optionComparator = null;
            formatter.setOptionComparator(optionComparator);

            Comparator<Option> expected = null;
            final Comparator<Option> actual = formatter.getOptionComparator();
    		File file = new File("test.txt");
    		file.createNewFile();
    		PrintWriter pw = new PrintWriter(file);
    		final int width = 6;
    		final String text = "test";
    		Option o = new Option("Option", "Nice option");
            OptionGroup optionGroup = new OptionGroup();
            optionGroup.addOption(o);
    		Options options = new Options();
    		options.addOption(o);
            options.addOptionGroup(optionGroup);
    		formatter.printUsage(pw, width, text, options);
    		pw.close();
    		
    		final StringBuffer expectedSb = new StringBuffer();
    		expectedSb.append("usage:");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" test");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" [-Opt");
    		expectedSb.append(System.getProperty("line.separator"));
    		expectedSb.append(" ion]");
    		expectedSb.append(System.getProperty("line.separator"));
    		
    		final StringBuffer actualSb = new StringBuffer();
    		Scanner myReader = new Scanner(file);
    		while (myReader.hasNextLine()) {
    			String data = myReader.nextLine();
    			actualSb.append(data);
    			actualSb.append(System.getProperty("line.separator"));
    		}
    		myReader.close();
    		file.delete();
    		
    		assertEquals(expectedSb.length(), actualSb.length());
    		assertEquals(expectedSb.toString(), actualSb.toString());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    @Test
    void testPrintUsageWithoutOptions() {
        try {
            HelpFormatter formatter = new HelpFormatter();
            File file = new File("test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            final int width = 6;
            final String text = "test";
            formatter.printUsage(pw, width, text);
            pw.close();

            final StringBuffer expected = new StringBuffer();
            expected.append("usage:");
            expected.append(System.getProperty("line.separator"));
            expected.append(" test");
            expected.append(System.getProperty("line.separator"));

            final StringBuffer actual = new StringBuffer();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                actual.append(data);
                actual.append(System.getProperty("line.separator"));
            }
            myReader.close();
            file.delete();
            
            assertEquals(expected.length(), actual.length());
            assertEquals(expected.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    void testPrintOptions() {
        try {
            HelpFormatter formatter = new HelpFormatter();
            File file = new File("test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            final int width = 9;
            Option o1 = new Option("Option_1", "Nice option");
            Option o2 = new Option("Option_2", "Good option");
            Options options = new Options();
            options.addOption(o1);
            options.addOption(o2);
            final int leftPad = 0;
            final int descPad = 0;
            formatter.printOptions(pw, width, options, leftPad, descPad);
            pw.close();
            
            final StringBuffer expected = new StringBuffer();
            expected.append("-Option_1");
            expected.append(System.getProperty("line.separator"));
            expected.append(" Nice");
            expected.append(System.getProperty("line.separator"));
            expected.append(" option");
            expected.append(System.getProperty("line.separator"));
            expected.append("-Option_2");
            expected.append(System.getProperty("line.separator"));
            expected.append(" Good");
            expected.append(System.getProperty("line.separator"));
            expected.append(" option");
            expected.append(System.getProperty("line.separator"));

            final StringBuffer actual = new StringBuffer();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                actual.append(data);
                actual.append(System.getProperty("line.separator"));
            }
            myReader.close();
            file.delete();
            
            assertEquals(expected.length(), actual.length());
            assertEquals(expected.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testPrintWrappedWithoutNextLineTabStop() {
        try {
            HelpFormatter formatter = new HelpFormatter();
            File file = new File("test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            final int width = 5;
            final String text = "hello";
            formatter.printWrapped(pw, width, text);
            pw.close();

            final StringBuffer expected = new StringBuffer();
            expected.append("hello");
            expected.append(System.getProperty("line.separator"));

            final StringBuffer actual = new StringBuffer();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                actual.append(data);
                actual.append(System.getProperty("line.separator"));
            }
            myReader.close();
            file.delete();
            
            assertEquals(expected.length(), actual.length());
            assertEquals(expected.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testPrintWrappedWithNextLineTabStop() {
        try {
            HelpFormatter formatter = new HelpFormatter();
            File file = new File("test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            final int width = 5;
            final int nextLineTabStop = 0;
            final String text = "hello";
            formatter.printWrapped(pw, width, nextLineTabStop, text);
            pw.close();

            final StringBuffer expected = new StringBuffer();
            expected.append("hello");
            expected.append(System.getProperty("line.separator"));

            final StringBuffer actual = new StringBuffer();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                actual.append(data);
                actual.append(System.getProperty("line.separator"));
            }
            myReader.close();
            file.delete();
            
            assertEquals(expected.length(), actual.length());
            assertEquals(expected.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testPrintWrappedWithNextLineTabStopAndNewLineInMiddleOfText() {
        try {
            HelpFormatter formatter = new HelpFormatter();
            File file = new File("test.txt");
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            final int width = 5;
            final int nextLineTabStop = 0;
            final String text = "hello\nthere";
            formatter.printWrapped(pw, width, nextLineTabStop, text);
            pw.close();

            final StringBuffer expected = new StringBuffer();
            expected.append("hello");
            expected.append(System.getProperty("line.separator"));
            expected.append("there");
            expected.append(System.getProperty("line.separator"));

            final StringBuffer actual = new StringBuffer();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                actual.append(data);
                actual.append(System.getProperty("line.separator"));
            }
            myReader.close();
            file.delete();
            
            assertEquals(expected.length(), actual.length());
            assertEquals(expected.toString(), actual.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testRenderOptionsWithSetOptionComparatorNull() {
    	HelpFormatter formatter = new HelpFormatter();
        Comparator<Option> optionComparator = null;
        formatter.setOptionComparator(optionComparator);

        Comparator<Option> expected = null;
        final Comparator<Option> actual = formatter.getOptionComparator();
        assertEquals(expected, actual);
        final StringBuffer sb = new StringBuffer();
    	final int width = 9;
        Option o1 = new Option("Option_1", "Nice option");
        Option o2 = new Option("Option_2", "Good option");
        Options options = new Options();
        options.addOption(o1);
        options.addOption(o2);
    	final int leftPad = 0;
    	final int descPad = 0;
        final StringBuffer expectedSb = new StringBuffer();
        expectedSb.append("-Option_1");
        expectedSb.append(System.getProperty("line.separator"));
        expectedSb.append(" Nice");
        expectedSb.append(System.getProperty("line.separator"));
        expectedSb.append(" option");
        expectedSb.append(System.getProperty("line.separator"));
        expectedSb.append("-Option_2");
        expectedSb.append(System.getProperty("line.separator"));
        expectedSb.append(" Good");
        expectedSb.append(System.getProperty("line.separator"));
        expectedSb.append(" option");
    	final StringBuffer actualSb = formatter.renderOptions(sb, width, options, leftPad, descPad);
    	assertEquals(expectedSb.length(), actualSb.length());
        assertEquals(expectedSb.toString(), actualSb.toString());
    }

    @Test
    void testRenderOptionsWithWithOptionNameNullAndHasArgFalseAndHasLongOpt() {
        HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
        final int width = 9;
        Option o = new Option(null, "Option One", false, "Nice option");
        Options options = new Options();
        options.addOption(o);
        final int leftPad = 0;
        final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append(System.getProperty("line.separator"));
        expected.append(" --Option");
        expected.append(System.getProperty("line.separator"));
        expected.append(" OneNice");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
        final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
        assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testRenderOptionsWithWithOptionDescriptionNull() {
        HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
        final int width = 9;
        Option o = new Option("Option", null);
        Options options = new Options();
        options.addOption(o);
        final int leftPad = 0;
        final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append("-Option");
        final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
        assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testRenderOptionsWithHasArgFalseAndHasLongOpt() {
    	HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
    	final int width = 9;
        Option o1 = new Option("Option_1", "Option One", false, "Nice option");
        Option o2 = new Option("Option_2", "Option Two", false, "Good option");
        Options options = new Options();
        options.addOption(o1);
        options.addOption(o2);
    	final int leftPad = 0;
    	final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append("-Option_1");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ,--Optio");
        expected.append(System.getProperty("line.separator"));
        expected.append(" n");
        expected.append(System.getProperty("line.separator"));
        expected.append(" OneNice");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
        expected.append(System.getProperty("line.separator"));
        expected.append("-Option_2");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ,--Optio");
        expected.append(System.getProperty("line.separator"));
        expected.append(" n");
        expected.append(System.getProperty("line.separator"));
        expected.append(" TwoGood");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
    	final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
    	assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testRenderOptionsWithHasArgTrueAndArgNameNull() {
    	HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
    	final int width = 9;
        Option o1 = new Option("Option_1", true, "Nice option");
        Option o2 = new Option("Option_2", true, "Good option");
        Options options = new Options();
        options.addOption(o1);
        options.addOption(o2);
    	final int leftPad = 0;
    	final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append("-Option_1");
        expected.append(System.getProperty("line.separator"));
        expected.append(" <arg>Nic");
        expected.append(System.getProperty("line.separator"));
        expected.append(" e option");
        expected.append(System.getProperty("line.separator"));
        expected.append("-Option_2");
        expected.append(System.getProperty("line.separator"));
        expected.append(" <arg>Goo");
        expected.append(System.getProperty("line.separator"));
        expected.append(" d option");
    	final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
    	assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testRenderOptionsWithHasArgTrueAndHasLongOptAndArgNameNull() {
    	HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
    	final int width = 9;
        Option o1 = new Option("Option_1", "Option One", true, "Nice option");
        Option o2 = new Option("Option_2", "Option Two", true, "Good option");
        Options options = new Options();
        options.addOption(o1);
        options.addOption(o2);
    	final int leftPad = 0;
    	final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append("-Option_1");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ,--Optio");
        expected.append(System.getProperty("line.separator"));
        expected.append(" n One");
        expected.append(System.getProperty("line.separator"));
        expected.append(" <arg>Nic");
        expected.append(System.getProperty("line.separator"));
        expected.append(" e option");
        expected.append(System.getProperty("line.separator"));
        expected.append("-Option_2");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ,--Optio");
        expected.append(System.getProperty("line.separator"));
        expected.append(" n Two");
        expected.append(System.getProperty("line.separator"));
        expected.append(" <arg>Goo");
        expected.append(System.getProperty("line.separator"));
        expected.append(" d option");
    	final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
    	assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testRenderOptionsWithHasArgTrueAndArgNameEmptyString() {
    	HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
    	final int width = 9;
        Option o1 = new Option("Option_1", true, "Nice option");
        Option o2 = new Option("Option_2", true, "Good option");
        o1.setArgName("");
        o2.setArgName("");
        Options options = new Options();
        options.addOption(o1);
        options.addOption(o2);
    	final int leftPad = 0;
    	final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append("-Option_1");
        expected.append(System.getProperty("line.separator"));
        expected.append(" Nice");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
        expected.append(System.getProperty("line.separator"));
        expected.append("-Option_2");
        expected.append(System.getProperty("line.separator"));
        expected.append(" Good");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
    	final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
    	assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testRenderOptionsWithHasArgTrueAndHasLongOptAndArgName() {
    	HelpFormatter formatter = new HelpFormatter();
        final StringBuffer sb = new StringBuffer();
    	final int width = 9;
        Option o1 = new Option("Option_1", "Option One", true, "Nice option");
        Option o2 = new Option("Option_2", "Option Two", true, "Good option");
        o1.setArgName("Time");
        o2.setArgName("Time");
        Options options = new Options();
        options.addOption(o1);
        options.addOption(o2);
    	final int leftPad = 0;
    	final int descPad = 0;
        final StringBuffer expected = new StringBuffer();
        expected.append("-Option_1");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ,--Optio");
        expected.append(System.getProperty("line.separator"));
        expected.append(" n One");
        expected.append(System.getProperty("line.separator"));
        expected.append(" <Time>Ni");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ce");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
        expected.append(System.getProperty("line.separator"));
        expected.append("-Option_2");
        expected.append(System.getProperty("line.separator"));
        expected.append(" ,--Optio");
        expected.append(System.getProperty("line.separator"));
        expected.append(" n Two");
        expected.append(System.getProperty("line.separator"));
        expected.append(" <Time>Go");
        expected.append(System.getProperty("line.separator"));
        expected.append(" od");
        expected.append(System.getProperty("line.separator"));
        expected.append(" option");
    	final StringBuffer actual = formatter.renderOptions(sb, width, options, leftPad, descPad);
    	assertEquals(expected.length(), actual.length());
        assertEquals(expected.toString(), actual.toString());
    }

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