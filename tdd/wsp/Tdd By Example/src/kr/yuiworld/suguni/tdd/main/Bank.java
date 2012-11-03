package kr.yuiworld.suguni.tdd.main;

import java.util.Hashtable;

public class Bank {
	
	Hashtable<Pair, Integer> rates;
	
	public Bank() {
		rates = new Hashtable<Pair, Integer>();
	}

	public Money reduce(Expression source, String to) {
		return source.reduce(this, to);
	}

	public void addRate(String from, String to, int rate) {
		rates.put(new Pair(from, to), new Integer(rate));
	}
	
	public int rate(String from, String to) {
		if (from.equals(to)) {
			return 1;
		}
		
		Integer i = rates.get(new Pair(from, to));
		return  i.intValue();
	}

}
