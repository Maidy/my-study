package ch10;

public interface QuackObservable {
	public void registerOberserver(Observer observer);
	public void notifyObserver();
}
