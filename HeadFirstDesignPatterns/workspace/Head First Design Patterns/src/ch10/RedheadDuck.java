package ch10;

public class RedheadDuck implements Quackable {
	
	Observable observable;

	public RedheadDuck() {
		observable = new Observable(this);
	}
	
	@Override
	public String toString() {
		return "RedheadDuck";
	}
	
	@Override
	public void quack() {
		System.out.println("Quack");
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
