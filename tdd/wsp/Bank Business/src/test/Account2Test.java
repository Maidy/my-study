package test;

import static org.junit.Assert.*;

import main.Account2;

import org.junit.Before;
import org.junit.Test;

public class Account2Test {
	
	private Account2 account;
	
	@Before
	public void setUp() {
		account = new Account2(10000);
	}

	@Test
	public void testAccount2() throws Exception {
//		assertNotNull(account);
	}
	
	@Test
	public void testBalance() throws Exception {	
		assertEquals(10000, account.getBalance());
		
		account = new Account2(1000);
		assertEquals(1000, account.getBalance());
		
		account = new Account2(0);
		assertEquals(0, account.getBalance());
	}
	
	@Test
	public void testDeposit() throws Exception {
		account.deposit(1000);
		assertEquals(11000, account.getBalance());
	}

	@Test
	public void testWithdraw() throws Exception {
		account.withdraw(1000);
		assertEquals(9000, account.getBalance());	
	}

}
