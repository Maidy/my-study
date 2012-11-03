package kr.yuiworld.suguni.tdd.main;

public interface Expression {

	Money reduce(Bank bank, String to);
	Expression plus(Expression addend);
	Expression times(int multiplier);
}
