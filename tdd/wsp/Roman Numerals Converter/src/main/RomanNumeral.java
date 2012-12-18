package main;

import java.util.HashMap;

public class RomanNumeral {
	
	/**
	 * 자연수를 로마숫자로 변환한다.
	 * 변환 가능한 숫자 범위는 1(I) ~ 3999(MMMCMXCIX)
	 * @param value
	 * @return
	 */
	public static String fromInt(int value) {
		
		String symbol = "";
		char decimal[] = Integer.toString(value).toCharArray();
		
		for (int i = 0, digit = decimal.length; i < digit; i++) {
			int v = Integer.valueOf(String.valueOf(decimal[i]));
			symbol += getRoman(v, digit - i);
		}
		
		return symbol;
	}

	private static String getRoman(int number, int digit) {
		String symbol = "";
		switch (number) {
		case 1:
		case 2:
		case 3:
			for (int n = 0; n < number; n++) {
				symbol += getRomanOne(digit);
			}
			break;
		case 4:
			symbol = getRomanOne(digit) + getRomanFive(digit);
			break;
		case 5:
			symbol = getRomanFive(digit);
			break;
		case 6:
		case 7:
		case 8:
			symbol = getRomanFive(digit);
			for (int n = 0; n < number - 5; n++) {
				symbol += getRomanOne(digit);
			}
			break;
		case 9:
			symbol = getRomanOne(digit) + getRomanTen(digit);
			break;
		}
		
		return symbol;
	}

	private static String getRomanTen(int digit) {
		return getRomanOne(digit + 1);
	}

	private static String getRomanFive(int digit) {
		String fives[] = new String[] { "V", "L", "D" };
		return fives[digit - 1];
	}

	private static String getRomanOne(int digit) {
		String ones[] = new String[] { "I", "X", "C", "M" };
		return ones[digit - 1];
	}

	/**
	 * 로마숫자를 자연수로 변환한다. 
	 * 변환 가능한 숫자 범위는 I(1) ~ MMMCMXCIX(3999)
	 * @param symbol
	 * @return
	 */
	public static int toInt(String symbol) {
		char symbols[] = symbol.toCharArray();
		int value = 0, old = Integer.MAX_VALUE;
		
		for (int i = 0; i < symbols.length; i++) {
			int d = getNumber(symbols[i]);
			if (i < symbols.length && old < d) {
				value -= (old * 2);
			}
			value += d;
			old = d;
		}
		return value;
	}
	
	private static HashMap<String, Integer> romanToDecimalMap = new HashMap<String, Integer>();
	static {
		romanToDecimalMap.put("I", 1);
		romanToDecimalMap.put("V", 5);
		romanToDecimalMap.put("X", 10);
		romanToDecimalMap.put("L", 50);
		romanToDecimalMap.put("C", 100);
		romanToDecimalMap.put("D", 500);
		romanToDecimalMap.put("M", 1000);
	}

	private static int getNumber(char c) {
		return romanToDecimalMap.get(String.valueOf(c));
	}

	/**
	 * 유효한 로마숫자인지 검사
	 * TODO 구현필요
	 * @param symbol
	 * @return
	 */
	public static boolean isValid(String symbol) {
		return true;
	}
	
}
