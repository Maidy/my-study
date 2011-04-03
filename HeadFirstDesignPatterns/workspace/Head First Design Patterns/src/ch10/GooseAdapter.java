package ch10;

public class GooseAdapter implements Quackable {

	Goose goose = null;
	Observable observable;
	
	public GooseAdapter(Goose goose) {
		this.goose = goose;
		observable = new Observable(this);
	}
	
	@Override
	public String toString() {
		return goose.toString() + " pretending to be a Duck";
	}
	
	
	@Override
	public void quack() {
		goose.honk();
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
