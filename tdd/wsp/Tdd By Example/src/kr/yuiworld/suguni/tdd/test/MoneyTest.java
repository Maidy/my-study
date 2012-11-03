package kr.yuiworld.suguni.tdd.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Hashtable;

import kr.yuiworld.suguni.tdd.main.Bank;
import kr.yuiworld.suguni.tdd.main.Expression;
import kr.yuiworld.suguni.tdd.main.Money;
import kr.yuiworld.suguni.tdd.main.Pair;
import kr.yuiworld.suguni.tdd.main.Sum;

import org.junit.Test;

public class MoneyTest {
	
	@Test
	public void testMultiplication() throws Exception {
//		Dollar five = new Dollar(5);
//		five.times(2);
//		assertEquals(10, five.amount);
		
//		Dollar five = new Dollar(5);
//		Dollar product = five.times(2);
//		assertEquals(10, product.amount);
//		product = five.times(3);
//		assertEquals(15, product.amount);
		
//		Dollar five = new Dollar(5);
//		assertEquals(new Dollar(10), five.times(2));
//		assertEquals(new Dollar(15), five.times(3));
		
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}

	@Test
	public void testEquality() throws Exception {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		assertFalse("CHF 5 is not $5", Money.franc(5).equals(Money.dollar(5)));
	}
	
	@Test
	public void testCurrency() throws Exception {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
		
	}
	
	@Test
	public void testSimpleAddition() throws Exception {
//		Money sum = Money.dollar(5).plus(Money.dollar(5));
//		assertEquals(sum, Money.dollar(10));
		
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
	
	@Test
	public void testPlusReturnSum() throws Exception {
		Money five = Money.dollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum)result;
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}
	
	@Test
	public void testReducedSum() throws Exception {
		
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), reduced);
	}
	
	@Test
	public void testReduceMoney() throws Exception {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1), "USD");
		assertEquals(Money.dollar(1), result);
	}
	
	@Test
	public void testReduceMoneyDifferentCurrency() throws Exception {
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(Money.franc(2), "USD");
		assertEquals(result, Money.dollar(1));
	}
	
	@Test
	public void testArrayEquals() throws Exception {
		assertEquals(new Object[] {"abc"}, new Object[] {"abc"});
//		assertArrayEquals(new Object[] {"abc"}, new Object[] {"abc"});
	}
	
	@Test
	public void testPairEqaulity() throws Exception {
		assertEquals(new Pair("CHF", "USD"), new Pair("CHF", "USD"));
		assertNotSame(new Pair("CHF", "USD"), new Pair("USD", "CHF"));
	}
	
	@Test
	public void testPairHashable() throws Exception {
		
		Hashtable<Pair,	Integer> table = new Hashtable<Pair, Integer>();
		Pair p1 = new Pair("CHF", "USD");
		Pair p2 = new Pair("USD", "CHF");
		table.put(p1, new Integer(2));
		table.put(p2, new Integer(1));
		assertEquals(table.get(p1), new Integer(2));
		assertEquals(table.get(p2), new Integer(1));
	}
	
	@Test
	public void testAddRate() throws Exception {
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 4);
		assertEquals(bank.rate("CHF", "USD"), 4);
	}
	
	@Test
	public void testIdentityRate() throws Exception {
		Bank bank = new Bank();
		assertEquals(bank.rate("USD", "USD"), 1);
	}
	
	@Test
	public void testMixedAddtion() throws Exception {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
		
		assertEquals(Money.dollar(10), result);
	}
	
	@Test
	public void testSumPlus() throws Exception {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		
		Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(15), result);
	}
	
	@Test
	public void testSumTimes() throws Exception {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		
		Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(20), result);
	}
	
//	@Test
//	public void testPlusSameCurrencyReturnsMoney() throws Exception {
//		Expression sum = Money.dollar(5).plus(Money.dollar(5));
//		assertTrue(sum instanceof Money);
//	}
}
