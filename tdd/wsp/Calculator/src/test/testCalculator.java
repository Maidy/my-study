package test;

import static org.junit.Assert.*;

import main.PrefixCalculator;

import org.junit.Test;

public class testCalculator {
	
	@Test
	public void testCreateCalculator() throws Exception {
		
		PrefixCalculator calc = new PrefixCalculator();
		
		String expr = "(+ 1 1)";
		int ret = calc.eval(expr);
		
		assertEquals(2, ret);
	}

}
