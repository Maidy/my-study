package main;

import java.util.HashMap;

public class RomanNumeral {
	
	static HashMap<Character, Integer> atoms = new HashMap<Character, Integer>();
	
	static {
		atoms.put('I', 1);
		atoms.put('V', 5);
		atoms.put('X', 10);
		atoms.put('L', 50);
		atoms.put('C', 100);
		atoms.put('D', 500);
		atoms.put('M', 1000);
	}
	
	private String symbol;

	public RomanNumeral(String symbol) {
		this.symbol = symbol;
	}

	public int toDemical() throws Exception {
		char[] chars = symbol.toCharArray();
		int value = 0;
		int prev = Integer.MAX_VALUE;
		int patternCount = 0;
		
		for (int i = 0; i < chars.length; i++) {
			
			int curr = atoms.get(chars[i]);
			
			if (prev == curr) {
				patternCount++;
			} else {
				patternCount = 0;
			}
			
			if (patternCount > 2) {
				throw new Exception();
			}
			
			if (prev < curr) {
				value -= prev * 2;
			}
			
			value += curr;
			prev = curr;
		}
		return value;
	}

}
