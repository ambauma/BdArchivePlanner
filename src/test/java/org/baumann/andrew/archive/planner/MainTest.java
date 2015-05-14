package org.baumann.andrew.archive.planner;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.baumann.andrew.archive.planner.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public final class MainTest {

	private final PrintStream realOut = System.out;
	private final PrintStream realErr = System.err;
	private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
	private final ByteArrayOutputStream testErr = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(testOut));
	    System.setErr(new PrintStream(testErr));
	}
	
	@After
	public void tearDown() {
		System.setOut(realOut);
	    System.setErr(realErr);
	}

	@Test
	public void testMain() {
		Main.main(new String[]{});
		assertEquals("Hello World!", testOut.toString());
	}

	

}
