package ch02;

public class ForecastDisplay implements Observer, DisplayElement {

	private String forecast;
	
	private Subject weatherData;
	
	public ForecastDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	@Override
	public void display() {
		System.out.println("Forecast: " + forecast);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		// TODO Check logic!
		forecast = "Hello Forecast!!!";
		display();
	}
	
	public void stop() {
		weatherData.removeObserver(this);
	}

}
