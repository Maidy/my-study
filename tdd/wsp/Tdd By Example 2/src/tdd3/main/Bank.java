package tdd3.main;

import java.util.Hashtable;

public class Bank {
	
	Hashtable<Pair, Integer> rates = new Hashtable<Pair, Integer>();

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
		return rates.get(new Pair(from, to)).intValue();
	}

}
