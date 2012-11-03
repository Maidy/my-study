package test;

import main.Account;
import junit.framework.TestCase;

public class AccountTest extends TestCase {
	
	Account account;

	protected void setUp() throws Exception {
		account = new Account(10000);
	}

	protected void tearDown() throws Exception {
		
	}

	public void testGetBalance() {
		assertEquals(account.getBalance(), 10000);
	}

	public void testWithdraw() {
		account.withdraw(1000);
		assertEquals(account.getBalance(), 9000);
	}

	public void testDeposit() {
		account.deposit(1000);
		assertEquals(account.getBalance(), 11000);
	}

}
