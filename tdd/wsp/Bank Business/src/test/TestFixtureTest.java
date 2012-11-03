package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFixtureTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Network is estabilished.");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Network is disconnected.");
	}
	
	@Before
	public void setup() throws Exception {
		System.out.println("logon guest:guest");
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("logout");
	}
	
	@Test
	public void testTerminalConnected() throws Exception {
		System.out.println("== connect test");
		assertTrue(true);
	}
	
	@Test
	public void testGetReturnMessage() throws Exception {
		System.out.println("== message test");
		assertTrue(true);
	}
	
	@Test (expected=NumberFormatException.class)
	public void testException() throws Exception {
		String value = "108a";
		assertEquals(108, Integer.parseInt(value));
	}

}
