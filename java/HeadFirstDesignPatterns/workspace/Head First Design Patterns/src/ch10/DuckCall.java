package ch10;

public class DuckCall implements Quackable {

	Observable observable;
	
	public DuckCall() {
		observable = new Observable(this);
	}
	
	@Override
	public String toString() {
		return "DuckCall";
	}
	
	@Override
	public void quack() {
		System.out.println("Kwak");
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
