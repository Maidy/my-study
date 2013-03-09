package kr.yuiworld.temperatureconverter.test;

import java.util.HashMap;

import junit.framework.TestCase;
import kr.yuiworld.temperatureconverter.InvalidTemperatureException;
import kr.yuiworld.temperatureconverter.TemperatureConverter;

public class TemperatureConverterTest extends TestCase {

	private static HashMap<Double, Double> conversionTableDouble;
	static {
		conversionTableDouble = new HashMap<Double, Double>();
		conversionTableDouble.put(0.0, 32.0);
		conversionTableDouble.put(100.0, 212.0);
		conversionTableDouble.put(-1.0, 30.20);
		conversionTableDouble.put(-100.0, -148.0);
		conversionTableDouble.put(32.0, 89.6);
		conversionTableDouble.put(-40.0, -40.0);
		conversionTableDouble.put(-273.0, -459.40);
	}

	public TemperatureConverterTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public final void testFahrenheitToCelsius() {
		for (double c : conversionTableDouble.keySet()) {
			final double f = conversionTableDouble.get(c);
			final double ca = TemperatureConverter.fahrenheitToCelsius(f);
			final double delta = Math.abs(c - ca);
			
			final String msg = "" + f + "F -> " + c + "C but was "
					+ ca + "C (delta " + delta + ")";
			
			assertTrue(msg, delta < 0.005);
		}
	}
	
	public final void testCelsiusToFahrenheit() {
		for (double c : conversionTableDouble.keySet()) {
			final double f = conversionTableDouble.get(c);
			final double fa = TemperatureConverter.celsiusToFahrenheit(c);
			final double delta = Math.abs(f - fa);
			
			final String msg = "" + c + "C -> " + f + "F but was "
					+ fa + "F (delta " + delta + ")";
			
			assertTrue(msg, delta < 0.005);
		}
	}
	
	public final void testExceptionForLessThanZbsoluteZeroF() {
		try {
			TemperatureConverter.fahrenheitToCelsius(
					TemperatureConverter.ABSOLUTE_ZERO_F - 1);
			fail();
		} catch (InvalidTemperatureException e) {
			
		}
	}
	
	public final void testExceptionForLessThanZbsoluteZeroC() {
		try {
			TemperatureConverter.celsiusToFahrenheit(
					TemperatureConverter.ABSOLUTE_ZERO_C - 1);
			fail();
		} catch (InvalidTemperatureException e) {
			
		}
	}

}
