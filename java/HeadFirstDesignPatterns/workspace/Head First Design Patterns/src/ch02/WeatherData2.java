package ch02;

import java.util.Observable;

public class WeatherData2 extends Observable {

	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData2() {
		
	}

	public void measurementsChanged() {
		setChanged();
		this.notifyObservers();
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		measurementsChanged();
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
}
