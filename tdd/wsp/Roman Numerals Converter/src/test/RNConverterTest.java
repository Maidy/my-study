package test;

import static org.junit.Assert.*;

import main.RomanNumeral;

import org.junit.Test;

public class RNConverterTest {

	@Test
	public void testToI() throws Exception {
		assertEquals("I", RomanNumeral.fromInt(1));	// min
		assertEquals("II", RomanNumeral.fromInt(2));
		assertEquals("III", RomanNumeral.fromInt(3));
	}
	
	@Test
	public void testToV() throws Exception {
		assertEquals("IV", RomanNumeral.fromInt(4));
		assertEquals("V", RomanNumeral.fromInt(5));
		assertEquals("VI", RomanNumeral.fromInt(6));
		assertEquals("VII", RomanNumeral.fromInt(7));
		assertEquals("VIII", RomanNumeral.fromInt(8));
	}
	
	@Test
	public void testToTen() throws Exception {
		assertEquals("IX", RomanNumeral.fromInt(9));
		assertEquals("X", RomanNumeral.fromInt(10));
		assertEquals("XI", RomanNumeral.fromInt(11));
	}
	
	@Test
	public void testToBig() throws Exception {
		assertEquals("MCMXLIV", RomanNumeral.fromInt(1944));
		assertEquals("MCMXC", RomanNumeral.fromInt(1990));
		assertEquals("MMVI", RomanNumeral.fromInt(2006));
		assertEquals("MMMCMXCIX", RomanNumeral.fromInt(3999)); // max
	}
	
	@Test
	public void testFromOne() throws Exception {
		assertEquals(1, RomanNumeral.toInt("I"));
		assertEquals(2, RomanNumeral.toInt("II"));
		assertEquals(3, RomanNumeral.toInt("III"));
	}
	
	@Test
	public void testFromFive() throws Exception {
		assertEquals(4, RomanNumeral.toInt("IV"));
		assertEquals(5, RomanNumeral.toInt("V"));
		assertEquals(6, RomanNumeral.toInt("VI"));
		assertEquals(7, RomanNumeral.toInt("VII"));
		assertEquals(8, RomanNumeral.toInt("VIII"));
	}
	
	@Test
	public void testFromTen() throws Exception {
		assertEquals(9, RomanNumeral.toInt("IX"));
		assertEquals(10, RomanNumeral.toInt("X"));
		assertEquals(11, RomanNumeral.toInt("XI"));
	}
	
	@Test
	public void testFromBig() throws Exception {
		assertEquals(1944, RomanNumeral.toInt("MCMXLIV"));
		assertEquals(1990, RomanNumeral.toInt("MCMXC"));
		assertEquals(2006, RomanNumeral.toInt("MMVI"));
		assertEquals(3999, RomanNumeral.toInt("MMMCMXCIX")); // max
	}
	
	@Test
	public void testValidate() throws Exception {
		assertEquals(true, RomanNumeral.isValid("MCMXLIV"));
		assertEquals(false, RomanNumeral.isValid("MICCMXLIV"));
		
	}
	
	
}
