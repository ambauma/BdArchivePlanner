package org.baumann.andrew.archive.planner;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.baumann.andrew.archive.planner.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

public final class MainTest {

    @Rule
    public final StandardOutputStreamLog out = new StandardOutputStreamLog();
    @Rule
    public final StandardErrorStreamLog err = new StandardErrorStreamLog();
	
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testMainNoInput() {
		Main.main(new String[]{});
		assertEquals(
		        "Defaulting disc size to 25GB." + System.lineSeparator()
		        + "Processing complete." + System.lineSeparator(), 
		        out.getLog());
        assertEquals("", err.getLog());
	}
	
    @Test
    public void testMainShortHelpOption() {
        Main.main(new String[] { "-h" });
        assertEquals("usage: BdArchivePlanner" + System.lineSeparator() 
                + " -e,--exclude <arg>   Folders to exclude" + System.lineSeparator()
                + " -h,--help            List commands for this application"  + System.lineSeparator() 
                + " -i,--include <arg>   Folders to include" + System.lineSeparator()
                + " -n,--number <arg>    Sets the number of discs available" + System.lineSeparator()
                + " -o,--output <arg>    File name with path for output"  + System.lineSeparator() 
                + " -s,--size <arg>      Sets the disc size" + System.lineSeparator(),
                out.getLog());
        assertEquals("", err.getLog());
    }
    
    @Test
    public void testMainLongHelpOption() {
        Main.main(new String[] { "--help" });
        assertEquals("usage: BdArchivePlanner" + System.lineSeparator() 
                + " -e,--exclude <arg>   Folders to exclude" + System.lineSeparator()
                + " -h,--help            List commands for this application"  + System.lineSeparator() 
                + " -i,--include <arg>   Folders to include" + System.lineSeparator()
                + " -n,--number <arg>    Sets the number of discs available" + System.lineSeparator()
                + " -o,--output <arg>    File name with path for output"  + System.lineSeparator() 
                + " -s,--size <arg>      Sets the disc size" + System.lineSeparator(),
                out.getLog());
        assertEquals("", err.getLog());
    }
    
    @Ignore //See https://issues.apache.org/jira/browse/CLI-6
    @Test
    public void testMainBadHelpOption() {
        Main.main(new String[] {"help"});
        assertEquals("", out.getLog());
        assertEquals("", err.getLog());
    }

    @Test
    public void testSetDiscNoSize() {
        Main.main(new String[]{"-s"});
        assertEquals("", out.getLog());
        assertEquals("Command Parsing failed.  Reason:  Missing argument for option: s" + System.lineSeparator(), err.getLog());
    }
    
    @Test
    public void testSetDiscSizeTooShort() {
        Main.main(new String[]{"-s","2B"});
        assertEquals("", out.getLog());
        assertEquals("Improper size of \"2B\" given.  Acceptable example: --size 25GB " + System.lineSeparator(), err.getLog());
    }
    
    @Test
    public void testSetDiscSizeNotNumericSmall() {
        Main.main(new String[]{"-s","GB"});
        assertEquals("", out.getLog());
        assertEquals("Improper size of \"GB\" given.  Acceptable example: --size 25GB " + System.lineSeparator(), err.getLog());
    }
    
    @Test
    public void testSetDiscSize1GB() {
        Main.main(new String[]{"-s","1GB"});
        assertEquals("Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }
    
    @Test
    public void testSetDiscSizeNotNumeric() {
        Main.main(new String[]{"-s","oneGB"});
        assertEquals("", out.getLog());
        assertEquals("Improper size of \"oneGB\" given.  Acceptable example: --size 25GB " + System.lineSeparator(), err.getLog());
    }
    
    @Test
    public void testSetDiscSizeBadUnit() {
        Main.main(new String[]{"-s","24es"});
        assertEquals("", out.getLog());
        assertEquals("Unsupported unit of \"es\".  Supported units:  GB" + System.lineSeparator(), err.getLog());
    }
    
	@Test
	public void testSetDiscSizeShortOption() {
	    Main.main(new String[]{"-s","25GB"});
	    assertEquals("Processing complete." + System.lineSeparator(), out.getLog());
	    assertEquals("", err.getLog());
	}
	
	@Test
    public void testSetDiscSizeLongOption() {
        Main.main(new String[]{"--size","25GB"});
        assertEquals("Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }

}
