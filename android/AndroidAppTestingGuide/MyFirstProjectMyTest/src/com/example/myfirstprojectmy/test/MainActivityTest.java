package com.example.myfirstprojectmy.test;

import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.TestCase;

public class MainActivityTest extends TestCase {
	
	public MainActivityTest() {
		this("MainActivityTest");
	}
	
	public MainActivityTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SmallTest
	public void testSomething() {
		fail("Not implemented yet.");
	}
	
	@VeryImportantTest
	public void testAnother() {
		fail("Too... not implemented yet.");
	}

}
