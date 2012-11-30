package tdd3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tdd3.main.Bank;
import tdd3.main.Expression;
import tdd3.main.Money;
import tdd3.main.Sum;

public class MoneyTest {

	@Test
	public void testSimpleAddition() throws Exception {
		Money five = Money.dollar(5);
		
//		assertEquals(five.plus(five), Money.dollar(10));
		
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
	
	@Test
	public void testSumExpression() throws Exception {
		Money five = Money.dollar(5);
		Expression result = five.plus(five);
		
		Sum sum = (Sum)result;
		
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}
	
	@Test
	public void testReduceSum() throws Exception {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), result);
	}
	
	@Test
	public void testReduceMoney() throws Exception {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(3), "USD");
		assertEquals(Money.dollar(3), result);
	}
	
	@Test
	public void testDifferentCurrency() throws Exception {
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(Money.franc(2), "USD");
		assertEquals(Money.dollar(1), result);
	}
	
	@Test
	public void testBankRate() throws Exception {
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 3);
		assertEquals(3, bank.rate("CHF", "USD"));
	}
	
	@Test
	public void testIdentityRate() throws Exception {
		assertEquals(1, new Bank().rate("USD", "USD"));
	}
	
	@Test
	public void testMixedAddtion() throws Exception {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		
		Money result = bank.reduce(fiveBucks.plus(tenFrancs).plus(tenFrancs), "USD");
		assertEquals(Money.dollar(15), result);
	}
	
	@Test
	public void testSumTimes() throws Exception {
		
		Expression sum = new Sum(Money.dollar(5), Money.franc(10));
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression result = bank.reduce(sum.times(2), "USD");
		assertEquals(Money.dollar(20), result);
	}
	
}
