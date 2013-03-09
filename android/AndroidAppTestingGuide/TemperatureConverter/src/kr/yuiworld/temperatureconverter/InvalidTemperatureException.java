package kr.yuiworld.temperatureconverter;

public class InvalidTemperatureException extends RuntimeException {
	
	private static final long serialVersionUID = -258735185190488333L;

	public InvalidTemperatureException(String message) {
		super(message);
	}
}
