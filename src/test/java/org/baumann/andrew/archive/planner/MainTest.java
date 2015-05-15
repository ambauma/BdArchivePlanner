package org.baumann.andrew.archive.planner;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
		        + "Defaulting output to 'output.txt'" + System.lineSeparator() 
		        + "Processing complete." + System.lineSeparator(), 
		        out.getLog());
        assertEquals("", err.getLog());
	}
	
    @Test
    public void testMainShortHelpOption() {
        Main.main(new String[] { "-h" });
        assertEquals("usage: BdArchivePlanner" + System.lineSeparator() 
                + " -e,--exclude <arg>   Folders to exclude, comma separated, and wrapped in" + System.lineSeparator() 
                    + "                      quotes" + System.lineSeparator()
                + " -h,--help            List commands for this application" + System.lineSeparator() 
                + " -i,--include <arg>   Folders to include, comma separated, and wrapped in" + System.lineSeparator() 
                    + "                      quotes" + System.lineSeparator()
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
                + " -e,--exclude <arg>   Folders to exclude, comma separated, and wrapped in" + System.lineSeparator() 
                    + "                      quotes" + System.lineSeparator()
                + " -h,--help            List commands for this application" + System.lineSeparator() 
                + " -i,--include <arg>   Folders to include, comma separated, and wrapped in" + System.lineSeparator() 
                    + "                      quotes" + System.lineSeparator()
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
        assertEquals("Defaulting output to 'output.txt'" + System.lineSeparator()
                + "Processing complete." + System.lineSeparator(), out.getLog());
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
        assertEquals("Unsupported unit of \"es\".  Supported units:  GB, MB, KB" + System.lineSeparator(), err.getLog());
    }
    
	@Test
	public void testSetDiscSizeShortOption() {
	    Main.main(new String[]{"-s","25GB"});
	    assertEquals("Defaulting output to 'output.txt'" + System.lineSeparator()
	            + "Processing complete." + System.lineSeparator(), out.getLog());
	    assertEquals("", err.getLog());
	}
	
	@Test
    public void testSetDiscSizeMB() {
        Main.main(new String[]{"-s","25MB"});
        assertEquals("Defaulting output to 'output.txt'" + System.lineSeparator()
                + "Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }
	
	@Test
    public void testSetDiscSizeKB() {
        Main.main(new String[]{"-s","25KB"});
        assertEquals("Defaulting output to 'output.txt'" + System.lineSeparator()
                + "Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }
	
    @Test
    public void testSetDiscSizeByte() {
        Main.main(new String[] { "-s", "25by" });
        assertEquals(
                "Defaulting output to 'output.txt'" + System.lineSeparator() 
                + "Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }
	
	@Test
    public void testSetDiscSizeLongOption() {
        Main.main(new String[]{"--size","25GB"});
        assertEquals("Defaulting output to 'output.txt'" + System.lineSeparator()
                + "Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }

	@Test
    public void testSetNumberShortOption() {
        Main.main(new String[]{"-n","5"});
        assertEquals("Defaulting disc size to 25GB." + System.lineSeparator()
                + "Defaulting output to 'output.txt'" + System.lineSeparator() 
                + "Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }
	
	@Test
    public void testSetNumberLongOption() {
        Main.main(new String[]{"--number","5"});
        assertEquals("Defaulting disc size to 25GB." + System.lineSeparator()
                + "Defaulting output to 'output.txt'" + System.lineSeparator() 
                + "Processing complete." + System.lineSeparator(), out.getLog());
        assertEquals("", err.getLog());
    }
	
	@Test
    public void testSetNumberNotANumber() {
        Main.main(new String[]{"--number","five"});
        assertEquals("Defaulting disc size to 25GB." + System.lineSeparator(), out.getLog());
        assertEquals("Improper number of \"five\" given.  Acceptable example: --number 5 "
                + System.lineSeparator(), err.getLog());
    }
	
    @Test
    public void testSetIncludeFilesShortOption() {
        Main.main(new String[] { "-i", "\"files\"" });
        assertEquals("", err.getLog());
        List<String> outLines = Arrays.asList(StringUtils.split(out.getLog(), System.lineSeparator()));
        assertEquals(19, outLines.size());
        assertEquals("include:  ", outLines.get(0));
        File here = new File("");
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files', recursing...", outLines.get(1));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder1', recursing...", outLines.get(2));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder1/folder1-1', recursing...", outLines.get(3));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-1.txt', size: 12506 bytes", outLines.get(4));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-2.txt', size: 11760 bytes", outLines.get(5));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-3.txt', size: 5901 bytes", outLines.get(6));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-4.txt', size: 6936 bytes", outLines.get(7));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder1/folder1-2', recursing...", outLines.get(8));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-1.txt', size: 4390 bytes", outLines.get(9));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-2.txt', size: 2703 bytes", outLines.get(10));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-3.txt', size: 7813 bytes", outLines.get(11));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-4.txt', size: 6844 bytes", outLines.get(12));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder2', recursing...", outLines.get(13));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder2/file2-1.txt', size: 487040 bytes", outLines.get(14));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder3'", outLines.get(15));
        assertEquals("Defaulting output to 'output.txt'", outLines.get(17));
        assertEquals("Processing complete.", outLines.get(18));
        System.out.println(here.getAbsolutePath());
    }
    
    @Test
    public void testSetIncludeFilesLongOption() {
        Main.main(new String[] { "--include", "\"files\"" });
        assertEquals("", err.getLog());
        List<String> outLines = Arrays.asList(StringUtils.split(out.getLog(), System.lineSeparator()));
        assertEquals(19, outLines.size());
        assertEquals("include:  ", outLines.get(0));
        File here = new File("");
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files', recursing...", outLines.get(1));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder1', recursing...", outLines.get(2));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder1/folder1-1', recursing...", outLines.get(3));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-1.txt', size: 12506 bytes", outLines.get(4));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-2.txt', size: 11760 bytes", outLines.get(5));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-3.txt', size: 5901 bytes", outLines.get(6));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-1/file1-1-4.txt', size: 6936 bytes", outLines.get(7));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder1/folder1-2', recursing...", outLines.get(8));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-1.txt', size: 4390 bytes", outLines.get(9));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-2.txt', size: 2703 bytes", outLines.get(10));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-3.txt', size: 7813 bytes", outLines.get(11));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder1/folder1-2/file1-2-4.txt', size: 6844 bytes", outLines.get(12));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder2', recursing...", outLines.get(13));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder2/file2-1.txt', size: 487040 bytes", outLines.get(14));
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder3'", outLines.get(15));
        assertEquals("Defaulting disc size to 25GB.", outLines.get(16));
        assertEquals("Defaulting output to 'output.txt'", outLines.get(17));
        assertEquals("Processing complete.", outLines.get(18));
        System.out.println(here.getAbsolutePath());
    }
    
    @Test
    public void testSetIncludeFilesBadFileName() {
        Main.main(new String[] { "--include", "\"duckAndCover\"" });
        assertEquals("Folder 'duckAndCover' does not exist."
                + System.lineSeparator(), err.getLog());
        assertEquals("include:  " + System.lineSeparator(), out.getLog());
    }
    
    @Test
    public void testSetExcludeFilesShortOption() {
        Main.main(new String[] { "-e", "\"files" + File.separator + "folder2\"" });
        assertEquals("", err.getLog());
        List<String> outLines = Arrays.asList(StringUtils.split(out.getLog(), System.lineSeparator()));
        assertEquals(6, outLines.size());
        assertEquals("exclude:  ", outLines.get(0));
        File here = new File("");
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder2', recursing...", outLines.get(1));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder2/file2-1.txt', size: 487040 bytes", outLines.get(2));
        assertEquals("Defaulting disc size to 25GB.", outLines.get(3));
        assertEquals("Defaulting output to 'output.txt'", outLines.get(4));
        assertEquals("Processing complete.", outLines.get(5));
    }
    
    @Test
    public void testSetExcludeFilesLongOption() {
        Main.main(new String[] { "--exclude", "\"files" + File.separator + "folder2\"" });
        assertEquals("", err.getLog());
        List<String> outLines = Arrays.asList(StringUtils.split(out.getLog(), System.lineSeparator()));
        assertEquals(6, outLines.size());
        assertEquals("exclude:  ", outLines.get(0));
        File here = new File("");
        assertEquals("Found folder '" + here.getAbsolutePath() + "/files/folder2', recursing...", outLines.get(1));
        assertEquals("Found file '" + here.getAbsolutePath() + "/files/folder2/file2-1.txt', size: 487040 bytes", outLines.get(2));
        assertEquals("Defaulting disc size to 25GB.", outLines.get(3));
        assertEquals("Defaulting output to 'output.txt'", outLines.get(4));
        assertEquals("Processing complete.", outLines.get(5));
        System.out.println(here.getAbsolutePath());
    }
    
    @Test
    public void testSetExcludeFilesBadFileName() {
        Main.main(new String[] { "-e", "\"files" + File.separator + "duckAndCover\"" });
        assertEquals("Folder 'files/duckAndCover' does not exist."
                + System.lineSeparator(), err.getLog());
        assertEquals("exclude:  " + System.lineSeparator(), out.getLog());
    }
    
    @Test
    public void testSetOutputFileNameShort() {
        Main.main(new String[] { "-o", "out.txt" });
        assertEquals("", err.getLog());
        assertEquals("Defaulting disc size to 25GB." + System.lineSeparator()
                + "Processing complete." + System.lineSeparator(), out.getLog());
    }
}
