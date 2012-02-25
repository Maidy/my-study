/**
 * 
 */
package ch10;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author suguni
 *
 */
public class Observable implements QuackObservable {

	ArrayList<Observer> observers = new ArrayList<Observer>();
	QuackObservable duck;
	
	public Observable(QuackObservable duck) {
		this.duck = duck;
	}
	
	@Override
	public void notifyObserver() {
		Iterator<Observer> iter = observers.iterator();
		while (iter.hasNext()) {
			Observer obs = iter.next();
			obs.update(duck);
		}
	}

	@Override
	public void registerOberserver(Observer observer) {
		observers.add(observer);
	}

}
