package tdd3.main;

public class Pair {

	private String to;
	private String from;

	public Pair(String from, String to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean equals(Object o) {
		Pair p = (Pair)o;
		return p.from == from && p.to == to;
	}
	
	public int hashCode() {
		return 0;
	}

}
