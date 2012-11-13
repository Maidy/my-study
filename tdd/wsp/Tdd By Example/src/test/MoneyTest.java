package test;

import static org.junit.Assert.*;

import main.Money;

import org.junit.Test;

public class MoneyTest {
	@Test
	public void testMultiplication() throws Exception {
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}
	
	@Test
	public void testFrancMultiplication() throws Exception {
		Money five = Money.franc(5);
		assertEquals(Money.franc(10), five.times(2));
		assertEquals(Money.franc(15), five.times(3));
	}
	
	@Test
	public void testEquality() throws Exception {
		assertFalse(Money.dollar(6).equals(Money.dollar(5)));
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.franc(6).equals(Money.franc(5)));
		assertTrue(Money.franc(5).equals(Money.franc(5)));
		
		// assertTrue(new Franc(10).equals(new Dollar(5)));
		assertFalse(Money.franc(5).equals(Money.dollar(5)));
	}
	
	@Test
	public void testCurrency() throws Exception {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
		
		assertEquals("USD", (Money.dollar(1).times(10)).currency());
		assertEquals("CHF", (Money.franc(1).times(10)).currency());
	}
	
//	@Test
//	public void testDifferenctClassEquality() throws Exception {
//		assertEquals(new Money(1, "USD"), new Dollar(1, "USD"));
//		assertEquals(new Money(1, "CHF"), new Franc(1, "CHF"));
//	}

}
