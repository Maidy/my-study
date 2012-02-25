package ch10;

public abstract class AbstractDuckFactory {
	public abstract Quackable createMallardDuck();
	public abstract Quackable createRedheadDuck();
	public abstract Quackable createRubberDuck();
	public abstract Quackable createDuckCall();
}

class DuckFactory extends AbstractDuckFactory {
	public Quackable createMallardDuck() {
		return new MallardDuck();
	}
	public Quackable createRedheadDuck() {
		return new RedheadDuck();
	}
	public Quackable createRubberDuck() {
		return new RubberDuck();
	}
	public Quackable createDuckCall() {
		return new DuckCall();
	}
}

class CountingDuckFactory extends AbstractDuckFactory {
	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck());
	}
	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedheadDuck());
	}
	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	}
	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}
}
