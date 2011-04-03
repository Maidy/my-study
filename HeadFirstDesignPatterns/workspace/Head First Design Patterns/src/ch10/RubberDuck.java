package ch10;

public class RubberDuck implements Quackable {

	Observable observable;
	
	public RubberDuck() {
		observable = new Observable(this);
	}
	
	@Override
	public String toString() {
		return "RubberDuck";
	}
	
	@Override
	public void quack() {
		System.out.println("Squeak");
		notifyObserver();
	}

	@Override
	public void registerOberserver(Observer observer) {
		observable.registerOberserver(observer);
	}

	@Override
	public void notifyObserver() {
		observable.notifyObserver();
	}
}
