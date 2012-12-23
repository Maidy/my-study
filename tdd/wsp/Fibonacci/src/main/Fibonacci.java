package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class Fibonacci {

	@Test
	public void testFibonacci() {
		
		int values[][] = {
			{ 0, 0 },
			{ 1, 1 },
			{ 1, 2 },
			{ 2, 3 },
			{ 3, 4 },
			{ 5, 5 },
			{ 8, 6 }
		};
		
		for (int i = 0; i < values.length; i++) {
			assertEquals(values[i][0], fib(values[i][1]));
		}
	}

	public int fib(int i) {
		if (i == 0) return 0;
		if (i <= 2) return 1;
		return fib(i - 2) + fib(i - 1);
	}
}
