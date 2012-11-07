package kr.yuiworld.suguni.tdd.main;

public class Money implements Expression {
	
	protected int amount;
	protected String currency;
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	
	public static Money franc(int amount) {
		return new Money(amount, "CHF");
	}
	
	public boolean equals(Object object) {
		Money money = (Money)object;
		return money.amount == this.amount && money.currency.equals(this.currency);
	}
	
	public String currency() {
		return this.currency;
	}
	
	public String toString() {
		return amount + " " + currency;
	}
	
	public Expression times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}

	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}
	
	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(this.currency,  to);
		return new Money(amount / rate, to);
	}
}