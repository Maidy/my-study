package ch10;

public class MallardDuck implements Quackable {

	Observable observable;
	
	public MallardDuck() {
		observable = new Observable(this);
	}
	
	@Override
	public String toString() {
		return "MallardDuck";
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
