package ch10;

public class QuackCounter implements Quackable {

	private Quackable duck;
	private static int numberOfQuacks;
	
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}
	
	@Override
	public void quack() {
		duck.quack();
		numberOfQuacks++;
	}
	
	public static  int getQuacks() {
		return numberOfQuacks;
	}

	@Override
	public void registerOberserver(Observer observer) {
		duck.registerOberserver(observer);
	}

	@Override
	public void notifyObserver() {
		duck.notifyObserver();
	}

}
