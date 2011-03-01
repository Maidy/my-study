package ch02;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionDisplay2 implements Observer, DisplayElement {

	private float temperature;
	private float humidity;
	
	private Observable observable;
	
	public CurrentConditionDisplay2(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData2) {
			WeatherData2 weatherData = (WeatherData2)obs;
			temperature = weatherData.getTemperature();
			humidity = weatherData.getHumidity();
			display();
		}
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature
				+ "F degrees and " + humidity + "% humidity");
	}
}
