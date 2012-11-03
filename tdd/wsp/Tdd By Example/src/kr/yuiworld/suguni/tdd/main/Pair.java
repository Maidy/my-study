package kr.yuiworld.suguni.tdd.main;

public class Pair {
	
	String from;
	String to;
	
	public Pair (String from, String to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean equals(Object o) {
		Pair p = (Pair)o;
		return from == p.from && to == p.to;
	}
	
	// ??? 왜 필요하지?
	public int hashCode() {
		return 0;
	}
}
