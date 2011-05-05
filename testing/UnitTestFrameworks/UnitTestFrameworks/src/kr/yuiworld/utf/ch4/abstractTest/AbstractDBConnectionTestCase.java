package kr.yuiworld.utf.ch4.abstractTest;

import junit.framework.TestCase;

public abstract class AbstractDBConnectionTestCase extends TestCase {
	public abstract DBConnection getConnection();
	
	public void testIsOpen() {
		DBConnection connection = getConnection();
		connection.connect();
		assertTrue(connection.isOpen());
	}
	
	public void testClose() {
		DBConnection connection = getConnection();
		connection.connect();
		connection.close();
		assertTrue(!connection.isOpen());
	}

}
