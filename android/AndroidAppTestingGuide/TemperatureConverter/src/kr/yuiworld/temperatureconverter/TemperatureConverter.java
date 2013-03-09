package kr.yuiworld.temperatureconverter;

public class TemperatureConverter {
	
	public static final double ABSOLUTE_ZERO_C = -273.15d;
	public static final double ABSOLUTE_ZERO_F = -459.67d;
	
	public static enum OP { C2F, F2C };
	
	private static final String ERROR_MESSAGE_BELOW_ZERO_FMT =
			"Invalid temperature: %.2f%c below absolute zero";

	public static double fahrenheitToCelsius(double temp) {
		if (temp < ABSOLUTE_ZERO_F)
			throw new InvalidTemperatureException(
					String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, temp, 'f'));
		return (temp - 32.0) / 1.8;
	}

	public static double celsiusToFahrenheit(double temp) {
		if (temp < ABSOLUTE_ZERO_C)
			throw new InvalidTemperatureException(
					String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, temp, 'c'));
		return temp * 1.8 + 32.0;
	}
}
