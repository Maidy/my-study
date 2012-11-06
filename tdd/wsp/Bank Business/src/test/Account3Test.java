package test;

import static org.junit.Assert.*;

import main.Account3;

import org.junit.Before;
import org.junit.Test;

public class Account3Test {
	
	private Account3 account;
	
	@Before
	public void setup() {
		account = new Account3(10000);
	}

	@Test
	public void testGetBalance() throws Exception {
		assertEquals(10000, account.getBalance());
		
		account = new Account3(100);
		assertEquals(100, account.getBalance());
	}
	
	@Test
	public void testDeposit() throws Exception {
		account.deposit(100);
		assertEquals(10100, account.getBalance());
	}

}
