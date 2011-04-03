package ch10;

import java.util.ArrayList;
import java.util.Iterator;

public class Flock implements Quackable {
	
	Observable observable;
	private ArrayList<Quackable> quackers = new ArrayList<Quackable>();
	
	public Flock() {
		observable = new Observable(this);
	}
	
	public void add(Quackable quacker) {
		quackers.add(quacker);
	}
	
	@Override
	public String toString() {
		return "Flock of Ducks";
	}

	@Override
	public void quack() {
		Iterator<Quackable> iter = quackers.iterator();
		while (iter.hasNext()) {
			Quackable quacker = iter.next();
			quacker.quack();
		}
	}

	@Override
	public void registerOberserver(Observer observer) {
		Iterator<Quackable> iter = quackers.iterator();
		while (iter.hasNext()) {
			Quackable quacker = iter.next();
			quacker.registerOberserver(observer);
		}
	}

	@Override
	public void notifyObserver() {
		/*
		Iterator<Quackable> iter = quackers.iterator();
		while (iter.hasNext()) {
			Quackable quacker = iter.next();
			quacker.notifyObserver();
		}
		*/
	}
}
