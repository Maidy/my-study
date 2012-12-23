package test;

import static org.junit.Assert.*;

import main.RomanNumeral;

import org.junit.Test;

public class testRoman {
	
	@Test
	public void testOneSymbol() throws Exception {
		assertEquals(1, new RomanNumeral("I").toDemical());
		assertEquals(5, new RomanNumeral("V").toDemical());
		assertEquals(10, new RomanNumeral("X").toDemical());
		assertEquals(50, new RomanNumeral("L").toDemical());
		assertEquals(100, new RomanNumeral("C").toDemical());
		assertEquals(500, new RomanNumeral("D").toDemical());
		assertEquals(1000, new RomanNumeral("M").toDemical());
	}

	@Test
	public void testI() throws Exception {
		RomanNumeral one = new RomanNumeral("I");
		assertEquals(1, one.toDemical());
		
		RomanNumeral two = new RomanNumeral("II");
		assertEquals(2, two.toDemical());
	}
	
	@Test
	public void testV() throws Exception {
		RomanNumeral five = new RomanNumeral("V");
		assertEquals(5, five.toDemical());
		
		RomanNumeral six = new RomanNumeral("VI");
		assertEquals(6, six.toDemical());
		
		RomanNumeral four = new RomanNumeral("IV");
		assertEquals(4, four.toDemical());
	}

	@Test
	public void testIV() throws Exception {
		RomanNumeral four = new RomanNumeral("IV");
		assertEquals(4, four.toDemical());
	}
	
	@Test (expected = Exception.class)
	public void testInvalid1() throws Exception {
		new RomanNumeral("IIII").toDemical();
	}
	
	@Test (expected = Exception.class)
	public void testInvalid2() throws Exception {
		new RomanNumeral("IIIX").toDemical();
	}
	
}
