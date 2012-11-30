package tdd3.main;

public class Money implements Expression {
	
	public int amount;
	public String currency;
	
	public Money(int money, String currency) {
		this.amount = money;
		this.currency = currency;
	}

	public boolean equals(Object o) {
		Money d = (Money)o;
		return d.amount == this.amount && d.currency.equals(this.currency);
	}
	
	public static Money dollar(int amount) {
		return new Money(amount, "USD");
	}
	
	public static Money franc(int amount) {
		return new Money(amount, "CHF");
	}

	public String toString() {
		return amount + " " + currency;
	}

	public Expression times(int multipler) {
		return new Money(amount * multipler, this.currency);
	}

	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}

	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(currency, to);
		return new Money(amount / rate, to);
	}
}
